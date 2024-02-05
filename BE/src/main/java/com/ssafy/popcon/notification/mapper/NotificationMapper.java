package com.ssafy.popcon.notification.mapper;

import com.ssafy.popcon.notification.dto.NotificationDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface NotificationMapper {

    void addUserToken(NotificationDto notificationDto) throws SQLException; // 회원 디바이스 토큰 저장
    void addNonUserToken(String token) throws SQLException; // 비회원 디바이스 토큰 저장
    void removeToken(String userId) throws SQLException;    // 회원 디바이스 토큰 삭제
}
