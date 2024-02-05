package com.ssafy.popcon.popup.service;

import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.popup.mapper.PopupMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final PopupMapper popupMapper;
    private static final Logger logger = LoggerFactory.getLogger(PopupRegisterService.class);


    public List<PopupDto> getPopupImageGood3() {
        try {
            return popupMapper.getPopupImageGood3();
        } catch (Exception e) {
            logger.error("Failed to retrieve popup images", e);
            throw new RuntimeException("Failed to retrieve popup images");
        }
    }

    public List<PopupDto> getPopupEnd() {
        try {
            return popupMapper.getPopupEnd();
        } catch (Exception e) {
            logger.error("Failed to retrieve popup images", e);
            throw new RuntimeException("Failed to retrieve popup images");
        }
    }
}
