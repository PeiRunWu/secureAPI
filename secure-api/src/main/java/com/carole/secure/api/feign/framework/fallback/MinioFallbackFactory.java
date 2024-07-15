package com.carole.secure.api.feign.framework.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.api.feign.framework.MinioServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/15 13:50
 * @Description
 */
@Slf4j
@Component
public class MinioFallbackFactory implements FallbackFactory<MinioServiceApi> {

    @Override
    public MinioServiceApi create(Throwable cause) {
        return new MinioServiceApi() {
            @Override
            public String uploadMinioFile(MultipartFile file, String directory) {
                log.error("上传到Minio服务远程接口熔断:{}", cause.getMessage(), cause);
                return null;
            }

            @Override
            public void deleteMinioFile(String path) {
                log.error("删除Minio服务远程接口熔断:{}", cause.getMessage(), cause);
            }
        };
    }
}
