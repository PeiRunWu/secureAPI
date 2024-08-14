package com.carole.secure.toolbox.controller;

import javax.annotation.Resource;

import com.carole.secure.toolbox.model.dto.DataFiledDTO;
import com.carole.secure.toolbox.model.dto.DataTableDTO;
import org.springframework.web.bind.annotation.*;

import com.carole.secure.common.util.PageUtil;
import com.carole.secure.elasticsearch.annotation.OperationLog;
import com.carole.secure.elasticsearch.enums.OperaModuleEnum;
import com.carole.secure.toolbox.model.dto.DataSourceDTO;
import com.carole.secure.toolbox.model.query.DataSourceQuery;
import com.carole.secure.toolbox.model.vo.DataSourceVO;
import com.carole.secure.toolbox.service.DataSourceService;

import java.util.List;

/**
 * @author CaroLe
 * @Date 2024/7/16 15:57
 * @Description 数据源管理
 */
@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Resource
    private DataSourceService dataSourceService;

    /**
     * 测试数据库连接
     * 
     * @param dataSourceVO vo
     */
    @PostMapping("/testConnection")
    public void testConnection(@RequestBody DataSourceVO dataSourceVO) {
        dataSourceService.testConnection(dataSourceVO);
    }

    /**
     * 数据库连接
     * 
     * @param dataSourceVO vo
     */
    @PostMapping("/connection")
    @OperationLog(operaDesc = "数据库连接", operaModule = OperaModuleEnum.DATA_SOURCE)
    public void connection(@RequestBody DataSourceVO dataSourceVO) {
        dataSourceService.connection(dataSourceVO);
    }

    /**
     * 分页获取数据源列表
     *
     * @param dataSourceQuery 分页信息
     * @return PageUtil
     */
    @GetMapping("/getDataSourceInfoByPage")
    public PageUtil<DataSourceDTO> getDataSourceInfoByPage(DataSourceQuery dataSourceQuery) {
        return dataSourceService.getDataSourceInfoByPage(dataSourceQuery);
    }

    /**
     * 更新数据库连接
     * 
     * @param dataSourceVO vo
     */
    @PutMapping("/updateDataSourceInfo")
    @OperationLog(operaDesc = "更新数据库连接", operaModule = OperaModuleEnum.DATA_SOURCE)
    public void updateDataSourceInfo(@RequestBody DataSourceVO dataSourceVO) {
        dataSourceService.updateDataSourceInfo(dataSourceVO);
    }

    /**
     * 删除数据库连接
     * 
     * @param id 主键Id
     */
    @DeleteMapping("/deleteDataSourceInfo/{id}")
    @OperationLog(operaDesc = "删除数据库连接", operaModule = OperaModuleEnum.DATA_SOURCE)
    public void deleteDataSourceInfo(@PathVariable("id") String id) {
        dataSourceService.deleteDataSourceInfo(id);
    }

    /**
     * 获取所有数据源
     * 
     * @return List
     */
    @GetMapping("/getAllDataSource")
    public List<DataSourceDTO> getAllDataSource() {
        return dataSourceService.getAllDataSource();
    }

    /**
     * 获取当前库下所有表名 -
     * 
     * @param id 库名Id
     * @return List
     */
    @GetMapping("/getTableInfo/{id}")
    public List<DataTableDTO> getTableInfo(@PathVariable("id") String id) {
        return dataSourceService.getTableInfo(id);
    }

    /**
     * 获取当前表的字段信息
     * 
     * @param dataSourceVO dataSourceVO
     * @return List
     */
    @PostMapping("/getFiledInfo")
    public List<DataFiledDTO> getFiledInfo(@RequestBody DataSourceVO dataSourceVO) {
        return dataSourceService.getFiledInfo(dataSourceVO);
    }

}
