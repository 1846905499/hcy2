package com.city.hcy.service.impl;



import com.city.hcy.mapper.UserMapper;
import com.city.hcy.model.User;
import com.city.hcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper = null;

//  @Cacheable(value = "usercache")
  public User login(String name) throws Exception {
    System.out.println("此时没有走缓存");
    return this.userMapper.selectByPrimaryKey(name);
  }

  @Override
  public void updatetime(User user) {
    userMapper.updateByPrimaryKey(user);
  }

  @Override
  public void add(User user) {
    userMapper.insert(user);
  }

  @Override
  public int selectnumber() {
    return userMapper.selectnumber();
  }
}