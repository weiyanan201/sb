package com.wei.eduyang.mapper;

import com.wei.eduyang.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findUserByName(@Param(value="userName")String userName);
}
