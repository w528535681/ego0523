package com.shsxt.manager.service.impl;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.mapper.GoodsMapper;
import com.shsxt.manager.pojo.Goods;
import com.shsxt.manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

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
}
