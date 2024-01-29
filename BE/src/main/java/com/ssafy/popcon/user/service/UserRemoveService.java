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
    private final JWTUtil jwtUtil;

    public int removeUser(String token) throws Exception {
        // token 을 통해 userId 가져오기
        String userId= jwtUtil.getUsername(token.split(" ")[1]);
        return userMapper.removeUser(userId);
    }
}
