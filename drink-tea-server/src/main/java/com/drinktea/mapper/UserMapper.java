package com.drinktea.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.drinktea.entity.User;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void insert(User user);
}
