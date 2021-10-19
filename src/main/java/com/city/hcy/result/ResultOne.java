package com.city.hcy.result;



public class ResultOne<T> {
  private T result = null;
  
  private int status = 0;
  
  private String message = null;
  
//  double resultnumber = 0.0D;0D
  
  public T getResult() {
    return this.result;
  }
  
  public void setResult(T result) {
    this.result = result;
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
  
//  public double getResultnumber() {
//    return this.resultnumber;
//  }
//
//  public void setResultnumber(double resultnumber) {
//    this.resultnumber = resultnumber;
//  }
}
