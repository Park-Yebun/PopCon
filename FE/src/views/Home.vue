<template>
  <div class="home-container">
    <br />
    <br />
    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button">&lt;</button>

      <!-- 오른쪽에 공지사항 텍스트 -->
      <h1 class="notice-title">팝업스토어 등록</h1>

      <!-- 가로줄 -->
      <hr class="divider" />
      <p class="contact-info">
        팝플리에 등록되어 있지 않은 팝업스토어나 오픈 예정인
      </p>
      <p class="contact-info">스토어가 있다면 팝업스토어 제보로 알려주세요.</p>
      <p class="contact-info">확인 후 등록하도록 하겠습니다!</p>
      <hr class="divider" />

      <!-- Registration Form -->
      <form @submit.prevent="submitForm" class="registration-form">
        <label for="username">브랜드 명:</label>
        <div class="input-with-icon">
        <input
          type="text"
          id="brand"
          v-model="formData.userbrand"
          placeholder="Ex : (주) 해피캣"
          required
        />
      </div>


        <label for="username">팝업 제목:</label>
        <input
          type="text"
          id="username"
          v-model="formData.username"
          placeholder="Ex : 슬램덩크 20주년 팝업스토어"
          required
        />

        <label for="address">주소:</label>
        <input
          type="text"
          id="address"
          v-model="formData.address"
          placeholder="Ex : www.yourwebsite.com"
        />

        <!-- Contact Information -->
        <label for="contact">연락처:</label>
        <input
          type="tel"
          id="contact"
          v-model="formData.contact"
          placeholder="연락처"
          required
        />

        <label for="birthdate">생년월일:</label>
        <input
          type="date"
          id="birthdate"
          v-model="formData.birthdate"
          required
        />

        <label for="startDate">가입 가능 시작일:</label>
        <input
          type="date"
          id="startDate"
          v-model="formData.startDate"
          required
        />

        <label for="endDate">가입 가능 종료일:</label>
        <input type="date" id="endDate" v-model="formData.endDate" required />

        <label for="gender">성별:</label>
        <select id="gender" v-model="formData.gender" required>
          <option value="other">선택</option>
          <option value="male">남성</option>
          <option value="female">여성</option>
        </select>

        <br />

        <button type="submit">제출하기</button>
      </form>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      formData: {
        brand: "",
        username: "",
        contact: "",
        address: "",
        birthdate: "",
        startDate: "",
        endDate: "",
        gender: "other",
      },
    };
  },
  methods: {
    goBack() {
      // 뒤로가기 로직을 추가하세요
      // 예: this.$router.go(-1);
    },
    handleButtonClick(buttonNumber) {
      // 버튼 클릭 시 로직을 추가하세요
      alert(`Button ${buttonNumber} clicked!`);
    },
    submitForm() {
      // Handle form submission logic here
      alert("Registration form submitted!");
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

/* Registration Form Styles */
.form-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.registration-form {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 300px;
}

.registration-form label {
  margin-bottom: 5px;
  font-size: 12px; /* Adjust the font size as needed */
}

.registration-form input {
  height: 25px;
  margin-bottom: 10px;
  border: 1px solid #ccc; /* Border color */
  border-radius: 10px; /* Rounded corners */
  padding: 5px; /* Padding for space inside the input field */
  background-color: transparent;
}

.registration-form button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
}

.registration-form button:hover {
  background-color: #45a049;
}
</style>
