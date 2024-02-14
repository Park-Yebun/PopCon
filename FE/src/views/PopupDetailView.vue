<script setup>
// import 구문 추가
import { ref, onMounted } from 'vue';
import { getPopup,getPopupReviewsTop9,getLikeOrNot, addLike, cancelLike } from '@/api/popup';
import {addLikeReview, cancelLikeReview} from '@/api/review';
import {useRouter, useRoute} from 'vue-router';
import axios from 'axios';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/user";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const { getUserInfo } = memberStore;

const router = useRouter();
const route = useRoute();

const param=ref({
  userId:''
});

// route.params.popupId -> 조회할 팝업 아이디 

// 네이버 지도 API 로드
const loadNaverMapScript = (lat, lng) => {
  console.log('2')
  console.log(lng, "로드되는 경도")
  const script = document.createElement("script");
  script.src =
    "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4khl77l611";
  script.async = true;
  script.defer = true;
  document.head.appendChild(script);

  script.onload = () => {
    // 네이버 지도 생성
    mapRef = new window.naver.maps.Map("map", {
    center: new window.naver.maps.LatLng(lat, lng),
    zoom: 18
  });
    new window.naver.maps.Marker({
      position: new window.naver.maps.LatLng(lat, lng),
      map: mapRef,
      zIndex: 999,
    });
  };
};

// ref 함수를 사용하여 변수를 생성
const popup = ref({});
const popupReviews=ref([]);
const popupLoaded=ref(false);
const currIdx = ref(0); // 사진 인덱스
const reviewSummary=ref({});
const lat=ref(0)
const lng=ref(0)
const isLogin=ref(false);
let mapRef = null; // 전역 변수로 선언
const photoReview=ref([]); // 사진 리뷰만 담기 
const popupId=ref();
const isLike=ref(false);
const reviewParam = ref({
  userId:'',
  popupId:''
})

// onMounted 훅 사용
onMounted(async () => {

  if(localStorage.getItem("accessToken")!=null) {
    await getUserInfo(localStorage.getItem("accessToken"));
    
    await getLikeOrNot(
      route.params.popupId,
      ({data})=>{
        console.log("좋아요 여부!!!");
        // console.log(data);
        isLogin.value=true;

        if(data==1) {
          isLike.value=true;
        } else {
          isLike.value=false;
        }
        console.log(isLike.value);
      },
      (error)=>{
        console.log(error);
      }
    )
  }

  getPopup(
    route.params.popupId,

    async ({data})=>{

      popupId.value=route.params.popupId;

      console.log(data);  // data 에 팝업스토어 정보 존재 
      popup.value=data;

      // console.log(popup.value.popupLatitude + '위도확인')
      // console.log(popup.value.popupLongitude + '경도확인')

      lat.value = popup.value.popupLatitude
      lng.value = popup.value.popupLongitude

      // console.log('1')
      // console.log(lat.value + '찐위도확인' )
      // console.log(lng.value + '찐경도확인' )

      popup.value.popupOperating = popup.value.popupOperating.replace(/\n/g, '<br>');
      popup.value.popupContent = popup.value.popupContent.replace(/\n/g, '<br>');
      if(popup.value.popupNotice!=null){
        popup.value.popupNotice=popup.value.popupNotice.replace(/\n/g, '<br>');      
      }
      reviewSummary.value=popup.value.reviewTagSummary.reviewSummary;
      
      // console.log("리뷰 요약 !!");
      // console.log(reviewSummary.value);

     // 해시맵을 배열로 변환
     if(reviewSummary.value!=null) {
      let entries = Object.entries(reviewSummary.value);

      entries.sort((a, b) => {
          return b[1] - a[1];
      });

      // 정렬된 배열을 다시 해시맵으로 변환

      const sortedHashmap = {};
      for (const [key, value] of entries) {
          sortedHashmap[key] = value;
      }

      reviewSummary.value = sortedHashmap;

     }

      // 리뷰 데이터 좋아요순 가져오기 
      
      if(userInfo.value==null) {
          reviewParam.value.userId=null;
      } else {
        reviewParam.value.userId=userInfo.value.userId;
      }
      reviewParam.value.popupId=route.params.popupId;


      getPopupReviewsTop9(
        route.params.popupId,
        reviewParam.value,
        ({data})=>{
          console.log("리뷰 데이터 조회 ! ");
          console.log(data);  // data 에 팝업스토어 리뷰 정보 존재 
          // console.log(data[0]);

          let cnt=0;
          for(var i=0;i<data.length;i++) {
            if(data[i].reviewImagePath!=null) {
              photoReview.value.push(data[i]);
              cnt++;
            }
            if(cnt==9) break;
          }

          popupReviews.value=data;  // 팝업스토어 리뷰 전체 담기 
          popupLoaded.value=true;

          // 데이터를 모두 받은 후에 loadNaverMapScript 호출
          loadNaverMapScript(lat.value, lng.value);
        },
        (error)=>{
          console.log(error);
        }
      )
    },
    (error)=>{
      console.log(error);
    }
  )
});


