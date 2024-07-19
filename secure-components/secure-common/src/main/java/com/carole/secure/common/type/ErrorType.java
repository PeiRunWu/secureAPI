package com.carole.secure.common.type;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:38
 * @Description
 */
public class ErrorType extends StatusType {

    public static final ErrorType SERVICE_ERROR = new ErrorType(202, "服务异常");

    public static final ErrorType PARSING_ERROR = new ErrorType(203, "解析异常");

    public static final ErrorType USERNAME_IS_EXIST = new ErrorType(204, "用户名已存在");

    public static final ErrorType ROLE_NAME_IS_EXIST = new ErrorType(205, "角色昵称已存在");

    public static final ErrorType MENU_NAME_IS_EXIST = new ErrorType(206, "菜单名称已存在");

    public static final ErrorType FILE_UPLOAD_FAILED = new ErrorType(207, "文件上传失败");

    public static final ErrorType FILE_DELETE_FAILED = new ErrorType(208, "文件删除失败");

    public static final ErrorType ES_LOG_FAILED = new ErrorType(209, "Elasticsearch 日志保存异常");

    public static final ErrorType MENU_BIND_ROLE = new ErrorType(210, "该菜单绑定了角色");

    public static final ErrorType MENU_HAS_CHILDREN = new ErrorType(211, "该菜单含有子菜单");

    public static final ErrorType FILE_TYPE_ERROR = new ErrorType(212, "文件类型解析错误");

    public static final ErrorType USER_NAME_NOT_EXIT = new ErrorType(213, "用户名不存在");

    public static final ErrorType ACCOUNT_STOP = new ErrorType(214, "账号已停用");

    public static final ErrorType PASSWORD_ERROR = new ErrorType(215, "密码错误");

    public static final ErrorType USER_BIND_ROLE = new ErrorType(216, "该角色绑定了用户");

    public static final ErrorType LOGIN_AUTH = new ErrorType(217, "用户未登陆");

    public static final ErrorType DRIVER_TYPE_ERROR = new ErrorType(218, "未查询到数据库类型");

    public static final ErrorType DATA_SOURCE_CONNECT_ERROR = new ErrorType(219, "数据库连接失败");

    public ErrorType(Integer code, String msg) {
        super(code, msg);
    }
}
