package com.carole.secure.toolbox.service;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.toolbox.model.dto.DataSourceDTO;
import com.carole.secure.toolbox.model.query.DataSourceQuery;
import com.carole.secure.toolbox.model.vo.DataSourceVO;

/**
 * @author CaroLe
 * @Date 2024/7/16 15:58
 * @Description
 */
public interface DataSourceService {

    /**
     * 测试数据库连接
     *
     * @param dataSourceVO vo
     */
    void testConnection(DataSourceVO dataSourceVO);

    /**
     * 数据库连接
     *
     * @param dataSourceVO vo
     */
    void connection(DataSourceVO dataSourceVO);

    /**
     * 分页获取日志列表
     *
     * @param dataSourceQuery 分页信息
     * @return PageUtil
     */
    PageUtil<DataSourceDTO> getDataSourceInfoByPage(DataSourceQuery dataSourceQuery);

    /**
     * 更新数据库连接
     *
     * @param dataSourceVO vo
     */
    void updateDataSourceInfo(DataSourceVO dataSourceVO);

    /**
     * 删除数据库连接
     *
     * @param id 主键Id
     */
    void deleteDataSourceInfo(String id);
}
