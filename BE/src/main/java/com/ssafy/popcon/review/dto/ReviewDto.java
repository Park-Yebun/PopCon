package com.ssafy.popcon.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private int reviewId;
    private int popupId;
    private String reviewContent;
    private int reviewScore;
    private String reviewSolo;
    private String reviewCouple;
    private String reviewCar;
    private String reviewGoods;
    private String reviewStation;
    private String reviewPhoto;
    private String reviewDate;
    private String reviewLike;
    private int userPK;
    private int reviewParentId;


}
