package com.city.hcy.mapper;

import com.city.hcy.model.Post;
import com.city.hcy.model.Reply;
import com.city.hcy.model.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper {
    //添加举报
    int insert(Report report);



    //分页查询举报的帖子
    List<Report> selectListByAllWithPage(@Param("start") int start, @Param("rows") int rows);


    //帖子总数
    @Select(" select count(*)  from report where replyid=0 and deleted=0")
    int selectCountByAll();

    //分页查询举报的回帖
    List<Report> selectReplyListByAllWithPage(@Param("start") int start, @Param("rows") int rows);
    //回帖总数
    @Select(" select count(*)  from report where postid=0 and deleted=0")
    int selectReplyCountByAll();

    //处理完毕回帖
    List<Report> selectReplyListByAllWithPageover(@Param("start") int start, @Param("rows") int rows);
    //回帖总数
    @Select(" select count(*)  from report where postid=0 and deleted=1")
    int overreply();

    //处理完毕的帖子
    List<Report> selectPostListByAllWithPageover(@Param("start") int start, @Param("rows") int rows);
    //回帖总数
    @Select(" select count(*)  from report where postid=0 and deleted=1")
    int overpost();

    int delete(@Param("reportid") Integer reportid);
}

