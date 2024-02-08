package com.ssafy.popcon.board.notice.service;

import com.ssafy.popcon.board.notice.dto.NoticeDto;
import com.ssafy.popcon.board.notice.dto.NoticeListDto;
import com.ssafy.popcon.board.notice.mapper.NoticeMapper;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final JWTUtil jwtUtil;

    public String addNotice(NoticeDto noticeDto, String token) throws Exception{
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){
            return "not allowed";
        }

        noticeMapper.addNotice(noticeDto);
        return "notice added";
    }

    // 전체 조회
    public List<NoticeListDto> findNotices() throws Exception {
        return noticeMapper.findNotices();
    }

    // 상세 조회
    public NoticeDto findNotice(int noticeId) throws Exception {
        return noticeMapper.findNotice(noticeId);
    }

    // 수정
    public String modifyNotice(int noticeId, NoticeDto noticeDto, String token) throws Exception {
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){   // 관리자 권한이 아님
            return "not allowed";
        }

        if(noticeMapper.findNotice(noticeId)==null) {    // 없는 게시글
            return "no content";
        }

        noticeMapper.modifyNotice(noticeDto);
        return "ok";    // 수정 완료
    }


    public String removeNotice(int noticeId, String token) throws Exception {
        String userType="";

        // token 을 통해 userType 가져오기
        userType=jwtUtil.getRole(token.split(" ")[1]);

        if(!userType.equals("ADMIN")){   // 관리자 권한이 아님
            return "not allowed";
        }

        if(noticeMapper.findNotice(noticeId)==null) {    // 없는 게시글
            return "no content";
        }

        noticeMapper.removeNotice(noticeId);
        return "ok";    // 삭제 완료
    }
}
