package com.ssafy.popcon.popup.service;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.popup.mapper.PopupMapper;
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
public class PopupRegisterService {

    private final PopupMapper popupMapper;
    private final S3UploadUtil s3UploadUtil;
    private static final Logger logger = LoggerFactory.getLogger(PopupRegisterService.class);

    // 모든 팝업을 조회하는 코드
    public List<PopupDto> getPopup() {
        try {
            // 모든 팝업을 조회하는 MyBatis 매퍼 메서드 호출
            return popupMapper.getPopup();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all popups", e);
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to retrieve popups");
        }
    }

    // 팝업 하나를 등록하는 코드
    @Transactional
    public void registerPopup(PopupDto popupDto) {
        try {
            // 여기서 필요에 따라 팝업 데이터의 유효성 검사 등을 수행할 수 있습니다.

            // 팝업 등록을 위해 Mapper의 메서드 호출
            popupMapper.registerPopup(popupDto);

            logger.info("Popup registered successfully: {}", popupDto);
        } catch (Exception e) {
            logger.error("Failed to register popup", e);
            // 예외가 발생할 경우 롤백이 수행됩니다.
            throw new RuntimeException("Failed to register popup", e);
        }
    }

    // 팝업 하나의 세부 정보를 조회하는 코드
    public PopupDto getPopupDetails(int popupId) {
        try {
            System.out.println("wkqwldklwkdlwqjdkjfkekfkewfnek");
            return popupMapper.getPopupDetails(popupId);
        } catch (Exception e) {
            System.out.println("뭔데데데데ㅔ데데데");
            logger.error("Failed to retrieve popup details", e);
            throw new RuntimeException("Failed to retrieve popup details");
        }
    }


    // 팝업 이미지 등록 코드
    @Transactional
    public void registerPopupImage(int popupId, MultipartFile file) {
        try {
            // 이미지 업로드
            if (file != null) {
                String imagePath = s3UploadUtil.upload(file, "popupImagedir");

                // 이미지 업로드 후에 파일 경로를 가지고 PopupImageDto 객체 생성
                PopupImageDto popupImageDto = new PopupImageDto();
                popupImageDto.setPopupId(popupId);
                popupImageDto.setPopupImagePath(imagePath);

                // 이미지 등록을 위해 Mapper의 메서드 호출
                popupMapper.registerPopupImage(popupImageDto);

                logger.info("Popup image registered successfully: {}", popupImageDto);
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
    public List<PopupDto> getPopupImagesByPopupId(int popupId) {
        try {
            return popupMapper.getPopupImagesByPopupId(popupId);
        } catch (Exception e) {
            logger.error("Failed to retrieve popup images", e);
            throw new RuntimeException("Failed to retrieve popup images");
        }
    }

    // 팝업 이미지 조회
    @Transactional(readOnly = true)
    public PopupImageDto getPopupImage(int popupImageId) {
        try {
            // 이미지 조회를 위해 Mapper의 메서드 호출
            return popupMapper.getPopupImage(popupImageId);
        } catch (Exception e) {
            logger.error("Failed to get popup image", e);
            throw new RuntimeException("Failed to get popup image", e);
        }
    }


}