const goBack=function() {
  // 뒤로가기 로직 추가
  router.go(-1);
}
const goHPage=function() {
  if(popup.value.popupSite!=null) {
    // 새로운 탭에서 링크 열기
    window.open(popup.value.popupSite, '_blank');
  }
}
const goSNS=function()  {
  if(popup.value.popupInstar!=null) {
    // 공식 sns 바로가기 
    window.open(popup.value.popupInstar, '_blank');
  }
}
const goVisitor=function(){
  // 방문자 사진 동영상 페이지 로직 추가
}
const goReview=function(){
  // 방문자 리뷰 페이지 로직 추가
}
const ratingToPercent=function(score){  // 별점을 퍼센트로
  score = (score / 5) * 100;
  // console.log(score);
  return score + '%';
}

const getImageUrl=function(index) {
  // 이미지 URL을 동적으로 제공하는 메소드
  // 예를 들어, `require`를 사용하거나 이미지 경로를 동적으로 생성하는 방법 사용
  // return popupReviews.value[index].reviewImagePath;
}


// 좋아요 버튼 api 요청
const goLike = function() {
  console.log("버튼눌림")

  if(userInfo.value==null) {
    alert('로그인 하세요!');
    // router.push({name:'login'});
    return;
  }

  param.value.userId=userInfo.value.userId;

  if(isLike.value) {  // 좋아요 취소 
    console.log("좋아요 취소하자!!");
    cancelLike(
      route.params.popupId,
      param.value,
      ({data})=>{
        console.log("좋아요 취소 완료!");
        popup.value.popupLike=popup.value.popupLike-1;
        isLike.value=!isLike.value;
      },
      (error)=>{
        console.log(error);
      }
    )

  }

  else {  // 좋아요 추가 
   addLike(
    route.params.popupId,
    param.value,
    ({data})=>{
      console.log("좋아요 추가 완료!");
      console.log(data);
      popup.value.popupLike=popup.value.popupLike+1;
      isLike.value=!isLike.value;
    },
    (error)=>{
      console.log("좋아요 추가 오류");
      console.log(error);
    }
   )

  }
}

// 리뷰 좋아요 버튼 api 요청 
// /{popupId}/{reviewId}/recommend")
const goReviewLike = function(like, reviewId){
  
  if(userInfo.value==null) {
    alert('로그인 하세요!');
    // router.push({name:'login'});
    return;
  }

  // console.log("리뷰 조아요");
  // console.log(reviewId);

  if(like==1) {
    cancelLikeReview(
      route.params.popupId,
      reviewId,
      ({data})=>{
        console.log("리뷰 좋아요 취소 완료!");
      },
      (error)=>{
        console.log(error);
      }
    )
  } // 좋아요 취소 

  else {
    addLikeReview(
      route.params.popupId,
      reviewId,
      ({data})=>{
        console.log("리뷰 좋아요 추가 완료!");
      },
      (error)=>{
        console.log(error);
      }
    )

  } // 좋아요 추가 

}

