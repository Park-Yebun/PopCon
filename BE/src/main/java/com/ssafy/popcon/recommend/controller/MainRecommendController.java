package com.ssafy.popcon.recommend.controller;

import com.ssafy.popcon.recommend.ai.service.AiService;
import com.ssafy.popcon.recommend.dto.RecommendDto;
import com.ssafy.popcon.recommend.popbti.service.PopbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommends")
public class MainRecommendController {      // 추천 페이지 최초 진입 컨트롤러

    private final AiService aiService;

    @GetMapping
    public ResponseEntity<?> recommendList(@RequestHeader("Authorization") String token) throws Exception {

        Map<String,List<RecommendDto>> result=new HashMap<>();

        List<RecommendDto> aiDtos=aiService.findUserAiResult(token);
        result.put("ai", aiDtos);

        // 좋아요 기반 추천 목록
        result.put("good", aiDtos);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);

    }
}
