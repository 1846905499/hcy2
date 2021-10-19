package com.city.hcy.service.impl;



import com.city.hcy.mapper.ReplyMapper;
import com.city.hcy.model.Reply;
import com.city.hcy.service.ReplyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
//@Cacheable(value = "usercache")
public class ReplyServiceImpl implements ReplyService {
  @Autowired
  private ReplyMapper replyMapper = null;
  
  public void add(Reply record) throws Exception {
    this.replyMapper.insert(record);
  }
  
  public void modifystarcount(int replyid) {
    this.replyMapper.updateStarCount(replyid);
  }
  
  public List<Reply> getListByAllWithPage(int rows, int page, int postid) throws Exception {
    return this.replyMapper.selectListByAllWithPage(rows * (page - 1), rows, postid);
  }
  
  public int getCountByAll(int postid) throws Exception {
    return this.replyMapper.selectCountByAll(postid);
  }
  
  public int getPageCountByAll(int rows, int postid) throws Exception {
    int pageCount = 0;
    int count = getCountByAll(postid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }

  @Override
  public List<Reply> getListByAllWithPageDeleted(int rows, int page) throws Exception {
    return this.replyMapper.selectListByIDWithPageDeleted(rows * (page - 1), rows);
  }

  @Override
  public int getCountByAllDeleted() throws Exception {
    return replyMapper.selectCountDeleted();
  }

  @Override
  public int getPageCountByAllDeleted(int rows) throws Exception  {
    int pageCount = 0;
    int count = getCountByAllDeleted();
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    }
    return pageCount;
  }

  public List<Reply> getListByIDWithPage(int rows, int page, int userid) throws Exception {
    return this.replyMapper.selectListByIDWithPage(rows * (page - 1), rows, userid);
  }

  @Override
  public List<Reply> getListByIDWithFrontPage(int rows,int page, int postid) {
    return this.replyMapper.selectListByIDWithFrontPage(rows * (page - 1), postid);
  }

  public int getCountByID(int userid) throws Exception {
    return this.replyMapper.selectCountByID(userid);
  }
  
  public int getPageCountByID(int rows, int userid) throws Exception {
    int pageCount = 0;
    int count = getCountByID(userid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    } 
    return pageCount;
  }
  
  public void addreplystar(Integer replyid, Integer userid) throws Exception {
    this.replyMapper.insertreplystar(replyid.intValue(), userid.intValue());
  }
  
  public void deletereplystar(Integer replyid, Integer userid) throws Exception {
    this.replyMapper.deletereplystar(replyid.intValue(), userid.intValue());
  }

  @Override
  public void updateReadByReplyostid(int[] replyid) {
    replyMapper.updateReadByReplyostid(replyid);
  }

  @Override
  public void updateDeleted(int[] replyid) {
    replyMapper.updateDeleted(replyid);
  }

  @Override
  public List<Reply> getListByuseridAndreplyobject( int rows, int page,int userid) {
    return replyMapper.selectListByuseridAndreplyobject(rows * (page - 1), rows,userid);
  }

  @Override
  public int getCountByIDAndreplyobject(int userid) throws Exception {
    return replyMapper.selectCountByIDAndreplyobject(userid);
  }

  @Override
  public int getPageCountByIDAndreplyobject(int rows, int userid) throws Exception {
    int pageCount = 0;
    int count = getCountByIDAndreplyobject(userid);
    if (count % rows == 0) {
      pageCount = count / rows;
    } else {
      pageCount = count / rows + 1;
    }
    return pageCount;
  }

  @Override
  public List<Reply> getListByReplyid(int[] replyid) {
    return replyMapper.selectListByReplyid(replyid);
  }

  @Override
  public void updatefloor(int replyid) {
    replyMapper.updatefloor(replyid);
  }

  @Override
  public int checkroleid(int postid, int roleid) {
    return replyMapper.checkroleid(postid, roleid);
  }

  @Override
  public Reply checkroleidinfo(int postid, int roleid) {
    return replyMapper.checkroleidinfo(postid, roleid);
  }

  @Override
  public void deletereply(int replyid) {
    replyMapper.deletereply(replyid);
  }

  @Override
  public List alluserid(int paramInt1) {
    return replyMapper.alluserid(paramInt1);
  }


}
