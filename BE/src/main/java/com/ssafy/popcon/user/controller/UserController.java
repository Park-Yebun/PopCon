package com.ssafy.popcon.user.controller;

import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRegisterService userRegisterService;

    // 회원가입
    @PostMapping
    public ResponseEntity<?> userAdd(@RequestPart("userDto") UserDto userDto, @RequestPart(value="file",required=false) MultipartFile file) throws Exception{
        logger.debug("join userDto : {}",userDto);

        userRegisterService.addUser(userDto,file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("회원가입이 완료되었습니다.");
    }

    // 중복 체크
    @PostMapping("/duplicate")
    public ResponseEntity<?> userAdd(@RequestBody Map<String,String> map) throws Exception{

        int result=userRegisterService.findDuplicate(map);

        if(result==0){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("사용 가능한 "+map.get("type")+" 입니다.");
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("이미 존재하는 "+map.get("type")+" 입니다.");
        }
    }

}
