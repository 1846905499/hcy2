package com.city.hcy.service.impl;

import com.city.hcy.mapper.UnreadMapper;
import com.city.hcy.model.Unread;
import com.city.hcy.service.UnreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
//@Cacheable(value = "usercache")
public class UnreadServiceImpl  implements UnreadService {
    @Autowired
    UnreadMapper um;
    @Override
    public List<Unread> getListByAll(int userid) throws Exception {
        return um.selectListByAllWithPage(userid);
    }

    @Override
    public List<Unread> getListByAllWithOutUnread(int userid) throws Exception {
        return um.selectListByAllWithOutUnread(userid);
    }

    @Override
    public int updateunread(int replyid) {
        return um.updateunread(replyid);
    }
}
