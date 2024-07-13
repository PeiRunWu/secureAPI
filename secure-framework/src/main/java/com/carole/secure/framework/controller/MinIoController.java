package com.carole.secure.framework.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.framework.service.MinIoService;

/**
 * @author CaroLe
 * @Date 2023/9/26 22:08
 * @Description minio
 */
@RestController
@RequestMapping("/minio")
public class MinIoController {

    @Resource
    private MinIoService minIoService;

    /**
     * 上传到minio
     *
     * @param file 文件
     * @param directory 目录
     * @return minio路径
     */
    @PostMapping(value = "/uploadMinioFile")
    public String uploadMinioFile(@RequestParam("file") MultipartFile file,
        @RequestParam("directory") String directory) {
        return minIoService.uploadMinioFile(file, directory);
    }

    /**
     * 删除文件minio
     *
     * @param path 文件路径
     */
    @DeleteMapping(value = "/deleteMinioFile")
    public void deleteMinioFile(@RequestParam String path) {
        minIoService.deleteMinioFile(path);
    }
}
