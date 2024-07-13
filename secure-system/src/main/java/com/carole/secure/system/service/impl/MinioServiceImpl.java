package com.carole.secure.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.framework.MinioServiceApi;
import com.carole.secure.system.service.FileService;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:29
 * @Description
 */
@Service
public class MinioServiceImpl implements FileService {

    @Resource
    private MinioServiceApi minioServiceApi;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @Override
    public String uploadFile(MultipartFile file, String directory) {
        return minioServiceApi.uploadMinioFile(file, directory);
    }

    /**
     * 删除文件
     *
     * @param jsonObject 文件路径
     */
    @Override
    public void deleteFile(JSONObject jsonObject) {
        minioServiceApi.deleteMinioFile(jsonObject.getString("path"));
    }
}
