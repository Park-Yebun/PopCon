package com.ssafy.popcon.board.notice.controller;

import com.ssafy.popcon.board.faq.dto.FaqDto;
import com.ssafy.popcon.board.notice.dto.NoticeDto;
import com.ssafy.popcon.board.notice.dto.NoticeListDto;
import com.ssafy.popcon.board.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notices")
@CrossOrigin("*")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 등록
    @PostMapping
    public ResponseEntity<?> noticeAdd(@RequestBody NoticeDto noticeDto, @RequestHeader("Authorization") String token) throws Exception {
        if(noticeService.addNotice(noticeDto, token).equals("not allowed")){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("관리자만 접근 가능합니다.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("공지사항 등록이 완료되었습니다.");
    }


    // 목록
    @GetMapping
    public ResponseEntity<?> noticeList() throws Exception {
        List<NoticeListDto> noticeListDtos=noticeService.findNotices();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noticeListDtos);
    }

    // 상세 조회
    @GetMapping("/{noticeId}")
    public ResponseEntity<?> noticeDetails(@PathVariable int noticeId) throws Exception {
        NoticeDto result = noticeService.findNotice(noticeId);

        if(result==null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당 게시글이 없습니다.");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    // 수정
    @PatchMapping("/{noticeId}")
    public ResponseEntity<?> noticeModify(@PathVariable int noticeId, @RequestBody NoticeDto noticeDto, @RequestHeader("Authorization") String token) throws Exception {
        String result=noticeService.modifyNotice(noticeId,noticeDto,token);

        if(result.equals("not allowed")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("관리자만 접근 가능합니다.");
        } else if(result.equals("no content")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당 게시글이 없습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("수정이 완료되었습니다.");
        }
    }

    // 삭제
    @DeleteMapping("{noticeId}")
    public ResponseEntity<?> noticeRemove(@PathVariable int noticeId, @RequestHeader("Authorization") String token) throws Exception {
        String result=noticeService.removeNotice(noticeId,token);

        if(result.equals("not allowed")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("관리자만 접근 가능합니다.");
        } else if(result.equals("no content")) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("해당 게시글이 없습니다.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("삭제가 완료되었습니다.");
        }
    }

}
