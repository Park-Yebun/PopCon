package com.ssafy.popcon.notification.service;

import com.ssafy.popcon.notification.dto.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class ScheduledService {

    private final NotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);


    @Scheduled(cron = "0 30 7 * * ?")
    public void scheduledSend() throws ExecutionException, InterruptedException {

        // TO DO : 회원용 알림이라면, 좋아요한 목록 中 오픈 예정, 마감 임박 (3일 이내) 인 팝업스토어 목록 조회 , 그 중에서 가장 인기 있는 팝업을 보여줄까
        // TO DO : 비회원 알림이라면, 그냥 인기 목록 보여주기 ? ,, GPS 기반은 클라이언트에서 요청이 들어와야 가능해서 어려울 것 같음
        // ==== > 아무튼 회원용 알림 조회 결과에는, 팝업스토어 이름, 사용자 토큰번호, 오픈예정/마감임박 중에 무엇인지 의 정보가 필요하다
        // ==== > 비회원 알림은, 사용자 토큰 번호, 팝업스토어 이름 이 필요하다

        // 받아온 값들을 이용하여 NotificationRequestDto 의 값들을 채워주기
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto();
        notificationRequestDto.setTitle("제목?");
        notificationRequestDto.setToken("사용자 디바이스 토큰 넣어주기");
        notificationRequestDto.setMessage("마감 or 오픈이 얼마 남지 않았습니다! 지금 바로 확인해보세요!");

        notificationService.sendNotification(notificationRequestDto);
        logger.info("알림이 보내졌습니다!");
    }
}
