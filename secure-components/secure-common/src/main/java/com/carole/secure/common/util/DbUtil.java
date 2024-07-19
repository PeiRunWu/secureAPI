package com.carole.secure.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.carole.secure.common.enums.DriverTypeEnum;
import com.carole.secure.common.exception.BaseException;
import com.carole.secure.common.type.ErrorType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/19 9:39
 * @Description
 */
@Slf4j
public class DbUtil {

    /**
     * 获取连接
     * 
     * @param driverType 驱动类型
     * @param ip ip地址
     * @param port 端口
     * @param tableName 库名
     * @param userName 用户名
     * @param password 密码
     * @return Connection
     */
    public static Connection getConnection(Integer driverType, String ip, String port, String tableName,
        String userName, String password) {
        try {
            DriverTypeEnum driverTypeEnum = DriverTypeEnum.getDriverTypeEnumByType(driverType);
            if (driverTypeEnum == null) {
                throw new BaseException(ErrorType.DRIVER_TYPE_ERROR);
            }
            String url = String.format(driverTypeEnum.getUrl(), ip, port, tableName);
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            log.error("连接失败:{}", e.getMessage());
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        }
    }

    /**
     * 关闭连接池
     * 
     * @param connection connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("关闭数据库连接失败....");
            }
        }
    }
}
