package com.ssafy.popcon.user.controller;

import com.ssafy.popcon.notification.service.NotificationService;
import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.user.dto.UserModifyDto;
import com.ssafy.popcon.user.service.UserRemoveService;
import com.ssafy.popcon.user.service.UserFindService;
import com.ssafy.popcon.user.service.UserModifyService;
import com.ssafy.popcon.user.service.UserRegisterService;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins="*", allowedHeaders="*")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRegisterService userRegisterService;
    private final UserModifyService userModifyService;
    private final UserFindService userFindService;
    private final UserRemoveService userRemoveService;
    private final NotificationService notificationService;
    private final JWTUtil jwtUtil;

    // 회원가입
    @PostMapping
    public ResponseEntity<?> userAdd(@RequestBody UserDto userDto) throws Exception{
        logger.debug("join userDto : {}",userDto);

        userRegisterService.addUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("회원가입이 완료되었습니다.");
    }

    // 중복 체크
    @PostMapping("/duplicate")
    public ResponseEntity<?> userCheckDetails(@RequestBody Map<String,String> map) throws Exception{

        String result=userRegisterService.findDuplicate(map);

        if(result.equals("not duplicate")){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("사용 가능한 "+map.get("type")+" 입니다.");
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("이미 존재하는 "+map.get("type")+" 입니다.");
        }
    }

    // 회원 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<?> userModify(@PathVariable String userId, @RequestPart(value="file",required=false) MultipartFile multipartFile, @RequestPart UserModifyDto userModifyDto, @RequestHeader("Authorization") String token) throws Exception{

        String result=userModifyService.modifyUser(userId,multipartFile,userModifyDto,token);
        if(result.equals("modify error")){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("회원 정보 수정이 완료되지 않았습니다.");
        }

        // 토큰 재발급
//        token= jwtUtil.createJwt(userModifyDto.getUserId(), userModifyDto.getUserType(),60*60*1000L*24*7);   // 7 days
//        HttpHeaders header = new HttpHeaders();
//        header.add("Authorization", "Bearer " + token); // 새로운 토큰을 헤더를 추가

        return ResponseEntity.ok()
//                .headers(header)
                .body("회원 정보 수정이 완료되었습니다.");
    }

    // 회원 정보 찾기
    @PostMapping("/search")
    public ResponseEntity<?> userFindDetails(@RequestBody Map<String,String> userFindDto) throws Exception {
        // type (찾고자 하는 값) : userId or userPassword , value : 찾는데 쓰이는 값 (아이디라면 이메일 주소, 패스워드라면 아이디)
        String result=userFindService.findUser(userFindDto);

        if(result.equals("no information")) {  // 잘못된 정보
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 회원 정보 입니다.");
        } else { // 정상 작업
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("이메일로 회원 정보가 전송되었습니다.");
        }
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> userFindById(@PathVariable String userId) throws Exception {
        System.out.println("유저 정보 찾기!!!");
        UserDto userDto=userFindService.findUserById(userId);

        if(userDto==null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("일치하는 사용자가 없습니다.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDto);
    }

    // 회원 탈퇴
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> userRemove(@PathVariable String userId) throws Exception {

        String result=userRemoveService.removeUser(userId);

        if(result.equals("remove complete")) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("회원탈퇴가 완료되었습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("사용자 정보가 없습니다.");
        }
    }

    // 로그아웃 -> 디바이스 토큰 삭제하기
    @PostMapping("/logout")
    public ResponseEntity<?> userTokenRemove(@RequestHeader("Authorization") String token) throws Exception{
//        notificationService.removeToken(token);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("로그아웃 되었습니다.");
    }
}
