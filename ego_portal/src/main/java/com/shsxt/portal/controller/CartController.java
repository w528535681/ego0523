package com.shsxt.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.common.pojo.Admin;
import com.shsxt.common.result.BaseResult;
import com.shsxt.rpc.service.CartService;
import com.shsxt.rpc.vo.CartResult;
import com.shsxt.rpc.vo.CartVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("cart")
public class CartController {

    @Reference(interfaceClass = CartService.class)
    private CartService cartService;


    /**
     * 加入购物车
     * @param cartVo
     * @param request
     * @return
     */
    @RequestMapping("addCart")
    @ResponseBody
    public BaseResult addCart(CartVo cartVo, HttpServletRequest request){
        cartVo.setAddTime(new Date());
        Admin admin = (Admin) request.getSession().getAttribute("user");

        return cartService.addCart(cartVo,admin);
    }

    @RequestMapping("getCartNum")
    @ResponseBody
    public Integer getCartNum(HttpServletRequest request){

        Admin admin = (Admin) request.getSession().getAttribute("user");
        return cartService.getCartNum(admin);
    }

    @RequestMapping("getCartList")
    public String getCartList(HttpServletRequest request, Model model){

        Admin admin = (Admin) request.getSession().getAttribute("user");
        CartResult cartResult = cartService.getCartList(admin);
        model.addAttribute("cartResult",null==cartResult?new CartResult():cartResult);

        return "cart/list";

    }
}
