package com.carole.secure.framework.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.carole.secure.framework.service.OssService;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:28
 * @Description
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliCloud.oss.endpoint}")
    private String endpoint;

    @Value("${aliCloud.accessKeyId}")
    private String accessKeyId;

    @Value("${aliCloud.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliCloud.bucketName}")
    private String bucketName;

    /**
     * oss 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @Override
    public String upload(MultipartFile file, String directory) {
        String fileName = directory + "/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID()
            + file.getOriginalFilename();
        OSS ossClient = null;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, inputStream);
        } catch (IOException e) {
            log.error("文件上传失败", e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }

    /**
     * 删除文件
     *
     * @param path 文件路径
     */
    @Override
    public void deleteOssFile(String path) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            String prefix = "https://" + bucketName + "." + endpoint + "/";
            ossClient.deleteObject(bucketName, path.replace(prefix, ""));
        } catch (Exception e) {
            log.error("文件删除失败", e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
