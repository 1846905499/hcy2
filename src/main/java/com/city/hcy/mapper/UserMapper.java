package com.city.hcy.mapper;


import com.city.hcy.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User selectByPrimaryKey(String paramLong);


    //查询当前注册人数
    @Select("select count(userid) from user")
    int selectnumber();

    int deleteByPrimaryKey(Integer paramInteger);

    int insert(User user);


    int updateByPrimaryKey(User user);

//  UPDATE `root123`.`user` SET `silencetime` = '2021-09-08 13:50:29' WHERE `userid` = '5';


    int insertSelective(User paramUser);

    int updateByPrimaryKeySelective(User paramUser);


}
