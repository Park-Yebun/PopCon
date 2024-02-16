package com.ssafy.popcon.board.faq.mapper;

import com.ssafy.popcon.board.faq.dto.FaqDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FaqMapper {
    void addFaq(FaqDto faqDto) throws SQLException; // 등록
    List<FaqDto> findFaqs() throws SQLException; // 전체 조회
    FaqDto findFaq(int faqId) throws SQLException;  // 상세 조회
    void modifyFaq(FaqDto faqDto) throws SQLException;  // 수정
    void removeFaq(int faqId) throws SQLException;  // 삭제
}
