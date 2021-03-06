package com.shsxt.manager.service;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.pojo.Goods;

/**
 * 商品Service
 */
public interface GoodsService {

    /**
     * 商品列表-保存
     * @param goods
     * @return
     */
    BaseResult save(Goods goods);

    /**
     * 商品列表-分页搜索
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    BaseResult search(Goods goods,Integer pageNum,Integer pageSize);
}
