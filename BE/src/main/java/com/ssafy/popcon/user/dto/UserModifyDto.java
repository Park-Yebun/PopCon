package com.ssafy.popcon.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class UserModifyDto {

    private String userId;
    private String userPassword;
    private String userPhone;   // 휴대폰 번호 or 사업장 번호
    private String userBusiness;    // 사업자 번호
    private String userType; // 토큰을 통해 유저 타입 저장
    private String userImagePath; // 변경할 프로필 경로
    private String userNickname;
}
