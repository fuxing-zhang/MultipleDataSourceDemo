package com.zfx.multipleDataSourceDemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipleDataSourceDemo.demos.configuration
 * @ClassName: DynamicDataSourceAspect
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:25
 * @Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class DynamicDataSourceAspect {

    @Pointcut("execution(public * com.zfx.multipleDataSourceDemo.web.mapper..*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 访问到目标方法的调用参数
        Object[] args = pjp.getArgs();
        // ------------------以上在目标方法之前织入------------------
        // 回到原来的目标方法，如果去掉此行，目标方法就不会执行
        Object returnObject = null;
        try {
            returnObject = pjp.proceed(args);
        } catch (Throwable exception) {
            log.error("执行异常信息为：{}", exception.getMessage(), exception);
            if (exception instanceof MyBatisSystemException) {
                // 数据源切换
                DynamicDataSourceContextHolder.setDataSourceType("mysqlDataSource2");
                // 切换后再次调用
                returnObject = pjp.proceed(args);
            }
        }
        // ------------------以下在目标方法之前织入------------------
        return returnObject;
    }
}
