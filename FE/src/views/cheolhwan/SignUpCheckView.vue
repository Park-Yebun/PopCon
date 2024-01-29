<!-- router 회원가입 페이지로 갈 수 있게 만들어줘야함 -->

<template>
    <div>
        <img src="../assets/로고/로고_배경제거.png" alt="logo">
    </div>

    <div>
        <h4>독특한 경험과 핫한 트렌드를 한눈에! POPCON과 함께하세요</h4>
    </div>

    <div><button @click="goToCompanyForm">기업회원</button></div>
    <div><button @click="goToMemberForm">개인회원</button></div>
  
  </template>
  
  <script>
  export default {
    data() {
      return {
    
      };
    },
    methods: {
        goToCompanyForm(){
            this.$router.push('/company-registration');
        },
        goToMemberForm(){
            this.$router.push('/member-registration');
        },
    }
  };
  </script>
  
  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>