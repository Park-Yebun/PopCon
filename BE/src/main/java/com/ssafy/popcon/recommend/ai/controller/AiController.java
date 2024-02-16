package com.ssafy.popcon.recommend.ai.controller;

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
@RequestMapping("/recommends/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @GetMapping
    public ResponseEntity<?> resultList(@RequestParam String className, @RequestHeader("Authorization") String token) throws Exception {
        List<RecommendDto> aiDtos = aiService.findAi(className,token);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(aiDtos);
    }

}
