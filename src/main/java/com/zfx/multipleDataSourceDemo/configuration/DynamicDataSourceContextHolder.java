package com.zfx.multipleDataSourceDemo.configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipleDataSourceDemo.demos.configuration
 * @ClassName: DynamicDataSourceContextHolder
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:19
 * @Version: 1.0
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    // 为每个线程维护变量，以避免影响其他线程
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     *  设置数据源
     * @param dataSourceType
     * @return void
     * @author zhangfuxing
     * @date 2023/6/14 17:21
     * @throws
    */
    public static void setDataSourceType(String dataSourceType) {
        DATASOURCE_CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     *  获得数据源
     * @param
     * @return String
     * @author zhangfuxing
     * @date 2023/6/14 17:21
     * @throws
    */
    public static String getDataSourceType() {
        return DATASOURCE_CONTEXT_HOLDER.get();
    }

    /**
     *  清空数据源
     * @param
     * @return void
     * @author zhangfuxing
     * @date 2023/6/14 17:21
     * @throws
    */
    public static void clearDataSourceType() {
        DATASOURCE_CONTEXT_HOLDER.remove();
    }
}
