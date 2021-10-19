package com.city.hcy.mapper;

import com.city.hcy.model.Unread;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UnreadMapper {
    @Select("SELECT * FROM unread  WHERE `userid`=#{userid} and unread=1")
    List<Unread> selectListByAllWithPage(@Param("userid") int userid);

    @Select("SELECT * FROM unread  WHERE `userid`=#{userid} ")
    List<Unread> selectListByAllWithOutUnread(@Param("userid") int userid);

    @Update("UPDATE  unread  SET `unread` = '0' WHERE `replyid` = #{replyid}")
    int updateunread(@Param("replyid") int replyid);
}
