package com.ssafy.popcon.popup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class PopupTotalDto {

    private double popupStar; // 별점
    private String popupReviewAge;  // 최다 방문 나이
    private String popupReviewSex;  // 최대 방문 성별

}
