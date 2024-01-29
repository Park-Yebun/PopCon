<template>
    <div>
      <h2>기업 회원가입</h2>
      <form @submit.prevent="submitForm">
        <div>
          <label for="companyName">회사 이름:</label>
          <input type="text" id="companyName" v-model="companyName" required>
        </div>
        <div>
          <label for="username">아이디:</label>
          <input type="text" id="username" v-model="username" required>
          <button @click="checkUsernameAvailability">중복 확인</button>
        </div>
        <div>
          <label for="email">이메일 주소:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <div>
          <label for="password">비밀번호:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div>
          <label for="confirmPassword">비밀번호 확인:</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" required>
        </div>
        <div>
          <label for="businessNumber">사업자 번호:</label>
          <input type="text" id="businessNumber" v-model="businessNumber" required>
          <button @click="checkBusinessNumberValidity">확인</button>
        </div>
        <button type="submit">회원가입</button>
      </form>
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
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>