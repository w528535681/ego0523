package com.shsxt.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shsxt.common.util.Md5Util;
import com.shsxt.common.util.UUIDUtil;
import com.shsxt.sso.mapper.AdminMapper;
import com.shsxt.common.pojo.Admin;
import com.shsxt.common.pojo.AdminExample;
import com.shsxt.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 单点登录实现类
 */
@Service(interfaceClass = SSOService.class)
@Component
public class SSOServiceImpl implements SSOService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${user.ticket}")
    private String userTicket;


    /**
     * 验证用户信息返回票据信息
     * @param admin
     * @return
     */
    @Override
    public String login(Admin admin) {

        //验证用户信息
        if (StringUtils.isEmpty(admin.getUserName())){
            System.out.println("用户名或密码不能为空!");
            return null;
        }

        if (StringUtils.isEmpty(admin.getPassword())){
            System.out.println("用户名或密码不能为空!");
            return null;
        }

        //创建查询对象
        AdminExample adminExample = new AdminExample();
        //设置查询条件
        adminExample.createCriteria().andUserNameEqualTo(admin.getUserName());
        //执行查询
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        //查不出或查出多个用户
        if (CollectionUtils.isEmpty(admins)||admins.size()>1){
            System.out.println("用户名或密码错误!");
            return null;
        }
        Admin a = admins.get(0);
        //判断用户名密码是否正确，需要先对密码加密再比较
        if (!(a.getPassword().equals(Md5Util.getMd5WithSalt(admin.getPassword(),a.getEcSalt())))){
            return null;
        }
        //如果用户存在生成ticket
        String ticket = UUIDUtil.getUUID();

        //将用户信息存到redis中,设置失效时间30分钟
        ValueOperations<String,Object> stringObjectValueOperations = redisTemplate.opsForValue();
        stringObjectValueOperations.set(userTicket+":"+ticket,a,30, TimeUnit.MINUTES);

        return ticket;

    }

    /**
     * 验证票据ticket返回用户信息
     * @param ticket
     * @return
     */
    @Override
    public Admin validate(String ticket) {

        //判断票据信息是否为空
        if (StringUtils.isEmpty(ticket)){
            System.out.println("票据信息不能为空!");
            return null;
        }

        //根据票据信息从redis里拿出用户信息
        ValueOperations<String,Object> stringObjectValueOperations = redisTemplate.opsForValue();
        Admin admin = (Admin) stringObjectValueOperations.get(userTicket + ":" + ticket);
        //判断用户信息是否为空
        if (null == admin || admin.getAdminId()==null){
            return null;
        }
        //不为空，返回用户信息
        return admin;
    }

    /**
     * 用户退出
     * @param ticket
     */
    @Override
    public void logout(String ticket) {
        redisTemplate.delete(userTicket+":"+ticket);
    }
}