</script>

<template>
  <div class="home-container">
    <br>
    <br>

    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button">&lt;</button>
    </div>

    <!-- 가로줄 -->
    <hr class="divider" />
    <div class="image-container" v-if="popupLoaded">
        <template v-if="popup.popupImages.length == 1">
          <img :src="popup.popupImages[0]" />
        </template>
        <template v-else>
          <span id="currPage" style="background-color: gray; opacity : 0.9; z-index: 1000;">{{ currIdx + 1 }}/{{ popup.popupImages.length }} </span>
          <div v-if="currIdx > 0" @click="currIdx--">
            <i id="idxMinus" class="fa-solid fa-circle-chevron-left fa-xl"></i>
          </div>
          <img :src="popup.popupImages[currIdx]"/>
          <div v-if="currIdx < popup.popupImages.length - 1"  @click="currIdx++">
            <i id="idxPlus" class="fa-solid fa-circle-chevron-right fa-xl"></i>
          </div>
        </template>
    </div>

    <div class="popup-title">
      <p style="font-weight:bold;">{{popup.popupName}}</p> 
      <!-- <button @click="Likes(popup.popupId)" >좋아요</button> -->
    </div>

    <div class="views" style="padding-left:20px;">
      <div>
        <i class="bi bi-eye-fill"></i>
        <span style="font-size:small; color:gray;">{{' '+popup.popupView}}</span>
      </div>
      <div v-if="popupLoaded">
        <div v-if="isLike" @click="goLike" style="cursor:pointer;">
          <i class="fa-solid fa-heart" style="color: #ff0000"></i>
          <span style="font-size:small; color:gray;">{{' '+popup.popupLike}}</span>
        </div>
        <div v-else @click="goLike" style="cursor:pointer;">
          <i class="fa-regular fa-heart" style="color: #ff0000"></i>
          <span style="font-size:small; color:gray;">{{' '+popup.popupLike}}</span>
        </div>
      </div>
    </div>

    <!-- 별점 + 숫자  -->
    <div class="star-ratings">
      <div class="star-ratings1" >
      <div 
        class="star-ratings-fill space-x-2 text-lg"
        :style="{ width: ratingToPercent(popup.popupStar)}">
        <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
      </div>
      <div class="star-ratings-base space-x-2 text-lg">
          <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
      </div>
      </div>
      <div class="star-ratings2">
        
        <!-- {{popup.popupStar }} -->
       <div class="star-ratings-score">
          <span>{{ '  '+popup.popupStar +" "}}</span>
        </div>
      </div>
    </div>

    <!--주소 -->
    <div>
      <i class="bi bi-geo-alt-fill"></i>
      <span style="font-size: small; color:gray;">{{popup.popupLocation}}</span>
      <!-- <span style="font-size:small; font-weight:600">{{popup.popupStart}} - {{ popup.popupEnd }}</span> -->
    </div>

    <!-- 일정 -->
    <div>
      <span style="font-size:small; font-weight:600">{{popup.popupStart}} - {{ popup.popupEnd }}</span>
    </div>

    <!-- 카테고리 -->
    <div class="category">
      <template v-for="(category, index) in popup.popupCategory">
        <div class="category-button">
          <span id="category-name">{{ category }}</span>
        </div>
      </template>
      
    </div>

    <hr class="divider" />

    <!-- 해쉬태그 -->
    <div class="hashtag">
      <div v-if="popup.popupCar=='Y'" class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-p-circle" viewBox="0 0 16 16">
        <path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8m15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.5 4.002h2.962C10.045 4.002 11 5.104 11 6.586c0 1.494-.967 2.578-2.55 2.578H6.784V12H5.5zm2.77 4.072c.893 0 1.419-.545 1.419-1.488s-.526-1.482-1.42-1.482H6.778v2.97z"/>
        </svg>
        <p style="font-size:x-small;">주차 가능</p>
      </div>
      <div v-if="popup.popupEntryFee=='Y'" class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-ticket-perforated" viewBox="0 0 16 16">
        <path d="M4 4.85v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9z"/>
        <path d="M1.5 3A1.5 1.5 0 0 0 0 4.5V6a.5.5 0 0 0 .5.5 1.5 1.5 0 1 1 0 3 .5.5 0 0 0-.5.5v1.5A1.5 1.5 0 0 0 1.5 13h13a1.5 1.5 0 0 0 1.5-1.5V10a.5.5 0 0 0-.5-.5 1.5 1.5 0 0 1 0-3A.5.5 0 0 0 16 6V4.5A1.5 1.5 0 0 0 14.5 3zM1 4.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v1.05a2.5 2.5 0 0 0 0 4.9v1.05a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-1.05a2.5 2.5 0 0 0 0-4.9z"/>
        </svg>
        <p style="font-size:x-small;">입장료 무료</p>
      </div>
      <div v-if="popup.popupWifi=='Y'" class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-wifi" viewBox="0 0 16 16">
        <path d="M15.384 6.115a.485.485 0 0 0-.047-.736A12.44 12.44 0 0 0 8 3C5.259 3 2.723 3.882.663 5.379a.485.485 0 0 0-.048.736.52.52 0 0 0 .668.05A11.45 11.45 0 0 1 8 4c2.507 0 4.827.802 6.716 2.164.205.148.49.13.668-.049"/>
        <path d="M13.229 8.271a.482.482 0 0 0-.063-.745A9.46 9.46 0 0 0 8 6c-1.905 0-3.68.56-5.166 1.526a.48.48 0 0 0-.063.745.525.525 0 0 0 .652.065A8.46 8.46 0 0 1 8 7a8.46 8.46 0 0 1 4.576 1.336c.206.132.48.108.653-.065m-2.183 2.183c.226-.226.185-.605-.1-.75A6.5 6.5 0 0 0 8 9c-1.06 0-2.062.254-2.946.704-.285.145-.326.524-.1.75l.015.015c.16.16.407.19.611.09A5.5 5.5 0 0 1 8 10c.868 0 1.69.201 2.42.56.203.1.45.07.61-.091zM9.06 12.44c.196-.196.198-.52-.04-.66A2 2 0 0 0 8 11.5a2 2 0 0 0-1.02.28c-.238.14-.236.464-.04.66l.706.706a.5.5 0 0 0 .707 0l.707-.707z"/>
        </svg>
        <p style="font-size:x-small;">와이파이 가능</p>
      </div>
      <div v-if="popup.popupEat=='Y'" class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-x-octagon" viewBox="0 0 16 16">
        <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1z"/>
        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
        </svg>
        <p style="font-size:x-small;">식음료 반입 금지</p>
      </div>
    </div>

    <hr class="divider" />

    <!-- 운영시간 ~ 주의사항 -->
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" 
          aria-expanded="false" aria-controls="flush-collapseOne"
          style="font-weight:bold">
          ⌚ 운영시간 
          </button>
        </h2>
        <div id="flush-collapseOne" class="accordion-collapse collapse show" >
          <div class="accordion-body">
            <div class="accordion-text" v-html= "popup.popupOperating"></div>
          </div>
        </div>
      </div> 
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed show" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" 
          aria-expanded="false" aria-controls="flush-collapseTwo"
          style="font-weight:bold">
          📝 팝업스토어 소개
          </button>
        </h2>
        <div id="flush-collapseTwo" class="accordion-collapse collapse show">
          <div class="accordion-body">
            <div  class="accordion-text" v-html="popup.popupContent"></div>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed show" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" 
          aria-expanded="false" aria-controls="flush-collapseThree"
          style="font-weight:bold">
          ⚠️ 안내 및 주의사항
          </button>
        </h2>
        <div id="flush-collapseThree" class="accordion-collapse collapse show">
          <div class="accordion-body">
            <div class="accordion-text" v-html="popup.popupNotice"></div>
          </div>
        </div>
      </div>
    </div> 

    <hr class="divider" />
    <!-- 팝콘이 알려드리는 꿀팁 -->
    
    <p style="font-weight:bold;">POPCON이 알려드리는 ✨꿀팁✨</p> 
    <div class="honey-box">
      <p v-if="popup.popupHoney==null">
        꿀팁이 없습니다. 
      </p>
      <p v-else class="honeytip-content" :style="{filter:isLogin? 'blur(0px)':'blur(5px)'}">
        {{ popup.popupHoney }}
      </p>
      <div v-if="!isLogin" id="goLogin" @click="$router.push('/user')">
        <span style="margin-right: 3px;">로그인 후 확인하기</span>
        <i class="fa-solid fa-circle-arrow-right" style="color: #FFCC00; "></i>
      </div>
    </div>
    
    <hr class="divider">
      <!-- <i class="fa-regular fa-paper-plane" style="color: #000000;"></i> -->
      <span @click="goHPage" class="moveSite" style="font-weight: bold">✈️ 브랜드 홈페이지 바로가기</span>

      <hr class="divider">
      <!-- <i class="fa-brands fa-instagram" style="color: #000000;"></i> -->
      <span @click="goSNS" class="moveSite" style="font-weight:bold">🌸 공식 SNS 바로가기</span>
    
    <hr class="divider">

      <!-- 지도 -->
    <p style="font-weight:bold;">🗺️ 오시는 길</p> 
    <div id="map"></div>
  
    <hr class="divider">
    <!-- 방문자 정보 -->
    <div class="center-text">
      <template v-if="popup.popupReviewAge==null">
        <p style="font-size: small; ">" 아직 통계가 없어요! <br> 방문 기록을 남겨주세요! "</p>
      </template>
      <template v-else>
        <p style="font-size:small; font-weight:bold;">" {{popup.popupReviewAge}} {{ popup.popupReviewSex }} 가장 많이 방문했어요! "</p>
      </template>
    </div>

    <!-- 해쉬태그 기능 -->
    <div class='review-summary' style="max-width: 100%;">
      <div v-for="(value,key) in reviewSummary" class="progress">
        <div class="progress-bar" role="progressbar" :style="{width:value+'%'}" >
        </div>
        <div class="progress-bar-text">
          <span style="font-weight:bold; color:darkslategray;">{{ key }}</span>
        </div>
      </div>
    </div>

    <hr class="divider" />

    <!-- 방문자 사진 영상  -->
    <div id="write-review">
      <div>
        <span style="font-weight:bold;">방문자 사진 영상</span>
      </div>
      <div>
        <i class="bi bi-pencil-square"></i>
        <span style="font-size:small;"  @click="$router.push({name:'review',params:{popupId}})"> 리뷰 쓰기</span>
      </div>
    </div>
    <div class="grid-container">
        <div v-for="(review, index) in photoReview" :key="index" class="grid-item">
          <div class="square-image-wrapper">
            <img :src="review.reviewImagePath" alt="Review Image">
          </div>
        </div>
    </div>
    <!-- <div id="app">
      <div @click="goVisitor" id="more-photo">
        <span style="padding-right:3px;">방문자 사진 영상 더보기</span>
        <i class="fa-solid fa-circle-arrow-right" style="color: #FFCC00; "></i>
      </div>
    </div> -->

    <hr class="divider" />



    <div class="accordion" id="accordionExample">
      <div class="accordion-item" style="border:none;">
        <h2 class="accordion-header" id="headingOne">
          <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"
          style="font-weight:bold;">
            방문자 리뷰
          </button>
        </h2>
        <div id="collapseOne" v-for="(review,index) in popupReviews" :key="index" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
            <div class="row" style="padding:3px;">
              <div class="review-content col-8">
              <div class="review-text" style="font-size:12px;">
                {{ review.reviewContent }}
              </div>

              <div class="review-etc" style="font-size:12px;">
              <span class="badge text-bg-light" style="border: 1px lightgray solid;">
                <div v-if="popupLoaded">
                  <template v-if="userInfo!=null"> <!-- 로그인 한 유저 -->
                    <div v-if="review.userReviewLike==1" @click="goReviewLike(1, review.reviewId); review.userReviewLike=0; review.reviewLike-=1;"
                    style="cursor:pointer;">
                      <i class="fa-solid fa-heart" style="color: #ff0000"></i>
                      {{' '+review.reviewLike}}
                    </div>
                    <div v-else @click="goReviewLike(0, review.reviewId); review.userReviewLike=1; review.reviewLike+=1;"
                    style="cursor:pointer;">
                      <i class="fa-regular fa-heart" style="color: #ff0000"></i>
                      {{' '+review.reviewLike}}
                    </div>
                  </template>

                  <template v-else> <!-- 로그인 안한 유저 -->
                    <div @click="goReviewLike(0, review.reviewId)" style="cursor:pointer;">
                      <i class="fa-regular fa-heart" style="color: #ff0000"></i>
                      {{' '+review.reviewLike}}
                    </div>
                  </template>

                </div>
                <!-- <i class="fa-solid fa-heart" style="color: red;"></i> 
                  {{ review.reviewLike }} -->
              </span>
              <span class="badge text-bg-light" style="border: 1px lightgray solid; cursor:pointer;" @click="$router.push({name:'reportreview', params:{'popupId':popupId,'reviewId':review.reviewId}})"><i class="fa-solid fa-skull-crossbones" style="color: #ff0000;"></i> 신고하기 </span>
              </div>

              <div class="review-author">
              <div class="review-profile">
                <img v-if="review.userImagePath!=null"
                  :src="review.userImagePath"
                  alt="profile-picture"
                  class="rounded-profile"
                />  
                <img v-else="review.userImagePath==null"
                  src="https://s3.ap-southeast-2.amazonaws.com/popcon.s3.bucket/profileImages/noProfile.png"
                />
              </div>
              <div class="review-date">
                <span style="font-size:10px; font-weight:bold;"> {{review.userNickname}}</span>
                <span style="font-size:8px; font-weight:bold; color:gray;"> {{review.reviewDate}}</span>
              </div>
              </div>
            </div>

            <div class="review-picture col-4">
            <div class="square-image-wrapper">
              <img v-if="review.reviewImagePath!=null"
                :src="review.reviewImagePath"
                style="border:1px lightgray solid;" 
              >
            </div>
            </div>
          </div>
              <hr class="divider" />
        </div>
      </div> 
    </div>
    
    <hr class="divider" />
  </div>
