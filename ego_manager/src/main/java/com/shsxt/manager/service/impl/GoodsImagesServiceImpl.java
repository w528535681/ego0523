package com.shsxt.manager.service.impl;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.mapper.GoodsImagesMapper;
import com.shsxt.manager.pojo.GoodsImages;
import com.shsxt.manager.service.GoodsImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsImagesServiceImpl implements GoodsImagesService {

    @Autowired
    private GoodsImagesMapper goodsImagesMapper;

    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    @Override
    public BaseResult save(GoodsImages goodsImages) {
        int result = goodsImagesMapper.insertSelective(goodsImages);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }
}
