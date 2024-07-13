package com.carole.secure.system.service;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.system.model.dto.SysLogDTO;
import com.carole.secure.system.model.query.SysLogQuery;

/**
 * @author CaroLe
 * @Date 2024/3/20 22:07
 * @Description
 */
public interface SysLogService {

    /**
     * 分页获取日志列表
     *
     * @param sysLogQuery 分页信息
     * @return PageUtil
     */
    PageUtil<SysLogDTO> getLogInfoByPage(SysLogQuery sysLogQuery);

    /**
     * 删除日志信息
     *
     * @param id 日志Id
     */
    void deleteLogInfo(String id);
}
