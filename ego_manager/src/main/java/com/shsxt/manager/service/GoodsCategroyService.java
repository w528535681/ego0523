package com.shsxt.manager.service;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.pojo.GoodsCategory;
import com.shsxt.manager.vo.GoodsCategoryVo;

import java.util.List;

/**
 * 商品分类service
 */
public interface GoodsCategroyService {

	/**
	 * 查询所有顶级分类
	 * @return
	 */
	List<GoodsCategory> selectTopList();

	/**
	 * 通过父id查询所有分类
	 * @param parentId
	 * @return
	 */
	List<GoodsCategory> selectListByParentId(Short parentId);

	/**
	 * 保存
	 * @param goodsCategory
	 * @return
	 */
	BaseResult save(GoodsCategory goodsCategory);

	/**
	 * 查询所有分类List
	 * @return
	 */
	List<GoodsCategoryVo> selectAllList();

	/**
	 * 查询所有商品的分类
	 * @return
	 */
	List<GoodsCategory> selectList();

}
