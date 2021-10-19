package com.city.hcy.service;


import com.city.hcy.model.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    void add(Reply paramReply) throws Exception;

    void modifystarcount(int paramInt);

    List<Reply> getListByAllWithPage(int paramInt1, int paramInt2, int paramInt3) throws Exception;

    int getCountByAll(int paramInt) throws Exception;

    int getPageCountByAll(int paramInt1, int paramInt2) throws Exception;

    List<Reply> getListByAllWithPageDeleted(int paramInt1, int paramInt2) throws Exception;

    int getCountByAllDeleted() throws Exception;

    int getPageCountByAllDeleted(int paramInt1) throws Exception;

    List<Reply> getListByIDWithPage(int paramInt1, int paramInt2, int paramInt3) throws Exception;

    List<Reply> getListByIDWithFrontPage(int rows, int page,int postid);

    int getCountByID(int paramInt) throws Exception;

    int getPageCountByID(int paramInt1, int paramInt2) throws Exception;

    void addreplystar(Integer paramInteger1, Integer paramInteger2) throws Exception;

    void deletereplystar(Integer paramInteger1, Integer paramInteger2) throws Exception;


    void updateReadByReplyostid(int[] replyid);

    void updateDeleted(int[] replyid);

    List<Reply> getListByuseridAndreplyobject(int userid, int rows, int page);

    int getCountByIDAndreplyobject(int userid) throws Exception;

    int getPageCountByIDAndreplyobject( int rows,int userid) throws Exception;

    List<Reply> getListByReplyid(int[] replyid);

    void updatefloor(int replyid);

    int checkroleid(int postid,int roleid);

    Reply checkroleidinfo(int postid,int roleid);

    void deletereply(int replyid);

    List alluserid(@Param("postid") int paramInt1);
}
