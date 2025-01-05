package com.sky.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.sky.config.WechatProperties;
import com.sky.constant.MessageConstant;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WechatProperties wechatProperties;
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    /*
     * 微信登录 by hllqk-2025
     */
    @Override
    public User wxLogin(String code) {
        //微信登录实现逻辑
        //调用微信服务的登录接口，获取用户信息
        String openid = getOpenid(code);
        //判断openid是否存在
        if (openid == null) {
            //如果openid不存在，登录失败，返回异常
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        //如果存在，则查询用户信息，是否存在用户表中
        User user = userMapper.getByOpenid(openid);
        if (user == null) {
            //如果用户不存在，则新增用户信息
            User newUser = User.builder()
                   .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            //将用户信息保存到数据库
            userMapper.insert(newUser);
            //返回用户信息
            return null;
        }
        //如果是新用户，则新增用户信息
        //如果是老用户，则更新用户信息
        //将用户信息保存到数据库
        //返回用户信息
        return null;
    }
    private String getOpenid(String code) {
        //微信登录实现逻辑
        //调用微信服务的登录接口，获取用户信息
        Map<String, String> map = new HashMap<>();
        map.put("appid", wechatProperties.getAppId());
        map.put("secret", wechatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN_URL, map);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
