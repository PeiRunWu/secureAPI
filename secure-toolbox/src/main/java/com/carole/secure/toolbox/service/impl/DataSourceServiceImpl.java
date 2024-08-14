package com.carole.secure.toolbox.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.hutool.crypto.digest.DigestUtil;
import com.carole.secure.toolbox.model.dto.DataFiledDTO;
import com.carole.secure.toolbox.model.dto.DataTableDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carole.secure.common.exception.BaseException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.util.DbUtil;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.toolbox.mapper.DataSourceMapper;
import com.carole.secure.toolbox.model.dto.DataSourceDTO;
import com.carole.secure.toolbox.model.pojo.DataSource;
import com.carole.secure.toolbox.model.query.DataSourceQuery;
import com.carole.secure.toolbox.model.vo.DataSourceVO;
import com.carole.secure.toolbox.service.DataSourceService;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/7/16 15:58
 * @Description
 */
@Slf4j
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private DataSourceMapper dataSourceMapper;

    /**
     * 测试数据库连接
     *
     * @param dataSourceVO vo
     */
    @Override
    public void testConnection(DataSourceVO dataSourceVO) {
        Connection connection = null;
        try {
            connection =
                DbUtil.getConnection(dataSourceVO.getDriverType(), dataSourceVO.getIp(), dataSourceVO.getPort(),
                    dataSourceVO.getTableName(), dataSourceVO.getUsername(), dataSourceVO.getPassword());
        } catch (Exception e) {
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    /**
     * 数据库连接
     *
     * @param dataSourceVO vo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void connection(DataSourceVO dataSourceVO) {
        Connection connection = null;
        try {
            connection =
                DbUtil.getConnection(dataSourceVO.getDriverType(), dataSourceVO.getIp(), dataSourceVO.getPort(),
                    dataSourceVO.getTableName(), dataSourceVO.getUsername(), dataSourceVO.getPassword());
            String loginId = StpUtil.getLoginIdAsString();
            DataSource dataSource = new DataSource();
            BeanUtil.copyProperties(dataSourceVO, dataSource);
            dataSource.setLoginId(loginId);
            dataSourceMapper.insert(dataSource);
        } catch (Exception e) {
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    /**
     * 分页获取日志列表
     *
     * @param dataSourceQuery 分页信息
     * @return PageUtil
     */
    @Override
    public PageUtil<DataSourceDTO> getDataSourceInfoByPage(DataSourceQuery dataSourceQuery) {
        dataSourceQuery.setLoginId(StpUtil.getLoginIdAsString());
        return PageUtil.startPage(dataSourceQuery.getCurrent(), dataSourceQuery.getPageSize(),
            () -> dataSourceMapper.getDataSourceInfoByPage(dataSourceQuery));
    }

    /**
     * 更新数据库连接
     *
     * @param dataSourceVO vo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDataSourceInfo(DataSourceVO dataSourceVO) {
        Connection connection = null;
        try {
            connection =
                DbUtil.getConnection(dataSourceVO.getDriverType(), dataSourceVO.getIp(), dataSourceVO.getPort(),
                    dataSourceVO.getTableName(), dataSourceVO.getUsername(), dataSourceVO.getPassword());
            String loginId = StpUtil.getLoginIdAsString();
            DataSource dataSource = new DataSource();
            BeanUtil.copyProperties(dataSourceVO, dataSource);
            dataSource.setLoginId(loginId);
            dataSourceMapper.updateDataSourceInfo(dataSource);
        } catch (Exception e) {
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        } finally {
            DbUtil.closeConnection(connection);
        }
    }

    /**
     * 删除数据库连接
     *
     * @param id 主键Id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDataSourceInfo(String id) {
        dataSourceMapper.deleteDataSourceInfo(id);
    }

    /**
     * 获取所有数据源
     *
     * @return List
     */
    @Override
    public List<DataSourceDTO> getAllDataSource() {
        DataSourceQuery dataSourceQuery = DataSourceQuery.builder().loginId(StpUtil.getLoginIdAsString()).build();
        return dataSourceMapper.getDataSourceInfoByPage(dataSourceQuery);
    }

    /**
     * 获取当前库下所有表名 -
     *
     * @param id 库名Id
     * @return List
     */
    @Override
    public List<DataTableDTO> getTableInfo(String id) {
        DataSource dataSource = dataSourceMapper.getDataSourceInfoById(id);
        Connection connection = null;
        List<DataTableDTO> tableList = new ArrayList<>();
        try {
            connection = DbUtil.getConnection(dataSource.getDriverType(), dataSource.getIp(), dataSource.getPort(),
                dataSource.getTableName(), dataSource.getUsername(), dataSource.getPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(dataSource.getTableName(), null, null, new String[] {"TABLE"});
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                String remarks = resultSet.getString("REMARKS");
                tableList.add(DataTableDTO.builder().tableName(tableName).remark(remarks).build());
            }
        } catch (Exception e) {
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        } finally {
            DbUtil.closeConnection(connection);
        }
        return tableList;
    }

    /**
     * 获取当前表的字段信息
     *
     * @param dataSourceVO dataSourceVO
     * @return List
     */
    @Override
    public List<DataFiledDTO> getFiledInfo(DataSourceVO dataSourceVO) {
        DataSource dataSource = dataSourceMapper.getDataSourceInfoById(dataSourceVO.getId());
        Connection connection = null;
        List<DataFiledDTO> tableList = new ArrayList<>();
        try {
            connection = DbUtil.getConnection(dataSource.getDriverType(), dataSource.getIp(), dataSource.getPort(),
                dataSource.getTableName(), dataSource.getUsername(), dataSource.getPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, "%", dataSourceVO.getTableName(), "%");
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, dataSourceVO.getTableName());
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                String typeName = resultSet.getString("TYPE_NAME");
                String remarks = resultSet.getString("REMARKS");
                Long columnSize = resultSet.getLong("COLUMN_SIZE");
                boolean isAutoincrement = resultSet.getBoolean("IS_AUTOINCREMENT");
                int nullable = resultSet.getInt("NULLABLE");
                boolean isRequired = nullable != DatabaseMetaData.columnNullable;
                boolean isPrimaryKey = false;
                while (primaryKeys.next()) {
                    if (primaryKeys.getString("COLUMN_NAME").equals(columnName)) {
                        isPrimaryKey = true;
                        break;
                    }
                }
                tableList.add(DataFiledDTO.builder().columnName(columnName).typeName(typeName).remark(remarks)
                    .columnSize(columnSize).autoincrement(isAutoincrement).required(isRequired).primaryKey(isPrimaryKey)
                    .build());
            }
        } catch (Exception e) {
            throw new BaseException(ErrorType.DATA_SOURCE_CONNECT_ERROR);
        } finally {
            DbUtil.closeConnection(connection);
        }
        return tableList;
    }
}
