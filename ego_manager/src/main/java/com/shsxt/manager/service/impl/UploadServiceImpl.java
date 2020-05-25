package com.shsxt.manager.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.shsxt.common.result.FileResult;
import com.shsxt.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 上传服务类
 *
 * @author wy
 * @create 2019/12/7
 * @since 1.0.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${qiniu.AccessKey}")
    private String accessKey;
    @Value("${qiniu.SecretKey}")
    private String secretKey;
    @Value("${qiniu.Bucket}")
    private String bucket;
    @Value("${qiniu.cdn.prefix}")
    private String cdn;
    //上传工具实力
    private UploadManager uploadManager;
    //上传凭证实例
    private String uploadToken;
    /**
     *  以流的形式上传
     * @param inputStream
     * @param fileName
     * @return
     */
    @Override
    public FileResult uploadFile (InputStream inputStream, String fileName) throws QiniuException {
        FileResult fileResult = new FileResult();
        getUploadManager();
        //上传文件
        Response response = this.uploadManager.put(inputStream,
                fileName, uploadToken, null, null);
        int retry = 0;
        //重试并且重试次数 3 次
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, fileName,
                    uploadToken, null, null);
            retry++;
        }
        //上传成功
        if (response.statusCode == 200) {
            // 返回上传的 url
            // 标准情况下需要在拼接链接之前，将文件名进行 urlencode 以兼容不同的字符。
            String result = String.format("%s/%s", "http://"+cdn,
                    fileName);
            fileResult.setSuccess("success");
            fileResult.setMessage("文件上传成功");
            fileResult.setFileUrl(result);
        }else {
            fileResult.setError("error");
            fileResult.setMessage("文件上传失败");
        }
        return fileResult;
    }

    /**
     * 构建一个七牛上传工具实例和上传凭证
     */
    private void getUploadManager() {
        // 认证信息实例
        Auth auth = Auth.create(accessKey, secretKey);
        //上传策略设置
        StringMap putPolicy = new StringMap();

        putPolicy.put("returnBody","{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\"," +
                "\"width\":$(imageInfo.width),\"height\":${imageInfo.height}}");

        //获取上传凭证
        /**
         * 第一个参数，空间名
         * 第二个参数，文件保存在空间中的名称
         * 第三个参数，超时时间，一小时。
         * 第四个参数，上传策略
         */
        this.uploadToken = auth.uploadToken(bucket, null, 3600,
                putPolicy);
        //自动判断自己空间所在的区域
        com.qiniu.storage.Configuration qiniuConfig = new
                com.qiniu.storage.Configuration(Region.autoRegion());
        //构建一个七牛上传工具实例
        this.uploadManager = new UploadManager(qiniuConfig);
    }
}
