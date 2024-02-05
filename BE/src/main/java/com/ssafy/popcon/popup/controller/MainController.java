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

import java.util.List;

@RestController
@RequestMapping("/home")
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired
    private MainService mainService;

//    @GetMapping
//    public ResponseEntity<?> getMainPage() throws Exception {
//        List<String,Object>
//    }

    // 좋아요가 가장 많은 3개 슬라이드, 마감 임박 리스트
    @GetMapping
    public ResponseEntity<List<PopupDto>> getPopupImageGood3() {
        try {
            List<PopupDto> popups = mainService.getPopupImageGood3();
            return ResponseEntity.status(HttpStatus.OK).body(popups);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all popups", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/end")
    public ResponseEntity<List<PopupDto>> getPopupEnd() {
        try {
            List<PopupDto> popups = mainService.getPopupEnd();
            return ResponseEntity.status(HttpStatus.OK).body(popups);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all popups", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
