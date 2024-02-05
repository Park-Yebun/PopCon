package com.ssafy.popcon.reportedpopup.service;

import com.ssafy.popcon.reportedpopup.dto.ReportedPopupDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupImageDto;
import com.ssafy.popcon.reportedpopup.mapper.ReportedPopupMapper;
import com.ssafy.popcon.user.dto.UserDto;
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

    // 제보된 팝업 전부 조회
    public List<ReportedPopupDto> getReportedPopup() {
        try {
            return reportedPopupMapper.getReportedPopup();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving reported popups", e);
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to retrieve reported popups", e);
        }
    }

    // 제보된 팝업 이미지와 함께 등록 / 이미지 없어도 가능
    @Transactional
    public String registerReportedPopupWithImages(ReportedPopupDto reportedPopupDto, List<MultipartFile> imageFiles) {
        System.out.println("오류찾기" + reportedPopupDto.getReportedPopupName());
        UserDto existingUser = reportedPopupMapper.getUserById(reportedPopupDto.getUserId());

        if(existingUser == null) {
            // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
            System.out.println("존재하지 않는 유저입니다.");
            return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
        }
        try {
            // 1. 팝업 등록
            reportedPopupMapper.registerReportedPopup(reportedPopupDto);

            // 2. 등록된 팝업의 ID 가져오기
            int reportedPopupId = reportedPopupDto.getReportedPopupId();

            // 3. 이미지 업로드 및 등록
            if (imageFiles != null && !imageFiles.isEmpty()) {
                for (MultipartFile imageFile : imageFiles) {
                    String imagePath = s3UploadUtil.upload(imageFile, "reportedPopupImageDir");

                    // 4. 이미지 등록을 위해 Mapper의 메서드 호출
                    ReportedPopupImageDto reportedPopupImageDto = new ReportedPopupImageDto();
                    reportedPopupImageDto.setReportedPopupId(reportedPopupId);
                    reportedPopupImageDto.setReportedPopupImgPath(imagePath);

                    reportedPopupMapper.registerReportedPopupImage(reportedPopupImageDto);

                    logger.info("Reported popup image registered successfully: {}", reportedPopupImageDto);
                }
            } else {
                logger.warn("No files provided for reported popup images registration.");
            }
        } catch (Exception e) {
            logger.error("Failed to register reported popup with images", e);
            // 예외가 발생할 경우 롤백이 수행됩니다.
            throw new RuntimeException("Failed to register reported popup with images", e);
        }
        return "noProblem";
    }

    // 제보된 팝업 세부정보 조회
    public ReportedPopupDto getReportedPopupDetails(int reportedPopupId) {
        try {
            return reportedPopupMapper.getReportedPopupDetails(reportedPopupId);
        }catch (Exception e) {
            logger.error("Failed to retrieve reportedpopup details", e);
            throw new RuntimeException("Failed to retrieve reportedpopup details");
        }
    }

    // 제보된 팝업에 속한 모든 이미지 조회
    public List<ReportedPopupDto> getReportedPopupImagesByPopupId(int ReportedPopupId) {
        try {
            return reportedPopupMapper.getReportedPopupImagesByPopupId(ReportedPopupId);
        } catch (Exception e) {
            logger.error("Failed to retrieve popup images", e);
            throw new RuntimeException("Failed to retrieve popup images");
        }
    }

    // 제보된 팝업 이미지 조회
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
