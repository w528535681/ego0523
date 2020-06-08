package com.shsxt.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shsxt.common.util.JsonUtil;
import com.shsxt.rpc.mapper.GoodsCategoryMapper;
import com.shsxt.rpc.pojo.GoodsCategory;
import com.shsxt.rpc.pojo.GoodsCategoryExample;
import com.shsxt.rpc.service.GoodsCategroyService;
import com.shsxt.rpc.vo.GoodsCategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = GoodsCategroyService.class)
@Component
public class GoodsCategoryServiceImpl implements GoodsCategroyService {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Value("${goods.category.list.key}")
	private String goodsCategoryRedisKey;


	/**
	 * 查询所有分类List
	 *
	 * @return
	 */
	@Override
	public List<GoodsCategoryVo> selectAllList() {

		//顶级分类的list
		List<GoodsCategoryVo> gcvList01 = new ArrayList<>();

		// //先从redis查询是否有数据
		ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();
		String gcvListJson = (String) stringObjectValueOperations.get(goodsCategoryRedisKey);
		//如果有值，直接转成list返回，没有就去数据库查询
		if (!StringUtils.isEmpty(gcvListJson)){
			List<GoodsCategoryVo> gcvList = JsonUtil.jsonToList(gcvListJson, GoodsCategoryVo.class);
			return gcvList;
		}

		//创建查询对象
		GoodsCategoryExample example = new GoodsCategoryExample();
		//设置查询条件 where parentId = 0 and level = 1
		example.createCriteria().andParentIdEqualTo((short) 0).andLevelEqualTo((byte) 1);
		//查询所有顶级分类
		List<GoodsCategory> gcList01 = goodsCategoryMapper.selectByExample(example);
		for (GoodsCategory gc01 : gcList01) {
			GoodsCategoryVo gcv01 = new GoodsCategoryVo();
			//拷贝对象
			BeanUtils.copyProperties(gc01, gcv01);
			//清除之前的查询条件
			example.clear();
			//设置查询条件   where parentId = 顶级分类的id and level = 2
			example.createCriteria().andParentIdEqualTo(gcv01.getId()).andLevelEqualTo((byte) 2);
			List<GoodsCategoryVo> gcvList02 = new ArrayList<>();
			//查询所有二级分类
			List<GoodsCategory> gcList02 = goodsCategoryMapper.selectByExample(example);
			for (GoodsCategory gc02 : gcList02) {
				GoodsCategoryVo gcv02 = new GoodsCategoryVo();
				BeanUtils.copyProperties(gc02, gcv02);
				//清除之前的查询条件
				example.clear();
				//设置查询条件
				example.createCriteria().andParentIdEqualTo(gcv02.getId()).andLevelEqualTo((byte) 3);
				//查询三级分类
				List<GoodsCategoryVo> gcvList03 = new ArrayList<>();
				List<GoodsCategory> gcList03 = goodsCategoryMapper.selectByExample(example);
				for (GoodsCategory gc03 : gcList03) {
					GoodsCategoryVo gcv03 = new GoodsCategoryVo();
					BeanUtils.copyProperties(gc03, gcv03);
					gcvList03.add(gcv03);
				}
				//把所有三级分类放入二级分类的子分类List
				gcv02.setChildrenList(gcvList03);
				gcvList02.add(gcv02);
			}
			//把二级分类放入一级分类的子分类List
			gcv01.setChildrenList(gcvList02);
			gcvList01.add(gcv01);
		}


		//将数据库查到的数据转成json字符串存入redis
		stringObjectValueOperations.set(goodsCategoryRedisKey, JsonUtil.object2JsonStr(gcvList01));
		return gcvList01;
	}
}