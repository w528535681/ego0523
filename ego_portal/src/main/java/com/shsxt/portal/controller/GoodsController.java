package com.shsxt.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.shsxt.rpc.service.GoodsCategroyService;
import com.shsxt.rpc.vo.GoodsCategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品Controller
 *
 * @author wy
 * @since 1.0.0
 */
@Controller
@RequestMapping("goodsCategory")
public class GoodsController {

    @Reference(interfaceClass = GoodsCategroyService.class)
    private GoodsCategroyService goodsCategroyService;

    /**
     * 商品分类列表
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public List<GoodsCategoryVo> goodsCategoryList(){
        return goodsCategroyService.selectAllList();
    }
}
