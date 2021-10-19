package com.city.hcy.service;


import com.city.hcy.model.Post;
import java.util.List;

public interface PostService {
  void add(Post paramPost) throws Exception;
  
  void addhistory(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void updatehistorytime(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void addcollection(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void addstar(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void deletestar(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void deletecollection(Integer paramInteger1, Integer paramInteger2) throws Exception;
  
  void modify(Post paramPost) throws Exception;
  
  void modifyrecenttime(int paramInt);
  
  void modifycommentcount(int paramInt);
  
  void modifyCollectionCount(int paramInt);
  
  void modifystarcount(int paramInt);
  
  void modifyistop(int paramInt);
  
  List<Post> getUserStarList(int paramInt) throws Exception;
  
  List<Post> getListByIdWithPage(int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  int getCountById(int paramInt) throws Exception;
  
  int getPageCountById(int paramInt1, int paramInt2) throws Exception;
  
  List<Post> getListByAllWithPageOrderByCollection(int paramInt1, int paramInt2) throws Exception;
  
  List<Post> getListByAllWithPageOrderByStar(int paramInt1, int paramInt2) throws Exception;
  
  List<Post> getListByAllWithPage(int paramInt1, int paramInt2) throws Exception;
  
  int getCountByAll() throws Exception;
  
  int getPageCountByAll(int paramInt) throws Exception;

  List<Post> getListByAllWithPageDeleted(int paramInt1, int paramInt2) throws Exception;

  int getCountByAllDeleted() throws Exception;

  int getPageCountByAllDeleted(int paramInt) throws Exception;

  
  List<Post> getCollectionListByAllWithPage(int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  int getCollectionCountByAll(int paramInt) throws Exception;
  
  int getCollectionPageCountByAll(int paramInt1, int paramInt2) throws Exception;
  
  List<Post> getHistoryListByAllWithPage(int paramInt1, int paramInt2, int paramInt3) throws Exception;
  
  int getHistoryCountByAll(int paramInt) throws Exception;
  
  int getHistoryPageCountByAll(int paramInt1, int paramInt2) throws Exception;
  
  List<Integer> getpostid();

  List<Integer> getpostidorderbytime();
  
  List<Integer> getpostidOrderByComment();
  
  List<Post> getListByPostid(int[] postid);
  List<Post> getListByPostidOrderBytime(int[] postid);

  void deletepost(int postid);

  List<Integer> istoppostid();

  void updateDeleted(int[] postid);
}
