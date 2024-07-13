package com.carole.secure.api.feign.pojo;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/11/2 22:25
 * @Description
 */
@Data
public class SysUser {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
