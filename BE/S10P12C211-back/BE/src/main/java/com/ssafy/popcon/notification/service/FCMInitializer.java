package com.ssafy.popcon.notification.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.ssafy.popcon.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class FCMInitializer {   // fcm 초기화

    private static final String FIREBASE_CONFIG_PATH = "popcon-5ccdd-firebase-adminsdk-yugby-dde53dc2bf.json";
    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

//    @PostConstruct
//    public void initialize() {
//        try {
//            GoogleCredentials googleCredentials = GoogleCredentials
//                    .fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream());
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(googleCredentials)
//                    .build();
//            FirebaseApp.initializeApp(options);
//        } catch (IOException e) {
//            logger.info(">>>>>>>>FCM error");
//            logger.error(">>>>>>FCM error message : " + e.getMessage());
//        }
//    }
}
