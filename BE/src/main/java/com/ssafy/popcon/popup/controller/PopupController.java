package com.ssafy.popcon.popup.controller;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupSearchDto;
import com.ssafy.popcon.review.dto.ReviewDto;
import com.ssafy.popcon.review.dto.ReviewImageDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.popup.service.PopupRegisterService;
import com.ssafy.popcon.review.service.ReviewRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/popups")
public class PopupController {

    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired private PopupRegisterService popupRegisterService;


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

    // 이미지와 함께 팝업 등록하는 엔드포인트
    @PostMapping()
    public ResponseEntity<String> registerPopupWithImages(
            @RequestPart PopupDto popupDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception{
        String problem = popupRegisterService.registerPopupWithImages(popupDto, images);

        try {
            if (problem.equals("notExistingPopupUser")) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않는 유저이거나, 사용자 타입이 CORP 아닙니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지와 함께 팝업 등록에 실패하였습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("팝업이 이미지와 함께 성공적으로 등록되었습니다.");
    }

    // 팝업 세부정보 한 개씩 보는
    @GetMapping("/{popupId}")
    public ResponseEntity<PopupDto> getPopupDetails(@PathVariable int popupId) {
        try {
            PopupDto popupDetails = popupRegisterService.getPopupDetailsWithCount(popupId);
//            List<ReviewDto> popupReviews = reviewRegisterService.getReview(popupId);
//            List<ReviewImageDto> popupReviewImages = reviewRegisterService.getReviewImage();

            if (popupDetails != null) {
//                popupDetails.setReviews(popupReviews);  // 리뷰 정보 설정
                return ResponseEntity.status(HttpStatus.OK).body(popupDetails);
            } else {
                logger.error("Error occurred while retrieving popup details");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while retrieving popup details", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 특정 팝업의 리뷰 조회 (9개만 반환)
    @GetMapping("/{popupId}/reviews")
    public ResponseEntity<?> getPopupReviews(@PathVariable int popupId) throws Exception {
        List<ReviewDto> reviewDtos=popupRegisterService.getReviewTop9(popupId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewDtos);
    }

    // 팝업에 속한 모든 이미지 조회
//    @GetMapping("/{popupId}/images")
//    public ResponseEntity<List<PopupDto>> getPopupImages(@PathVariable int popupId) {
//        try {
//            List<PopupDto> popupImages = popupRegisterService.getPopupImagesByPopupId(popupId);
//            return ResponseEntity.status(HttpStatus.OK).body(popupImages);
//        } catch (Exception e) {
//            logger.error("Error occurred while retrieving popup images", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    // 특정 팝업 이미지 조회
//    @GetMapping("/{popupId}/images/{imageId}")
//    public ResponseEntity<PopupImageDto> getPopupImage(@PathVariable int popupId, @PathVariable int imageId) {
//        try {
//            PopupImageDto popupImage = popupRegisterService.getPopupImage(imageId);
//            if (popupImage != null && popupImage.getPopupId() == popupId) {
//                return ResponseEntity.status(HttpStatus.OK).body(popupImage);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (Exception e) {
//            logger.error("Error occurred while retrieving popup image", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    // 특정 팝업에 좋아요 추가
    @PostMapping("/{popupId}/like")
    public ResponseEntity<String> addLikeToPopup(@PathVariable int popupId, @RequestParam String userId) throws Exception {
        String problemGoods = popupRegisterService.addLikeToPopup(popupId, userId);
        try {
            if (problemGoods.equals("notExistingPopupUser")) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않은 유저입니다.", HttpStatus.BAD_REQUEST);
            }
            else if (problemGoods.equals("alreadyGoods")) {
                return new ResponseEntity<>("이미 좋아요를 한 상태입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("좋아요가 추가되었습니다.", HttpStatus.OK);
    }

    // 특정 팝업에 좋아요 취소
    @DeleteMapping("/{popupId}/like")
    public ResponseEntity<String> cancelLikeToPopup(@PathVariable int popupId, @RequestParam String userId) throws Exception{
        String problem = popupRegisterService.cancelLikeToPopup(popupId, userId);
        try {
            if (problem.equals("notExistingPopupUser")) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않은 유저입니다.", HttpStatus.BAD_REQUEST);
            }
            else if (problem.equals("alreadyCancel")) {
                return new ResponseEntity<>("이미 좋아요취소를 한 상태입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("좋아요가 취소되었습니다.", HttpStatus.OK);
    }

    // 팝업 검색
    @GetMapping("/search")
    public ResponseEntity<List<PopupSearchDto>> getPopupByCategory(@RequestParam Map<String,String> parameter) throws Exception{

        // startDate , endDate, area, status, category
        List<PopupSearchDto> popupSearchDtos=popupRegisterService.getPopupByFilter(parameter);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popupSearchDtos);
    }

    // 좋아요 클릭 여부 반환
    @GetMapping("/{popupId}/like")
    public ResponseEntity<?> getLikeOrNot(@PathVariable int popupId,@RequestHeader("Authorization") String token) throws Exception {
        int like=popupRegisterService.getLikeOrNot(popupId, token);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(like);
    }

}