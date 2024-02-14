package com.ssafy.popcon.popup.dto;

import com.ssafy.popcon.review.dto.ReviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class PopupDto {

    private int popupId;
    private String popupName;
    private String popupBrand;
    private String popupContent;
    private String popupLocation;
    private String popupStart;
    private String popupEnd;
    private String popupOperating;
    private String popupEvent;
    private int popupView;
    private char popupCar;
    private char popupEntryfee;
    private char popupWifi;
    private char popupEat;
    private String popupSite;
    private String popupInstar;
    private String popupNotice;
    private String popupPhone;
    private String popupHoney;
    private int popupLike;
    private BigDecimal popupLatitude;
    private BigDecimal popupLongitude;
    private String userId;


    private String previewImagePath;    // 미리보기 이미지의 경로
    private List<String> popupImages=new ArrayList<>();

    private double popupStar; // 별점
    private String popupReviewAge;  // 최다 방문 나이
    private String popupReviewSex;  // 최대 방문 성별
    private Map<String,Object> reviewTagSummary=new HashMap<>();

    private MultipartFile file;

    private List<ReviewDto> reviews;
    private List<String> popupCategory=new ArrayList<>();


    // 생성자, 게터, 세터 등 필요한 부분...

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    // Getters and setters
}
