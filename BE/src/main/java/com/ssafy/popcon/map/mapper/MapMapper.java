package com.ssafy.popcon.map.mapper;

import com.ssafy.popcon.map.dto.MapDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface MapMapper {
    List<MapDto> findNearbyPopups(double latitude, double longitude) throws SQLException;    // 내 위치 근방 2km 의 팝업들 조회

    List<String> findCategories(int popupId) throws SQLException;   // 팝업의 카테고리 조회

//    List<MapDto> findList(String keyword, double latitude, double longitude) throws SQLException;

    List<MapDto> findList(Map<String, String> parameter) throws SQLException;
}
