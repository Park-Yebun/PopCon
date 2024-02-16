package com.ssafy.popcon.recommend.ai.mapper;

import com.ssafy.popcon.recommend.dto.RecommendDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AiMapper {
    void saveAiResult(String className, String userId) throws SQLException;   // ai 결과 저장
    List<RecommendDto> findAi(String className) throws SQLException;    // ai 검사 후 결과 조회
    List<RecommendDto> findUserAiResult(String userId) throws SQLException; // user의 ai 결과 조회 (메인페이지)
}
