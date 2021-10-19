package com.city.hcy.service;

import com.city.hcy.model.Post;
import com.city.hcy.model.Report;

import java.util.List;

public interface ReportService {
    void add(Report report) throws Exception;




    List<Report> getListByAllWithPage(int rows, int page) throws Exception;

    int getCountByAll() throws Exception;

    int getPageCountByAll(int rows) throws Exception;

    List<Report> getReplyListByAllWithPage(int rows, int page) throws Exception;

    int getReplyCountByAll() throws Exception;

    int getReplyPageCountByAll(int rows) throws Exception;



    List<Report> getPostListByAllWithPageover(int rows, int page) throws Exception;

    int getPostCountByAllover() throws Exception;

    int getPostPageCountByAllover(int rows) throws Exception;



    List<Report> getReplyListByAllWithPageover(int rows, int page) throws Exception;

    int getReplyCountByAllover() throws Exception;

    int getReplyPageCountByAllover(int rows) throws Exception;


    void delete(int reportid);

}
