package com.ssafy.popcon.review.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Date reviewDate;
    private String reviewLike;
    private String userId;
    private int reviewParentId;

}
