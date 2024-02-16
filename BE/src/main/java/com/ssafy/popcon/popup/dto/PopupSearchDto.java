package com.ssafy.popcon.popup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PopupSearchDto {

    // 반환에 필요한 부분
    private int popupId;
    private String previewImagePath;
    private String popupName;
    private String popupStart;
    private String popupEnd;
    private String popupLocation;

    private int reviewCnt;  // resultMap선언 x
    private int popupView;
    private int popupLike;
    private String time;

    // parameter 부분
//    private String startDate;
//    private String endDate;
//    private String area;
//    private String status;
//    private String category;

}
