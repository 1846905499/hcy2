package com.city.hcy.controller;

import com.city.hcy.model.Report;
import com.city.hcy.model.Unread;
import com.city.hcy.result.ResultList;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.UnreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/unread"})
@CrossOrigin({"*"})
public class UnreadController {
    @Autowired
    UnreadService unreadService;

    @RequestMapping({"/list/all"})
    @ResponseBody
    public ResultList<Unread> getListByAll(int userid) throws Throwable {
        ResultList<Unread> result = new ResultList();
        result.setList(unreadService.getListByAll(userid));
        result.setStatus(200);
        result.setMessage("取得某一用户未读消息");
        return result;
    }

    @RequestMapping({"/list/allwithoutunread"})
    @ResponseBody
    public ResultList<Unread> getListByAllWithoutUnread(int userid) throws Throwable {
        ResultList<Unread> result = new ResultList();
        result.setList(unreadService.getListByAllWithOutUnread(userid));
        result.setStatus(200);
        result.setMessage("取得某一用户所有消息");
        return result;
    }

    @RequestMapping({"/updateunread"})
    @ResponseBody
    public ResultOne<String> updateunread(int replyid) throws Throwable{

        ResultOne<String> res = new ResultOne<>();
        unreadService.updateunread(replyid);
        res.setStatus(200);
        res.setMessage("修改unread状态成功");


        return res;

    }


}
