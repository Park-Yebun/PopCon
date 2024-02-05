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
            userDto = userMapper.findUserById(userId);
            if (userDto == null) {
                throw new UsernameNotFoundException("아이디 : "+userId+"에 해당하는 사용자가 없습니다");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new CustomUserDetails(userDto);
    }
}