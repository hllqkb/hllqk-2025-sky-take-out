package com.sky.service;

import com.sky.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User wxLogin(String code);
}
