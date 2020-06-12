package com.shsxt.portal;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.common.pojo.Admin;
import com.shsxt.rpc.service.CartService;
import com.shsxt.rpc.vo.CartVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车service测试类
 */
@SpringBootTest
public class CartServiceTest {

    @Reference(interfaceClass = CartService.class)
    private CartService cartService;

    /**
     * 添加购物车
     */
    @Test
    public void testAddCart(){
        Admin admin = new Admin();admin.setAdminId((short) 1);
        CartVo cartVo = new CartVo();
        cartVo.setGoodsId(123456);
        cartVo.setGoodsName("JAVA核心技术");
        cartVo.setMarketPrice(new BigDecimal("290"));
        cartVo.setAddTime(new Date());
        cartVo.setGoodsNum(5);
        cartService.addCart(cartVo,admin);
    }

    /**
     * 获取购物车数量
     */
    @Test
    public void testGetCartNum(){
        Admin admin = new Admin();
        admin.setAdminId((short) 1);
        Integer cartNum = cartService.getCartNum(admin);
        System.out.println(cartNum);
    }

}
