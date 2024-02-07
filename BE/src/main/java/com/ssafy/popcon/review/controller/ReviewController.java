package com.ssafy.popcon.review.controller;

import com.ssafy.popcon.popup.controller.PopupController;
import com.ssafy.popcon.review.dto.ReviewDto;
import com.ssafy.popcon.review.mapper.ReviewMapper;
import com.ssafy.popcon.review.service.ReviewRegisterService;
import com.ssafy.popcon.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ssafy.popcon.util.JWTUtil;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(PopupController.class);

    @Autowired
    private ReviewRegisterService reviewRegisterService;
    private ReviewMapper reviewMapper;
    private ReviewDto reviewDto;

    JWTUtil jwtUtil;


    // 리뷰 등록
    @PostMapping("/{popupId}")
    public ResponseEntity<String> registerPopupWithImages(
            @PathVariable("popupId") int popupId,
            @RequestPart ReviewDto reviewDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception{
        System.out.println("controller!!!!!!!!!");
        System.out.println(reviewDto);
        String problem = reviewRegisterService.registerReviewWithImages(reviewDto, images);

        try {
            if (problem.equals("notExistingPopupUser")) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지와 함께 리뷰 등록에 실패하였습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("리뷰가 이미지와 함께 성공적으로 등록되었습니다.");
    }

    // 리뷰 수정
    @PatchMapping("/{popupId}/{reviewId}")
    public ResponseEntity<String> updatePopupWithImages(
            @PathVariable("popupId") int popupId,
            @PathVariable("reviewId") int reviewId,
            @RequestPart ReviewDto updatedReviewDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestHeader("Authorization") String token){
        try {
            String problem = reviewRegisterService.updateReviewWithImages(popupId, token, reviewId, updatedReviewDto, images);

            if ("notExistingPopupUser".equals(problem)) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
            } else if ("notExistingReview".equals(problem)) {
                return new ResponseEntity<>("수정하려는 리뷰가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
            } else if ("differentUser".equals(problem)) {
                return new ResponseEntity<>("유저가 다릅니다.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.status(HttpStatus.OK).body("리뷰가 이미지와 함께 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 수정에 실패하였습니다.");
        }
    }



    // 리뷰 삭제
    @DeleteMapping("/{popupId}/{reviewId}")
    public ResponseEntity<String> deletePopupWithImages(
            @PathVariable("popupId") int popupId,
            @PathVariable("reviewId") int reviewId,
            @RequestHeader("Authorization") String token){
        try {
            String problem = reviewRegisterService.deleteReviewWithImages(token, popupId, reviewId);

            if ("notExistingPopupUser".equals(problem)) {
                return new ResponseEntity<>("존재하지 않는 팝업이거나 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
            } else if ("notExistingReview".equals(problem)) {
                return new ResponseEntity<>("삭제하려는 리뷰가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
            } else if ("unauthorizedAccess".equals(problem)) {
                return new ResponseEntity<>("해당 리뷰에 대한 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.status(HttpStatus.OK).body("리뷰가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 삭제에 실패하였습니다.");
        }
    }

    // 리뷰 좋아요 추가
    @PostMapping("/{popupId}/{reviewId}/recommend")
    public ResponseEntity<String> addReviewRecommend(
            @PathVariable("popupId") int popupId,
            @PathVariable("reviewId") int reviewId,
            @RequestHeader("Authorization") String token) throws Exception{

        String problemGoods = reviewRegisterService.addLikeToReview(popupId, reviewId, token);

        try {
            if(problemGoods.equals("notExistingPopupUser")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("존재하지 않는 팝업이거나 존재하지 않은 유저입니다.");
            }
            else if (problemGoods.equals("alreadyGoods")) {
                return new ResponseEntity<>("이미 좋아요를 한 상태입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body("리뷰 좋아요가 추가되었습니다.");
    }

    // 리뷰 좋아요 삭제
    @DeleteMapping("/{popupId}/{reviewId}/recommend")
    public ResponseEntity<String> removeReviewRecommend(
            @PathVariable("popupId") int popupId,
            @PathVariable("reviewId") int reviewId,
            @RequestHeader("Authorization") String token) throws Exception{
        String problemGoods = reviewRegisterService.cancelLikeToReview(popupId, reviewId, token);

        try {
            if(problemGoods.equals("notExistingPopupUser")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("존재하지 않는 팝업이거나 존재하지 않은 유저입니다.");
            }
            else if (problemGoods.equals("alreadyCancel")) {
                return new ResponseEntity<>("이미 좋아요 취소를 한 상태입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body("리뷰 좋아요가 삭제되었습니다.");
    }

    // 리뷰 좋아요 수 조회
    @GetMapping("/{popupId}/{reviewId}/recommend/count")
    public ResponseEntity<Integer> getReviewRecommendCount(
            @PathVariable("popupId") int popupId,
            @PathVariable("reviewId") int reviewId) {
        try {
            int count = reviewRegisterService.getRecommendCount(reviewId);
            return ResponseEntity.status(HttpStatus.OK).body(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1); // 에러 발생 시 -1 리턴
        }
    }
}
