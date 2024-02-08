package com.ssafy.popcon.recommend.ai.service;

import com.ssafy.popcon.recommend.ai.mapper.AiMapper;
import com.ssafy.popcon.recommend.dto.RecommendDto;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {

    private final AiMapper aiMapper;
    private final JWTUtil jwtUtil;

    public List<RecommendDto> findAi(String className, String token) throws Exception { // ai 매칭 결과 찾기
        String userId=jwtUtil.getUsername(token.split(" ")[1]);
        aiMapper.saveAiResult(className, userId);
        return aiMapper.findAi(className);
    }


    public List<RecommendDto> findUserAiResult(String token) throws Exception { // user 의 ai 매칭 결과 찾기
        String userId=jwtUtil.getUsername(token.split(" ")[1]);
        return aiMapper.findUserAiResult(userId);
    }
}
