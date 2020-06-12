package com.shsxt.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shsxt.common.pojo.Admin;
import com.shsxt.common.result.BaseResult;
import com.shsxt.common.util.JsonUtil;
import com.shsxt.rpc.service.CartService;
import com.shsxt.rpc.vo.CartResult;
import com.shsxt.rpc.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车service
 */

@Service(interfaceClass = CartService.class)
@Component
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${user.cart}")
    private String userCart;

    private HashOperations<String, String, String> hashOperations = null;

    /**
     * 加入购物车实现类
     *
     * @param cart
     * @param admin
     * @return
     */
    @Override
    public BaseResult addCart(CartVo cart, Admin admin) {

        //1.判断用户是否存在
        if (null == admin || null == admin.getAdminId()) {
            return BaseResult.error();
        }

        //2.根据用户id去redis里面取数据
        Short adminId = admin.getAdminId();
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + adminId);
        //3.判断cartMap里是否有数据
        if (!CollectionUtils.isEmpty(cartMap)) {
            //4.判断加入的购物车信息的商品id和cartMap里的商品id是否一致
            String cartStr = cartMap.get(String.valueOf(cart.getGoodsId()));
            if (!StringUtils.isEmpty(cartStr)) {
                //id存在,数量相加
                CartVo cartVo = JsonUtil.jsonStr2Object(cartStr, CartVo.class);
                cartVo.setGoodsNum(cart.getGoodsNum() + cartVo.getGoodsNum());

                //修改商品最新价格
                cartVo.setMarketPrice(cart.getMarketPrice());
                cartMap.put(String.valueOf(cart.getGoodsId()), JsonUtil.object2JsonStr(cartVo));
            } else {
                //如果id不存在，将加入的购物车信息存入redis
                cartMap.put(String.valueOf(cart.getGoodsId()), JsonUtil.object2JsonStr(cart));
            }
        } else {
            //cartMap里没有数据，我们直接将购物车信息加入redis
            cartMap = new HashMap<>();
            cartMap.put(String.valueOf(cart.getGoodsId()), JsonUtil.object2JsonStr(cart));
        }
        hashOperations.putAll(userCart + ":" + adminId, cartMap);
        return BaseResult.success();
    }

    /**
     * 获取购物车数量
     *
     * @param admin
     * @return
     */
    @Override
    public Integer getCartNum(Admin admin) {

        //1.判断用户是否存在
        if (null == admin || admin.getAdminId() == null) {
            return 0;
        }
        //初始化返回结果
        Integer result = 0;
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        //2.如果拿到数据
        if (!CollectionUtils.isEmpty(cartMap)){
            for (Map.Entry<String, String> entry : cartMap.entrySet()) {
                //拿到购物车对象
                CartVo cartVo = JsonUtil.jsonStr2Object(entry.getValue(),CartVo.class);
                //将数量相加
                result += cartVo.getGoodsNum();
            }
        }
        return result;
    }

    /**
     * 获取购物车列表
     * @param admin
     * @return
     */
    @Override
    public CartResult getCartList(Admin admin) {

        //判断用户是否存在
        if (null ==admin ||admin.getAdminId()==null){
            return null;
        }

        //初始化返回对象
        CartResult cartResult = null;
        List<CartVo> cartList = null;
        BigDecimal totalPrice = new BigDecimal("0");

        //从redis里获取购物车信息
        hashOperations = redisTemplate.opsForHash();
        Map<String, String> cartMap = hashOperations.entries(userCart + ":" + admin.getAdminId());
        //判断是否存在购物车信息
        if (!CollectionUtils.isEmpty(cartMap)){
            //如果存在
            cartResult = new CartResult();
            cartList = new ArrayList<>();
            for (Map.Entry<String, String> entry : cartMap.entrySet()) {
                CartVo cartVo = JsonUtil.jsonStr2Object(entry.getValue(),CartVo.class);
                //将购物车对象放入list
                cartList.add(cartVo);

                //计算总价
                /**
                 * 先算单价*数量
                 * 在把所有价格相加
                 */
                BigDecimal singlePrice =
                        cartVo.getMarketPrice().multiply(new BigDecimal(String.valueOf(cartVo.getGoodsNum())));
                totalPrice = totalPrice.add(singlePrice);
            }
            //将购物车list放入返回结果对象
            cartResult.setCartList(cartList);

            //格式化两位小数，四舍五入
            totalPrice.setScale(2,BigDecimal.ROUND_UP);
            cartResult.setTotalPrice(totalPrice);
        }
        return cartResult;
    }

    /**
     * 清除购物车
     * @param admin
     * @return
     */
    @Override
    public BaseResult clearCart(Admin admin) {
        return null;
    }
}
