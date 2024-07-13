package com.carole.secure.system.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:23
 * @Description
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    String uploadFile(MultipartFile file, String directory);

    /**
     * 删除文件
     *
     * @param jsonObject 文件路径
     */
    void deleteFile(JSONObject jsonObject);
}
