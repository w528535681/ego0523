package com.shsxt.manager.service.impl;

import com.shsxt.common.enums.BaseResultEnum;
import com.shsxt.common.result.BaseResult;
import com.shsxt.common.util.AssertUtil;
import com.shsxt.manager.mapper.GoodsCategoryMapper;
import com.shsxt.manager.model.ResultInfo;
import com.shsxt.manager.pojo.GoodsCategory;
import com.shsxt.manager.pojo.GoodsCategoryExample;
import com.shsxt.manager.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 查询商品列表顶级分类
     * @return
     */
    @Override
    public List<GoodsCategory> queryGoodsCategoryList() {

        //创建响应信息
        ResultInfo<BaseResult> resultInfo = new ResultInfo<BaseResult>();
        //创建查询对象
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        //设置查询条件
        goodsCategoryExample.createCriteria().andParentIdEqualTo((short) 0);

        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByExample(goodsCategoryExample);

        if (goodsCategories != null || goodsCategories.size()>0){
            return goodsCategories;
        }

        return null;

    }

    /**
     * 根据perentId查询所有商品分类
     * @param parentId
     * @return
     */
    @Override
    public List<GoodsCategory>queryGoodsCategoryListByParentId(Short parentId) {

        Map<String,Object> result = new HashMap<String, Object>();
        ResultInfo<BaseResult> resultInfo = new ResultInfo<BaseResult>();

        /**
         * 校验参数
         */
        AssertUtil.isTrue(parentId == null || goodsCategoryMapper.selectByPrimaryKey(parentId) ==null,"查询商品列表失败！");
        //创建查询对象
        GoodsCategoryExample goodsCategoryExample = new GoodsCategoryExample();
        //设置查询条件
        goodsCategoryExample.createCriteria().andParentIdEqualTo((parentId));

        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByExample(goodsCategoryExample);

        if (goodsCategories!=null||goodsCategories.size()>0){
            return  goodsCategories;
        }else {
            resultInfo.setCode(BaseResultEnum.ERROR.getCode());
            resultInfo.setMessage("查询商品分类失败！");
            result.put("error",resultInfo);
        }
        return null;
    }
}
