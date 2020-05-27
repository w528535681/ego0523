package com.shsxt.manager.service.impl;


import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.mapper.GoodsCategoryMapper;
import com.shsxt.manager.pojo.GoodsCategory;
import com.shsxt.manager.pojo.GoodsCategoryExample;
import com.shsxt.manager.service.GoodsCategroyService;
import com.shsxt.manager.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类实现类
 *
 * @author zhoubin
 * @create 2019/12/25
 * @since 1.0.0
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategroyService {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	/**
	 * 查询所有顶级分类
	 *
	 * @return
	 */
	@Override
	public List<GoodsCategory> selectTopList() {
		//创建查询对象
		GoodsCategoryExample example = new GoodsCategoryExample();
		//设置查询条件
		example.createCriteria().andParentIdEqualTo((short) 0);
		return goodsCategoryMapper.selectByExample(example);
	}

	/**
	 * 通过父id查询所有分类
	 *
	 * @param parentId
	 * @return
	 */
	@Override
	public List<GoodsCategory> selectListByParentId(Short parentId) {
		//创建查询对象
		GoodsCategoryExample example = new GoodsCategoryExample();
		//设置查询条件
		example.createCriteria().andParentIdEqualTo(parentId);
		return goodsCategoryMapper.selectByExample(example);
	}

	/**
	 * 保存
	 *
	 * @param goodsCategory
	 * @return
	 */
	@Override
	public BaseResult save(GoodsCategory goodsCategory) {
		//保存
		int result = goodsCategoryMapper.insertSelective(goodsCategory);
		return result > 0 ? BaseResult.success() : BaseResult.error();
	}


	/**
	 * 查询所有分类List
	 * @return
	 */
	@Override
	public List<GoodsCategoryVo> selectAllList() {

		//创建顶级分类的List
		List<GoodsCategoryVo> gcvList01 = new ArrayList<GoodsCategoryVo>();

		//创建查询对象
		GoodsCategoryExample example = new GoodsCategoryExample();
		//设置查询条件
		example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
		//查询所有顶级分类
		List<GoodsCategory> gcList01 = goodsCategoryMapper.selectByExample(example);
		for (GoodsCategory gc01 : gcList01 ) {
			GoodsCategoryVo gv01 = new GoodsCategoryVo();
			//拷贝对象
			BeanUtils.copyProperties(gc01,gv01);
			//清除之前的查询条件
			example.clear();
			//设置查询条件   where parentId = 顶级分类的id and level = 2
			example.createCriteria().andParentIdEqualTo(gv01.getId()).andLevelEqualTo((byte) 2);

			List<GoodsCategoryVo> gcvList02 = new ArrayList<>();
			//查询所有二级分类
			List<GoodsCategory> gcList02 = goodsCategoryMapper.selectByExample(example);
			for (GoodsCategory gc02:gcList02 ) {
				GoodsCategoryVo gv02 = new GoodsCategoryVo();
				BeanUtils.copyProperties(gc02,gv02);
				example.clear();
				//设置查询条件   where parentId = 二分类的id and level = 3
				example.createCriteria().andParentIdEqualTo(gv02.getId()).andLevelEqualTo((byte) 3);
				List<GoodsCategoryVo> gvList03 = new ArrayList<>();
				//查询所有三级分类
				List<GoodsCategory> gcList03 = goodsCategoryMapper.selectByExample(example);
				for (GoodsCategory gc03 : gcList03) {
					GoodsCategoryVo gv03 = new GoodsCategoryVo();
					BeanUtils.copyProperties(gc03,gv03);
					gvList03.add(gv03);
				}
				gv02.setChildrenList(gvList03);
				gcvList02.add(gv02);
			}
			gv01.setChildrenList(gcvList02);
			gcvList01.add(gv01);
		}
		return gcvList01;
	}

	/**
	 * 查询所有商品的分类
	 * @return
	 */
	@Override
	public List<GoodsCategory> selectList() {
		return goodsCategoryMapper.selectByExample(new GoodsCategoryExample());
	}

}