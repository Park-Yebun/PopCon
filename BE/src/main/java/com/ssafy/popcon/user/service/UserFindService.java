package com.ssafy.popcon.user.service;

import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.mapper.UserMapper;
import com.ssafy.popcon.util.MailSenderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserMapper userMapper;
    private final MailSenderUtil mailSenderUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int findUser(Map<String, String> userFindDto) throws Exception {
        String type=userFindDto.get("type");
        String value=userFindDto.get("value");
        UserDto userDto;

        if(type.equals("userId")){  // 아이디 찾기
            userDto=userMapper.findUserByEmail(value);
            if(userDto==null) return 0;
            // 이메일로 아이디 전송
            mailSenderUtil.sendUserId(userDto.getUserId(),userDto.getUserEmail());
        }
        else{   // 패스워드 찾기
            userDto=userMapper.findUserById(value);
            if(userDto==null) return 0;
            // 임시 패스워드 생성 및 회원 패스워드 변경
            String randomPassword=createCode();
            userMapper.modifyUserPassword(userDto.getUserId(),bCryptPasswordEncoder.encode(randomPassword));

            // 이메일로 임시 패스워드 전송
            mailSenderUtil.sendUserPassword(randomPassword,userDto.getUserEmail());
        }
        return 1;
    }

    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }
}
