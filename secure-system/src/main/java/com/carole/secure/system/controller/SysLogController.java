package com.carole.secure.system.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.SysLogDTO;
import com.carole.secure.system.model.query.SysLogQuery;
import com.carole.secure.system.service.SysLogService;

/**
 * @author CaroLe
 * @Date 2024/3/20 22:07
 * @Description
 */
@RestController
@RequestMapping("/sysLog")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 分页获取日志列表
     * 
     * @param sysLogQuery 分页信息
     * @return PageUtil
     */
    @GetMapping("/getLogInfoByPage")
    public PageUtil<SysLogDTO> getLogInfoByPage(SysLogQuery sysLogQuery) {
        return sysLogService.getLogInfoByPage(sysLogQuery);
    }

    /**
     * 删除日志信息
     * 
     * @param id 日志Id
     */
    @DeleteMapping("/deleteLogInfo/{id}")
    public void deleteLogInfo(@PathVariable("id") String id) {
        sysLogService.deleteLogInfo(id);
    }

}
