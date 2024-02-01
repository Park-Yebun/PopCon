package com.ssafy.popcon.popup.controller;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.popup.service.PopupRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/popups")
public class PopupController {

    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired
    private PopupRegisterService popupRegisterService;

    //  팝업 전부를 불러오는 코드
    @GetMapping
    public ResponseEntity<List<PopupDto>> getPopup() {
        try {
            List<PopupDto> popups = popupRegisterService.getPopup();
            return ResponseEntity.status(HttpStatus.OK).body(popups);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all popups", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 팝업 등록
    @PostMapping()
    public ResponseEntity<String> registerPopup(@ModelAttribute PopupDto popupDto) {
        try {
            // Popup 등록 서비스 호출
            popupRegisterService.registerPopup(popupDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Popup registered successfully.");
        } catch (Exception e) {
            // 예외 처리 로직 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register popup.");
        }
    }

    // 팝업 세부정보 한 개씩 보는
    @GetMapping("/{popupId}")
    public ResponseEntity<PopupDto> getPopupDetails(@PathVariable int popupId) {
        try {
            PopupDto popupDetails = popupRegisterService.getPopupDetails(popupId);
            System.out.println(popupDetails);
            if (popupDetails != null) {
                System.out.println("실행이 된다");
                return ResponseEntity.status(HttpStatus.OK).body(popupDetails);
            } else {
                System.out.println("실행이 안된다");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 팝업 이미지 등록
    @PostMapping("/{popupId}/images")
    public ResponseEntity<?> registerPopupImage(
            @PathVariable int popupId,
            @RequestPart(value="file", required=false) MultipartFile file) throws Exception {
        try {
            System.out.println(file);
            // 이미지 등록 서비스 호출
            popupRegisterService.registerPopupImage(popupId, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Popup image registered successfully.");
        } catch (Exception e) {
            // 예외 처리 로직 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register popup image.");
        }
    }

    // 팝업에 속한 모든 이미지 조회
    @GetMapping("/{popupId}/images")
    public ResponseEntity<List<PopupDto>> getPopupImages(@PathVariable int popupId) {
        try {
            List<PopupDto> popupImages = popupRegisterService.getPopupImagesByPopupId(popupId);
            return ResponseEntity.status(HttpStatus.OK).body(popupImages);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup images", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 팝업 이미지 조회
    @GetMapping("/{popupId}/images/{imageId}")
    public ResponseEntity<PopupImageDto> getPopupImage(@PathVariable int popupId, @PathVariable int imageId) {
        try {
            PopupImageDto popupImage = popupRegisterService.getPopupImage(imageId);
            if (popupImage != null && popupImage.getPopupId() == popupId) {
                return ResponseEntity.status(HttpStatus.OK).body(popupImage);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}