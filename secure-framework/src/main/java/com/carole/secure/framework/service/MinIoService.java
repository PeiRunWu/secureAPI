package com.carole.secure.framework.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/9/26 22:09
 * @Description
 */
public interface MinIoService {

    /**
     * 上传到minio
     *
     * @param file 文件
     * @param directory 路径
     * @return minio路径
     */
    String uploadMinioFile(MultipartFile file, String directory);

    /**
     * 删除文件minio
     *
     * @param path 文件路径
     */
    void deleteMinioFile(String path);
}
