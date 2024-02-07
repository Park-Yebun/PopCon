package com.ssafy.popcon.map.mapper;

import com.ssafy.popcon.map.dto.MapDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {
    List<MapDto> findNearbyPopups(double latitude, double longitude);    // 내 위치 근방 2km 의 팝업들 조회
}
