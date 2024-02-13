package com.ssafy.popcon.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class KakaoGeocodingUtil {

    private static final String KAKAO_MAPS_API_URL = "https://dapi.kakao.com/v2/local/search/address.json";
    private static final String API_KEY = "9963b86cedb1a08fb2a52222c9fc85a9";

    public static double[] getCoordinates(String address) {
        String apiUrl = KAKAO_MAPS_API_URL + "?query=" + address;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<KakaoApiResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, KakaoApiResponse.class);
        KakaoApiResponse response = responseEntity.getBody();

        if (response != null && response.getDocuments() != null && !response.getDocuments().isEmpty()) {
            KakaoApiResponse.Document document = response.getDocuments().get(0);
            return new double[]{document.getY(), document.getX()};
        } else {
            throw new RuntimeException("Failed to geocode address");
        }
    }

    private static class KakaoApiResponse {
        private List<Document> documents;

        public List<Document> getDocuments() {
            return documents;
        }

        public static class Document {
            private double x;
            private double y;

            public double getX() {
                return x;
            }

            public double getY() {
                return y;
            }
        }
    }
}