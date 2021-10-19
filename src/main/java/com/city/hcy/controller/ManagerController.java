package com.city.hcy.controller;

import com.city.hcy.mapper.ManageroperateMapper;
import com.city.hcy.model.Manager;
import com.city.hcy.model.User;
import com.city.hcy.result.ResultLogin;
import com.city.hcy.result.ResultManagerLogin;
import com.city.hcy.result.ResultOne;
import com.city.hcy.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/manager"})
@CrossOrigin({"*"})
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    ManageroperateMapper manageroperateMapper;

    @PostMapping({"/login"})
    public ResultManagerLogin<String> verifyUser(@RequestBody Manager manager) throws Exception {
        ResultManagerLogin<String> res = new ResultManagerLogin();
        Manager trueUser = this.managerService.login(manager.getManageraccount());
        if (trueUser != null && trueUser.getManagerpassword().equals(manager.getManagerpassword().trim())) {

            res.setManagerid(trueUser.getManagerid());
            res.setManageraccount(trueUser.getManageraccount());
            res.setManagerpassword(trueUser.getManagerpassword());
            res.setStatus(200);
            res.setMessage("登录成功");

//            manageroperateMapper.insert(trueUser.getManagerid(),"管理员登陆了");
        } else {
            res.setStatus(401);
            res.setMessage("登录失败，请检查用户名和密码！");
        }
        return res;
    }

    @RequestMapping({"/add"})
    @ResponseBody
    public ResultOne<String> add(@RequestBody  Manager manager) throws Exception {
        ResultOne<String> res = new ResultOne<>();
        Manager trueUser = this.managerService.login(manager.getManageraccount());


      if ((trueUser != null)) {

            res.setStatus(401);
            res.setMessage("注册失败：原因，用户名已被注册");
        } else {
            this.managerService.add(manager);
            res.setStatus(200);
            res.setMessage("注册成功");
        }


        return res;
    }

}
