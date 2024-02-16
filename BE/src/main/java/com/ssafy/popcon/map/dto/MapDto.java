package com.ssafy.popcon.map.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapDto {   // 나중에 얘는 없애고 팝업 dto 를 가져오는걸로 map.xml 변경하기
    private double popupLatitude;
    private double popupLongitude;
    private int popupId;
    private String popupName;
    private String popupLocation;
    private String popupStart;
    private String popupEnd;
    private int popupLike;

    private BigDecimal distance;
    private String previewImagePath;

    // 카테고리
    private List<String> popupCategory=new ArrayList<>();
}
