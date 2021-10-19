package com.city.hcy.mapper;


import com.city.hcy.model.Post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostMapper {
    int insert(Post paramPost);

    int updateByPrimaryKeySelective(Post paramPost);

    //更新最新回复时间
    int updateRecentReplyTime(@Param("postid") int paramInt);

    int updateCommentCount(@Param("postid") int paramInt);

    int updateCollectionCount(@Param("postid") int paramInt);

    int updateStarCount(@Param("postid") int paramInt);

    int updateistop(int paramInt);

    List<Post> selectListByAllWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2);

    List<Post> selectListByAllWithPageOrderByStar(@Param("start") int paramInt1, @Param("rows") int paramInt2);

    List<Post> selectListByAllWithPageOrderByCollection(@Param("start") int paramInt1, @Param("rows") int paramInt2);

    List<Post> selectListByAllWithPageDeleted(@Param("start") int paramInt1, @Param("rows") int paramInt2);

    int selectCountByAllDeleted();
    int selectCountByAll();

    List<Post> selectListByIdWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("userid") int paramInt3);

    int selectByIdCountByAll(@Param("userid") int paramInt);

    int inserthistory(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    int updatehistorytime(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    int insertcollection(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    int insertstar(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    int deletecollection(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    int deletestar(@Param("postid") int paramInt1, @Param("userid") int paramInt2);

    List<Post> selectHistoryListByAllWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("userid") int paramInt3);

    int selectHistoryCountByAll(@Param("userid") int paramInt);

    List<Post> selectCollectionListByAllWithPage(@Param("start") int paramInt1, @Param("rows") int paramInt2, @Param("userid") int paramInt3);

    int selectCollectionCountByAll(@Param("userid") int paramInt);

    List<Post> selectStarList(@Param("userid") int paramInt);

    List<Integer> selectpostid();

    List<Integer> selectpostidorderbytime();

    List<Integer> selectpostidOrderByComment();

    List<Post> selectListByPostid(int[] postid);

    List<Post> selectListByPostidOrderByTime(int[] postid);

    Post selectByPrimaryKey(Integer paramInteger);

    int deleteByPrimaryKey(Integer paramInteger);

    int deletepost(@Param("postid") Integer postid);

    @Select("SELECT postid FROM post WHERE istop=1")
    List<Integer> istoppostid();

    int updateDeleted(int[] postid);
}
