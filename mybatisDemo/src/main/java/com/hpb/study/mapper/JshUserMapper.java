package com.hpb.study.mapper;

import com.hpb.study.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @Classname PersonMapper
 * @Description TODO
 * @Date 2020/2/6 14:14
 * @Created by CXR
 */
@Mapper
public interface JshUserMapper {

    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "username", column = "username")
    })
    @Select(value = "SELECT id,name FROM jsh_user WHERE username = #{username}")
    User findByUserName(@Param(value = "userName") String userName);
}
