<template>
  <div class="m-3">
    <div class="row mb-3">
      <div class="col-3"></div>
      <div class="col-6">
        <img class="w-100 popcon-logo-size" src="../../assets/logo/POPCON_로고_배경제거.png" alt="">
      </div>
      <div class="col-3"></div>
    </div>
   
    <div>
      <div class="mb-3">
        <h5>로그인</h5>
      </div>
      <div class="form-floating mb-3">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput"><i class="bi bi-envelope"></i> 메일주소</label>
      </div>
      <div class="form-floating mb-3">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword"><i class="bi bi-key"></i> 비밀번호</label>
      </div>
      <div class="container text-center">
        <div class="row mb-3">
          <div class="col-5 form-check form-switch small-text">
            <input class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" checked>
            <label class="form-check-label fw-bold" for="flexSwitchCheckChecked">아이디 기억하기</label>
          </div>
          <div class="col-7 small-text text-end">
            <span class=" fw-bold">
              아이디 찾기
            </span>
            <span class=" fw-bold">
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
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around">
              <p></p>
              <p>로그인</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
          </button>
        </div>
        <div class="row d-flex justify-content-center  mb-3">
          또는
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="portal-logo-size" src="../../assets/logo/구글logo.png" alt="구글로고">  구글 로그인</button>
        </div>
        <div class="row">
          <button type="button" class="btn btn-light mb-3"><img class="portal-logo-size" src="../../assets/logo/Kakao_logo.jpg" alt="카카오로고">  카카오 로그인</button>
    
        </div>
        <div class="row" style="font-size: 13px; text-align: center;">
          <div class="col-8">
            <p>계정이 없으신가요?</p>
          </div>
          <div class="col-4">
            <a href="" style="color: #ff534c;">회원가입</a>
          </div>
        </div>

      </div>
      <div class="col-2"></div>
    </div>

  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const emailaddress = ref('');
    const password = ref('');

    const login = () => {
      // 여기에 로그인 처리 로직을 작성합니다.
      console.log('Logging in...', emailaddress.value, password.value);
      // 로그인 후에는 다음 페이지로 이동할 수 있습니다.
      // 여기서는 예시로 새로운 페이지로 이동하는 것으로 가정합니다.
      // 실제로는 라우터를 사용하여 이동하도록 구현해야 합니다.
      // router.push('/dashboard');
    };

    return {
      emailaddress,
      password,
      login
    };
  }
};
</script>

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