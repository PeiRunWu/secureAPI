package com.carole.secure.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2023/10/15 23:31
 * @Description
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    /**
     * NULL
     */
    NULL("", ""),
    /**
     * OSS
     */
    OSS("oss", "ossServiceImpl"),
    /**
     * COS
     */
    COS("cos", "cosServiceImpl"),
    /**
     * MINIO
     */
    MINIO("minio", "minioServiceImpl");

    /**
     * 类型
     */
    private final String fileType;

    /**
     * 实现类
     */
    private final String fileInterface;

    public static FileTypeEnum getFileTypeEnumByType(String fileType) {
        for (FileTypeEnum value : FileTypeEnum.values()) {
            if (value.fileType.equals(fileType)) {
                return value;
            }
        }
        return FileTypeEnum.NULL;
    }
}
