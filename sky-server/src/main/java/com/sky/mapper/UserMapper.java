package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;


@Mapper
public interface UserMapper {


    //根据openid来查询用户
    @Select("select * from user where openid=#{openai}")
    User getByOpenid(String openid);

    //插入数据
    void insert(User user);

    @Select("select * from user where id=#{userId}")
    User getById(Long userId);


    //根据动态
    Integer countByMap(Map map);
}
