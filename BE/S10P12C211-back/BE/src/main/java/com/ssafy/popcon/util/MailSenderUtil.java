package com.ssafy.popcon.util;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderUtil {

    private final JavaMailSender mailSender;

    // 아이디 전송
    @Async
    public void sendUserId(String userId, String userEmail) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("[POPCON] 아이디 찾기 이메일 입니다.");
        mailMessage.setFrom("POPCON");

        StringBuffer sb=new StringBuffer();
        sb.append("안녕하세요. POPCON 입니다. \n");
        sb.append("가입하신 아이디는 [ "+userId+" ] 입니다."+"\n");

        mailMessage.setText(sb.toString());
        mailSender.send(mailMessage);
    }

    @Async
    public void sendUserPassword(String randomPassword, String userEmail) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(userEmail);
        mailMessage.setSubject("[POPCON] 임시 패스워드 발급 이메일 입니다.");
        mailMessage.setFrom("POPCON");

        StringBuffer sb=new StringBuffer();
        sb.append("안녕하세요. POPCON 입니다. \n");
        sb.append("임시 패스워드는 [ "+randomPassword+" ] 입니다."+"\n");
        sb.append("홈페이지에 접속하여 로그인 해주세요!");

        mailMessage.setText(sb.toString());
        mailSender.send(mailMessage);
    }

}
