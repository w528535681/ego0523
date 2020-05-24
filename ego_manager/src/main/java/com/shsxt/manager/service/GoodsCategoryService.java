package com.shsxt.manager.service;

import com.shsxt.manager.pojo.GoodsCategory;

import java.util.List;


public interface GoodsCategoryService{

    /**
     * 查询商品所有顶级分类
     * @return
     */
    List<GoodsCategory> queryGoodsCategoryList();

    /**
     * 根据perentId查询所有商品分类
     * @param parentId
     * @return
     */
    List<GoodsCategory> queryGoodsCategoryListByParentId(Short parentId);

}
