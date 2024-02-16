package com.ssafy.popcon.recommend.good.service;

import com.ssafy.popcon.recommend.good.mapper.GoodMapper;
import com.ssafy.popcon.recommend.dto.RecommendDto;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodService {

    private final GoodMapper goodMapper;
    private final JWTUtil jwtUtil;

    public List<RecommendDto> findUserGoodResult(String token) throws Exception { // user 의 ai 매칭 결과 찾기
        String userId=jwtUtil.getUsername(token.split(" ")[1]);
        return goodMapper.findUserGoodResult(userId);
    }

}
