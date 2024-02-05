package com.ssafy.popcon.popup.controller;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired
    private MainService mainService;

    // 좋아요가 가장 많은 3개 슬라이드, 마감 임박 리스트
    @GetMapping()
    public ResponseEntity<Map<String, List<PopupDto>>> getHomePopupData() {
        try {
            Map<String, List<PopupDto>> result = new HashMap<>();

            List<PopupDto> popupgood = mainService.getPopupImageGood3();
            List<PopupDto> popupend = mainService.getPopupEnd();

            result.put("popupgood", popupgood);
            result.put("popupend", popupend);

            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving home popup data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
