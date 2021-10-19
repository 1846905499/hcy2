package com.city.hcy.result;

import lombok.Data;

@Data
public class ResultLogin<T> {

    private T result = null;


    private Integer userid;

    private String  userphone;

    private String userpassword;

    private int status = 0;

    private String message = null;

}
