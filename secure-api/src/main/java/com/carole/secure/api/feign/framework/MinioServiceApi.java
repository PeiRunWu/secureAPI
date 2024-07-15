package com.carole.secure.api.feign.framework;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.api.feign.framework.fallback.MinioFallbackFactory;

/**
 * @author CaroLe
 * @Date 2023/10/9 21:32
 * @Description
 */
@FeignClient(value = "secure-framework", contextId = "MinioService", fallbackFactory = MinioFallbackFactory.class)
public interface MinioServiceApi {
    /**
     * 上传到minio
     *
     * @param file 文件
     * @param directory 目录
     * @return minio路径
     */
    @PostMapping(value = "/minio/uploadMinioFile")
    String uploadMinioFile(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory);

    /**
     * 删除文件minio
     *
     * @param path 文件路径
     */
    @DeleteMapping(value = "/minio/deleteMinioFile")
    void deleteMinioFile(@RequestParam("path") String path);
}
