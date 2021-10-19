package com.city.hcy.model;



import com.city.hcy.model.Post;
import com.city.hcy.model.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class Reply   implements Serializable {
  private Integer replyid;
  
  private Integer postid;
  
  private String replytext;


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date replytime;
  
  private Integer roleid;
  
  private Integer starcount;
  
  private Integer userid;
  
  private Integer replyfloor;
  
  private Integer replyObjectId;
  
  private Post post;
  
  private User user;

  int replyObjectRead;
  int replyObjectFloor;

  int replyObjectRoleid;

}
