package com.example.mall2.service;

import com.example.mall2.pojo.Result;
import com.example.mall2.pojo.User;
import com.example.mall2.pojo.User_Role;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lala
 */

public interface LogService {

//    public List<User> getByMap(Map<String, Object> map);
//
//    public User getById(Integer id);
//
//    public User create(User user)throws DataAccessException;
//
//    public User update(User user);
//
//    public int delete(Integer id);
//
//    public User getByUserName(String userName);
//
//    public List<User_Role> getRoles(Integer id);

    public Result findEmail(String user_email);
    public Result userRegister(Map<Object, Object> map);
}
