package com.ssafy.popcon.review.mapper;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.review.dto.ReviewDto;
import com.ssafy.popcon.review.dto.ReviewRecommendDto;
import com.ssafy.popcon.review.dto.ReviewImageDto;
import com.ssafy.popcon.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

//    void registerReviewWithImages(ReviewDto reviewDto);

    void registerReview(ReviewDto reviewDto);

    void registerReviewImage(ReviewImageDto reviewImageDto);


    PopupDto getPopupById(@Param("popupId") int popupId);

    UserDto getUserById(String userId);

    ReviewDto getReviewById(int popupId, int reviewId);

    void updateReview(ReviewDto reviewDto);

    void updateReviewImage(ReviewImageDto reviewImageDto);

    void deleteReview(int reviewId);

    List<ReviewDto> getReview(int popupId);

    List<ReviewDto> getReviewTop9(int popupId); // 상세보기 페이지 에서는 리뷰 9개만 주면 됨

    void addRecommend(ReviewRecommendDto reviewRecommendDto);

    void removeRecommend(ReviewRecommendDto reviewRecommendDto);

    int getRecommendCount(@Param("reviewId") int reviewId);
}
