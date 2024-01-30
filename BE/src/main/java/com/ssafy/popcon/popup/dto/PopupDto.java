package com.ssafy.popcon.popup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

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
    private int userId;

    private MultipartFile file;
    // Getters and setters
}