</template>



<style scoped>

.rounded-profile {
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지가 부모 요소에 맞게 비율을 유지하면서 보이도록 함 */
}
.center-text {
  text-align: center;
}

.honeytip-content {
  /* filter: blur(5px); */
}
.centered-image {
  display: inline-block;
}
.star-ratings {
  /* color: #aaa9a9;  */
  position: relative;
  /* unicode-bidi: bidi-override; */
  display:flex;
  flex-direction: row;
  width: max-content;
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
  margin-top: 80px; /*임의설정값, 나중에 css 건드리면 또 고쳐야됨 ㅠㅠ*/
  margin: 5px 0px;
} 

.star-ratings-fill {
  /* color: #fff58c; */
  padding: 0;
  /* position: absolute; */
  z-index: 1;
  /* display: flex; */
  top: 0;
  left: 0;
  overflow: hidden;
  -webkit-text-fill-color: gold;
}
.star-ratings-base {
  padding: 0;
  position: absolute;
  z-index: 0;
  /* display: flex; */
  top: 0;
  left: 0;
  /* overflow: hidden; */
}

.star-ratings-score {
  top:0;
  margin-left:5px;
}

.views {
  vertical-align: middle;
  float: left; width: 20%;
}

.popup-title {
  margin: 0;
  margin-left: 0px;
  font-size: 22px;
  font-weight: normal;
  float: left; width: 80%;
}

