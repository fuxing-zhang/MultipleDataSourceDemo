package com.zfx.multipleDataSourceDemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipleDataSourceDemo.demos.configuration
 * @ClassName: DynamicDataSource
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:17
 * @Version: 1.0
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = DynamicDataSourceContextHolder.getDataSourceType();
        log.info("Current DataSource is {}!", dataSourceType);
        return dataSourceType;
    }
}
