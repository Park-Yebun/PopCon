
<template>
  <div class="container">
    <RouterView class="router-view"/>
    <div v-if="store.nowURL !== '/map'" title="알림검색 상단바" class="topbar">
      <img v-if="store.nowURL !== '/search' && store.nowURL !== '/map'" @click="goSearch()" class="search-button" src="@/assets/images/searchbutton.png" alt="검색버튼">
      <img class="notification-button" src="@/assets/images/notification_false.png" alt="알림버튼" data-bs-toggle="modal" data-bs-target="#exampleModal">
    </div>
    <nav class="navbar">
      <div class="container-fluid">
        <div @click="goHome" class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_home.png" alt="홈 아이콘">
          <div class="nav-text">Home</div>
        </div>
        <div @click="goMap" class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_map.png" alt="지도 아이콘">
          <div class="nav-text">Map</div>
        </div>
        <div class="nav-item">
          <img class="nav-icon-camera" src="@/assets/images/nav_camera.png" alt="카메라 아이콘">
        </div>
        <div>
          <img @click="goRec" class="nav-icon" src="@/assets/images/nav_rec.png" alt="추천 아이콘">
          <div class="nav-text">Rec</div>
        </div>
        <div @click="goOthers" class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_others.png" alt="더보기 아이콘">
          <div class="nav-text">Others</div>
        </div>
      </div>
    </nav>
  </div>

<!-- 알람 모달 템플릿 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-fullscreen">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">알림</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      </div>
    </div>
  </div>
</div>
</template>

<style scoped>
.container {
  max-width: 360px;
  position:relative;
  margin-bottom: 88px; /* navbar의 높이만큼 margin-bottom 추가 */
}


.navbar {
  width: 360px;
  height: 88px;
  box-shadow: 0px -3px 8px rgba(156.74, 177.69, 213.56, 0.13);

  position: fixed;
  width: 100%;
  bottom: 0;
  left: 0;
  z-index: 1000;
  background-color: white;
}

.container-fluid {
  width: 331.17px;
  height: 46px;
}

.nav-item {
  justify-content: center;
}
.nav-text {
  color: #2C3550;
  text-align: center;
  font-family: ABeeZee;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.nav-icon {
  width: 25.15px;
  height: 24px;
}
.nav-icon-camera {
  width: 48.21px;
  height: 46px;
  filter: drop-shadow(0px 8px 20px rgba(74, 67, 236, 0.25));
}

/* 검색알람버튼 */
.topbar {
  position: fixed;
  top: 0;
  right: 0;

  margin-right: 13.51px;
  margin-top: 5px;
}

.search-button {
  margin-right: 5px;
}
</style>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useCounterStore } from '@/stores/counter'

const router = useRouter()
const store = useCounterStore()
console.log(store.personalToken)

// 이거 실행이 안되는 것 같아서 일단 주석처리 해놨습니당.. 콘솔에 출력이 안되네용..-예분-
// onMounted(()=>{
//   navigator.serviceWorker
//         .register("registerSW.js")
//         .then(function (registration) {
//           console.log(". Service worker successfully registered.");
//           return registration;
//         })
//         .catch(function (err) {
//           console.error("1. Unable to register service worker.", err);
//         });
// })

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSearch = function() {
  router.push({ name: 'search', params: {'category': ''}})
}

const goHome = function() {
  router.push({name : 'home'})
}

const goMap = function() {
  router.push({name : 'map'})
}

const goRec = function() {
  router.push({name : 'recommend'})
}

const goOthers = function() {
  // 만약 현재 로그인한 유저가 개인회원이라면
  if (store.modifyUser.userType == "GENERAL") {
    router.push({name : 'other-member'})
  }
  // 만약 현재 로그인한 유저가 기업회원이라면
  else if (store.modifyUser.userType == "CORP") {
    router.push({name : 'other-corporate'})
  }
  // 만약 현재 로그인한 유저가 비회원이라면
  else {
    
  }
  router.push({name : 'other-member'})
}

import { useMemberStore } from "@/stores/user";


// others 페이지 인증권한 확인용 함수
const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = localStorage.getItem("accessToken");

  if(token!=null){  // 토큰이 있으면 아이디 찾아오기
    console.log(1); 
    await getUserInfo(token);
    next();
  } else {  // 없으면 로그인 시키기 
    next({name:"user-login"});
  }

};

</script>
