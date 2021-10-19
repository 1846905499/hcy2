package com.city.hcy.result;



import java.util.List;

public class ResultList<T> {
  private int pageindex = 0;
  
  private int pageCount = 0;
  
  private int rows = 0;
  
  private int allDataCount = 0;
  
  private List<T> list = null;
  
  private int status = 0;
  
  private String message = null;
  
  private String query = null;
  
  public int getPageCount() {
    return this.pageCount;
  }
  
  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
  
  public int getRows() {
    return this.rows;
  }
  
  public void setRows(int rows) {
    this.rows = rows;
  }
  
  public int getPageindex() {
    return this.pageindex;
  }
  
  public void setPageindex(int pageindex) {
    this.pageindex = pageindex;
  }
  
  public int getAllDataCount() {
    return this.allDataCount;
  }
  
  public void setAllDataCount(int allDataCount) {
    this.allDataCount = allDataCount;
  }
  
  public List<T> getList() {
    return this.list;
  }
  
  public void setList(List<T> list) {
    this.list = list;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getQuery() {
    return this.query;
  }
  
  public void setQuery(String query) {
    this.query = query;
  }
}
