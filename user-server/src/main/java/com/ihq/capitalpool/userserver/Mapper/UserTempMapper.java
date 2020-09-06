package com.ihq.capitalpool.userserver.Mapper;

import com.ihq.capitalpool.userserver.Entity.UserTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserTempMapper {
    int insert(UserTemp userTemp);

    @Select("select * from user_auth.user_temp where id = #{id}")
    UserTemp selectById(Integer id);
}
