package com.ssafy.popcon.recommend.popbti.service;

import com.ssafy.popcon.recommend.dto.RecommendDto;
import com.ssafy.popcon.recommend.popbti.mapper.PopbtiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopbtiService {

    private final PopbtiMapper popbtiMapper;

    public List<RecommendDto> findPopbti(int code) throws Exception {
        return popbtiMapper.findPopbti(code);
    }
}
