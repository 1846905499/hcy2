package com.city.hcy.mapper;

import com.city.hcy.model.Appdetails;
import com.city.hcy.model.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AppdetailsMapper {
    @Select("select * from appdetails")
    Appdetails select();
}
