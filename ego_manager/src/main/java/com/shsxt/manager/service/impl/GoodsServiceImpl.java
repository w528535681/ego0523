package com.shsxt.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.common.result.BaseResult;
import com.shsxt.common.result.FileResult;
import com.shsxt.manager.mapper.GoodsMapper;
import com.shsxt.manager.pojo.Goods;
import com.shsxt.manager.pojo.GoodsExample;
import com.shsxt.manager.service.GoodsService;
import com.shsxt.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 商品Service实现类
 *
 * @author wy
 * @create 2019/12/26
 * @since 1.0.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public BaseResult save(Goods goods) {

        //如果详情描述不为空，进行转义保存
        if (!StringUtils.isEmpty(goods.getGoodsContent())){
            String escape = HtmlUtils.htmlEscape(goods.getGoodsContent(), "UTF-8");
            goods.setGoodsContent(escape);
        }

        if (null != goods.getGoodsId()) {
            return BaseResult.error();
        }

        BaseResult baseResult = BaseResult.success();

        int result = goodsMapper.insertSelective(goods);

        if (result > 0) {
            //把插入后的id值传回前台
            baseResult.setMessage(String.valueOf(goods.getGoodsId()));
        }else {
            baseResult = BaseResult.error();
        }

        return baseResult;
    }

    /**
     * 商品列表-分页搜索
     * @param goods
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public BaseResult search(Goods goods, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        //创建查询对象
        GoodsExample example = new GoodsExample();
        //设置查询条件
        GoodsExample.Criteria criteria = example.createCriteria();
        //如果分类id不为空
        if (null != goods.getCatId()) {
            criteria.andCatIdEqualTo(goods.getCatId());
        }
        //如果品牌id不为空
        if (null != goods.getBrandId()) {
            criteria.andBrandIdEqualTo(goods.getBrandId());
        }
        //如果关键词不为空
        if (!StringUtils.isEmpty(goods.getGoodsName())) {
            criteria.andGoodsNameLike("%" + goods.getGoodsName() + "%");
        }

        List<Goods> list = goodsMapper.selectByExample(example);
        //如果查询结果不为空,存放分页对象返回
        if (!CollectionUtils.isEmpty(list)) {
            PageInfo<Goods> pageInfo = new PageInfo<>(list);
            return BaseResult.success(pageInfo);
        }

        return BaseResult.error();
    }

}
