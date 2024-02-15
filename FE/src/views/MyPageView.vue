<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/user";
import {deleteUser} from "@/api/user";

const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);

const router = useRouter();

const reviews=ref([]);

// 내 정보 수정 버튼 클릭시 
const goModifyPage=function(){
  router.push({name:"edit-member"});
}

const isModalShow=ref(false);

// 탈퇴 버튼 클릭시 
const removeUser=function(){
  
  deleteUser(
    userInfo.value.userId,
    ({data})=>{
      // console.log("정상!");
      // console.log(data);
      isModalShow.value=false;
      // localStorage 에서 토큰 삭제 
      localStorage.removeItem("accessToken");
      router.push({name:"user-login"});
    },
    ({response}) => {
      // console.log("error");
    }
  )
}


</script>

<template>

  <div>
    <i class="bi bi-arrow-left"></i>
  </div>
  <div class="m-3" id="container">
    <div class="mb-3">
      <span style="font-size: 20px;">마이페이지</span>
    </div>
    <div class="d-flex justify-content-center">
      <img
          :src="userInfo.userImagePath"
          style="width: 100px; height: 100px; border-radius:50%"
        />
    </div>
    <div class="d-flex justify-content-center mb-3" style="font-weight: bold;">{{userInfo.userId}}<i class="bi bi-pencil-square" style="color:#ff534c;"></i></div>
    <div class="d-flex justify-content-center">
      <button class="editBtn" @click="goModifyPage"><i class="bi bi-pencil-square"></i>회원정보수정</button>
    </div>
  </div>

  <div>
    <div class="d-flex justify-content-between m-3">
      <div style="font-weight: bold;">방문한 팝업스토어</div>
      <div>
        <button type="button" class="btnStyle" @click="isModalShow=true">더보기</button>
      </div>
    
    </div>
    <div title="마감임박 팝업리스트" class="popup-group">
      <div class="popup">
        <img src="@/assets/images/poster_01.jpg" class="popup-img" alt="">
        <h5 class="popup-title">Card title</h5>
      </div>
      <div class="popup">
        <img src="@/assets/images/poster_02.jpg" class="popup-img" alt="">
        <h5 class="popup-title">Card title</h5>
      </div>
      <div class="popup">
        <img src="@/assets/images/poster_03.png" class="popup-img" alt="">
        <h5 class="popup-title">Card title</h5>
      </div>
      <div class="popup">
        <img src="@/assets/images/poster_01.jpg" class="popup-img" alt="">
        <h5 class="popup-title">Card title</h5>
      </div>
      <div class="popup">
        <img src="@/assets/images/poster_02.jpg" class="popup-img" alt="">
        <h5 class="popup-title">Card title</h5>
      </div>
    </div>

  </div>

      
    <!-- 방문한 팝업 스토어 이미지 목록 -->

  <div>
    <!-- 작성한 리뷰 목록 -->
    <div>
      <h3>작성한 리뷰</h3>
      <div v-if="reviews.length > 0">
        <div v-for="(review, index) in reviews" :key="index">
          <p>{{ review }}</p>
        </div>
      </div>
      <div v-else>
        <p>작성한 리뷰가 없습니다.</p>
      </div>
    </div>
    <div class="d-flex justify-content-center m-3">
      <button type="button" class="btnStyle">더보기</button>
    </div>
    <div class="d-flex justify-content-center">
      <button class="editBtn" @click="removeUser">회원탈퇴</button>
    </div>
  </div>

  <div v-if="isModalShow" class="pop-modal">
    <p>정말로 탈퇴하시겠어요?</p>
    <div>
      <button @click="removeUser">탈퇴</button>
      <button @click="isModalShow = false">닫기</button>
    </div>
  </div>

  </template>

  
  <style scoped>
.pop-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.pop-modal div {
  text-align: center;
}

.pop-modal button{
    background-color: #ff534c;
    border: none;
    border-radius: 10px;
    color:white;
    padding: 5px 20px;
    font-size: 15px;
    font-weight: bold;
}

  .editBtn{
    background-color: white;
    border: 2px solid #ff534c;
    border-radius: 10px;
    color:#ff534c;
    padding: 5px 20px;
    font-size: 15px;
    font-weight: bold;
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
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 5px;
}
.btnStyle{
    width: 60px;
    height: 25px;
    font-size: 10px;
    border: #ff534C;
    border-radius: 20px; 
    text-align: center;
    background-color: #ff534C;
    color : white;
  }
  </style>