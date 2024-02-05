package com.ssafy.popcon.map.controller;


import com.ssafy.popcon.map.dto.TempMapDto;
import com.ssafy.popcon.map.service.MapNearbySearchService;
import com.ssafy.popcon.popup.dto.PopupDto;
import com.ssafy.popcon.user.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MapController {

    private static final Logger logger = LoggerFactory.getLogger(MapController.class);
    private final MapNearbySearchService mapNearbySearchService;

    @GetMapping("/nearby")
    public ResponseEntity<?> mapNearbyList(@RequestParam("lat") double latitude, @RequestParam("lng") double longitude) throws Exception{

        List<TempMapDto> popups=mapNearbySearchService.findNearbyPopups(latitude,longitude);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(popups);
    }
}
