package com.city.hcy.result;

import lombok.Data;

@Data
public class AppdetailsList<T> {

    private T result = null;

    private String version;
    private Integer isshowfunding;
    private Integer maxperson;
Integer personnumber;

String notice;

    String versionfailedtext;
    String fundingtext;

    private int status = 0;

    private String message = null;
}
