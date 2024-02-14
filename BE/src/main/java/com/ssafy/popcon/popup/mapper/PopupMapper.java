package com.ssafy.popcon.popup.mapper;

import com.ssafy.popcon.popup.dto.*;
import com.ssafy.popcon.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PopupMapper {

    List<PopupDto> getPopup(); // 여러 팝업을 가져오는 메서드로 수정

    void registerPopup(PopupDto popupDto);

    void registerPopupCategory(Map<String,Object> category); // 팝업 카테고리 등록

//    PopupDto getPopupDetails(@Param("popupId") int popupId) throws Exception;
    PopupDto getPopupDetails(@Param("popupId") int popupId);

    List<String> getPopupCategory(int popupId);

    // 특정 팝업 이미지 조회
    PopupImageDto getPopupImage(@Param("popupImageId") int popupImageId);

    // 팝업에 속한 모든 이미지 조회
    List<String> getPopupImagesByPopupId(@Param("popupId") int popupId);

    // 팝업 이미지 등록
    void registerPopupImage(PopupImageDto popupImageDto);

    // 팝업 좋아요 추가
    void addLikeToPopup(int popupId);

    // 팝업 좋아요 취소
    void cancelLikeToPopup(int popupId);

    // 팝업 추천 정보 추가
    void addPopupRecommend(PopupRecommendDto popupRecommendDto);

    // 팝업 추천 정보 삭제
    void deletePopupRecommend(PopupRecommendDto popupRecommendDto);

    PopupDto getPopupById(@Param("popupId") int popupId);

    UserDto getUserById(String userId);

    int duplicatePopupRecommend(PopupRecommendDto duplicateCheckDto);

    UserDto getUserByIdAndType(String userId);

    PopupTotalDto getPopupTotal(int popupId);

    void addViewsToPopup(int popupId);  // 조회수 증가

    Map<String, BigDecimal> getPopupReviewTags(int popupId);   // 팝업 아이디를 기반으로 리뷰 태그들의 개수 가져오기

    List<PopupSearchDto> getPopupByFilter(Map<String,String> filter); // 필터 기반 팝업 가져오기 (검색)

    List<PopupDto> getPopupImageGood3();

    List<PopupDto> getPopupEnd();

    int getLikeOrNot(Map<String,Object> info) throws SQLException;
}