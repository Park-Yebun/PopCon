<template>
    <div>
      <h2>회원가입</h2>
      <form @submit.prevent="submitForm">
        <div>
          <label for="email">이메일 주소:</label>
          <input type="email" id="email" v-model="email" placeholder="메일주소" required>
          <button @click="checkEmailAvailability">중복 확인</button>
        </div>
        <div>
          <label for="nickname">닉네임:</label>
          <input type="text" id="nickname" v-model="nickname" placeholder="닉네임" required>
        </div>
        <div>
          <label for="password">비밀번호:</label>
          <input type="password" id="password" v-model="password" placeholder="패스워드" required>
        </div>
        <div>
          <label for="confirmPassword">비밀번호 확인:</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="패스워드확인" required>
        </div>
        <div>
          <label for="birthday">생년월일:</label>
          <input type="date" id="birthday" v-model="birthday" required>
        </div>
        <div>
          <label for="gender">성별:</label>
          <select id="gender" v-model="gender" required>
            <option value="male">남성</option>
            <option value="female">여성</option>
          </select>
        </div>
        <div>
          <label for="phoneNumber">휴대폰번호:</label>
          <input type="tel" id="phoneNumber" v-model="phoneNumber" placeholder="선택">
        </div>
        <button type="submit">회원가입</button>
      </form>
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
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>