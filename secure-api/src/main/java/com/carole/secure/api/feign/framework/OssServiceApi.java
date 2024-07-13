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
 * @Date 2023/10/8 21:50
 * @Description
 */
@FeignClient(value = "secure-framework", contextId = "OssService")
public interface OssServiceApi {

    /**
     * oss 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @PostMapping(value = "/oss/uploadOssFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadOssFile(@RequestPart("file") MultipartFile file, @RequestParam("directory") String directory);

    /**
     * oss删除文件
     *
     * @param path 文件路径
     */
    @DeleteMapping(value = "/oss/deleteOssFile")
    void deleteOssFile(@RequestParam("path") String path);
}
