package com.ssafy.popcon.recommend.popbti.service;

import com.ssafy.popcon.recommend.popbti.dto.PopbtiDto;
import com.ssafy.popcon.recommend.popbti.mapper.PopbtiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopbtiService {

    private final PopbtiMapper popbtiMapper;

    public List<PopbtiDto> findPopbti(int code) throws Exception {
        return popbtiMapper.findPopbti(code);
    }
}
