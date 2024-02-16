package com.ssafy.popcon.review.mapper;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupRecommendDto;
import com.ssafy.popcon.review.dto.ReviewDto;
import com.ssafy.popcon.review.dto.ReviewRecommendDto;
import com.ssafy.popcon.review.dto.ReviewImageDto;
import com.ssafy.popcon.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

//    void registerReviewWithImages(ReviewDto reviewDto);

    void registerReview(ReviewDto reviewDto);

    void registerReviewImage(ReviewImageDto reviewImageDto);


    PopupDto getPopupById(@Param("popupId") int popupId);

    UserDto getUserById(String userId);

    ReviewDto getReviewById(@Param("popupId")int popupId, @Param("reviewId")int reviewId);

    void updateReview(ReviewDto reviewDto);

    void updateReviewImage(ReviewImageDto reviewImageDto);

    void deleteReview(int reviewId);

    List<ReviewDto> getReview(int popupId);

    List<ReviewDto> getReviewTop9(Map<String,Object> map);

    void addLikeToReview(ReviewDto reviewDto);

    int getRecommendCount(@Param("reviewId") int reviewId);

    int duplicateReviewRecommend(ReviewRecommendDto duplicateCheckDto);

    void addReviewRecommend(ReviewRecommendDto reviewRecommendDto);

    void deleteReviewRecommend(ReviewRecommendDto reviewRecommendDto);

    void cancelLikeToReview(Map<String,Object> map);
}
