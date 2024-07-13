package com.carole.secure.auth.model.pojo;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/10/16 22:12
 * @Description
 */
@Data
public class UserInfo {

    /**
     * 用户名称
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
