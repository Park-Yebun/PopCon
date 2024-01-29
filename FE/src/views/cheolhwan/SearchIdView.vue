
<template>
    <div>
      <h2>아이디 찾기</h2>
      <form @submit.prevent="findUsername">
        <div>
          <label for="email">가입 시 등록한 이메일 주소:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <button type="submit">아이디 찾기</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: ''
      };
    },
    methods: {
      findUsername() {
        // 여기에 아이디를 찾는 로직을 구현합니다.
        console.log('이메일 주소:', this.email);
        // 실제로 아이디를 찾는 로직을 구현합니다.
      }
    }
  };
  </script>
  
  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>