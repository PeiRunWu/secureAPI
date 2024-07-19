package com.carole.secure.toolbox.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carole.secure.toolbox.model.dto.DataSourceDTO;
import com.carole.secure.toolbox.model.pojo.DataSource;
import com.carole.secure.toolbox.model.query.DataSourceQuery;

/**
 * @author CaroLe
 * @Date 2024/7/17 17:39
 * @Description
 */
@Mapper
public interface DataSourceMapper {
    /**
     * 插入
     * 
     * @param dataSource dataSource
     */
    void insert(@Param("param") DataSource dataSource);

    /**
     * 分页获取日志列表
     *
     * @param dataSourceQuery 分页信息
     * @return PageUtil
     */
    List<DataSourceDTO> getDataSourceInfoByPage(@Param("param") DataSourceQuery dataSourceQuery);

    /**
     * 更新数据源信息
     * 
     * @param dataSource dataSource
     */
    void updateDataSourceInfo(@Param("param") DataSource dataSource);

    /**
     * 删除数据库连接
     *
     * @param id 主键Id
     */
    void deleteDataSourceInfo(String id);
}
