package com.city.hcy.controller;

import com.city.hcy.mapper.ManageroperateMapper;
import com.city.hcy.model.Post;
import com.city.hcy.result.ResultList;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.PostService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping({"/api/post"})
@CrossOrigin({"*"})
public class PostController {
    @Autowired
    private PostService postService = null;
    @Autowired
    ManageroperateMapper manageroperateMapper;

    @RequestMapping({"/add"})
    @ResponseBody
    public ResultOne<String> add(@RequestBody Post post) throws Exception {
        ResultOne<String> res = new ResultOne();

        this.postService.add(post);
        res.setStatus(200);
        res.setMessage("发帖成功");
        return res;
    }

    @RequestMapping({"/addhistory"})
    @ResponseBody
    public ResultOne<String> addhistory(Integer postid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        try {
            this.postService.addhistory(postid, userid);
            res.setStatus(200);
            res.setMessage("加入历史记录成功");
        } catch (Exception e) {
            this.postService.updatehistorytime(postid, userid);
            res.setStatus(200);
            res.setMessage("加入历史记录成功(实际上是修改时间)");
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping({"/addcollection"})
    @ResponseBody
    public ResultOne<String> addcollection(Integer postid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.addcollection(postid, userid);
        this.postService.modifyCollectionCount(postid.intValue());
        res.setStatus(200);
        res.setMessage("加入收藏列表成功");
        return res;
    }

    @RequestMapping({"/addstar"})
    @ResponseBody
    public ResultOne<String> addstar(Integer postid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.addstar(postid, userid);
        this.postService.modifystarcount(postid.intValue());
        res.setStatus(200);
        res.setMessage("点赞成功");
        return res;
    }

    @RequestMapping({"/deletestar"})
    @ResponseBody
    public ResultOne<String> deletestar(Integer postid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.deletestar(postid, userid);
        this.postService.modifystarcount(postid.intValue());
        res.setStatus(200);
        res.setMessage("取消点赞成功");
        return res;
    }

    @RequestMapping({"/deletecollection"})
    @ResponseBody
    public ResultOne<String> deletecollection(Integer postid, Integer userid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.deletecollection(postid, userid);
        this.postService.modifyCollectionCount(postid.intValue());
        res.setStatus(200);
        res.setMessage("取消加入收藏列表成功");
        return res;
    }

    @RequestMapping({"/modify"})
    @ResponseBody
    public ResultOne<String> modify(@RequestBody Post post) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.modify(post);
        res.setStatus(200);
        res.setMessage("修改帖子成功");
        return res;
    }

    @RequestMapping({"/modifyistop"})
    @ResponseBody
    public ResultOne<String> modifyistop(int postid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.modifyistop(postid);
        res.setStatus(200);
        res.setMessage("置顶成功");
        return res;
    }

    @RequestMapping({"/list/all"})
    @ResponseBody
    public ResultList<Post> getListByAll(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCountByAll());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getPageCountByAll(rows));
        result.setList(this.postService.getListByAllWithPage(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得全部帖子成功");
        return result;
    }

    @RequestMapping({"/list/alldeleted"})
    @ResponseBody
    public ResultList<Post> getListByAlldeleted(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCountByAllDeleted());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getPageCountByAllDeleted(rows));
        result.setList(this.postService.getListByAllWithPageDeleted(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得全部被删除帖子成功");
        return result;
    }


    @RequestMapping({"/list/allorderbystar"})
    @ResponseBody
    public ResultList<Post> getListByAllOrderByStar(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCountByAll());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getPageCountByAll(rows));
        result.setList(this.postService.getListByAllWithPageOrderByStar(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得全部帖子成功(按热度)");
        return result;
    }

    @RequestMapping({"/list/allorderbycollection"})
    @ResponseBody
    public ResultList<Post> getListByAllOrderByCollection(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCountByAll());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getPageCountByAll(rows));
        result.setList(this.postService.getListByAllWithPageOrderByCollection(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得全部帖子成功(按收藏数)");
        return result;
    }

    @RequestMapping({"/list/allById"})
    @ResponseBody
    public ResultList<Post> getListById(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex, int userid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCountById(userid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getPageCountById(rows, userid));
        result.setList(this.postService.getListByIdWithPage(rows, pageindex, userid));
        result.setStatus(200);
        result.setMessage("分页取得某一用户的全部发帖记录成功");
        return result;
    }

    @RequestMapping({"/list/allcollection"})
    @ResponseBody
    public ResultList<Post> getCollectionListByAll(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex, int userid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getCollectionCountByAll(userid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getCollectionPageCountByAll(rows, userid));
        result.setList(this.postService.getCollectionListByAllWithPage(rows, pageindex, userid));
        result.setStatus(200);
        result.setMessage("分页取得某一用户收藏列表");
        return result;
    }

    @RequestMapping({"/list/allhistory"})
    @ResponseBody
    public ResultList<Post> getHistoryListByAll(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows, @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex, int userid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setAllDataCount(this.postService.getHistoryCountByAll(userid));
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(this.postService.getHistoryPageCountByAll(rows, userid));
        result.setList(this.postService.getHistoryListByAllWithPage(rows, pageindex, userid));
        result.setStatus(200);
        result.setMessage("分页取得某一用户历史记录成功");
        return result;
    }

    @RequestMapping({"/list/allstar"})
    @ResponseBody
    public ResultList<Post> getStarByAll(int userid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setList(this.postService.getUserStarList(userid));
        result.setStatus(200);
        result.setMessage("取得某一用户的点赞列表成功");
        return result;
    }

    @RequestMapping({"/list/allpostid"})
    @ResponseBody
    public ResultList<Integer> getpostid() throws Throwable {
        ResultList<Integer> result = new ResultList();
        result.setList(this.postService.getpostid());
        result.setStatus(200);
        result.setMessage("取得10000个postid成功");
        return result;
    }

    @RequestMapping({"/list/allpostidorderbytime"})
    @ResponseBody
    public ResultList<Integer> getpostidorderbytime() throws Throwable {
        ResultList<Integer> result = new ResultList();
        result.setList(this.postService.getpostidorderbytime());
        result.setStatus(200);
        result.setMessage("取得10000个postid成功 发帖顺序" +
                "");
        return result;
    }

    @RequestMapping({"/list/toppostid"})
    @ResponseBody
    public ResultList<Integer> gettoppostid() throws Throwable {
        ResultList<Integer> result = new ResultList();
        result.setList(this.postService.istoppostid());
        result.setStatus(200);
        result.setMessage("取得10000个置顶postid成功");
        return result;
    }


    @RequestMapping({"/list/allpostidorderbycomment"})
    @ResponseBody
    public ResultList<Integer> getpostidorderbyComment() throws Throwable {
        ResultList<Integer> result = new ResultList();
        result.setList(this.postService.getpostidOrderByComment());
        result.setStatus(200);
        result.setMessage("取得10000个postid成功,热度顺序");
        return result;
    }

    @RequestMapping({"/list/allByPostid"})
    @ResponseBody
    public ResultList<Post> getByPostid(int[] postid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setList(this.postService.getListByPostid(postid));
        result.setStatus(200);
        result.setMessage("取得多个指定postid");
        return result;
    }

    @RequestMapping({"/list/allByPostidOrderByComment"})
    @ResponseBody
    public ResultList<Post> getByPostidOrderByComment(int[] postid) throws Throwable {
        ResultList<Post> result = new ResultList();
        result.setList(this.postService.getListByPostidOrderBytime(postid));
        result.setStatus(200);
        result.setMessage("取得多个指定postid(评论顺序)");
        return result;
    }

    @RequestMapping({"/deletepost"})
    @ResponseBody
    public ResultOne<String> deletepost(int postid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.deletepost(postid);
//        manageroperateMapper.insert(managerid, "管理员逻辑删除了" + postid + "号帖子");
        res.setStatus(200);
        res.setMessage("删除帖子成功");
        return res;
    }

    @RequestMapping({"/deletepostformanager"})
    @ResponseBody
    public ResultOne<String> deletepostformanager(int postid,int managerid) throws Exception {
        ResultOne<String> res = new ResultOne();
        this.postService.deletepost(postid);
         manageroperateMapper.insert(managerid, "管理员逻辑删除了" + postid + "号帖子");
        res.setStatus(200);
        res.setMessage("删除帖子成功");
        return res;
    }


    @RequestMapping({"/updatedeleted"})
    @ResponseBody
    public ResultOne<String> updatedeleted(int[] postid, int managerid) throws Throwable {
        ResultOne<String> result = new ResultOne();

        postService.updateDeleted(postid);
        StringBuffer a = new StringBuffer();
        for (int i = 0; i < postid.length; i++) {
            a.append(postid[i] + ",");
        }
        manageroperateMapper.insert(managerid, "管理员取消逻辑删除" + a.deleteCharAt(a.length() - 1) + "号帖子");

        result.setStatus(200);
        result.setMessage("批量修改删除状态成功");
        return result;
    }
}
