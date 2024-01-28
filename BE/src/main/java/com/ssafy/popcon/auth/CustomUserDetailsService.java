package com.ssafy.popcon.auth;

import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDto userDto= null;
        try {
            userDto = userMapper.findUser(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new CustomUserDetails(userDto);
    }
}