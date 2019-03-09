package com.thatman.testservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    private String name;

    private String password;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer isDelete;

}