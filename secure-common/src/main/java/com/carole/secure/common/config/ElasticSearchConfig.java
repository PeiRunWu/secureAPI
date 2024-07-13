package com.carole.secure.common.config;

import javax.annotation.Resource;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carole.secure.common.properties.ElasticProperties;

/**
 * @author CaroLe
 * @Date 2023/9/17 15:33
 * @Description
 */
@Configuration
public class ElasticSearchConfig {

    @Resource
    private ElasticProperties elasticProperties;

    /**
     * 配置请求选项
     */
    public static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
            new HttpHost(elasticProperties.getHostname(), elasticProperties.getPort(), elasticProperties.getScheme())));
    }

}
