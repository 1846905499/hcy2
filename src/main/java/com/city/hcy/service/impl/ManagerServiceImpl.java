package com.city.hcy.service.impl;

import com.city.hcy.mapper.ManagerMapper;
import com.city.hcy.model.Manager;
import com.city.hcy.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImpl  implements ManagerService {


    @Autowired
    ManagerMapper managerMapper=null;

    @Override
    public Manager login(String paramLong) throws Exception {
        return managerMapper.login(paramLong);
    }

    @Override
    public void add(Manager manager) {
        managerMapper.insert(manager);

    }
}
