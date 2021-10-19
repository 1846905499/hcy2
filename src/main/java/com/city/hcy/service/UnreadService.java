package com.city.hcy.service;

import com.city.hcy.model.Report;
import com.city.hcy.model.Unread;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnreadService {
    List<Unread> getListByAll(int userid) throws Exception;
    List<Unread> getListByAllWithOutUnread(int userid) throws Exception;

    int updateunread(int replyid);

}
