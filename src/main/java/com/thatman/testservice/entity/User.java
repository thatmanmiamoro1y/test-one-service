package com.thatman.testservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@TableName(value = "user")
@Data
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private String id;

    private String name;

    private String password;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer isDelete;
}