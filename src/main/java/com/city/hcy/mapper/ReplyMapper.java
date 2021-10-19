package com.city.hcy.mapper;


import com.city.hcy.model.Post;
import com.city.hcy.model.Reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReplyMapper {

    List<Reply> selectListByReplyid(int[] replyid);

    List<Reply> selectListByAllWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("postid") int paramInt3);

    int selectCountByAll(@Param("postid") int paramInt);

    List<Reply> selectListByIDWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("userid") int paramInt3);

    List<Reply> selectListByIDWithFrontPage(@Param("page") int paramInt2, @Param("postid") int paramInt3);

    int selectCountByID(@Param("userid") int paramInt);

    List<Reply> selectListByIDWithPageDeleted(@Param("start") int paramInt1, @Param("rows") int paramInt2);
    int selectCountDeleted();
    int insert(Reply paramReply);

    int insertreplystar(@Param("replyid") int paramInt1, @Param("userid") int paramInt2);

    int deletereplystar(@Param("replyid") int paramInt1, @Param("userid") int paramInt2);

    int updateStarCount(@Param("replyid") int paramInt);

    Reply selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(Reply paramReply);

    int updateByPrimaryKey(Reply paramReply);

    int updateReadByReplyostid(int[] replyid);

    int updateDeleted(int[] replyid);

    //查找指定userid和replyobject=0的数据
    List<Reply> selectListByuseridAndreplyobject(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("userid") int userid);

    int selectCountByIDAndreplyobject(@Param("userid") int userid);


    //修改楼层
    int updatefloor(@Param("replyid") int paramInt1);

    @Select("SELECT COUNT(*) FROM reply WHERE postid=#{postid} AND roleid=#{roleid}")
    int checkroleid(@Param("postid") int paramInt1, @Param("roleid") int paramInt2);

    @Select("SELECT * FROM reply WHERE postid=#{postid} AND roleid=#{roleid} LIMIT 1")
    Reply checkroleidinfo(@Param("postid") int paramInt1, @Param("roleid") int paramInt2);


    int deletereply(@Param("replyid") Integer replyid);


    @Select("SELECT r.userid,r.roleid  FROM post p INNER JOIN reply r ON r.`postid`= p.`postid`  WHERE p.postid=#{postid}")
    List<Reply>  alluserid(@Param("postid") int paramInt1);
}
