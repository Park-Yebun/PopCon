package com.ssafy.popcon.board.faq.controller;

import com.ssafy.popcon.board.faq.dto.FaqDto;
import com.ssafy.popcon.board.faq.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faqs")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    // 등록
    @PostMapping
    public ResponseEntity<?> faqAdd(@RequestBody FaqDto faqDto, @RequestHeader("Authorization") String token) throws Exception {
        if(faqService.addFaq(faqDto, token).equals("not allowed")){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("관리자만 접근 가능합니다.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("FAQ 등록이 완료되었습니다.");
    }

    // 목록
    @GetMapping
    public ResponseEntity<?> faqList() throws Exception {
        List<FaqDto> faqDtos=faqService.findFaqs();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(faqDtos);
    }

    // 수정
    @PatchMapping("/{faqId}")
    public ResponseEntity<?> faqModify(@PathVariable int faqId, @RequestBody FaqDto faqDto, @RequestHeader("Authorization") String token) throws Exception {
        String result=faqService.modifyFaq(faqId,faqDto,token);

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
    @DeleteMapping("{faqId}")
    public ResponseEntity<?> faqRemove(@PathVariable int faqId, @RequestHeader("Authorization") String token) throws Exception {
        String result=faqService.removeFaq(faqId,token);

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

    // 상세 조회
//    @GetMapping("/{faqId}")
//    public ResponseEntity<?> faqDetails(@PathVariable int faqId) throws Exception {
//        FaqDto result = faqDetailService.findFaq(faqId);
//
//        if(result==null) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_FOUND)
//                    .body("해당 게시글이 없습니다.");
//        }
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(result);
//    }


}
