package com.ssafy.popcon.reportedpopup.controller;

import com.ssafy.popcon.popup.controller.PopupController;
import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupImageDto;
import com.ssafy.popcon.reportedpopup.service.ReportedPopupRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/popups/reported")
public class ReportedPopupController {

    private static final Logger logger = LoggerFactory.getLogger(ReportedPopupController.class);

    @Autowired
    private ReportedPopupRegisterService reportedPopupRegisterService;

    @GetMapping
    public ResponseEntity<List<ReportedPopupDto>> getReportedPopup() {
        try {
            List<ReportedPopupDto> reportedPopups = reportedPopupRegisterService.getReportedPopup();
            return ResponseEntity.status(HttpStatus.OK).body(reportedPopups);
        } catch (Exception e) {
            logger.error("제보된 팝업을 불러오는 코드 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<String> registerReportedPopup(@ModelAttribute ReportedPopupDto reportedPopupDto) {
        try {
            reportedPopupRegisterService.reportedRegisterPopup(reportedPopupDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("제보된 팝업 등록 성공");
        } catch (Exception e) {
            System.out.println("jwdjwjawkfkaekengksn");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("제보된 팝업 등록 실패");
        }
    }

    // 팝업 세부정보 한 개씩 보는
    @GetMapping("/{reportedPopupId}")
    public ResponseEntity<ReportedPopupDto> getReportedPopupDetails(@PathVariable int reportedPopupId) {
        try {
            ReportedPopupDto reportedPopupDetails = reportedPopupRegisterService.getReportedPopupDetails(reportedPopupId);
            if (reportedPopupDetails != null) {
                return ResponseEntity.status(HttpStatus.OK).body(reportedPopupDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 팝업 이미지 등록
    @PostMapping("/{reportedPopupId}/images")
    public ResponseEntity<?> registerReportedPopupImage(
            @PathVariable int reportedPopupId,
            @RequestPart(value="file", required=false) MultipartFile file) throws Exception {
        try {
            System.out.println(file);
            // 이미지 등록 서비스 호출
            reportedPopupRegisterService.registerReportedPopupImage(reportedPopupId, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Popup image registered successfully.");
        } catch (Exception e) {
            // 예외 처리 로직 추가
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register popup image.");
        }
    }

    // 팝업에 속한 모든 이미지 조회
    @GetMapping("/{reportedPopupId}/images")
    public ResponseEntity<List<ReportedPopupDto>> getPopupImages(@PathVariable int reportedPopupId) {
        try {
            List<ReportedPopupDto> reportedPopupImages = reportedPopupRegisterService.getReportedPopupImagesByPopupId(reportedPopupId);
            return ResponseEntity.status(HttpStatus.OK).body(reportedPopupImages);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup images", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 팝업 이미지 조회
    @GetMapping("{reportedPopupId}/images/{reportedPopupImgId}")
    public ResponseEntity<ReportedPopupImageDto> getPopupImage(@PathVariable int reportedPopupId, @PathVariable int reportedPopupImgId) {
        try {
            ReportedPopupImageDto reportedPopupImageDto = reportedPopupRegisterService.getReportedPopupImage(reportedPopupImgId);
            if (reportedPopupImageDto != null && reportedPopupImageDto.getReportedPopupId() == reportedPopupId) {
                return ResponseEntity.status(HttpStatus.OK).body(reportedPopupImageDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup image", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
