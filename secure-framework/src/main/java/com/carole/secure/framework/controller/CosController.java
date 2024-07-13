package com.carole.secure.framework.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.carole.secure.framework.service.CosService;

/**
 * @author CaroLe
 * @Date 2023/7/23 22:54
 * @Description cos
 */
@RestController
@RequestMapping("/cos")
public class CosController {

    @Resource
    private CosService cosService;

    /**
     * 上传到腾讯云cos
     *
     * @param file 文件
     * @param directory 目录
     * @return cos路径
     */
    @PostMapping(value = "/uploadCosFile")
    public String uploadCosFile(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory) {
        return cosService.uploadCosFile(file, directory);
    }

    /**
     * 删除文件cos
     *
     * @param path 文件路径
     */
    @DeleteMapping(value = "/deleteCosFile")
    public void deleteCosFile(@RequestParam String path) {
        cosService.deleteCosFile(path);
    }

}
