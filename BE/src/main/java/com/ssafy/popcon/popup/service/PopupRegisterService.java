package com.ssafy.popcon.popup.service;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.popup.dto.PopupRecommendDto;
import com.ssafy.popcon.popup.mapper.PopupMapper;
import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.popcon.util.JWTUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopupRegisterService {

    private final PopupMapper popupMapper;
    private final JWTUtil jwtUtil;

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

    // 팝업 하나의 세부 정보를 조회하는 코드
    public PopupDto getPopupDetails(int popupId) {
        try {
            return popupMapper.getPopupDetails(popupId);
        } catch (Exception e) {
            logger.error("Failed to retrieve popup details", e);
            throw new RuntimeException("Failed to retrieve popup details");
        }
    }

    // 팝업 및 이미지 등록 코드
    @Transactional
    public String registerPopupWithImages(PopupDto popupDto, List<MultipartFile> imageFiles) {
        try {
            // 사용자 타입 확인
            UserDto existingUser = popupMapper.getUserByIdAndType(popupDto.getUserId());
            System.out.println(existingUser);

            if (existingUser.getUserId() == null || "GENERAL".equals(existingUser.getUserType())) {
                // 팝업이 존재하지 않거나 사용자가 존재하지 않거나 사용자 타입이 CORP 아닐 경우 처리
                System.out.println("존재하지 않는 유저이거나, 사용자 타입이 GENERAL입니다.");
                return "notExistingPopupUser";
            }

            // 여기서 필요에 따라 팝업 데이터의 유효성 검사 등을 수행할 수 있습니다.
            // 팝업 등록을 위해 Mapper의 메서드 호출
            popupMapper.registerPopup(popupDto);

            // 등록된 팝업의 ID를 가져오기
            int popupId = popupDto.getPopupId();
            System.out.println(popupId);

            logger.info("Popup registered successfully: {}", popupDto);

            // 이미지 업로드 및 등록
            if (imageFiles != null && !imageFiles.isEmpty()) {
                for (MultipartFile file : imageFiles) {
                    String imagePath = s3UploadUtil.upload(file, "popupImagedir");

                    // 이미지 업로드 후에 파일 경로를 가지고 PopupImageDto 객체 생성
                    PopupImageDto popupImageDto = new PopupImageDto();
                    popupImageDto.setPopupId(popupId); // 방금 등록한 팝업의 ID 사용
                    popupImageDto.setPopupImagePath(imagePath);

                    // 이미지 등록을 위해 Mapper의 메서드 호출
                    popupMapper.registerPopupImage(popupImageDto);

                    logger.info("Popup image registered successfully: {}", popupImageDto);
                }
            } else {
                logger.warn("No files provided for popup images registration.");
            }
        } catch (Exception e) {
            logger.error("Failed to register popup with images", e);
            // 예외가 발생할 경우 롤백이 수행됩니다.
            throw new RuntimeException("Failed to register popup with images", e);
        }
        return "noProblem";
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

    // 특정 팝업에 좋아요 추가
    public String addLikeToPopup(int popupId, String userId) throws Exception {
        // 1. popup_id가 존재하는지 확인
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PopupDto existingPopup = popupMapper.getPopupById(popupId);
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        UserDto existingUser = popupMapper.getUserById(userId);

        if (existingPopup == null || existingUser == null) {
            // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
            System.out.println("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.");
            return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
        }

        try {
            // 2. 중복 확인
            PopupRecommendDto duplicateCheckDto = new PopupRecommendDto();
            duplicateCheckDto.setPopupId(popupId);
            duplicateCheckDto.setUserId(userId);

            int duplicateCount = popupMapper.duplicatePopupRecommend(duplicateCheckDto);

            if (duplicateCount > 0) {
                // 이미 좋아요를 한 경우 예외 처리 또는 원하는 동작 수행
                System.out.println("이미 좋아요를 한 상태입니다.");
                return "alreadyGoods"; // 또는 예외를 던지거나 원하는 대응을 수행
            }

            // 3. popup_like를 증가시키는 업데이트 쿼리 수행
            popupMapper.addLikeToPopup(popupId);

            // 4. popup_recommend 테이블에 사용자와 팝업 정보 추가
            PopupRecommendDto popupRecommendDto = new PopupRecommendDto();
            popupRecommendDto.setPopupId(popupId);
            popupRecommendDto.setUserId(userId);

            // 5. 중복 없이 좋아요 삽입
            popupMapper.addPopupRecommend(popupRecommendDto);
        } catch (Exception e) {
            // 중복 발생 시 처리할 내용
            System.out.println("중복된 좋아요는 할 수 없습니다.");
            // 또는 원하는 로깅 등의 작업 수행 가능
            throw new Exception("중복된 좋아요는 할 수 없습니다.");
        }
        return "noProblem";
    }

    // 특정 팝업에 좋아요 취소
    public String cancelLikeToPopup(int popupId, String userId) throws Exception {
        // 팝업에 대한 비즈니스 로직 수행
        PopupDto existingPopup = popupMapper.getPopupById(popupId);
        UserDto existingUser = popupMapper.getUserById(userId);

        if (existingPopup == null || existingUser == null) {
            // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
            System.out.println("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.");
            return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
        }

        try {
            // 2. 중복 확인
            PopupRecommendDto duplicateCheckDto = new PopupRecommendDto();
            duplicateCheckDto.setPopupId(popupId);
            duplicateCheckDto.setUserId(userId);

            int duplicateCount = popupMapper.duplicatePopupRecommend(duplicateCheckDto);

            if (duplicateCount == 0) {
                // 이미 좋아요를 한 경우 예외 처리 또는 원하는 동작 수행
                System.out.println("이미 좋아요 취소 한 상태입니다.");
                return "alreadyCancel"; // 또는 예외를 던지거나 원하는 대응을 수행
            }
            // 1. popup_like를 감소시키는 업데이트 쿼리 수행
            popupMapper.cancelLikeToPopup(popupId);

            // 2. popup_recommend 테이블에서 해당 사용자와 팝업 정보 삭제
            PopupRecommendDto popupRecommendDto = new PopupRecommendDto();
            popupRecommendDto.setPopupId(popupId);
            popupRecommendDto.setUserId(userId);
            popupMapper.deletePopupRecommend(popupRecommendDto);

        } catch (Exception e) {
            // 중복 발생 시 처리할 내용
            System.out.println("중복된 좋아요 취소는 할 수 없습니다.");
            // 또는 원하는 로깅 등의 작업 수행 가능
            throw new Exception("중복된 좋아요 취소는 할 수 없습니다.");
        }
        return "noProblem";
    }
}
