package com.ssafy.popcon.review.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReviewDto {

    private int reviewId;
    private int popupId;
    private String reviewContent;
    private int reviewScore;
    private String reviewQuality;
    private String reviewCost;
    private String reviewType;
    private String reviewDesign;
    private String reviewTrendy;
    private String reviewNew;
    private String reviewKindness;
    private String reviewArea;
    private String reviewCar;
    private String reviewConcept;
    private String reviewClean;
    private String reviewGift;
    private String reviewDate;
    private int reviewLike;
    private String userId;
    private int reviewParentId;
    private String reviewImagePath;
    private String userImagePath;
    private String userNickname;
    private int userReviewLike;
}
