package com.carole.secure.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.framework.OssServiceApi;
import com.carole.secure.system.service.FileService;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:28
 * @Description
 */
@Service
public class OssServiceImpl implements FileService {

    @Resource
    private OssServiceApi ossServiceApi;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @Override
    public String uploadFile(MultipartFile file, String directory) {
        return ossServiceApi.uploadOssFile(file, directory);
    }

    /**
     * 删除文件
     *
     * @param jsonObject 文件路径
     */
    @Override
    public void deleteFile(JSONObject jsonObject) {
        ossServiceApi.deleteOssFile(jsonObject.getString("path"));
    }
}
