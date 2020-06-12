package com.shsxt.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.common.enums.BaseResultEnum;
import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.service.CookieService;
import com.shsxt.sso.pojo.Admin;
import com.shsxt.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Controller
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Reference(interfaceClass = SSOService.class)
    private SSOService ssoService;

    @Autowired
    private CookieService cookieService;

    /**
     * 用户登录
     * @param admin
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public BaseResult login(Admin admin, HttpServletRequest request, HttpServletResponse response,String verify){
        String pictureVerifyKey = (String) request.getSession().getAttribute("pictureVerifyKey");
        BaseResult baseResult = new BaseResult();
        //验证码为空或者不正确
        if (StringUtils.isEmpty(verify) || !verify.trim().equals(pictureVerifyKey)) {
            baseResult.setCode(BaseResultEnum.PASS_ERROR_03.getCode());
            baseResult.setMessage(BaseResultEnum.PASS_ERROR_03.getMessage());
            return baseResult;
        }

        //去单点登录系统验证用户信息返回票据信息
        String ticket = ssoService.login(admin);
        //判断票据信息是否为空，如果为空表示用户名或密码错误
        if (StringUtils.isEmpty(ticket)){
            return BaseResult.error();
        }
        //把票据信息放入cookie,返回布尔值结果
        boolean result = cookieService.setCookie(ticket,request,response);
        //将用户信息存入session，用于页面返显
        request.getSession().setAttribute("user",admin);
        return result?BaseResult.success():BaseResult.error();
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){

        //通过request拿cookie的值，就是用户的ticket
        String ticket = cookieService.getCookie(request);
        ////如果ticket不为空
        if (!StringUtils.isEmpty(ticket)){
            //清楚redis
            ssoService.logout(ticket);
            //清楚session
            request.getSession().removeAttribute("user");
            //清除cookie
            cookieService.deleteCookie(request,response);
        }
        return "login";
    }
}