.hashtag-item {
  text-align: center;
  margin-right: 20px;
}
.home-container {
  max-width: 400px;
  margin: 0 auto;
  /* border: 2px solid gray;  */
}
.header-container {
  display: flex;
  flex-direction: column; /* 세로 방향으로 배치합니다. */
  align-items: flex-start; /* 세로로 정렬합니다. */
  padding: 10px;
  background-color: transparent;
}
.back-button {
  background: transparent;
  border: none; /* 테두리 제거 */
  height: 30px; /* 높이 조절 */
  font-size: 30px; /* 폰트 크기 조절 */
  margin-right: 10px; /* 오른쪽 여백 추가 */
}

.divider {
  width: 100%;
  border: 1px solid lightgray;
  margin-top: 10px; /* 위에 간격을 줍니다. */
}

.image-container {
  max-width: 360px;
  height: 360px;
  text-align: center;
  line-height: 0;
  background-color: none;
  position: relative;
}

.image-container img{
  width:100%;
  height:100%;
  object-fit: contain;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px; /* 그리드 아이템 간의 간격을 조절할 수 있습니다. */
  padding-bottom:5px;
}

.grid-item {
  width: 100%; /* 그리드 아이템이 부모 그리드 셀에 꽉 차도록 설정 */
  height: 0;
  padding-bottom: 100%; /* 정사각형으로 만들기 위해 padding-bottom을 사용합니다. */
  position: relative;
  overflow: hidden;
}

