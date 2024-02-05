<template>
  <div class="m-3">
      <div class="mb-3">
        <h5>회원가입</h5>
      </div>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="companyName" placeholder="">
        <label for="companyName"><i class="bi bi-buildings-fill"></i> 회사 이름</label>
      </div>
      <div class="row">
        <div class="form-floating mb-3 col-8">
          <input type="id" class="form-control" id="idInput" placeholder="">
          <label for="idInput"><i class="bi bi-person-circle"></i> 아이디</label>
        </div>
        <div class="col-4">
          <button type="button" class="btn btnStyle col-12" style="font-size: 15px;">중복확인</button>
        </div>
      </div>
      <div class="form-floating mb-3">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput"><i class="bi bi-envelope"></i> 메일주소</label>
      </div>

      <div class="form-floating mb-3">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드</label>
      </div>
      <div class="form-floating mb-3">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드 확인</label>
      </div>
      <div class="row" style="margin-bottom: 40px;">
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
            <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around">
              <p></p>
              <p>회원가입</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
        <div class="col-2"></div>
      </div>
    </div>

  </template>
  
  <script>
  export default {
    data() {
      return {
        companyName: '',
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        businessNumber: ''
      };
    },
    methods: {
      checkUsernameAvailability() {
        // 아이디 중복 확인 로직을 추가할 수 있습니다.
        console.log('아이디 중복 확인');
      },
      checkBusinessNumberValidity() {
        // 사업자 번호 유효성 확인 로직을 추가할 수 있습니다. (국세청 API 활용)
        console.log('사업자 번호 유효성 확인');
      },
      submitForm() {
        // 기업 회원가입 폼 제출 로직을 추가할 수 있습니다.
        console.log('기업 회원가입 폼 제출');
        // 여기에 실제로 서버로 회원가입 정보를 전송하는 로직을 추가합니다.
      }
    }
  };
  </script>
  
  <style scoped>
  .btnStyle{
    width: 88px;
    height: 56px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
  .BtnStyle2{
    width: 210px;
    height: 40px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>