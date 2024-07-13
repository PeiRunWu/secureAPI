package com.carole.secure.api.feign.framework;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author CaroLe
 * @Date 2023/10/8 23:06
 * @Description
 */
@FeignClient(value = "secure-framework", contextId = "CosService")
public interface CosServiceApi {
    /**
     * 上传到腾讯云cos
     *
     * @param file 文件
     * @param directory 目录
     * @return cos路径
     */
    @PostMapping(value = "/cos/uploadCosFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadCosFile(@RequestPart("file") MultipartFile file, @RequestParam("directory") String directory);

    /**
     * 删除文件cos
     *
     * @param path 文件路径
     */
    @DeleteMapping(value = "/cos/deleteCosFile")
    void deleteCosFile(@RequestParam("path") String path);
}