.grid-item img {
  width: 100%; /* 이미지가 그리드 아이템에 꽉 차도록 설정 */
  height: 100%;
  object-fit: cover; /* 이미지가 그리드 아이템에 비율을 유지하면서 맞출 수 있도록 설정 */
}

.square-image-wrapper {
  position: relative;
  width: 100%;
  padding-bottom: 100%; /* 1:1 비율을 위한 트릭 */
  overflow: hidden;
}

.square-image-wrapper img {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 이미지가 부모 요소에 맞게 비율을 유지하면서 보이도록 함 */
   border-radius: 10px; /* 둥글게 깎을 정도를 조절할 수 있습니다. */
}

#map {
  width: 100%;
  height: 400px;
}

.moveSite:hover{
  cursor:pointer;
}

.review-box{
  height:30%;
}

.review-image {
  display: flex;
  flex-direction: column;
}

.review-left {
  /* display: flex;
  flex-direction: row; */
  width:66%;
  background-color: lightblue;
}

.review-right {
  /* display: flex; */
  width:34%;
  background-color: lightcoral;
}

/*zzzzzzzzzz */

#idxMinus {
  position: absolute;
  top: 50%;
  left: 10px;
  cursor: pointer;
}

#idxPlus {
  position: absolute;
  top: 50%;
  right: 10px;
  cursor: pointer;
}

