package com.city.hcy.service.impl;

import com.city.hcy.mapper.ReportMapper;
import com.city.hcy.model.Post;
import com.city.hcy.model.Report;
import com.city.hcy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
//@Cacheable(value = "usercache")
public class ReportServiceImpl implements ReportService {


    @Autowired
    private ReportMapper reportMapper;



    @Override
    public void add(Report report)  throws Exception{
        reportMapper.insert(report);

    }



    @Override
    public List<Report> getListByAllWithPage(int rows, int page) throws Exception {
        return reportMapper.selectListByAllWithPage(rows * (page - 1), rows);
    }

    @Override
    public int getCountByAll() throws Exception {
        return reportMapper.selectCountByAll();
    }

    @Override
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
    public List<Report> getReplyListByAllWithPage(int rows, int page) throws Exception {
        return reportMapper.selectReplyListByAllWithPage(rows * (page - 1), rows);
    }

    @Override
    public int getReplyCountByAll() throws Exception {
        return reportMapper.selectReplyCountByAll();
    }

    @Override
    public int getReplyPageCountByAll(int rows) throws Exception {
        int pageCount = 0;
        int count = getReplyCountByAll();
        if (count % rows == 0) {
            pageCount = count / rows;
        } else {
            pageCount = count / rows + 1;
        }
        return pageCount;
    }

    @Override
    public List<Report> getPostListByAllWithPageover(int rows, int page) throws Exception {
        return reportMapper.selectPostListByAllWithPageover(rows * (page - 1), rows);
    }

    @Override
    public int getPostCountByAllover() throws Exception {
        return reportMapper.overpost();
    }

    @Override
    public int getPostPageCountByAllover(int rows) throws Exception {
        int pageCount = 0;
        int count = getPostCountByAllover();
        if (count % rows == 0) {
            pageCount = count / rows;
        } else {
            pageCount = count / rows + 1;
        }
        return pageCount;
    }

    @Override
    public List<Report> getReplyListByAllWithPageover(int rows, int page) throws Exception {
        return reportMapper.selectReplyListByAllWithPageover(rows * (page - 1), rows);
    }

    @Override
    public int getReplyCountByAllover() throws Exception {
        return reportMapper.overreply();
    }

    @Override
    public int getReplyPageCountByAllover(int rows) throws Exception {
        int pageCount = 0;
        int count = getReplyCountByAllover();
        if (count % rows == 0) {
            pageCount = count / rows;
        } else {
            pageCount = count / rows + 1;
        }
        return pageCount;
    }

    @Override
    public void delete(int reportid) {
        reportMapper.delete(reportid);
    }
}
