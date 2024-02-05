<template>
  <div class="home-container">
    <br />
    <br />
    <h1 class="title">Others</h1>
    <div class="image-container2">
      <img
        src="@/assets/images/alarm.png"
        alt="이미지 설명"
        width="30"
        height="30"
      />
    </div>
    <br />
    <br />
    <!-- 이미지를 감싸는 컨테이너 추가 -->
    <div class="image-container">
      <img
        src="@/assets/images/profile.png"
        alt="이미지 설명"
        width="50"
        height="50"
      />
    </div>

    <div class="content">
      <p class="welcome-text">POPCON 님</p>
      <p class="small-text">환영합니다!</p>
      <br />
      <br />

      <!-- 버튼을 감싸는 컨테이너 추가 -->
      <div class="button-container">
        <button @click="handleButtonClick(1)">마이페이지</button>
        <button @click="handleButtonClick(2)">체험단</button>
        <button @click="handleButtonClick(3)">북마크</button>
        <button @click="handleButtonClick(4)">공지사항</button>
        <button @click="handleButtonClick(5)">1:1 문의</button>
        <button @click="handleButtonClick(6)">FAQ</button>
      </div>

      <br />
      <br />
      <br />
      <br />
      <br />
      <div class="button-container2">
        <button @click="handleButtonClick(2)">팝업스토어 제보</button>
        <button @click="handleButtonClick(3)">로그아웃</button>
      </div>
      <br />
      <br />
      <br />
    </div>
    <br />
  </div>
</template>

<script>
export default {
  name: "HomePage",
  methods: {
    handleButtonClick(buttonNumber) {
      alert(`Button ${buttonNumber} clicked!`);
    },
  },
};
</script>

<style scoped>
.title {
  margin-left: 15px; /* 왼쪽 마진을 10px로 설정 */
  font-size: 22px;
  font-weight: normal; /* 굵기를 일반(normal)으로 설정 */
  line-height: 0;
}

.home-container {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid gray; /* 테두리 스타일 설정 */
}

.image-container2 {
  text-align: right;
  margin-right: 10px; /* 오른쪽 마진을 10px로 설정 */
  margin-top: -30px;
}

/* 이미지 컨테이너를 가운데 정렬 */
.image-container {
  text-align: center;
  line-height: 0;
}

.content {
  text-align: center; /* 가운데 정렬 */
}

.welcome-text {
  font-size: 22px; /* 원하는 크기로 조절 */
  line-height: 0; /* 1로 설정하면 폰트 크기와 동일한 줄 간격이 됩니다. 필요에 따라 조절하세요. */
}

.small-text {
  font-size: 13px; /* 원하는 크기로 조절 */
  /* font-weight를 따로 설정하지 않으면 기본 값인 normal이 적용됩니다. */
  line-height: 0;
}

/* 버튼 컨테이너를 가운데 정렬 */
.button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* 가로 방향 가운데 정렬 */
  gap: 10px;
}

/* 버튼을 정사각형으로 만들기 위해 높이와 너비를 동일하게 설정 */
.button-container button {
  width: 100px; /* 필요에 따라 조절하세요 */
  height: 100px; /* 필요에 따라 조절하세요 */
  background-color: #ddd; /* 기본 배경색, 필요에 따라 조절하세요 */
  color: #fa5a5a; /* 기본 글자색, 필요에 따라 조절하세요 */
  font-size: 16px;
  border: 2px solid #fa5a5a; /* 테두리 스타일 설정 */
  border-radius: 10px; /* 테두리의 모서리를 둥글게 만들기 위한 설정 */
}

.button-container2 {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* 가로 방향 가운데 정렬 */
  gap: 10px;
}

/* 버튼을 정사각형으로 만들기 위해 높이와 너비를 동일하게 설정 */
.button-container2 button {
  width: 150px; /* 필요에 따라 조절하세요 */
  height: 40px; /* 필요에 따라 조절하세요 */
  background-color: transparent; /* 기본 배경색, 필요에 따라 조절하세요 */
  color: red; /* 기본 글자색, 필요에 따라 조절하세요 */
  border: 2px solid #fa5a5a; /* 테두리 스타일 설정 */
  border-radius: 10px; /* 테두리의 모서리를 둥글게 만들기 위한 설정 */
  font-size: 16px;
  font-weight: bold;
}

/* 홀수번째 버튼에 빨간색 배경을 적용 */
.button-container button:nth-child(odd) {
  background-color: #fa5a5a;
  color: white; /* 홀수번째 버튼 글자색을 흰색으로 설정 */
}

/* 짝수번째 버튼 배경색을 없애기 */
.button-container button:nth-child(even) {
  background-color: transparent;
}

/* Your other styles here */
</style>
