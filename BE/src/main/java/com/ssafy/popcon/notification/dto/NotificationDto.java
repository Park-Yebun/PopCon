package com.ssafy.popcon.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {  // 회원 유저 디바이스 토큰 저장용 dto
    String userDeviceTokenId;
    String userId;
    String userDeviceToken;
}
