<script setup>
import { ref } from 'vue';
import { dupCheck, userJoin } from "@/api/user";
import { useRouter } from 'vue-router';

const router = useRouter();

const param=ref({
  type:"",
  value:""
})

const joinUser = ref({
  "userId":"",
	"userPassword":"",
	"userEmail":"",
	"userPlatform":"default",  // 기본은 default, 카카오는 kakao, 구글은 google
	"userNickname":"",
	"userBirth":"", // 0000-11-11
	"userSex":"", // 여성의 경우 "F", 남성은 "M", 미선택은 "N"
	"userType":"GENERAL", 
	"userToken":"", 
	"userBusiness":"", 
	"userPhone":"", 
});

// 아이디 체크 부분 
const idInput=ref('');
const isValidIdBoolean=ref(false);
const isValidId=ref('');

const checkIdAvailability = function() {
  // console.log(emailInput.value);
  param.value.type="id";
  param.value.value=idInput.value;
  dupCheck(
    param.value,
    ({data})=>{
      // console.log(data);
      isValidIdBoolean.value=true;
      isValidId.value="사용 가능한 아이디입니다.";
    },
    ({ response }) => {
      // console.log(response);
      isValidIdBoolean.value=true;
      isValidId.value="중복 아이디 입니다.";
    }
  )
}

// 이메일 체크 부분 
const emailInput=ref('');
const isValidEmailBoolean=ref(false);
const isValidEmail=ref('');

const checkEmailAvailability = function() {
  console.log(emailInput.value);
  param.value.type="email";
  param.value.value=emailInput.value;
  dupCheck(
    param.value,
    ({data})=>{
      // console.log(data);
      isValidEmailBoolean.value=true;
      isValidEmail.value="사용 가능한 이메일입니다.";
    },
    ({ response }) => {
      // console.log(response);
      isValidEmailBoolean.value=true;
      isValidEmail.value="중복 이메일 입니다.";
    }
  )
}

// 패스워드 확인용 
const passwordInput=ref('')
const passwordCheckInput=ref('');

const isValidInput=ref('');
const isValidInputBoolean=ref(false);

// 회원가입
const join=function(){
  // 유효한 이메일, 아이디, 패스워드 일치 여부 확인 
  if(!isValidEmailBoolean.value || !isValidIdBoolean.value || passwordInput.value != passwordCheckInput.value || joinUser.value.userBirth=='' || joinUser.value.userSex=='') {
    // alert('유효하지 않은 입력 값이 있습니다!');
    isValidInput.value="유효하지 않은 입력 값이 있습니다!";
    isValidInputBoolean.value=true;
  } else {
    joinUser.value.userId=idInput.value;
    joinUser.value.userEmail=emailInput.value;
    joinUser.value.userPassword=passwordInput.value;
    // console.log(joinUser.value);
    userJoin(
    joinUser.value,
    ({data})=>{
      console.log(data);
      router.push({ name: "home" });
    },
    ({ response }) => {
      console.log(response);
    }
  )
  }

}

</script>

<template>
  <div class="m-3">
    <div class="mb-3">
      <h5>회원가입</h5>
    </div>
    <div class="row">
      <div class="form-floating mb-3 col-8">
        <input type="id" class="form-control" v-model="idInput">
        <label for="floatingInput"><i class="bi bi-envelope"></i>아이디</label>
      </div>
      <div class="col-4">
        <button type="button" class="btn btnStyle col-12" style="font-size: 15px;" @click="checkIdAvailability">중복확인</button>
      </div>
      <p style="color:red;" v-show="isValidIdBoolean">{{ isValidId }}</p>
    </div>
    <div class="row">
      <div class="form-floating mb-3 col-8">
        <input type="email" class="form-control" v-model="emailInput">
        <label for="floatingInput"><i class="bi bi-envelope"></i>메일 주소</label>
      </div>
      <div class="col-4">
        <button type="button" class="btn btnStyle col-12" style="font-size: 15px;" @click="checkEmailAvailability">중복확인</button>
      </div>
      <p style="color:red;" v-show="isValidEmailBoolean">{{ isValidEmail }}</p>
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="NickName" placeholder="" v-model="joinUser.userNickname">
      <label for="NickName">닉네임</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="passwordInput">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="passwordCheckInput">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드 확인</label>
    </div>
    <div class="row">
      <div class="form-floating mb-3 col-8">
        <input type="date" class="form-control" id="birthday" placeholder="" v-model="joinUser.userBirth">
        <label for="birthday">생년월일</label>
      </div>
      <div class="col-4">
        <select class="form-select" aria-label="Default select example" v-model="joinUser.userSex">
          <option selected disabled>성별</option>
          <option value="M">남성</option>
          <option value="F">여성</option>
          <option value="N">무응답</option>
        </select>
      </div>
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="phoneNumber" placeholder="" v-model="joinUser.userPhone">
      <label for="phoneNumber">휴대폰 번호 (선택)</label>
    </div>
    <p v-show="isValidInputBoolean" style="color:red;">{{ isValidInput }}</p>
    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <div class="row">
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around" @click="join">
            <p></p>
            <p>회원가입</p>
            <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
          </button>
        </div>
        <div class="row d-flex justify-content-center  mb-3">
          또는
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="logo-size" src="@/assets/logo/구글logo.png" alt="구글로고">  구글 로그인</button>
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="logo-size" src="@/assets/logo/Kakao_logo.jpg" alt="카카오로고">  카카오 로그인</button>
    
        </div>
        <div class="row" style="font-size: 11px; text-align: center;">
          <div class="col-9">
            <p>이미 계정이 있으신가요?</p>
          </div>
          <div class="col-3">
            <a href="" style="color:#ff534c">로그인</a>
          </div>
        </div>

      </div>
      <div class="col-2"></div>
    </div>
  </div>
      

  </template>
  
  <!-- <script>
  export default {
    data() {
      return {
        email: '',
        nickname: '',
        password: '',
        confirmPassword: '',
        birthday: '',
        gender: 'male',
        phoneNumber: ''
      };
    },
    methods: {
      checkEmailAvailability() {
        // 이메일 중복 확인 로직을 추가할 수 있습니다.
        console.log('이메일 중복 확인');
      },
      submitForm() {
        // 회원가입 폼 제출 로직을 추가할 수 있습니다.
        console.log('회원가입 폼 제출');
        // 여기에 실제로 서버로 회원가입 정보를 전송하는 로직을 추가합니다.
      }
    }
  };
  </script> -->
  
  <style scoped>
  .logo-size{
  width:20px;
  height: 20px;
}

.btnStyle{
    width: 88px;
    height: 56px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
  .BtnStyle2{
    height: 40px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
  </style>