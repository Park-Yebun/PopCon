package com.ssafy.popcon.user.mapper;

import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.dto.UserModifyDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserMapper {

    void addUser(UserDto user) throws SQLException; // 회원가입
    int findDuplicate(String type, String value) throws SQLException; // 중복 체크
    UserDto findUserById(String userId) throws SQLException; // 아이디로 회원 조회
    int modifyUser(UserModifyDto userModifyDto) throws SQLException; // 회원 수정
    UserDto findUserByEmail(String email) throws SQLException;  // 이메일로 회원 조회 (아이디 찾기)
    int modifyUserPassword(String userId,String randomPassword) throws SQLException; // 패스워드 수정
    int removeUser(String userId) throws SQLException; // 회원 탈퇴


}
