<template>
  <div class="home-container">
    <br />
    <br />
    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button">&lt;</button>

      <!-- 오른쪽에 공지사항 텍스트 -->
      <h1 class="notice-title">1:1 문의</h1>

      <!-- 가로줄 -->
      <hr class="divider" />
      <br />
      <p class="contact-info">1:1 문의를 원하시는 경우</p>
      <p class="contact-info">
        popcon_contact@gmail.com을 통해 문의 부탁드립니다.
      </p>
      <br />
      <p class="contact-info">
        최대한 빠른 시일 내에 팝콘 담당자가 확인하여 답변드리도록 하겠습니다.
      </p>
    </div>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
  </div>
</template>

<script>
export default {
  name: "HomeView",
  methods: {
    goBack() {
      // 뒤로가기 로직을 추가하세요
      // 예: this.$router.go(-1);
    },
    handleButtonClick(buttonNumber) {
      // 버튼 클릭 시 로직을 추가하세요
      alert(`Button ${buttonNumber} clicked!`);
    },
  },
};
</script>

<style scoped>
.home-container {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid gray; /* 테두리 스타일 설정 */
}

.header-container {
  display: flex;
  flex-direction: column; /* 세로 방향으로 배치합니다. */
  align-items: flex-start; /* 세로로 정렬합니다. */
  padding: 10px;
  background-color: transparent;
}

.back-button {
  background: transparent;
  border: none; /* 테두리 제거 */
  height: 30px; /* 높이 조절 */
  font-size: 30px; /* 폰트 크기 조절 */
  margin-right: 10px; /* 오른쪽 여백 추가 */
}

.notice-title {
  margin: 0;
  margin-top: -30px; /* 위에 간격을 줍니다. */
  margin-left: 40px;
  font-size: 22px;
  font-weight: normal;
}

.divider {
  width: 100%;
  border: 1px solid #ccc;
  margin-top: 10px; /* 위에 간격을 줍니다. */
}

.contact-info {
  margin: 0; /* 위아래 여백을 제거합니다. */
  font-size: 14px;
}
</style>
