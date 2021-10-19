package com.city.hcy.controller;

import com.city.hcy.mapper.ManageroperateMapper;
import com.city.hcy.model.Post;
import com.city.hcy.model.Reply;
import com.city.hcy.result.ResultList;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.PostService;
import com.city.hcy.service.ReplyService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/reply"})
@CrossOrigin({"*"})
public class ReplyController {
    @Autowired
    private PostService postService = null;

    @Autowired
    private ReplyService replyService = null;
    @Autowired
    ManageroperateMapper manageroperateMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping("/add")
    @ResponseBody
    public ResultOne<String> add(@RequestBody Reply reply) throws Exception {
        ResultOne<String> res = new ResultOne();
        int a =reply.getUserid();
        if (replyService.checkroleid(reply.getPostid(),reply.getRoleid())!=0&&!replyService.checkroleidinfo(reply.getPostid(),reply.getRoleid()).getUserid().equals(a))
        {
                res.setStatus(400);
                res.setMessage("回帖角色已被选择");
                return res;
        }
        else {
            this.replyService.add(reply);
            this.postService.modifyrecenttime(reply.getPostid().intValue());
            System.err.println(reply.getPostid());
            System.err.println(reply.getReplyid());
            System.err.println(reply);
            this.postService.modifycommentcount(reply.getPostid());

            res.setStatus(200);
            res.setMessage("回帖成功");
            return res;
        }
    }

    @RequestMapping({"/addstar"})
    @ResponseBody
    public ResultOne<String> addstar(Integer replyid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.replyService.addreplystar(replyid, userid);
        this.replyService.modifystarcount(replyid.intValue());
        res.setStatus(200);
        res.setMessage("回帖点赞成功");
        return res;
    }

    @RequestMapping({"/deletestar"})
    @ResponseBody
    public ResultOne<String> deletestar(Integer replyid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.replyService.deletereplystar(replyid, userid);
        this.replyService.modifystarcount(replyid.intValue());
        res.setStatus(200);
        res.setMessage("回帖取消点赞成功");
        return res;
    }

