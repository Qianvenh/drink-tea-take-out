package com.drinktea.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drinktea.constant.MessageConstant;
import com.drinktea.dto.UserLoginDTO;
import com.drinktea.entity.User;
import com.drinktea.exception.LoginFailedException;
import com.drinktea.mapper.UserMapper;
import com.drinktea.properties.WeChatProperties;
import com.drinktea.service.UserService;
import com.drinktea.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.drinktea.utils.PhoneNumberUtil;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    public WeChatProperties weChatProperties;

    @Autowired
    public UserMapper userMapper;

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    @Override
    @Transactional
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信的接口服务，获得当前微信用户的openid
        String openid = getOpenid(userLoginDTO.getCode());
        // 判断openid是否为0，如果为空表示登录失败，抛出业务异常
        if (openid == null || openid.isEmpty()) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 判断当前用户是否为为新用户
        User user = userMapper.getByOpenid(openid);
        // 如果是，自动完成注册
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .phone(PhoneNumberUtil.randomPhoneNumberGenerate()) // 手机号不可为空
                    .build();
            userMapper.insert(user);
        }
        // 返回这个用户对象
        return user;
    }

    /**
     * 调用微信的接口服务，获取微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", weChatProperties.getAppid());
        params.put("secret", weChatProperties.getSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, params);
        JSONObject jsonObject = JSON.parseObject(json);
        return jsonObject.getString("openid");
    }
}
