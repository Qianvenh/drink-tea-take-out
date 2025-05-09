package com.drinktea.service;

import com.drinktea.dto.UserLoginDTO;
import com.drinktea.entity.User;


public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
