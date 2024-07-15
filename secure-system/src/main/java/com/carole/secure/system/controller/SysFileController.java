package com.carole.secure.system.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.carole.secure.elasticsearch.annotation.OperationLog;
import com.carole.secure.elasticsearch.enums.OperaModuleEnum;
import com.carole.secure.system.service.SysFileService;

/**
 * @author CaroLe
 * @Date 2023/10/8 22:33
 * @Description 文件管理
 */
@RestController
@RequestMapping("/sysFile")
public class SysFileController {

    @Resource
    private SysFileService sysFileService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @param directory 目录
     * @return 文件地址
     */
    @PostMapping("/uploadFile")
    @OperationLog(operaDesc = "文件上传", operaModule = OperaModuleEnum.SYSTEM_FILE)
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory) {
        return sysFileService.uploadFile(file, directory);
    }

    /**
     * 删除文件
     *
     * @param jsonObject 文件路径
     */
    @PostMapping("/deleteFile")
    @OperationLog(operaDesc = "删除文件", operaModule = OperaModuleEnum.SYSTEM_FILE)
    public void deleteFile(@RequestBody JSONObject jsonObject) {
        sysFileService.deleteFile(jsonObject);
    }
}
