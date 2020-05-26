package com.shsxt.manager.mapper;


import java.util.List;

import com.shsxt.manager.pojo.GoodsImages;
import com.shsxt.manager.pojo.GoodsImagesExample;
import org.apache.ibatis.annotations.Param;

public interface GoodsImagesMapper {
    long countByExample(GoodsImagesExample example);

    int deleteByExample(GoodsImagesExample example);

    int deleteByPrimaryKey(Integer imgId);

    int insert(GoodsImages record);

    int insertSelective(GoodsImages record);

    List<GoodsImages> selectByExample(GoodsImagesExample example);

    GoodsImages selectByPrimaryKey(Integer imgId);

    int updateByExampleSelective(@Param("record") GoodsImages record, @Param("example") GoodsImagesExample example);

    int updateByExample(@Param("record") GoodsImages record, @Param("example") GoodsImagesExample example);

    int updateByPrimaryKeySelective(GoodsImages record);

    int updateByPrimaryKey(GoodsImages record);
}