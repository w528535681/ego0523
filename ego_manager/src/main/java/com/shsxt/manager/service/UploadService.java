package com.shsxt.manager.service;

import com.qiniu.common.QiniuException;
import com.shsxt.common.result.FileResult;

import java.io.InputStream;

public interface UploadService {

    FileResult uploadFile(InputStream inputStream,String fileName) throws QiniuException;
}
