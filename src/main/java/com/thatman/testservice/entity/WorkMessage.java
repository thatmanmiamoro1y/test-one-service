package com.thatman.testservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: thatman
 * @Date: 2019/3/21 21:45
 * @Version 1.0
 */
@Data
@TableName(value = "tb_work_message")
public class WorkMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private String id;
    private String workType;
    private String workComment;
    private Date workReceiveTime;
    private Date workSendTime;
    private Date workStartTime;
    private Date workEndTime;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String isDelete;


}
