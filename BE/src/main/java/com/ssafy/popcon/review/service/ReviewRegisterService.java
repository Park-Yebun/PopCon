package com.ssafy.popcon.review.service;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupRecommendDto;
import com.ssafy.popcon.popup.mapper.PopupMapper;
import com.ssafy.popcon.popup.service.PopupRegisterService;
import com.ssafy.popcon.review.dto.ReviewDto;
import com.ssafy.popcon.review.dto.ReviewImageDto;
import com.ssafy.popcon.review.dto.ReviewRecommendDto;
import com.ssafy.popcon.review.mapper.ReviewMapper;
import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.util.JWTUtil;
import com.ssafy.popcon.util.S3UploadUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewRegisterService {

    private final ReviewMapper reviewMapper;
    private final PopupMapper popupMapper;
    private final JWTUtil jwtUtil;

    private final S3UploadUtil s3UploadUtil;
    private static final Logger logger = LoggerFactory.getLogger(PopupRegisterService.class);


    public String registerReviewWithImages(ReviewDto reviewDto, List<MultipartFile> imageFiles) throws Exception {
        try {
            // 1. popup_id가 존재하는지 확인
            UserDto existingUser = reviewMapper.getUserById(reviewDto.getUserId());
            PopupDto existingPopup = reviewMapper.getPopupById(reviewDto.getPopupId());

            if (existingPopup == null || existingUser == null) {
                // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
                System.out.println("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.");
                return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
            }
            // 여기서 필요에 따라 팝업 데이터의 유효성 검사 등을 수행할 수 있습니다.
            // 팝업 등록을 위해 Mapper의 메서드 호출
            reviewMapper.registerReview(reviewDto);

            // 등록된 팝업의 ID를 가져오기
            int reviewId = reviewDto.getReviewId();
            System.out.println(reviewId);

            logger.info("Popup registered successfully: {}", reviewDto);

            // 이미지 업로드 및 등록
            if (imageFiles != null && !imageFiles.isEmpty()) {
                for (MultipartFile file : imageFiles) {
                    String imagePath = s3UploadUtil.upload(file, "reviewImagedir");

                    // 이미지 업로드 후에 파일 경로를 가지고 PopupImageDto 객체 생성
                    ReviewImageDto reviewImageDto = new ReviewImageDto();
                    reviewImageDto.setReviewId(reviewId); // 방금 등록한 팝업의 ID 사용
                    reviewImageDto.setReviewImagePath(imagePath);

                    // 이미지 등록을 위해 Mapper의 메서드 호출
                    reviewMapper.registerReviewImage(reviewImageDto);

                    logger.info("Popup image registered successfully: {}", reviewImageDto);
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




    // 리뷰 수정
    public String updateReviewWithImages(int popupId, String token, int reviewId, ReviewDto updatedReviewDto, List<MultipartFile> imageFiles) throws Exception {
        try {
            // 1. popup_id와 review_id가 존재하는지 확인
            PopupDto existingPopup = reviewMapper.getPopupById(popupId);
            ReviewDto existingReview = reviewMapper.getReviewById(popupId, reviewId);

            String userId=jwtUtil.getUsername(token.split(" ")[1]);


            if (existingPopup == null || existingReview == null) {
                System.out.println("존재하지 않는 팝업이거나 존재하지 않는 리뷰입니다.");
                return "notExistingPopupUser";
            }

            // 2. 유저가 기존 유저와 같은지 확인
            if (!existingReview.getUserId().equals(userId)) {
                System.out.println("유저가 기존 유저와 다릅니다.");
                return "differentUser"; // 또는 예외를 던지거나 원하는 대응을 수행
            }

            // 2. 기존 리뷰 정보를 불러와서 업데이트할 정보를 추가
            existingReview.setReviewContent(updatedReviewDto.getReviewContent());
            existingReview.setReviewScore(updatedReviewDto.getReviewScore());
            // 나머지 필드도 업데이트

            // 3. 리뷰 수정을 위해 Mapper의 메서드 호출
            reviewMapper.updateReview(existingReview);

            // 4. 이미지 업로드 및 등록 로직은 이하 동일
            // 이미지 업로드 및 등록
            if (imageFiles != null && !imageFiles.isEmpty()) {
                for (MultipartFile file : imageFiles) {
                    String imagePath = s3UploadUtil.upload(file, "reviewImagedir");

                    // 이미지 업로드 후에 파일 경로를 가지고 ReviewImageDto 객체 생성
                    ReviewImageDto reviewImageDto = new ReviewImageDto();
                    reviewImageDto.setReviewId(reviewId); // 방금 수정한 리뷰의 ID 사용
                    reviewImageDto.setReviewImagePath(imagePath);

                    // 이미지 등록을 위해 Mapper의 메서드 호출
                    reviewMapper.updateReviewImage(reviewImageDto);

                    logger.info("Review image updated successfully: {}", reviewImageDto);
                }
            } else {
                logger.warn("No files provided for review images update.");
            }

            // 나중에 필요하면 수정 가능
        } catch (Exception e) {
            logger.error("Failed to update review with images", e);
            throw new RuntimeException("Failed to update review with images", e);
        }
        return "noProblem";
    }

    // 리뷰 삭제
    public String deleteReviewWithImages(String token, int popupId, int reviewId) throws Exception {
        try {
            // 1. popup_id와 review_id가 존재하는지 확인
            String userId=jwtUtil.getUsername(token.split(" ")[1]);

            PopupDto existingPopup = reviewMapper.getPopupById(popupId);
            ReviewDto existingReview = reviewMapper.getReviewById(popupId, reviewId);
            UserDto existingUser = reviewMapper.getUserById(userId);

            if (existingPopup == null || existingUser == null) {
                return "notExistingPopupUser";
            }

            if (existingReview == null) {
                return "notExistingReview";
            }

//          현재 사용자의 ID와 리뷰를 작성한 사용자의 ID 비교
            if (!Objects.equals(existingReview.getUserId(), userId)) {
                return "unauthorizedAccess";
            }

            // 리뷰 삭제를 위해 Mapper의 메서드 호출
            reviewMapper.deleteReview(reviewId);

            logger.info("Review deleted successfully: {}", reviewId);

            // on delete 가 없으면 이미지 삭제 추가해야함
        } catch (Exception e) {
            logger.error("Failed to delete review with images", e);
            // 예외가 발생할 경우 롤백이 수행됩니다.
            throw new RuntimeException("Failed to delete review with images", e);
        }
        return "noProblem";
    }

    // 리뷰 좋아요
    public String addLikeToReview(int popupId, int reviewId, String token) {
        String userId = jwtUtil.getUsername(token.split(" ")[1]);
        ReviewDto existingReview = reviewMapper.getReviewById(popupId, reviewId);
        UserDto existingUser = popupMapper.getUserById(userId);

        if (existingReview == null || existingUser == null) {
            // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
            System.out.println("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.");
            return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
        }

        try {
            // 2. 중복 확인
            ReviewRecommendDto duplicateCheckDto = new ReviewRecommendDto();
            duplicateCheckDto.setReviewId(reviewId);
            duplicateCheckDto.setUserId(userId);

            int duplicateCount = reviewMapper.duplicateReviewRecommend(duplicateCheckDto);

            if (duplicateCount > 0) {
                // 이미 좋아요를 한 경우 예외 처리 또는 원하는 동작 수행
                System.out.println("이미 좋아요를 한 상태입니다.");
                return "alreadyGoods"; // 또는 예외를 던지거나 원하는 대응을 수행
            }
        }catch (Exception e) {
            logger.error("Failed to add review recommendation", e);
            throw new RuntimeException("Failed to add review recommendation", e);
        }
        reviewMapper.addLikeToReview(existingReview);   // 좋아요 수 + 1

        ReviewRecommendDto reviewRecommendDto = new ReviewRecommendDto();
        reviewRecommendDto.setReviewId(reviewId);
        reviewRecommendDto.setUserId(userId);

        reviewMapper.addReviewRecommend(reviewRecommendDto);

        logger.info("Review recommendation added successfully: {}", reviewRecommendDto);

        return "noProblem";
    }

    // 특정 팝업에 좋아요 취소
    public String cancelLikeToReview(int popupId, int reviewId, String token) throws Exception {
        String userId = jwtUtil.getUsername(token.split(" ")[1]);
        ReviewDto existingReview = reviewMapper.getReviewById(popupId, reviewId);
        UserDto existingUser = popupMapper.getUserById(userId);

        if (existingReview == null || existingUser == null) {
            // 팝업이 존재하지 않거나 사용자가 존재하지 않을 경우 예외 처리 또는 원하는 동작 수행
            System.out.println("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.");
            return "notExistingPopupUser"; // 또는 예외를 던지거나 원하는 대응을 수행
        }

        try {
            // 2. 중복 확인
            ReviewRecommendDto duplicateCheckDto = new ReviewRecommendDto();
            duplicateCheckDto.setReviewId(reviewId);
            duplicateCheckDto.setUserId(userId);

            int duplicateCount = reviewMapper.duplicateReviewRecommend(duplicateCheckDto);

            if (duplicateCount == 0) {
                // 이미 좋아요를 한 경우 예외 처리 또는 원하는 동작 수행
                System.out.println("이미 좋아요 취소 한 상태입니다.");
                return "alreadyCancel"; // 또는 예외를 던지거나 원하는 대응을 수행
            }
            // 1. popup_like를 감소시키는 업데이트 쿼리 수행
            Map<String,Object> map=new HashMap<>();
            map.put("popupId",popupId);
            map.put("reviewId",reviewId);
            reviewMapper.cancelLikeToReview(map);

            // 2. popup_recommend 테이블에서 해당 사용자와 팝업 정보 삭제
            ReviewRecommendDto reviewRecommendDto = new ReviewRecommendDto();
            reviewRecommendDto.setReviewId(reviewId);
            reviewRecommendDto.setUserId(userId);
            reviewMapper.deleteReviewRecommend(reviewRecommendDto);

        } catch (Exception e) {
            // 중복 발생 시 처리할 내용
            System.out.println("중복된 좋아요 취소는 할 수 없습니다.");
            // 또는 원하는 로깅 등의 작업 수행 가능
            throw new Exception("중복된 좋아요 취소는 할 수 없습니다.");
        }
        return "noProblem";
    }

    // 리뷰 좋아요 카운트
    public int getRecommendCount(int reviewId) {
        try {
            return reviewMapper.getRecommendCount(reviewId);
        } catch (Exception e) {
            logger.error("Failed to get review recommendation count", e);
            throw new RuntimeException("Failed to get review recommendation count", e);
        }
    }

}
