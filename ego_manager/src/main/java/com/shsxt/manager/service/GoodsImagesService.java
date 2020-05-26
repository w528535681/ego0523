package com.shsxt.manager.service;

import com.shsxt.common.result.BaseResult;
import com.shsxt.manager.pojo.GoodsImages;

public interface GoodsImagesService {

    /**
     * 商品相册-保存
     * @param goodsImages
     * @return
     */
    BaseResult save(GoodsImages goodsImages);

}
