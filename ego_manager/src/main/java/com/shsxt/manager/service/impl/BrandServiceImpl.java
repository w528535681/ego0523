package com.shsxt.manager.service.impl;

import com.shsxt.manager.mapper.BrandMapper;
import com.shsxt.manager.pojo.Brand;
import com.shsxt.manager.pojo.BrandExample;
import com.shsxt.manager.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    
    @Autowired
    private BrandMapper brandMapper;


    /**
     * 查询所有品牌
     * @return
     */
    @Override
    public List<Brand> selectList() {

       return brandMapper.selectByExample(new BrandExample());
    }
}
