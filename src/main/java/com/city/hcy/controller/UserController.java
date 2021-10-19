package com.city.hcy.controller;

import com.city.hcy.mapper.AppdetailsMapper;
import com.city.hcy.model.Report;
import com.city.hcy.model.User;
import com.city.hcy.result.AppdetailsList;
import com.city.hcy.result.ResultLogin;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping({"/api/user"})
@CrossOrigin({"*"})
public class UserController {
    @Autowired
    private UserService userService = null;


    @Autowired
    AppdetailsMapper appdetailsMapper;

    @PostMapping({"/login"})
    public ResultLogin<String> verifyUser(@RequestBody User user) throws Exception {
        ResultLogin<String> res = new ResultLogin();
        User trueUser = this.userService.login(user.getUserphone());
        if (trueUser != null && trueUser.getUserpassword().equals(user.getUserpassword().trim())) {
            res.setUserid(trueUser.getUserid());
            res.setUserphone(trueUser.getUserphone());
            res.setUserpassword(trueUser.getUserpassword());
            res.setStatus(200);
            res.setMessage("登录成功");
        } else {
            res.setStatus(401);
            res.setMessage("登录失败，请检查用户名和密码！");
        }
        return res;
    }


    @PostMapping("/updatetime")
    public ResultOne<String> updatetime(@RequestBody User user) throws Exception {
        ResultOne<String> res = new ResultOne();
        userService.updatetime(user);
        res.setStatus(200);
        res.setMessage("禁言成功");
        return res;
    }

    @RequestMapping({"/add"})
    @ResponseBody
    public ResultOne<String> add(@RequestBody User user) throws Exception {
        ResultOne<String> res = new ResultOne<>();
        User trueUser = this.userService.login(user.getUserphone());

        System.out.println("我传入的user" + user);
        System.out.println("真实user" + trueUser);
        System.out.println("检测是否true：" + (trueUser == null));

        if (this.userService.selectnumber() >= appdetailsMapper.select().getMaxperson()) {
            res.setStatus(400);
            res.setMessage("注册失败：原因，超过注册上限");
        } else if ((trueUser != null)) {

            res.setStatus(401);
            res.setMessage("注册失败：原因，用户名已被注册");
        } else {
            this.userService.add(user);
            res.setStatus(200);
            res.setMessage("注册成功");
        }


        return res;
    }


    @PostMapping("/usercount")
    public ResultOne<String> usercount() throws Exception {
        ResultOne<String> res = new ResultOne();
        res.setResult(userService.selectnumber() + "");
        res.setStatus(200);
        res.setMessage("当前注册人数");
        return res;
    }

    @PostMapping("/appdetails")
    public AppdetailsList<String> appdeatils() throws Exception {
        AppdetailsList<String> res = new AppdetailsList<>();
        res.setStatus(200);
        res.setPersonnumber(userService.selectnumber());
        res.setMaxperson(appdetailsMapper.select().getMaxperson());
        res.setVersion(appdetailsMapper.select().getVersion());
        res.setIsshowfunding(appdetailsMapper.select().getIsshowfunding());
        res.setNotice(appdetailsMapper.select().getNotice());
        res.setFundingtext(appdetailsMapper.select().getFundingtext());
        res.setVersionfailedtext(appdetailsMapper.select().getVersionfailedtext());
        res.setMessage("获取版本信息成功");


        return res;
    }

}
