package com.city.hcy.result;



public class ExceptionResult {
  private String status = null;
  
  private String message = null;
  
  private Class<?> exceptionClass = null;
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Class<?> getExceptionClass() {
    return this.exceptionClass;
  }
  
  public void setExceptionClass(Class<?> exceptionClass) {
    this.exceptionClass = exceptionClass;
  }
  
  public String getStatus() {
    return this.status;
  }
  
  public void setStatus(String status) {
    this.status = status;
  }
}
