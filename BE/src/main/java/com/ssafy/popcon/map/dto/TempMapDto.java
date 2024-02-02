package com.ssafy.popcon.map.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempMapDto {   // 나중에 얘는 없애고 팝업 dto 를 가져오는걸로 map.xml 변경하기
    private double latitude;
    private double longitude;
}
