package com.shsxt.manager.service;

import com.shsxt.manager.pojo.Brand;

import java.util.List;

public interface BrandService {


    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> selectList();
}
