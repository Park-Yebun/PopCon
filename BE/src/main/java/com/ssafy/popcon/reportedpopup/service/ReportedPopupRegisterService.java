package com.ssafy.popcon.reportedpopup.service;

import com.ssafy.popcon.reportedpopup.dto.ReportedPopupDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupImageDto;
import com.ssafy.popcon.reportedpopup.mapper.ReportedPopupMapper;
import com.ssafy.popcon.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportedPopupRegisterService {

    private final ReportedPopupMapper reportedPopupMapper;
    private final S3UploadUtil s3UploadUtil;

    private static final Logger logger = LoggerFactory.getLogger(ReportedPopupRegisterService.class);

    public List<ReportedPopupDto> getReportedPopup() {
        try {
            return reportedPopupMapper.getReportedPopup();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving reported popups", e);
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to retrieve reported popups", e);
        }
    }

    @Transactional
    public void reportedRegisterPopup(ReportedPopupDto reportedPopupDto) {
        try {
            reportedPopupMapper.registerReportedPopup(reportedPopupDto);
            System.out.println("여긴가");
            logger.info("제보된 팝업 등록 성공 : {}", reportedPopupDto);
        } catch (Exception e) {
            logger.error("제보된 팝업 등록 실패", e);

            throw new RuntimeException("Fail 제보된 팝업 등록", e);
        }
    }

    public ReportedPopupDto getReportedPopupDetails(int reportedPopupId) {
        try {
            return reportedPopupMapper.getReportedPopupDetails(reportedPopupId);
        }catch (Exception e) {
            logger.error("Failed to retrieve reportedpopup details", e);
            throw new RuntimeException("Failed to retrieve reportedpopup details");
        }
    }

    // 팝업 이미지 등록 코드
    @Transactional
    public void registerReportedPopupImage(int reportedPopupId, MultipartFile file) {
        try {
            // 이미지 업로드
            if (file != null) {
                System.out.println(file);
                String imagePath = s3UploadUtil.upload(file, "reportedPopupImagedir");

                // 이미지 업로드 후에 파일 경로를 가지고 PopupImageDto 객체 생성
                ReportedPopupImageDto reportedPopupImageDto = new ReportedPopupImageDto();
                reportedPopupImageDto.setReportedPopupId(reportedPopupId);
                reportedPopupImageDto.setReportedPopupImgPath(imagePath);

                // 이미지 등록을 위해 Mapper의 메서드 호출
                reportedPopupMapper.registerReportedPopupImage(reportedPopupImageDto);

                logger.info("Popup image registered successfully: {}", reportedPopupImageDto);
            } else {
                logger.warn("No file provided for popup image registration.");
            }
        } catch (Exception e) {
            logger.error("Failed to register popup image", e);
            // 예외가 발생할 경우 롤백이 수행됩니다.
            throw new RuntimeException("Failed to register popup image", e);
        }
    }

    // 팝업에 속한 모든 이미지 조회
    public List<ReportedPopupDto> getReportedPopupImagesByPopupId(int ReportedPopupId) {
        try {
            return reportedPopupMapper.getReportedPopupImagesByPopupId(ReportedPopupId);
        } catch (Exception e) {
            logger.error("Failed to retrieve popup images", e);
            throw new RuntimeException("Failed to retrieve popup images");
        }
    }

    // 팝업 이미지 조회
    @Transactional(readOnly = true)
    public ReportedPopupImageDto getReportedPopupImage(int reportedPopupImageId) {
        try {
            // 이미지 조회를 위해 Mapper의 메서드 호출
            return reportedPopupMapper.getReportedPopupImage(reportedPopupImageId);
        } catch (Exception e) {
            logger.error("Failed to get popup image", e);
            throw new RuntimeException("Failed to get popup image", e);
        }
    }


}
