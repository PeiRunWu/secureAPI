package com.carole.secure.system.model.vo;

import java.util.List;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/9/14 21:44
 * @Description 用户信息
 */
@Data
public class SysUserVO {

    /**
     * id
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
     * 密码
     */
    private String password;

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
     * 所属角色
     */
    private List<String> roleIds;

}
