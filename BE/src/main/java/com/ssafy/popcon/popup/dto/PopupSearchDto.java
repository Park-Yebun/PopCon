package com.ssafy.popcon.popup.dto;

import lombok.Getter;
import lombok.Setter;

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

    // parameter 부분
//    private String startDate;
//    private String endDate;
//    private String area;
//    private String status;
//    private String category;

}
