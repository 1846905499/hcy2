package com.city.hcy.controller;

import com.city.hcy.model.Post;
import com.city.hcy.model.Report;
import com.city.hcy.result.ResultList;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/report"})
@CrossOrigin({"*"})
public class ReportController {

    @Autowired
    private  ReportService reportService;

    @RequestMapping({"/list/allreportpost"})
    @ResponseBody
    public ResultList<Report> getListByAll(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex)
            throws Throwable {
        ResultList<Report> result = new ResultList();
        result.setAllDataCount(this.reportService.getCountByAll());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(reportService.getPageCountByAll(rows));
        result.setList(this.reportService.getListByAllWithPage(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得帖子举报信息成功");
        return result;
    }

    @RequestMapping({"/list/allreportreply"})
    @ResponseBody
    public ResultList<Report> getReplyListByAll(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex)
            throws Throwable {
        ResultList<Report> result = new ResultList();
        result.setAllDataCount(this.reportService.getReplyCountByAll());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(reportService.getReplyPageCountByAll(rows));
        result.setList(this.reportService.getReplyListByAllWithPage(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得回帖举报信息成功");
        return result;
    }




    @RequestMapping({"/add"})
    @ResponseBody
    public ResultOne<String> add(@RequestBody Report report) throws Exception {
        ResultOne<String> res = new ResultOne<>();
        this.reportService.add(report);
        res.setStatus(200);
        res.setMessage("举报成功");
        return res;
    }

    @RequestMapping({"/list/allreportpostover"})
    @ResponseBody
    public ResultList<Report> getListByAllover(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex)
            throws Throwable {
        ResultList<Report> result = new ResultList();
        result.setAllDataCount(this.reportService.getPostCountByAllover());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(reportService.getPostPageCountByAllover(rows));
        result.setList(this.reportService.getPostListByAllWithPageover(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得帖子举报信息成功");
        return result;
    }


    @RequestMapping({"/list/allreportreplyover"})
    @ResponseBody
    public ResultList<Report> getReplyListByAllover(@RequestParam(value = "rows", required = false, defaultValue = "10") int rows,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int pageindex)
            throws Throwable {
        ResultList<Report> result = new ResultList();
        result.setAllDataCount(this.reportService.getReplyCountByAllover());
        result.setPageindex(pageindex);
        result.setRows(rows);
        result.setPageCount(reportService.getReplyPageCountByAllover(rows));
        result.setList(this.reportService.getReplyListByAllWithPageover(rows, pageindex));
        result.setStatus(200);
        result.setMessage("分页取得回帖举报信息成功");
        return result;
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public ResultOne<String> delete(int reportid) throws Exception {
        ResultOne<String> res = new ResultOne<>();
        this.reportService.delete(reportid);
        res.setStatus(200);
        res.setMessage("逻辑删除成功");
        return res;
    }
}
