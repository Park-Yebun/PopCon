package com.ssafy.popcon.map.service;

import com.ssafy.popcon.map.dto.MapDto;
import com.ssafy.popcon.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapNearbySearchService {

    private final MapMapper mapMapper;

    // 내 위치 기반 반경 2km 팝업 검색 및 반환 -> 2km 는 임시값
    public List<MapDto> findNearbyPopups(double latitude, double longitude){

//        latitude=126.97725;
//        longitude=37.570892; // 테스트값

        return mapMapper.findNearbyPopups(latitude,longitude);
    }
}
