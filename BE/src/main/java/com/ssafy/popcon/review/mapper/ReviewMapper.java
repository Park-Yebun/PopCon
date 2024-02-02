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

    void addRecommend(ReviewRecommendDto reviewRecommendDto);

    void removeRecommend(ReviewRecommendDto reviewRecommendDto);

    int getRecommendCount(@Param("reviewId") int reviewId);
}
