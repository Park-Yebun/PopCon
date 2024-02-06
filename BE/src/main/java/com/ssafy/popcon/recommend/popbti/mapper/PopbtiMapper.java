package com.ssafy.popcon.recommend.popbti.mapper;

import com.ssafy.popcon.recommend.dto.RecommendDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PopbtiMapper {
    List<RecommendDto> findPopbti(int code) throws SQLException;
}
