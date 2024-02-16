package com.ssafy.popcon.reportedpopup.mapper;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupDto;
import com.ssafy.popcon.reportedpopup.dto.ReportedPopupImageDto;
import com.ssafy.popcon.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportedPopupMapper {

    // 모든 제보된 팝업 불러오는 코드
    List<ReportedPopupDto> getReportedPopup();

    void registerReportedPopup(ReportedPopupDto reportedPopupDto);

    ReportedPopupDto getReportedPopupDetails(@Param("reportedPopupId") int reportedPopupId);

    void registerReportedPopupImage(ReportedPopupImageDto reportedPopupImageDto);

    // 특정 팝업 이미지 조회
    ReportedPopupImageDto getReportedPopupImage(@Param("reportedPopupImgId") int reportedPopupImgId);

    // 팝업에 속한 모든 이미지 조회
    List<ReportedPopupDto> getReportedPopupImagesByPopupId(@Param("reportedPopupId") int reportedPopupId);

    UserDto getUserById(String userId);

}
