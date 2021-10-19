package com.city.hcy.service;

import com.city.hcy.model.Manager;

public interface ManagerService {

    Manager login(String paramLong) throws Exception;

    void  add(Manager manager);
}
