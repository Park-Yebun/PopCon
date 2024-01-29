<template>
    <div>
      <h2>회원 정보 수정</h2>
      <form @submit.prevent="updateMemberInfo">
        <div>
          <label for="email">이메일 주소:</label>
          <input type="email" id="email" v-model="email" required>
        </div>
        <div>
          <label for="nickname">닉네임:</label>
          <input type="text" id="nickname" v-model="nickname" required>
        </div>
        <div>
          <label for="password">비밀번호:</label>
          <input type="password" id="password" v-model="password" required>
        </div>
        <div>
          <label for="confirmPassword">비밀번호 확인:</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" required>
        </div>
        <button type="submit">저장</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        email: 'user@example.com', // 현재 이메일 주소
        nickname: '사용자 닉네임', // 현재 닉네임
        password: '', // 비밀번호
        confirmPassword: '' // 비밀번호 확인
      };
    },
    methods: {
      updateMemberInfo() {
        // 회원 정보 수정 로직을 구현합니다.
        console.log('이메일 주소:', this.email);
        console.log('닉네임:', this.nickname);
        console.log('비밀번호:', this.password);
        console.log('비밀번호 확인:', this.confirmPassword);
        // 실제로 회원 정보를 업데이트하는 로직을 추가합니다.
      }
    }
  };
  </script>
  
  <style scoped>

  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>