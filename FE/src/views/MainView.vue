<template>
  <!-- 캐루셀 -->
  <div title="캐루셀" id="carouselExampleIndicators" class="carousel slide h-250">
    <div class="carousel-indicators">
      <button class="active"
        id="button1"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="0"
        aria-current="true"
        aria-label="Slide 1">
      </button>
      <button id="button2"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="1"
        aria-label="Slide 2">
      </button>
      <button id="button3"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="2"
        aria-label="Slide 3">
      </button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="@/assets/images/poster_01.jpg" class="d-block w-100 rounded-bottom-4" alt="">
      </div>
      <div class="carousel-item">
        <img src="@/assets/images/poster_02.jpg" class="d-block w-100 rounded-bottom-4" alt="">
      </div>
      <div class="carousel-item">
        <img src="@/assets/images/poster_03.png" class="d-block w-100 rounded-bottom-4" alt="">
      </div>
    </div>
  </div>
<!-- 마감임박 팝업스토어 리스트 -->
<div class="deadline-container">
  <div class="deadline-text-group">
    <h5 style="opacity: 0.84;
        font-size: 16px;
        font-weight: 800;
        line-height: 30px;
        word-wrap: break-word">마감임박</h5>
    <div href="#"
        style="width: 100%;
        height: 100%;
        text-align:right;
        color: #747688;
        font-size: 13px;
        font-weight: 400;
        line-height: 23px;
        word-wrap: break-word">더보기</div>
  </div>
  <div title="마감임박 팝업리스트" class="popup-group">
    <div class="popup">
      <img src="@/assets/images/popup_01.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="@/assets/images/popup_02.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="@/assets/images/popup_03.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="@/assets/images/popup_01.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="@/assets/images/popup_02.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
  </div>
</div>

<!-- 본문 -->
<div>
  <a @click="goSearch">검색 버튼</a>
  <a href="#">알람 버튼</a>
</div>

<!-- 카테고리 -->
<div title="category">
  <div class="category-container">
    <div class="category-text">
      <img class="category-logo" src="@/assets/images/category_beauty.png" alt="패션/뷰티 아이콘">패션/뷰티
    </div>
    <div class="category-text">
      <img class="category-logo" src="@/assets/images/category_foods.png" alt="식음료 아이콘">식음료
    </div>

    <!-- 철환 확인용 div -->
    <div>
    <div class="category-text">
      <img class="category-logo" src="@/assets/images/category_content.png" alt="콘텐츠 아이콘">콘텐츠
    </div>
    <div class="category-text">
      <img class="category-logo" src="@/assets/images/category_hobby.png" alt="취미/여가 아이콘">취미/여가
    </div>
    <div class="category-text">
      <img class="category-logo" src="@/assets/images/category_finance.png" alt="금융 아이콘">금융

    </div>
    </div>
    <div class="category-container">
      <div class="category-text">
        <img class="category-logo" src="@/assets/images/category_entertain.png" alt="연예 아이콘">연예
      </div>
      <div class="category-text">
        <img class="category-logo" src="@/assets/images/category_digital.png" alt="가전/디지털 아이콘">가전/디지털
      </div>
      <div class="category-text">
        <img class="category-logo" src="@/assets/images/category_living.png" alt="리빙 아이콘">리빙
      </div>
      <div class="category-text">
        <img class="category-logo" src="@/assets/images/category_game.png" alt="게임 아이콘">게임
      </div>
      <div class="category-text">
        <img class="category-logo" src="@/assets/images/category_character.png" alt="캐릭터 아이콘">캐릭터
      </div>
    </div>
  </div>
</div>
<div>
  <button @click="goError">오류페이지</button>
      <button @click="goEditMember">프로필수정페이지</button>
      <button @click="goEditCompany">기업프로필수정페이지</button>
      <button @click="goLogin">로그인페이지</button>
      <button @click="goMypage">마이페이지</button>
      <button @click="goRecommend">추천페이지</button>
      <button @click="goReportReview">리뷰신고페이지</button>
      <button @click="goSearchId">아이디찾기페이지</button>
      <button @click="goSearchPassword">비밀번호페이지</button>
      <button @click="goSignUp">회원가입선택페이지</button>
      <button @click="goSignUpCompany">기업회원가입페이지</button>
      <button @click="goSignUpMember">개인회원가입페이지</button>
</div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSearch = function(category) {
  router.push({ name: 'search', params: category})
}

//철환 라우터
const goError = function() {
  router.push({ name: 'error'})
}
const goEditMember = function() {
  router.push({ name: 'editMember'})
}
const goEditCompany = function() {
  router.push({ name: 'editCompany'})
}
const goLogin = function() {
  router.push({ name: 'login'})
}
const goMypage = function() {
  router.push({ name: 'mypage'})
}
const goRecommend = function() {
  router.push({ name: 'recommend'})
}
const goReportReview = function() {
  router.push({ name: 'reportreview'})
}
const goSearchId = function() {
  router.push({ name: 'searchId'})
}
const goSearchPassword = function() {
  router.push({ name: 'searchPassword'})
}
const goSignUp = function() {
  router.push({ name: 'signUp'})
}
const goSignUpCompany = function() {
  router.push({ name: 'signUpCompany'})
}
const goSignUpMember = function() {
  router.push({ name: 'signUpMember'})
}


</script>

<style>
/* 이미지랑 캐러셀 세로 사이즈 통일(전체 길이의 약 1/3) */
img {
  height: 279px;
}
.carousel {
  height: 279px;
  position: relative;
}

/* [수정중] 인디케이터 동그랗게 만들기 */
.carousel-indicators {
  position: absolute;
  bottom: 0%;
}

/* #button1 {
  width: 3px;
  height: 3px;
  left: 50%;
  top: 0px;
  position: absolute;
  background: #616266;
  border-radius: 9999px;
}

#button2 {
  width: 9px;
  height: 9px;
  left: 13px;
  top: 0px;
  position: absolute;
  background: #E2E2E2;
  border-radius: 9999px;
}

#button3 {
  width: 9px;
  height: 9px;
  left: 26px;
  top: 0px;
  position: absolute;
  background: #E2E2E2;
  border-radius: 9999px;
} */

.deadline-text-group {
  display: flex;
  justify-content: space-between;
  width: 316px;
  height: 30px;
  flex-shrink: 0;
}

.deadline-container {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 3.19px;
}

.category-logo {
  width: 42px;
  height: 42px;
}

.category-text {
  display: flex;
  flex-direction: column;
  font-size: 10px;
  font-family: ABeeZee;
  font-weight: 400;
  line-height: 22px;
  word-wrap: break-word
}

.category-container {
  width: 282px;
  display: flex;
  text-align: center;
  justify-content: space-between;
  margin-left: 40px;
}

.popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 237px;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
}

/* 스크롤 안보이게 숨기기 */
.popup-group{
   -ms-overflow-style: none;
}
.popup-group::-webkit-scrollbar{
  display:none;
}

.popup-img {
  width: 146.56px;
  height: 170.89px;
}
</style>