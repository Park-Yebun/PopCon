
<template>

  <div class="container">
    <h1 class="text-center mt-5">아이디 찾기</h1>
    <form class="mt-5">
      <div class="mb-3">
        <label for="email" class="form-label">이메일 주소</label>
        <input type="email" class="form-control" id="email" placeholder="example@example.com">
      </div>
      <button type="submit" class="btn btn-primary">아이디 찾기</button>
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