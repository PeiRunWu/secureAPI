package com.carole.secure.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.carole.secure.common.context.ElasticContext;
import com.carole.secure.common.exception.DataException;
import com.carole.secure.common.type.ErrorType;
import com.carole.secure.common.util.PageUtil;
import com.carole.secure.elasticsearch.config.ElasticSearchConfig;
import com.carole.secure.system.model.dto.SysLogDTO;
import com.carole.secure.system.model.query.SysLogQuery;
import com.carole.secure.system.service.SysLogService;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2024/3/20 22:07
 * @Description
 */
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 分页获取日志列表
     *
     * @param sysLogQuery 分页信息
     * @return PageUtil
     */
    @Override
    public PageUtil<SysLogDTO> getLogInfoByPage(SysLogQuery sysLogQuery) {
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.sort("createTime", SortOrder.DESC);
            sourceBuilder.from((sysLogQuery.getCurrent() - 1) * sysLogQuery.getPageSize());
            sourceBuilder.size(sysLogQuery.getPageSize());
            SearchRequest searchRequest = new SearchRequest(ElasticContext.SYS_OPERATION_LOG_INDEX);
            searchRequest.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            List<SysLogDTO> sysLogDTOList = new ArrayList<>();
            if (ObjectUtil.equals(response.status().getStatus(), HttpStatus.OK.value())) {
                SearchHit[] hitsHits = response.getHits().getHits();
                for (SearchHit hitsHit : hitsHits) {
                    SysLogDTO sysLogDTO = JSON.parseObject(hitsHit.getSourceAsString(), SysLogDTO.class);
                    sysLogDTO.setId(hitsHit.getId());
                    sysLogDTOList.add(sysLogDTO);
                }
            }
            PageInfo<SysLogDTO> pageInfo = new PageInfo<>(sysLogDTOList);
            return PageUtil.startPage(pageInfo);
        } catch (Exception e) {
            log.error("Elastic 查询日志列表数据失败", e);
            throw new DataException(ErrorType.SERVICE_ERROR);
        }
    }

    /**
     * 删除日志信息
     *
     * @param id 日志Id
     */
    @Override
    public void deleteLogInfo(String id) {
        try {
            DeleteRequest deleteRequest = new DeleteRequest(ElasticContext.SYS_OPERATION_LOG_INDEX, id);
            restHighLevelClient.delete(deleteRequest, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (Exception e) {
            log.error("Elastic 删除日志信息失败", e);
            throw new DataException(ErrorType.SERVICE_ERROR);
        }
    }
}
