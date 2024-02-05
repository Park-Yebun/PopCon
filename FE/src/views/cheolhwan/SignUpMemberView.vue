<template>
  <div class="m-3">
    <div class="mb-3">
      <h5>회원가입</h5>
    </div>
    <div class="row">
      <div class="form-floating mb-3 col-8">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput"><i class="bi bi-envelope"></i> 메일주소</label>
      </div>
      <div class="col-4">
        <button type="button" class="btn btnStyle col-12" style="font-size: 15px;">중복확인</button>
      </div>
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
        <input type="date" class="form-control" id="birthday" placeholder="">
        <label for="birthday">생년월일</label>
      </div>
      <div class="col-4">
        <select class="form-select" aria-label="Default select example">
          <option selected>성별</option>
          <option value="male">남성</option>
          <option value="female">여성</option>
          <option value="other">무응답</option>
        </select>
      </div>
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="phoneNumber" placeholder="">
      <label for="phoneNumber">휴대폰 번호 (선택)</label>
    </div>

    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <div class="row">
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around">
              <p></p>
              <p>회원가입</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
        <div class="row d-flex justify-content-center  mb-3">
          또는
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="logo-size" src="../../assets/logo/구글logo.png" alt="구글로고">  구글 로그인</button>
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="logo-size" src="../../assets/logo/Kakao_logo.jpg" alt="카카오로고">  카카오 로그인</button>
    
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
        console.log('이메일 중복 확인');
      },
      submitForm() {
        // 회원가입 폼 제출 로직을 추가할 수 있습니다.
        console.log('회원가입 폼 제출');
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