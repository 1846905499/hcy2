package com.city.hcy.mapper;

import com.city.hcy.model.Manager;
import com.city.hcy.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {

    Manager login(String paramLong);

    void insert(Manager manager);
}
