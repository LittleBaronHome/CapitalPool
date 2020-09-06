package com.ihq.capitalpool.userserver.Mapper;

import com.ihq.capitalpool.userserver.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into user_auth.user(username, name, email, phone, password) " +
            "select username, name, email, phone, password from user_auth.user_temp where id = #{id};")
    int insertFromTemp(Integer id);

    @Select("select * from user_auth.user where email = #{email};")
    User selectByEmail(String email);

    @Select("select * from user_auth.user where username = #{username} and password = #{password};")
    User selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    @Select("select * from user_auth.user where uuid = #{uuid};")
    User selectByUUID(String uuid);
}
