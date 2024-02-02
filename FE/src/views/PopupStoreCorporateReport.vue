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
        <label for="name">팝업 제목:</label>
        <input
          type="text"
          id="popup_name"
          v-model="formData.name"
          placeholder="Ex : 슬램덩크 20주년 팝업스토어"
          required
        />

        <label for="brand">브랜드 명:</label>
        <div class="input-with-icon">
          <input
            type="text"
            id="popup_brand"
            v-model="formData.brand"
            placeholder="Ex : (주) 해피캣"
            required
          />
        </div>

        <label for="content">팝업 내용(소개글):</label>
        <textarea
          id="popup_content"
          v-model="formData.content"
          placeholder="게시글에 대한 소개글을 작성해주세요."
          required
        ></textarea>

        <label for="location">팝업 위치:</label>
        <input
          type="text"
          id="popup_location"
          v-model="formData.location"
          placeholder="Ex : 주소를 입력해주세요"
        />

        <label for="start">팝업 시작일:</label>
        <input type="date" id="popup_start" v-model="formData.start" required />

        <label for="end">팝업 종료일:</label>
        <input type="date" id="popup_end" v-model="formData.end" required />

        <label for="operating">운영 시간:</label>
        <textarea
          id="popup_operating"
          v-model="formData.operating"
          placeholder="월~금 운영시간을 입력해주세요"
          required
        ></textarea>

        <label for="event">이벤트 및 프로모션:</label>
        <textarea
          id="popup_event"
          v-model="formData.event"
          placeholder="이벤트 및 프로모션을 입력해주세요"
          required
        ></textarea>

        <!-- 추가: 주차 여부 -->
        <label for="car">주차 여부:</label>
        <input type="checkbox" id="popup_car" v-model="formData.car" />
        <label for="car">주차 가능</label>

        <!-- 추가: 입장료 여부 -->
        <label for="entryfee">입장료:</label>
        <input
          type="checkbox"
          id="popup_entryfee"
          v-model="formData.entryfee"
        />
        <label for="entryfee">입장료 있음</label>

        <!-- 추가: 와이파이 여부 -->
        <label for="wifi">와이파이:</label>
        <input type="checkbox" id="popup_wifi" v-model="formData.wifi" />
        <label for="wifi">와이파이 제공</label>

        <!-- 추가: 식음료 여부 -->
        <label for="eat">식음료 판매:</label>
        <input
          type="checkbox"
          id="popup_eat"
          v-model="formData.eat"
        />
        <label for="eat">식음료 판매</label>

        <label for="site">브랜드 공식사이트:</label>
        <input
          type="text"
          id="popup_site"
          v-model="formData.site"
          placeholder="Ex : www.yourwebsite.com"
        />

        <label for="instar">브랜드 공식인스타그램:</label>
        <input
          type="text"
          id="popup_instar"
          v-model="formData.instar"
          placeholder="Ex : https://www.instagram.com"
        />

        <label for="notice">추가 공지사항:</label>
        <textarea
          id="popup_notice"
          v-model="formData.notice"
          placeholder="추가 공지사항을 입력해주세요"
          required
        ></textarea>

        <!-- Contact Information -->
        <label for="phone">담당자 연락처:</label>
        <input
          type="tel"
          id="popup_phone"
          v-model="formData.phone"
          placeholder="연락처"
          required
        />

        <label for="honey">꿀팁:</label>
        <textarea
          id="popup_honey"
          v-model="formData.honey"
          placeholder="꿀팁을 입력해주세요"
          required
        ></textarea>

        <button type="submit">제출하기</button>
      </form>
    </div>
    <br />
  </div>
</template>

<script>
export default {
  name: "PopupStoreCorporateReport",
  data() {
    return {
      formData: {
        popup_name: "",
        popup_brand: "",
        popup_content: "",
        popup_location: "",
        popup_start: "",
        popup_end: "",
        popup_operating: "",
        popup_event: "",
        popup_car: "",
        popup_entryfee: "",
        popup_wifi: "",
        popup_eat: "",
        popup_site: "",
        popup_instar: "",
        popup_notice: "",
        popup_phone: "",
        popup_honey: "",
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