#currPage {
  position: absolute;
  right: 10px;
  top: 2%;
  background-color: rgba(155, 155, 155, 0.4);
  padding: 12px 5px;
  border-radius: 5px;
}

i {
  color: rgba(0, 0, 0, 0.5);
  cursor: pointer;
  z-index: 1000;
}

.category-button {
  background-color: #FF534C;
  border-radius: 20px;
  border: 0px;
  margin-right: 5px;
  padding: 2px 7px;
}

#category-name {
  color:white;
  font-size: x-small;
}

.category {
  display: flex;
  flex-direction: row;
  margin: 10px 0px;
  padding-bottom: 10px;
}

.accordion-text{
  font-size:small;
}

.honey-box {
  display:flex;
  flex-direction: column;
  align-items: center; /* 세로 가운데 정렬 */
}

#goLogin {
  background-color: #FF534C;
  border-radius: 20px;
  border: 0px;
  padding: 7px 10px;
  color:white;
  font-size: small;
  width: 70%;
  display: flex;
  justify-content: center; /* 가로 가운데 정렬 */
  align-items: center; /* 세로 가운데 정렬 */
}

/* .review-summary {
  display:flex;
  flex-direction: column;
  position: relative;
} */

/* .progress {
  height: 40px;
  border-radius:20px; 
  margin-bottom: 10px;
  overflow:hidden;
  display:flex;
  justify-content: center;
} */

