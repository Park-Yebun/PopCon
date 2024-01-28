package com.ssafy.popcon.user.service;

import com.ssafy.popcon.user.dto.UserModifyDto;
import com.ssafy.popcon.user.mapper.UserMapper;
import com.ssafy.popcon.util.JWTUtil;
import com.ssafy.popcon.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserModifyService {

    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final S3UploadUtil s3UploadUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public int modifyUser(String userId, MultipartFile multipartFile, UserModifyDto userModifyDto, String token) throws Exception {
        // userId 저장
        userModifyDto.setUserId(userId);    // 기존 아이디 저장

        // token 을 통해 userType 가져오기
        String userType=jwtUtil.getRole(token.split(" ")[1]);
        userModifyDto.setUserType(userType);

        // 프로필 이미지 변경
        if(multipartFile!=null){
            String storedUrl= s3UploadUtil.upload(multipartFile,"profileImages");
            userModifyDto.setUserImagePath(storedUrl);
        }

        // 패스워드 변경
        userModifyDto.setUserPassword(bCryptPasswordEncoder.encode(userModifyDto.getUserPassword()));

        return userMapper.modifyUser(userModifyDto);

    }
}
