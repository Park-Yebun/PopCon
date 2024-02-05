package com.ssafy.popcon.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDto {

    // 푸시 알람을 보내는 dto
    String token;
    String message;
    String title;
}
