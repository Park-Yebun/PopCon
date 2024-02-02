package com.ssafy.popcon.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import com.ssafy.popcon.notification.dto.NotificationDto;
import com.ssafy.popcon.notification.dto.NotificationRequestDto;
import com.ssafy.popcon.notification.mapper.NotificationMapper;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.firebase.messaging.Message;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;
    private final JWTUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

    // 토큰 저장
    public void addToken(String token, String authorization) throws Exception {
        if(authorization==null) {   // 비회원 토큰 저장
            notificationMapper.addNonUserToken(token);
        } else {  // 회원 토큰 저장
            String userId=jwtUtil.getUsername(authorization.split(" ")[1]);
            NotificationDto notificationDto=new NotificationDto();
            notificationDto.setUserId(userId);
            notificationDto.setUserDeviceToken(token);
            notificationMapper.addUserToken(notificationDto);
        }
    }

    // 알림 보내기
    public void sendNotification(NotificationRequestDto notificationRequestDto) throws ExecutionException, InterruptedException {
        Message message=Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                .setNotification(WebpushNotification.builder()
                        .setTitle(notificationRequestDto.getTitle())
                        .setBody(notificationRequestDto.getMessage())
                        .build())
                        .build())
                .setToken(notificationRequestDto.getToken())
                .build();

        String response= FirebaseMessaging.getInstance().sendAsync(message).get();
        logger.info(">>>> Send Message: "+response);
    }

    // 토큰 삭제 (회원용) ==> 비회원은 디바이스 토큰 언제 삭제 하지?
    public void removeToken(String token) throws Exception {
        String userId=jwtUtil.getUsername(token.split(" ")[1]);
        notificationMapper.removeToken(userId);
    }


}
