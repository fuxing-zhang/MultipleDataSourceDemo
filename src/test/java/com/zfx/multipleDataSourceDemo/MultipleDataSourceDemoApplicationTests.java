package com.zfx.multipleDataSourceDemo;

import com.zfx.multipleDataSourceDemo.web.entity.Bank;
import com.zfx.multipleDataSourceDemo.web.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class MultipleDataSourceDemoApplicationTests {

    @Resource
    private BankService bankService;

    @Test
    void test(){
        Bank bank = bankService.queryBank(1L);
        log.info("Bank = {}", bank);
    }
}
