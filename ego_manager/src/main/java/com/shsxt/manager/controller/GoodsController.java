package com.shsxt.manager.controller;

import com.shsxt.common.result.BaseResult;
import com.shsxt.common.result.FileResult;
import com.shsxt.manager.pojo.Goods;
import com.shsxt.manager.pojo.GoodsImages;
import com.shsxt.manager.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 商品Controller
 *
 * @author wy
 * @create 2019/12/26
 * @since 1.0.0
 */
@Controller
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsCategroyService goodsCategroyService;

    @Resource
    private BrandService brandService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private UploadService uploadService;

    @Resource
    private GoodsImagesService goodsImagesService;


    /**
     * 商品列表-跳转列表页面
     *
     * @return
     */
    @RequestMapping("list")
    public String list(){

        return "goods/goods-list";
    }

    @RequestMapping("add")
    public String  add(Model model){
        //查询所有顶级分类
        model.addAttribute("gcList",goodsCategroyService.selectTopList());

        //查询所有品牌
        model.addAttribute("brandList",brandService.selectList());

        return "goods/goods-add";
    }

    @RequestMapping("save")
    @ResponseBody
    public BaseResult save(Goods goods){
        return goodsService.save(goods);
    }

    /**
     * 商品相册上传(多文件上传相当于多次调用该方法)
     *
     * @param file
     * @param goodsId
     * @return
     */
    @RequestMapping("images/save")
    @ResponseBody
    public BaseResult imageSave(MultipartFile file,Integer goodsId) throws IOException {

        String fileName = file.getOriginalFilename();
        //格式化时间
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
        FileResult result = uploadService.uploadFile(file.getInputStream(),fileName);
        //表示上传成功
        if (!StringUtils.isEmpty(result.getFileUrl())){
            //创建GoodsImages对象
            GoodsImages goodsImages = new GoodsImages();
            //设置上传成功后的url
            goodsImages.setImageUrl(result.getFileUrl());
            //设置商品Id
            goodsImages.setGoodsId(goodsId);

            BaseResult baseResult = goodsImagesService.save(goodsImages);
            return baseResult;
        }else {
            //上传失败
            return BaseResult.error();
        }

    }
}

