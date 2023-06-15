package com.zfx.multipleDataSourceDemo.web.service;

import com.zfx.multipleDataSourceDemo.web.mapper.BankMapper;
import com.zfx.multipleDataSourceDemo.web.entity.Bank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipleDataSourceDemo.web.service
 * @ClassName: BankService
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:51
 * @Version: 1.0
 */
@Service
public class BankService {

    @Resource
    private BankMapper bankMapper;

    public Bank queryBank(Long bankId){
        return bankMapper.selectById(bankId);
    }
}

