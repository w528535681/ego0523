package com.shsxt.manager.controller;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.pojo.Goods;
import com.shsxt.manager.service.BrandService;
import com.shsxt.manager.service.GoodsCategroyService;
import com.shsxt.manager.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * 商品Controller
 *
 * @author wy
 * @create 2019/12/26
 * @since 1.0.0
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsCategroyService goodsCategroyService;

    @Resource
    private BrandService brandService;

    @Resource
    private GoodsService goodsService;


    /**
     * 商品列表-跳转列表页面
     *
     * @return
     */
    @RequestMapping("list")
    public String list(){

        return "goods/goods-list";
    }

    @RequestMapping("add")
    public String  add(Model model){
        //查询所有顶级分类
        model.addAttribute("gcList",goodsCategroyService.selectTopList());

        //查询所有品牌
        model.addAttribute("brandList",brandService.selectList());

        return "goods/goods-add";
    }

    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(Goods goods){
        return goodsService.save(goods);
    }
}

