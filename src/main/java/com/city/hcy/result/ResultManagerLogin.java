package com.city.hcy.result;

import lombok.Data;

@Data
public class ResultManagerLogin<T> {

    private T result = null;


    private Integer managerid;

    private String  manageraccount;

    private String managerpassword;

    private int status = 0;

    private String message = null;

}
