package com.ssafy.popcon.map.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MapDto {   // 나중에 얘는 없애고 팝업 dto 를 가져오는걸로 map.xml 변경하기
    private double popupLatitude;
    private double popupLongitude;
    private int popupId;
    private String popupName;
    private String popupLocation;
    private BigDecimal distance;
}
