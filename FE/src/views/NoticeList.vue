<template>
  <div class="home-container">
    <br />
    <br />
    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button fixed-height">&lt;</button>

      <!-- 오른쪽에 공지사항 텍스트 -->
      <h1 class="notice-title">공지사항</h1>

      <!-- 가로줄 -->
      <hr class="divider" />

      <!-- 버튼을 추가한 부분 -->
      <div class="button-container">
        <button
          @click="handleButtonClick(1)"
          class="custom-button"
          @mouseover="showDescription(1)"
          @mouseleave="hideDescription"
        >
          [공지사항] 팝콘 지도 리뉴얼 업데이트
          <div v-if="buttonDescription === 1" class="description-text">
            {{ descriptionText }}
          </div>
        </button>
        <button
          @click="handleButtonClick(2)"
          class="custom-button"
          @mouseover="showDescription(2)"
          @mouseleave="hideDescription"
        >
          [이벤트] 팝콘 댓글이벤트
          <div v-if="buttonDescription === 2" class="description-text">
            {{ descriptionText }}
          </div>
        </button>
        <button
          @click="handleButtonClick(3)"
          class="custom-button"
          @mouseover="showDescription(3)"
          @mouseleave="hideDescription"
        >
          [이벤트] 팝콘 체험단 이벤트
          <div v-if="buttonDescription === 3" class="description-text">
            {{ descriptionText }}
          </div>
        </button>
        <!-- 추가 버튼들을 원하시는 만큼 반복하여 추가 -->
      </div>
    </div>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
  </div>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {
      buttonDescription: null,
      showDescriptionText: false,
      descriptionText: "",
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
    showDescription(buttonNumber) {
      // 마우스를 올린 버튼에 따라 설명을 설정합니다.
      this.showDescriptionText = true;
      this.descriptionText = `2024.02.1${buttonNumber}`;
      this.buttonDescription = buttonNumber;
    },
    hideDescription() {
      // 마우스가 벗어날 때 설명을 숨깁니다.
      this.showDescriptionText = false;
      this.descriptionText = "";
      this.buttonDescription = null;
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
  margin-top: -40px; /* 위에 간격을 줍니다. */
  margin-left: 40px;
  font-weight: normal;
}

.divider {
  width: 100%;
  border: 1px solid #ccc;
  margin-bottom:0px;
}

.button-container {
  display: flex;
  flex-direction: column;
  background: transparent;
  width: 100%;
  position: relative;
  z-index: 1; /* 추가 */
}

.custom-button {
  position: relative;
  height: 30px;
  text-align: left; /* 텍스트를 왼쪽 정렬합니다. */
  padding-left: 10px; /* 왼쪽 여백을 추가합니다. */
  background: transparent;
  border: 0px;
  border-bottom: 1px solid #ccc;
}

.description-text {
  position: absolute;
  top: 30%;
  right: 2%;
  width: 100%;
  background: transparent;
  font-size: 12px;
  z-index: 2;
  text-align: right;
  color: gray;
}
</style>
