package com.ssafy.popcon.board.faq.service;

import com.ssafy.popcon.board.faq.dto.FaqDto;
import com.ssafy.popcon.board.faq.mapper.FaqMapper;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqMapper faqMapper;
    private final JWTUtil jwtUtil;

    // 등록
    public String addFaq(FaqDto faqDto, String token) throws Exception {
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){
            return "not allowed";
        }

        faqMapper.addFaq(faqDto);
        return "faq added";
    }

    // 조회
    public List<FaqDto> findFaqs() throws Exception {
        return faqMapper.findFaqs();
    }

    // 수정
    public String modifyFaq(int faqId, FaqDto faqDto, String token) throws Exception {
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){   // 관리자 권한이 아님
            return "not allowed";
        }

        if(faqMapper.findFaq(faqId)==null) {    // 없는 게시글
            return "no content";
        }

        faqMapper.modifyFaq(faqDto);
        return "ok";    // 수정 완료
    }

    // 삭제
    public String removeFaq(int faqId, String token) throws Exception {
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){   // 관리자 권한이 아님
            return "not allowed";
        }

        if(faqMapper.findFaq(faqId)==null) {    // 없는 게시글
            return "no content";
        }

        faqMapper.removeFaq(faqId);
        return "ok";    // 삭제 완료
    }
}
