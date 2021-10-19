package com.city.hcy.model;

import lombok.Data;

import java.util.Date;
@Data
public class Manageroperate {
    int logid;
    int managerid;
    Date operatetime;
    String wholesql;
}
