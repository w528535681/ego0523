package com.shsxt.manager.controller;

import com.shsxt.manager.pojo.GoodsCategory;
import com.shsxt.manager.service.GoodsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("category")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    /**
     * 跳转商品分类页面
     * @return
     */
    @RequestMapping("list")
    public String list(){

        return "goods/category/category-list";
    }

    /**
     * 查询所有商品顶级分类
     * @param model
     * @return
     */
    @RequestMapping("add")
    public String add(Model model){
        model.addAttribute("gcList",goodsCategoryService.queryGoodsCategoryList());
        return "goods/category/category-add";
    }

    /**
     * 根据parentId查询商品分类
     * @param parentId
     * @return
     */
    @RequestMapping("/{parentId}")
    @ResponseBody
    public List<GoodsCategory> queryGoodsCategoryByParentInd(@PathVariable Short parentId){
        return goodsCategoryService.queryGoodsCategoryListByParentId(parentId);
    }

}
