package com.ssafy.popcon.popup.dto;

import com.ssafy.popcon.review.dto.ReviewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    private char popupEntryFee;
    private char popupWifi;
    private char popupEat;
    private String popupSite;
    private String popupInstar;
    private String popupNotice;
    private String popupPhone;
    private String popupHoney;
    private int popupLike;
    private String userId;

    private MultipartFile file;

    private List<ReviewDto> reviews;

    // 생성자, 게터, 세터 등 필요한 부분...

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    // Getters and setters
}