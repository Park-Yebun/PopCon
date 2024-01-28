package com.ssafy.popcon.user.mapper;

import com.ssafy.popcon.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserMapper {

    void addUser(UserDto user) throws SQLException; // 회원가입
    int findDuplicate(String type, String value); // 중복 체크
    UserDto findUser(String userId); // 회원 조회
}
