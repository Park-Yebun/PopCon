<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/user";

const router = useRouter()

const memberStore = useMemberStore();
const { userLogin, getUserInfo } = memberStore;
const { isLogin } = storeToRefs(memberStore);

const goIdSearch = function() {
  router.push({ name: 'user-search-id'})
}
const goPasswordSearch = function() {
  router.push({ name: 'user-search-password'})
}
const goSignUp = function() {
  router.push({ name: 'user-join'})
}

const loginUser = ref({
  "userId":"",
	"userPassword":""
});

const isValidBoolean=ref(false);
const isValid=ref('');

const login = async() => {
  await userLogin(loginUser.value); // 로그인을 pinia 에서 
  if(isLogin.value){
    router.push({name:"home"});
  } else {
    isValid.value="아이디 또는 비밀번호를 다시 확인해주세요.";
    isValidBoolean.value=true;
  }
}


</script>

<template>
  <div class="m-3">
    <div class="row mb-3">
      <div class="col-3"></div>
      <div class="col-6">
        <img class="w-100 popcon-logo-size" src="../assets/logo/POPCON_로고_배경제거.png" alt="">
      </div>
      <div class="col-3"></div>
    </div>
   
    <div>
      <div class="mb-3">
        <h5>로그인</h5>
      </div>
      <div class="form-floating mb-3">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="loginUser.userId">
        <label for="floatingInput"><i class="bi bi-envelope"></i> 아이디</label>
      </div>
      <div class="form-floating mb-3">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="loginUser.userPassword">
        <label for="floatingPassword"><i class="bi bi-key"></i> 비밀번호</label>
      </div>
      <p style="color:red;" v-show="isValidBoolean">{{ isValid }}</p>
      <div class="container text-center">
        <div class="row mb-3">
          <div class="col-5 form-check form-switch small-text">
            <!-- <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" checked>
            <label class="form-check-label fw-bold" for="flexSwitchCheckChecked">아이디 기억하기</label> -->
          </div> 
          <div class="col-7 small-text text-end">
            <span class=" fw-bold" @click="goIdSearch">
              아이디 찾기 
            </span>
            <span class=" fw-bold" @click="goPasswordSearch">
              비밀번호 찾기
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <div class="row">
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around" @click="login">
              <p></p>
              <p>로그인</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
          </button>
        </div>
        <!-- <div class="row d-flex justify-content-center  mb-3">
          또는
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="portal-logo-size" src="../../assets/logo/구글logo.png" alt="구글로고">  구글 로그인</button>
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="portal-logo-size" src="../../assets/logo/Kakao_logo.jpg" alt="카카오로고">  카카오 로그인</button>
    
        </div> -->
        <div class="row" style="font-size: 12px; text-align: center;">
          <div class="col-7">
            <p>계정이 없으신가요?</p>
          </div>
          <div class="col-5">
            <span @click="goSignUp" style="color: #ff534c;">회원가입</span>
          </div>
        </div>

      </div>
      <div class="col-2"></div>
    </div>

  </div>
</template>


<style scoped>
.small-text {
  font-size: 12px; /* 원하는 크기로 설정 */
}
.portal-logo-size{
  width:20px;
  height: 20px;
}
.popcon-logo-size{
  width:180px;
  height: 180px;
}
.BtnStyle2{
    width: 254px;
    height: 40px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
</style>