package com.carole.secure.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.api.feign.framework.CosServiceApi;
import com.carole.secure.system.service.FileService;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:26
 * @Description
 */
@Service
public class CosServiceImpl implements FileService {

    @Resource
    private CosServiceApi cosServiceApi;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @Override
    public String uploadFile(MultipartFile file, String directory) {
        return cosServiceApi.uploadCosFile(file, directory);
    }

    /**
     * 删除文件
     *
     * @param jsonObject 文件路径
     */
    @Override
    public void deleteFile(JSONObject jsonObject) {
        cosServiceApi.deleteCosFile(jsonObject.getString("path"));
    }
}
