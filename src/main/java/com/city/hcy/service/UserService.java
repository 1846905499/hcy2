package com.city.hcy.service;


import com.city.hcy.model.User;

public interface UserService {
  User login(String paramLong) throws Exception;

  void updatetime(User user);

  void add(User user);

  int selectnumber();
}
