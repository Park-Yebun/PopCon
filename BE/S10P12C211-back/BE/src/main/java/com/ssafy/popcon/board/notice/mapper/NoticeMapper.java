package com.ssafy.popcon.board.notice.mapper;

import com.ssafy.popcon.board.notice.dto.NoticeDto;
import com.ssafy.popcon.board.notice.dto.NoticeListDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface NoticeMapper {
    void addNotice(NoticeDto noticeDto) throws SQLException;
    List<NoticeListDto> findNotices() throws SQLException;  // 전체 조회
    NoticeDto findNotice(int noticeId) throws SQLException; // 상세 조회
    void modifyNotice(NoticeDto noticeDto) throws SQLException; // 수정
    void removeNotice(int noticeId) throws SQLException;  // 삭제
}
