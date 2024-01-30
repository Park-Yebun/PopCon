package com.ssafy.popcon.user.service;

import com.ssafy.popcon.user.controller.UserController;
import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.mapper.UserMapper;
import com.ssafy.popcon.util.PwEncryptUtil;
import com.ssafy.popcon.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserMapper userMapper;
    private final S3UploadUtil s3UploadUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 회원가입
    @Transactional
    public void addUser(UserDto userDto, MultipartFile file) throws Exception {

        // 이미지 업로드
        if(file!=null){
            String storedUrl= s3UploadUtil.upload(file,"profileImages");
            userDto.setUserImagePath(storedUrl);
        }

        // 패스워드 암호화
//        userDto.setUserSalt(PwEncryptUtil.makeSalt());
//        userDto.setUserPassword(PwEncryptUtil.pwHashing(userDto.getUserPassword(),userDto.getUserSalt()));
        userDto.setUserPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()));

        // DB 저장
        userMapper.addUser(userDto);
    }

    // 중복 체크
    public String findDuplicate(Map<String, String> map) throws Exception{
        if(userMapper.findDuplicate(map.get("type"),map.get("value"))==1) {
            return "duplication error";
        } else {
            return "not duplicate";
        }
    }
}
