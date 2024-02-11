package com.ssafy.popcon.recommend.good.controller;


import com.ssafy.popcon.recommend.dto.RecommendDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.popcon.recommend.good.service.GoodService;

import java.util.List;

@RestController
@RequestMapping("/recommends/good")
@RequiredArgsConstructor
public class GoodController {

    private final GoodService goodService;

    @GetMapping
    public ResponseEntity<?> resultList(@RequestHeader("Authorization") String token) throws Exception {
        List<RecommendDto> goodDtos = goodService.findUserGoodResult(token);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(goodDtos);
    }

}
