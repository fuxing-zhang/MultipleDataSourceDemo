package com.zfx.multipleDataSourceDemo.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfx.multipleDataSourceDemo.web.entity.Bank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipledatasourcedemo.demos.web.dao
 * @ClassName: BankDao
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:10
 * @Version: 1.0
 */
@Mapper
public interface BankMapper extends BaseMapper<Bank> {
    /**
     *  根据id查询
     * @param bankId
     * @return Bank
     * @author zhangfuxing
     * @date 2023/6/14 17:13
     * @throws
    */
    Bank queryById(@Param("bankId") Long bankId);
}
