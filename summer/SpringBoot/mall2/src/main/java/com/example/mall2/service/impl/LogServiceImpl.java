package com.example.mall2.service.impl;

import com.example.mall2.mapper.LogMapper;
import com.example.mall2.mapper.RedisMapper;
import com.example.mall2.pojo.RandomSecurityCode;
import com.example.mall2.pojo.Result;
import com.example.mall2.service.LogService;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lala
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private RedisMapper redisMapper;

    private Map map;

    @Override
    public Result userLogin(String user_name, String user_password) {
        map.clear();
        Result result = null;
        Integer count = logMapper.userLogin(user_name, user_password);
        if(count == 0) {
            //登录失败
            result.setCode(43);
            result.setMsg("登录失败");
            result.setData(null);
        } else {
            //登录成功
            result.setCode(22);
            result.setMsg("登陆成功");
            map.put("user_name", user_name);
            map.put("user_password", user_password);
            result.setData(map);
        }
        return result;
    }

    @Override
    public Result findEmail(String user_email) {
        map.clear();
        Result result = null;
        Integer count = logMapper.findEmail(user_email);
        if(count == 0) {
            //发送验证码
            SimpleEmail email = new SimpleEmail();
            try{
                email.setHostName("smtp.qq.com");
                email.setCharset("utf-8");
                //收件人
                email.addTo(user_email);
                email.setFrom("2665687186@qq.com", "mall");
                //需要认证信息,用户名,密码(授权码)
                email.setAuthentication("2665687186@qq.com", "mddnittagwwlecfd");
                email.setSSLOnConnect(true);
                String securityCode = RandomSecurityCode.getCode();
                email.setSubject("注册验证码");
                email.setMsg("尊敬的用户您好!\n注册验证码为:" + securityCode + "\n");
                email.send();
                result.setCode(44);
                result.setMsg("验证码发送成功");
                result.setData(null);
                redisMapper.setKey("securityCode", securityCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //失败
            result.setCode(40);
            result.setMsg("邮箱已经被注册");
            result.setData(null);
        }
        return result;
    }

    @Override
    public Result userRegister(Map<Object, Object> map) {
        map.clear();
        Result result = null;
        //看用户名是否注册过
        String user_name = (String)(map.get("user_name"));
        String user_password = (String)(map.get("user_password"));
        String user_email = (String)(map.get("user_email"));
        String securityCode = redisMapper.getValue("securityCode");
        if(((String)(map.get(securityCode))) == securityCode) {
            //验证码正确
            int count = logMapper.insertRegister(user_name, user_password, user_email);
            if(count == 0) {
                //失败
                result.setCode(41);
                result.setMsg("用户名被注册");
                result.setCode(null);
            } else {
                //成功
                result.setCode(21);
                result.setMsg("注册成功");
                result.setData(null);
            }
        } else {
            //验证码错误，注册失败
            result.setCode(42);
            result.setMsg("验证码错误");
            result.setCode(null);
        }
        return result;
    }
}
