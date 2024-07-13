package com.carole.secure.system.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.common.enums.FileTypeEnum;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.properties.SysFileProperties;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.system.service.FileService;
import com.carole.secure.system.service.SysFileService;

import cn.hutool.core.util.StrUtil;

/**
 * @author CaroLe
 * @Date 2023/10/8 22:35
 * @Description
 */
@Service
public class SysFileServiceImpl implements SysFileService {

    @Resource
    private SysFileProperties sysFileProperties;

    @Resource
    private Map<String, FileService> fileMap = new ConcurrentHashMap<>(FileTypeEnum.values().length);

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @Override
    public String uploadFile(MultipartFile file, String directory) {
        FileTypeEnum fileTypeEnum = FileTypeEnum.getFileTypeEnumByType(sysFileProperties.getUploadType());
        if (StrUtil.isBlank(fileTypeEnum.getFileType())) {
            throw new DataException(ErrorType.FILE_TYPE_ERROR);
        }
        return fileMap.get(fileTypeEnum.getFileInterface()).uploadFile(file, directory);
    }

    /**
     * oss删除文件
     *
     * @param jsonObject 文件路径
     */
    @Override
    public void deleteFile(JSONObject jsonObject) {
        FileTypeEnum fileTypeEnum = FileTypeEnum.getFileTypeEnumByType(sysFileProperties.getUploadType());
        if (StrUtil.isBlank(fileTypeEnum.getFileType())) {
            throw new DataException(ErrorType.FILE_TYPE_ERROR);
        }
        fileMap.get(fileTypeEnum.getFileInterface()).deleteFile(jsonObject);
    }
}
