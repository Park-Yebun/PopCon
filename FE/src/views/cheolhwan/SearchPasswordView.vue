<template>

<div class="container">
    <h1 class="text-center mt-5">비밀번호 찾기</h1>
    <form class="mt-5">
      <div class="mb-3">
        <label for="id" class="form-label">아이디입력</label>
        <input type="text" class="form-control" id="id">
      </div>
      <button type="submit" class="btn btn-primary">임시 비밀번호 이메일 보내기</button>
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