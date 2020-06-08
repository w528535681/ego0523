package com.shsxt.rpc.service;

import com.shsxt.rpc.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类service
 */
public interface GoodsCategroyService {

	/**
	 * 查询所有分类List
	 * @return
	 */
	List<GoodsCategoryVo> selectAllList();

}