    @RequestMapping({"/list/all"})
    @ResponseBody
    public ResultList<Reply> getListByAll(@RequestParam(value = "rows", required = false,
            defaultValue = "10") int rows, @RequestParam(value = "page", required = false,
            defaultValue = "1") int pageindex, int postid) throws Throwable {
        ResultList<Reply> result = new ResultList();
        result.setAllDataCount(this.replyService.getCountByAll(postid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.replyService.getPageCountByAll(rows, postid));
        result.setList(this.replyService.getListByAllWithPage(rows, pageindex, postid));
        result.setStatus(200);
        result.setMessage("分页取得全部回帖成功");
        return result;
    }

    @RequestMapping({"/list/allByID"})
    @ResponseBody
    public ResultList<Reply> getListByID(@RequestParam(value = "rows", required = false,
            defaultValue = "5") int rows, @RequestParam(value = "page", required = false,
            defaultValue = "1") int pageindex, int userid) throws Throwable {
        ResultList<Reply> result = new ResultList();
        result.setAllDataCount(this.replyService.getCountByID(userid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.replyService.getPageCountByID(rows, userid));
        result.setList(this.replyService.getListByIDWithPage(rows, pageindex, userid));
        result.setStatus(200);
        result.setMessage("分页取得某一用户全部回帖成功");
        return result;
    }

    @RequestMapping({"/list/alldeleted"})
    @ResponseBody
    public ResultList<Reply> getListByIDdeleted(@RequestParam(value = "rows", required = false,
            defaultValue = "5") int rows, @RequestParam(value = "page", required = false,
            defaultValue = "1") int pageindex) throws Throwable {
        ResultList<Reply> result = new ResultList();
        result.setAllDataCount(this.replyService.getCountByAllDeleted());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.replyService.getPageCountByAllDeleted(rows));
        result.setList(this.replyService.getListByAllWithPageDeleted(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得全部删除回帖成功");
        return result;
    }


    @RequestMapping({"/list/alluserid"})
    @ResponseBody
    public ResultList<Reply> getalluserid( int postid) throws Throwable {
        ResultList<Reply> result = new ResultList();


        result.setList(this.replyService.alluserid(postid));
        result.setStatus(200);
        result.setMessage("取得全部userid");
        return result;
    }

    @RequestMapping({"/list/allByIDWithFrontPage"})
    @ResponseBody
    public ResultList<Reply> getListByIDWithFrontPage(@RequestParam(value = "rows", required = false, defaultValue = "5") int rows,
                                                      @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex,
                                                      int postid) throws Throwable {
        ResultList<Reply> result = new ResultList();
        result.setAllDataCount(this.replyService.getCountByID(postid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.replyService.getPageCountByID(rows, postid));
        result.setList(this.replyService.getListByIDWithFrontPage(rows,pageindex+1, postid));
        result.setStatus(200);
        result.setMessage("分页取得某一帖子前几页回帖成功");
        return result;
    }



    @RequestMapping({"/updateReadByReplyostid"})
    @ResponseBody
    public ResultOne<String> updateReadByReplyostid(int[] replyid) throws Throwable {
        ResultOne<String> result = new ResultOne();
        replyService.updateReadByReplyostid(replyid);
        result.setStatus(200);
        result.setMessage("批量修改ReadByReplyostid成功");
        return result;
    }




    @RequestMapping({"/updatedeleted"})
    @ResponseBody
    public ResultOne<String> updatedeleted(int[] replyid,int managerid) throws Throwable {
        ResultOne<String> result = new ResultOne();
        replyService.updateDeleted(replyid);
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < replyid.length; i++) {
            a.append(replyid[i] + ",");
        }
        manageroperateMapper.insert(managerid, "管理员取消逻辑删除" + a.deleteCharAt(a.length() - 1) + "号回帖");

        result.setStatus(200);
        result.setMessage("批量修改删除状态成功");
        System.err.println(replyid);
        return result;
    }


    @RequestMapping({"/list/allByuseridandreplyobjectRead"})
    @ResponseBody
    public ResultList<Reply> getListByuseridandreplyobjectRead(@RequestParam(value = "rows", required = false, defaultValue = "5") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex, int userid) throws Throwable {
        ResultList<Reply> result = new ResultList();
result.setAllDataCount(this.replyService.getCountByIDAndreplyobject(userid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.replyService.getPageCountByIDAndreplyobject(rows,userid));
result.setList(this.replyService.getListByuseridAndreplyobject(rows, pageindex, userid));
        result.setStatus(200);
        result.setMessage("分页取得某一用户未回复的帖子成功");
        return result;
    }


    @RequestMapping({"/list/allbyreplyid"})
    @ResponseBody
    public ResultList<Reply> getListByreplyid(int[] replyid) throws Throwable {
        ResultList<Reply> result = new ResultList();

        result.setList(this.replyService.getListByReplyid(replyid));
        result.setStatus(200);
        result.setMessage("取得指定replyid的数据成功");
        return result;
    }


    @RequestMapping({"/deletereply"})
    @ResponseBody
    public ResultOne<String> deletereply(int replyid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.replyService.deletereply(replyid);
//        manageroperateMapper.insert(managerid,"管理员逻辑删除了"+replyid+"号帖子");
        res.setStatus(200);
        res.setMessage("删除回帖成功");
        return res;
    }

    @RequestMapping({"/deletereplyformanager"})
    @ResponseBody
    public ResultOne<String> deletereplyformanager(int replyid,int managerid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.replyService.deletereply(replyid);
       manageroperateMapper.insert(managerid,"管理员逻辑删除了"+replyid+"号回帖");
        res.setStatus(200);
        res.setMessage("删除回帖成功");
        return res;
    }

}

