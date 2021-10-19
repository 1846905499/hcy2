package com.city.hcy.service.impl;



import com.city.hcy.mapper.PostMapper;
import com.city.hcy.model.Post;
import com.city.hcy.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
//@Cacheable(value = "usercache")
public class PostServiceImpl implements PostService {
  @Autowired
  private PostMapper postMapper = null;
  
  public void add(Post record) throws Exception {

    this.postMapper.insert(record);
  }
  
  public void addhistory(Integer postid, Integer userid) throws Exception {
    this.postMapper.inserthistory(postid.intValue(), userid.intValue());
  }
  
  public void updatehistorytime(Integer postid, Integer userid) throws Exception {
    this.postMapper.updatehistorytime(postid.intValue(), userid.intValue());
  }
  
  public void addcollection(Integer postid, Integer userid) throws Exception {
    this.postMapper.insertcollection(postid.intValue(), userid.intValue());
  }
  
  public void addstar(Integer postid, Integer userid) throws Exception {
    this.postMapper.insertstar(postid.intValue(), userid.intValue());
  }
  
  public void deletestar(Integer postid, Integer userid) throws Exception {
    this.postMapper.deletestar(postid.intValue(), userid.intValue());
  }
  
  public void deletecollection(Integer postid, Integer userid) throws Exception {
    this.postMapper.deletecollection(postid.intValue(), userid.intValue());
  }
  
  public void modify(Post record) throws Exception {
    this.postMapper.updateByPrimaryKeySelective(record);
  }
  
  public void modifyrecenttime(int postid) {
    this.postMapper.updateRecentReplyTime(postid);
  }
  
  public void modifycommentcount(int postid) {
    this.postMapper.updateCommentCount(postid);
  }
  
  public void modifyCollectionCount(int postid) {
    this.postMapper.updateCollectionCount(postid);
  }
  
  public void modifystarcount(int postid) {
    this.postMapper.updateStarCount(postid);
  }
  
  public void modifyistop(int postid) {
    this.postMapper.updateistop(postid);
  }
  
  public List<Post> getUserStarList(int userid) throws Exception {
    return this.postMapper.selectStarList(userid);
  }
  
  public List<Post> getListByIdWithPage(int rows, int page, int userid) throws Exception {
    return this.postMapper.selectListByIdWithPage(rows * (page - 1), rows, userid);
  }
  
  public int getCountById(int userid) throws Exception {
    return this.postMapper.selectByIdCountByAll(userid);
  }
  
  public int getPageCountById(int rows, int userid) throws Exception {
    int pageCount = 0;
    int count = getCountById(userid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }
  
  public List<Post> getListByAllWithPageOrderByCollection(int rows, int page) throws Exception {
    return this.postMapper.selectListByAllWithPageOrderByCollection(rows * (page - 1), rows);
  }
  
  public List<Post> getListByAllWithPageOrderByStar(int rows, int page) throws Exception {
    return this.postMapper.selectListByAllWithPageOrderByStar(rows * (page - 1), rows);
  }
  
  public List<Post> getListByAllWithPage(int rows, int page) throws Exception {
    return this.postMapper.selectListByAllWithPage(rows * (page - 1), rows);
  }
  
  public int getCountByAll() throws Exception {
    return this.postMapper.selectCountByAll();
  }
  
  public int getPageCountByAll(int rows) throws Exception {
    int pageCount = 0;
    int count = getCountByAll();
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }

  @Override
  public List<Post> getListByAllWithPageDeleted(int rows, int page) throws Exception {
    return postMapper.selectListByAllWithPageDeleted(rows * (page - 1), rows);
  }

  @Override
  public int getCountByAllDeleted() throws Exception {
    return postMapper.selectCountByAllDeleted();
  }

  @Override
  public int getPageCountByAllDeleted(int rows ) throws Exception {
    int pageCount = 0;
    int count = getCountByAllDeleted();
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    }
    return pageCount;
  }

  public List<Post> getCollectionListByAllWithPage(int rows, int page, int userid) throws Exception {
    return this.postMapper.selectCollectionListByAllWithPage(rows * (page - 1), rows, userid);
  }
  
  public int getCollectionCountByAll(int userid) throws Exception {
    return this.postMapper.selectCollectionCountByAll(userid);
  }
  
  public int getCollectionPageCountByAll(int rows, int userid) throws Exception {
    int pageCount = 0;
    int count = getCollectionCountByAll(userid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }
  
  public List<Post> getHistoryListByAllWithPage(int rows, int page, int userid) throws Exception {
    return this.postMapper.selectHistoryListByAllWithPage(rows * (page - 1), rows, userid);
  }
  
  public int getHistoryCountByAll(int userid) throws Exception {
    return this.postMapper.selectHistoryCountByAll(userid);
  }
  
  public int getHistoryPageCountByAll(int rows, int userid) throws Exception {
    int pageCount = 0;
    int count = getHistoryCountByAll(userid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }
  
  public List<Integer> getpostid() {
    return this.postMapper.selectpostid();
  }

  @Override
  public List<Integer> getpostidorderbytime() {
    return postMapper.selectpostidorderbytime();
  }

  public List<Integer> getpostidOrderByComment() {
    return this.postMapper.selectpostidOrderByComment();
  }
  
  public List<Post> getListByPostid(int[] postid) {
    return this.postMapper.selectListByPostid(postid);
  }

  @Override
  public List<Post> getListByPostidOrderBytime(int[] postid) {
    return this.postMapper.selectListByPostidOrderByTime(postid);
  }

  @Override
  public void deletepost(int postid) {
    postMapper.deletepost(postid);
  }

  @Override
  public List<Integer> istoppostid() {
    return postMapper.istoppostid();
  }

  @Override
  public void updateDeleted(int[] postid) {
    postMapper.updateDeleted(postid);
  }
}
