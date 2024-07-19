package com.carole.secure.toolbox.service.impl;

import java.sql.Connection;

import javax.annotation.Resource;

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

}
