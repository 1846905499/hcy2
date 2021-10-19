package com.city.hcy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper


public interface ManageroperateMapper {
    @Options(useGeneratedKeys = false)
    @Insert("insert into Manageroperate(managerid, wholesql) values (#{managerid}, #{wholesql})")
    int insert(@Param("managerid") int managerid, @Param("wholesql")String wholesql);
}
