package com.carole.secure.framework.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.framework.service.MinIoService;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/9/26 22:09
 * @Description
 */
@Slf4j
@Service
public class MinIoServiceImpl implements MinIoService {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 上传到minio
     *
     * @param file 文件
     * @param directory 路径
     * @return minio路径
     */
    @Override
    public String uploadMinioFile(MultipartFile file, String directory) {
        MinioClient minioClient = createMinioClient();
        try {
            String fileName = directory + "/" + new DateTime().toString("yyyy-MM-dd") + "/" + IdUtil.simpleUUID()
                + file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(fileName)
                .stream(file.getInputStream(), file.getInputStream().available(), -1).contentType(file.getContentType())
                .build());
            return endpoint + "/" + bucketName + "/" + fileName;
        } catch (Exception e) {
            log.error("文件上传失败", e);
        }
        return null;
    }

    /**
     * 删除文件minio
     *
     * @param path 文件路径
     */
    @Override
    public void deleteMinioFile(String path) {
        MinioClient minioClient = createMinioClient();
        try {
            path = URLUtil.getPath(path).substring(URLUtil.getPath(path).indexOf(bucketName) + bucketName.length());
            minioClient
                .removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(URLUtil.getPath(path)).build());
        } catch (Exception e) {
            log.error("文件删除失败", e);
        }
    }

    private MinioClient createMinioClient() {
        return MinioClient.builder().endpoint(endpoint).credentials(accessKey, secretKey).build();
    }

}
