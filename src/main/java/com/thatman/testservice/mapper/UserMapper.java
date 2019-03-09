package com.thatman.testservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thatman.testservice.entity.User;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}