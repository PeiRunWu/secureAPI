package com.carole.secure.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CaroLe
 * @Date 2023/9/16 21:38
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUtil<T> {

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 当前页大小
     */
    private Integer pageSize;

    /**
     * 总数
     */
    private Long total;

    /**
     * 当前页的数量
     */
    private Integer size;

    /**
     * 分页数据
     */
    private List<T> records;

    /**
     * 开始分页
     * 
     * @param current 当前页
     * @param pageSize 当前页大小
     * @param supplier 函数式表达式
     * @return PageUtil
     */
    public static <T> PageUtil<T> startPage(Integer current, Integer pageSize, Supplier<List<T>> supplier) {
        PageHelper.startPage(current, pageSize);
        List<T> data = supplier.get();
        if (CollectionUtil.isEmpty(data)) {
            data = new ArrayList<>();
        }
        PageInfo<T> pageInfo = new PageInfo<>(data);
        PageHelper.clearPage();
        return new PageUtil<T>(current, pageSize, pageInfo.getTotal(), pageInfo.getSize(), pageInfo.getList());
    }

    /**
     * 开始分页
     * 
     * @param pageInfo pageInfo信息
     * @return PageUtil
     */
    public static <T> PageUtil<T> startPage(PageInfo<T> pageInfo) {
        return new PageUtil<T>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getSize(),
            pageInfo.getList());
    }

}
