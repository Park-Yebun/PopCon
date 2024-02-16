package com.ssafy.popcon.notification.controller;

import com.ssafy.popcon.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private NotificationService notificationService;

    // 유저의 디바이스 토큰을 데이터베이스에 저장
    @PostMapping
    public ResponseEntity<?> tokenAdd(@RequestBody String token, HttpServletRequest request) throws Exception {
//        String authorization=request.getHeader("Authorization"); // 유저 권한 토큰
        notificationService.addToken(token,request.getHeader("Authorization"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("디바이스 토큰이 정상적으로 저장되었습니다.");
    }


}
