package com.carole.secure.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2024/7/17 15:24
 * @Description
 */
@Getter
@AllArgsConstructor
public enum DriverTypeEnum {
    MYSQL(0, "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s");

    private final Integer driverType;

    private final String driverClass;

    private final String url;

    public static DriverTypeEnum getDriverTypeEnumByType(Integer driverType) {
        for (DriverTypeEnum driverTypeEnum : DriverTypeEnum.values()) {
            if (driverTypeEnum.getDriverType().equals(driverType)) {
                return driverTypeEnum;
            }
        }
        return null;
    }
}
