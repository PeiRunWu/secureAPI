package com.carole.secure.framework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:28
 * @Description
 */
public interface OssService {

    /**
     * oss 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    String upload(MultipartFile file, String directory);

    /**
     * 删除文件阿里云oss
     *
     * @param path 文件路径
     */
    void deleteOssFile(String path);
}
