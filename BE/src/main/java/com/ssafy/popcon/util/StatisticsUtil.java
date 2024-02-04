package com.ssafy.popcon.util;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component
public class StatisticsUtil {

    private static Map<String,String> hashMap=new HashMap<>();

    // 리뷰 통계 내서 top3 값 반환해주기
    public Map<String,Integer> countTop3Tags(Map<String, BigDecimal> reviewTags) {

        hashMap.put("reviewQuality","품질이 좋아요");
        hashMap.put("reviewCost","가성비가 좋아요");
        hashMap.put("reviewType","종류가 다양해요");
        hashMap.put("reviewDesign","독특한 디자인이 많아요");
        hashMap.put("reviewTrendy","트렌디해요");
        hashMap.put("reviewNew","신상품이 많아요");
        hashMap.put("reviewKindness","친절해요");
        hashMap.put("reviewArea","매장이 넓어요");
        hashMap.put("reviewCar","주차하기 편해요");
        hashMap.put("reviewConcept","컨셉이 독특해요");
        hashMap.put("reviewClean","시설이 깔끔해요");
        hashMap.put("reviewGift","선물하기 좋아요");

        // sum 구하기
        // BigDecimal 값들의 합 구하기 (Java 8 이상)
        BigDecimal sum = reviewTags.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        // HashMap의 Entry를 List로 변환
        List<Map.Entry<String, BigDecimal>> entryList = new ArrayList<>(reviewTags.entrySet());
        entryList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        // 정렬된 Entry에서 상위 3개 값 가져오기
        List<Map.Entry<String, BigDecimal>> top3Entries = entryList.subList(0, Math.min(3, entryList.size()));

        Map<String,Integer> resultMap=new HashMap<>();

        for (Map.Entry<String, BigDecimal> entry : top3Entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            int cal=(int)(((double)entry.getValue().intValue()/ (double)sum.intValue())*100);
            System.out.println(cal);

            resultMap.put(hashMap.get(entry.getKey()),cal);
        }

        return resultMap;
    }
}
