<template>
    <div>
      <h2>비밀번호 재설정</h2>
      <form @submit.prevent="resetPassword">
        <div>
          <label for="username">아이디:</label>
          <input type="text" id="username" v-model="username" required>
        </div>
        <div>
          <label for="email">가입 시 등록한 이메일 주소:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <button type="submit">비밀번호 재설정</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        username: '',
        email: ''
      };
    },
    methods: {
      resetPassword() {
        // 여기에 비밀번호를 재설정하는 로직을 구현합니다.
        console.log('아이디:', this.username);
        console.log('이메일 주소:', this.email);
        // 실제로 비밀번호를 재설정하는 로직을 구현합니다.
      }
    }
  };
  </script>
  
  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>