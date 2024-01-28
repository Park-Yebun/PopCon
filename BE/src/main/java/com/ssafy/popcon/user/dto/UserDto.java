package com.ssafy.popcon.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String userPk;
    private String userId;
    private String userEmail;
    private String userImagePath;
    private String userPassword;
    private String userPlatform;
    private String userNickname;
    private String userBirth;
    private String userSex;
    private String userType;
    private String userToken;
    private String userBusiness;
    private String userPhone;
    private String userSalt;

}
