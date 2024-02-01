package com.ssafy.popcon.popup.mapper;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.dto.PopupImageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PopupMapper {

    List<PopupDto> getPopup(); // 여러 팝업을 가져오는 메서드로 수정

    void registerPopup(PopupDto popupDto);

//    PopupDto getPopupDetails(@Param("popupId") int popupId) throws Exception;
    PopupDto getPopupDetails(@Param("popupId") int popupId);

    // 특정 팝업 이미지 조회
    PopupImageDto getPopupImage(@Param("popupImageId") int popupImageId);

    // 팝업에 속한 모든 이미지 조회
    List<PopupDto> getPopupImagesByPopupId(@Param("popupId") int popupId);

    // 팝업 이미지 등록
    void registerPopupImage(PopupImageDto popupImageDto);

    // 좋아요 추가
    void addPopupLike(@Param("popupId") int popupId, @Param("userId") String userId);

    // 좋아요 취소
    void cancelPopupLike(@Param("popupId") int popupId, @Param("userId") String userId);



}