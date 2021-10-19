package com.city.hcy.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Unread   implements Serializable {
    int unreadid;
    int postedid;
    int repliedid;
    int userid;
    int unread;
    int replyid;

}
