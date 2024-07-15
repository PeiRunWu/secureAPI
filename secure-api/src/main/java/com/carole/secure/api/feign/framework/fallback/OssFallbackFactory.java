package com.carole.secure.api.feign.framework.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.api.feign.framework.OssServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/15 13:53
 * @Description
 */
@Slf4j
@Component
public class OssFallbackFactory implements FallbackFactory<OssServiceApi> {

    @Override
    public OssServiceApi create(Throwable cause) {
        return new OssServiceApi() {
            @Override
            public String uploadOssFile(MultipartFile file, String directory) {
                log.error("上传到oss服务远程接口熔断:{}", cause.getMessage(), cause);
                return null;
            }

            @Override
            public void deleteOssFile(String path) {
                log.error("删除oss服务远程接口熔断:{}", cause.getMessage(), cause);
            }
        };
    }
}
