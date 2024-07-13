package com.carole.secure.framework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:54
 * @Description
 */
public interface CosService {
    /**
     * 上传到腾讯云cos
     * 
     * @param file 文件
     * @param directory 目录
     * @return cos路径
     */
    String uploadCosFile(MultipartFile file, String directory);

    /**
     * 删除文件cos
     * 
     * @param path 文件路径
     */
    void deleteCosFile(String path);
}
