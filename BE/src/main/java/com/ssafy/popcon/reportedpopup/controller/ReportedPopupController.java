package com.ssafy.popcon.reportedpopup.controller;

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

    // 제보 받은 팝업 전부 불러옴
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

    // 제보받은 팝업 이미지와 같이 등록
    @PostMapping()
    public ResponseEntity<String> registerReportedPopupWithImages(
            @RequestPart ReportedPopupDto reportedPopupDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles) {
        String problem = reportedPopupRegisterService.registerReportedPopupWithImages(reportedPopupDto, imageFiles);
        try {
            if (problem.equals("notExistingPopupUser")) {
                return new ResponseEntity<>("존재하지 않은 유저입니다.", HttpStatus.BAD_REQUEST);
            }
            // 서비스 메서드 호출
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("제보된 팝업 및 이미지 등록 실패");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("제보된 팝업 및 이미지 등록 성공");

    }

    // 제보된 팝업 세부정보 한 개씩 보는
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

    // 제보된 팝업에 속한 모든 이미지 조회
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

    // 제보된 특정 팝업 이미지 조회
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
