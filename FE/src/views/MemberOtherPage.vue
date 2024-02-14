<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/user";
import {  logout  } from "@/api/user";

const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);

const router = useRouter();

const userLogout=function(){
  logout(
    ({data})=>{
      console.log("정상!");
      localStorage.removeItem("accessToken");
      router.push({name:"home"});
    },
    ({response})=>{
      console.log("error");
    }
  )

}

</script>


<template>
  <div class="home-container">
    <br />
    <br />
    <h1 class="title">Others</h1>
    <br />
    <br />
    <!-- 이미지를 감싸는 컨테이너 추가 -->
    <div class="image-container">
      <div style="margin: 5%;">
        <img
          :src="userInfo.userImagePath" 
          width="80"
          height="80"
          style="display: block; margin: 0 auto; border-radius: 50%;"
        />
      </div>
      <div>
        <p class="welcome-text" style="font-weight:bold">{{userInfo.userNickname}} 님</p>
        <p class="small-text">환영합니다!</p>
      </div>
      <!-- <span class="welcome-text">POPCON 님</span>
      <span class="small-text">환영합니다!</span> -->
    </div>

    <div class="content">
      <!-- 버튼을 감싸는 컨테이너 추가 -->
      <div class="button-container">
        <button @click="$router.push('/user/mypage')">마이페이지</button>
        <button>체험단</button>
        <button>북마크</button>
        <button @click="$router.push('/notice')">공지사항</button>
        <button @click="$router.push('/onetoone')">1:1 문의</button>
        <button @click="$router.push('/FAQ')">FAQ</button>
      </div>

      <br />
      <br />
      <br />
      <br />
      <br />
      <div class="button-container2">
        <button @click="$router.push('/report/popup')">팝업스토어 제보</button>
        <button @click="userLogout">로그아웃</button>
      </div>
      <br />
      <br />
      <br />
    </div>
    <br />
  </div>
</template>


<style scoped>
.title {
  margin-left: 15px; /* 왼쪽 마진을 10px로 설정 */
  font-size: 22px;
  font-weight: normal; /* 굵기를 일반(normal)으로 설정 */
  line-height: 0;
}

.home-container {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid gray; /* 테두리 스타일 설정 */
}

.image-container2 {
  text-align: right;
  margin-right: 10px; /* 오른쪽 마진을 10px로 설정 */
  margin-top: -30px;
}

.image-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  line-height: 0;
}

.content {
  text-align: center; /* 가운데 정렬 */
}

.welcome-text {
  font-size: 22px; 
  line-height: 0;
  text-align: center;
}

.small-text {
  font-size: 16px;
  line-height: 0.5;
  text-align: center;
} 

/* 버튼 컨테이너를 가운데 정렬 */
.button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* 가로 방향 가운데 정렬 */
  gap: 10px;
  margin:5%;
}

/* 버튼을 정사각형으로 만들기 위해 높이와 너비를 동일하게 설정 */
.button-container button {
  width: 90px; /* 필요에 따라 조절하세요 */
  height: 90px; /* 필요에 따라 조절하세요 */
  background-color: #ddd; /* 기본 배경색, 필요에 따라 조절하세요 */
  color: #fa5a5a; /* 기본 글자색, 필요에 따라 조절하세요 */
  font-size: 16px;
  border: 2px solid #fa5a5a; /* 테두리 스타일 설정 */
  border-radius: 10px; /* 테두리의 모서리를 둥글게 만들기 위한 설정 */
}

.button-container2 {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* 가로 방향 가운데 정렬 */
  gap: 10px;
}

/* 버튼을 정사각형으로 만들기 위해 높이와 너비를 동일하게 설정 */
.button-container2 button {
  width: 150px; /* 필요에 따라 조절하세요 */
  height: 40px; /* 필요에 따라 조절하세요 */
  background-color: transparent; /* 기본 배경색, 필요에 따라 조절하세요 */
  color: red; /* 기본 글자색, 필요에 따라 조절하세요 */
  border: 2px solid #fa5a5a; /* 테두리 스타일 설정 */
  border-radius: 10px; /* 테두리의 모서리를 둥글게 만들기 위한 설정 */
  font-size: 16px;
  font-weight: bold;
}

/* 홀수번째 버튼에 빨간색 배경을 적용 */
.button-container button:nth-child(odd) {
  background-color: #fa5a5a;
  color: white; /* 홀수번째 버튼 글자색을 흰색으로 설정 */
}

/* 짝수번째 버튼 배경색을 없애기 */
.button-container button:nth-child(even) {
  background-color: transparent;
}

/* Your other styles here */
</style>
