package com.carole.secure.api.feign.framework.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.api.feign.framework.CosServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/15 13:34
 * @Description
 */
@Slf4j
@Component
public class CosFallbackFactory implements FallbackFactory<CosServiceApi> {

    @Override
    public CosServiceApi create(Throwable cause) {
        return new CosServiceApi() {
            @Override
            public String uploadCosFile(MultipartFile file, String directory) {
                log.error("上传到腾讯云cos服务远程接口熔断:{}", cause.getMessage(), cause);
                return null;
            }

            @Override
            public void deleteCosFile(String path) {
                log.error("删除腾讯云cos服务远程接口熔断:{}", cause.getMessage(), cause);
            }
        };
    }
}
