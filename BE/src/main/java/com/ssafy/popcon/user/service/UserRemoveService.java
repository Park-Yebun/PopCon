package com.ssafy.popcon.user.service;

import com.ssafy.popcon.user.mapper.UserMapper;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserRemoveService {

    private final UserMapper userMapper;

    public int removeUser(String userId) throws Exception {
        return userMapper.removeUser(userId);
    }
}
