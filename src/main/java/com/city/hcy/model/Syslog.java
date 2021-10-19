package com.city.hcy.model;


import lombok.Data;

import java.util.Date;

@Data
public class Syslog {

    private long id;

    private String uri;

    private String daoMethodName;

    private String ip;

    private String wholeSql;

    private String desc;

    private Date createDate;
}