/* .progress-bar {
  border-radius:20px; 
  background-color:#FF534C; 
  color:white;
  overflow:hidden;
  z-index:1;
} */

.review-summary {
  display: flex;
  flex-direction: column;
}

.progress {
  display: flex;
  align-items: center;
  /* justify-content: center; */
  height: 40px;
  border-radius: 20px;
  margin-bottom: 10px;
  position:relative;
}

.progress-bar {
  flex-grow: 1;
  /* background-color:#ffa8a5;  */
  background-color: #FF534C;
  z-index:1;
  height: 40px;
  border-radius: 20px;
  position:absolute;
}

.progress-bar-text {
  /* text-align: center;
  justify-content: center; */

  display: flex;
  justify-content: center;
  align-items: center;

  z-index:2; 
  height:40px;
  border-radius: 20px;
  /* top:50%; */
  left:50%;
  transform: translateX(-50%);
  /* transform:translateY(-50%); */
  position:absolute;
  /* align-items:center; */
}


/* .review-summary-item{
  background-color: #FF534C; 
  margin:5px 0px; 
  padding: 7px 10px;
  z-index:1;
  border-radius: 20px;
  color: white;
  display:flex;
  justify-content:space-between;
} */

#write-review {
  display:flex;
  flex-direction: row;
  justify-content:space-between;
  padding-bottom: 10px ;
}

#app {
  display:flex;
  flex-direction: column;
  align-items: center; /*세로 가운데 정렬 */
}

#more-photo{
  background-color: #FF534C;
  border-radius: 20px;
  border: 0px;
  padding: 7px 10px;
  color:white;
  font-size: small;
  width: 70%;
  display: flex;
  justify-content: center; /* 가로 가운데 정렬 */
  align-items: center; /* 세로 가운데 정렬 */
}

.review-author {
  display:flex;
  flex-direction: row;
}

.review-profile {
  text-align:center;
}

.review-profile img {
  width: 18px; /* 프로필 이미지의 크기를 조절할 수 있습니다. */
  height: 18px;
  overflow: hidden;
  border-radius: 50%; /* 반지름 50%로 설정하여 원형으로 만듭니다. */
  display: block; /* 이미지를 블록 요소로 만들어줍니다. */
  margin: 0 auto; /* 좌우 여백을 자동으로 설정하여 이미지를 가운데 정렬합니다. */
}

.review-date {
  display: flex;
  flex-direction: column;
  padding-left:5px;
}

.review-text {
  height: 65%;
  padding: 3px;
}

.review-etc {
  height: 15%;
  text-align: right;
}

.review-etc span {
  margin-left: 3px;
}

.review-author {
  height: 20%;
}

#more-review{
  background-color: #FF534C;
  border-radius: 20px;
  border: 0px;
  padding: 7px 10px;
  margin-top : 10px;
  color:white;
  font-size: small;
  width: 70%;
  display: flex;
  justify-content: center; /* 가로 가운데 정렬 */
  align-items: center; /* 세로 가운데 정렬 */
}

.bottom-box {
  display:flex;
  flex-direction: column;
  align-items: center; /*세로 가운데 정렬 */
}

.honey-box {
  font-size:small;
}

</style>