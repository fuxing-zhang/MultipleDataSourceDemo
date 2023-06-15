package com.zfx.multipleDataSourceDemo.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright (c) 2022
 *
 * @ProjectName: MultipleDataSourceDemo
 * @Package: com.zfx.multipledatasourcedemo.demos.web.entity
 * @ClassName: Bank
 * @Author: zhangfuxing
 * @Description:
 * @Date: 2023/6/14 17:09
 * @Version: 1.0
 */
@Data
@TableName("ec_bank")
public class Bank implements Serializable {

    @TableId
    private Long bankId;

    private Long parentId;

    private String bankName;

    private String bankCode;

    private String comments;

    private String state;

    private Date stateDate;

    private String bankCard;

    private String verifyMethod;
}
