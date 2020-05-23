package com.shsxt.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class GoodsCategoryController {

    @RequestMapping("list")
    public String list(){

        return "goods/category/category-list";
    }

    @RequestMapping("add")
    public String add(){

        return "goods/category/category-add";
    }
}
