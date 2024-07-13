package com.carole.secure.framework.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.framework.service.OssService;

/**
 * @author CaroLe
 * @Date 2023/6/8 21:25
 * @Description oss
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource
    private OssService ossService;

    /**
     * oss 文件上传
     * 
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @PostMapping(value = "/uploadOssFile")
    public String uploadOssFile(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory) {
        return ossService.upload(file, directory);
    }

    /**
     * oss删除文件
     * 
     * @param path 文件路径
     */
    @DeleteMapping(value = "/deleteOssFile")
    public void deleteOssFile(@RequestParam String path) {
        ossService.deleteOssFile(path);
    }

}