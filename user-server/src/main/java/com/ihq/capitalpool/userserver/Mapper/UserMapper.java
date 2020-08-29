package com.ihq.capitalpool.userserver.Mapper;

import com.ihq.capitalpool.userserver.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int addUser(User user);

    int updateUserStatus(Integer id);
}
