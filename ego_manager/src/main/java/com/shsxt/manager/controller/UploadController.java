package com.shsxt.manager.controller;

import com.shsxt.common.result.FileResult;
import com.shsxt.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("fileUpload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public FileResult upload(MultipartFile file) throws IOException {

        //获取文件名并重命名，防止文件名一样导致覆盖
        String filename = file.getOriginalFilename();
        //加上日期在上传时会根据日期创建目录
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd/").format(LocalDateTime.now());
        filename = date + System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        return uploadService.uploadFile(file.getInputStream(), filename);

    }
}
