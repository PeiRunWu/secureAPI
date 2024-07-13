package com.carole.secure.system.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2024/3/25 21:38
 * @Description
 */
@Data
public class SysLogDTO {

    /**
     * id
     */
    private String id;

    /**
     * 菜单描述
     */
    private String operaDesc;

    /**
     * 操作模块
     */
    private String operaModule;

    /**
     * 主机名
     */
    private String hostName;

    /**
     * 请求地址
     */
    private String requestUri;

    /**
     * 请求模式
     */
    private String requestMethod;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 主机地址
     */
    private String hostAddress;

    /**
     * 是否内网
     */
    private Boolean innerIP;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
