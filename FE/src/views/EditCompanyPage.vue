<template>
  <div class="m-3">
    <div class="mb-3">
      <h5>정보수정</h5>
    </div>
    <div class="row">
      <div class="d-flex justify-content-center">
        <img
            src="@/assets/images/profile.png"
            alt="이미지 설명"
            style="width: 100px; height: 100px;"
          />
      </div>
      <div class="d-flex justify-content-center mb-3" style="font-weight: bold;">POPUP STORE MAN</div>
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="NickName" placeholder="">
      <label for="NickName">닉네임</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드 확인</label>
    </div>
    <div class="row">
        <div class="form-floating mb-3 col-8">
          <input type="text" class="form-control" id="businessNumber" placeholder="">
          <label for="businessNumber"><i class="bi bi-123"></i> 사업자 번호</label>
        </div>
        <div class="col-4">
          <button type="button" class="btn btnStyle col-12" style="font-size: 15px;">확인</button>
        </div>
      </div>

    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <div class="row">
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around">
              <p></p>
              <p>수정하기</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
      

  </template>
  
  <script>
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
        // console.log('이메일 중복 확인');
      },
      submitForm() {
        // 회원가입 폼 제출 로직을 추가할 수 있습니다.
        // console.log('회원가입 폼 제출');
        // 여기에 실제로 서버로 회원가입 정보를 전송하는 로직을 추가합니다.
      }
    }
  };
  </script>
  
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