package com.ssafy.popcon.recommend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendDto {

    private int popupId;
    private String popupName;
    private String previewImage;


    private int popupRecommend;
    private String userId;

    private String categoryName;

    private String popupImagePath;
}