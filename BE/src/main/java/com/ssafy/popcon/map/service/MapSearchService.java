package com.ssafy.popcon.map.service;

import com.ssafy.popcon.map.dto.MapDto;
import com.ssafy.popcon.map.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapSearchService {

    private final MapMapper mapMapper;

    public List<MapDto> findList(Map<String, String> parameter) throws SQLException {
        return mapMapper.findList(parameter);
    }

}