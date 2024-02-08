package com.ssafy.popcon.recommend.popbti.controller;

import com.ssafy.popcon.recommend.popbti.dto.PopbtiDto;
import com.ssafy.popcon.recommend.popbti.service.PopbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommends/popbti")
@RequiredArgsConstructor
public class PopbtiController {

    private final PopbtiService popbtiService;

    @GetMapping
    public ResponseEntity<?> resultList(@RequestParam int code) throws Exception {
        List<PopbtiDto> popbtiDtos=popbtiService.findPopbti(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popbtiDtos);
    }
}
