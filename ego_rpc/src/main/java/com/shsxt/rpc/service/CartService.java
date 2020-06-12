package com.shsxt.rpc.service;

import com.shsxt.common.pojo.Admin;
import com.shsxt.common.result.BaseResult;
import com.shsxt.rpc.vo.CartResult;
import com.shsxt.rpc.vo.CartVo;

/**
 * 购物车service
 */
public interface CartService {

    /**
     * 加入购物车
     * @param cartVo
     * @param admin
     * @return
     */
    BaseResult addCart(CartVo cartVo, Admin admin);

    /**
     * 获取购物车数量
     * @param admin
     * @return
     */
    Integer getCartNum(Admin admin);


    /**
     *  获取购物车列表
     * @param admin
     * @return
     */
    CartResult getCartList(Admin admin);

    /**
     * 清除购物车
     * @param admin
     * @return
     */
    BaseResult clearCart(Admin admin);
}
