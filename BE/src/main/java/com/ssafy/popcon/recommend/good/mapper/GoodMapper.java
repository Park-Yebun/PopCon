package com.ssafy.popcon.recommend.good.mapper;


import com.ssafy.popcon.recommend.dto.RecommendDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface GoodMapper {

    List<RecommendDto> findUserGoodResult(String userId) throws SQLException; // user의 Good 결과 조회 (추천페이지)
}
