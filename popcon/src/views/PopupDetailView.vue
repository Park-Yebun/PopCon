<template>
  <div class="home-container">
    <br>
    <br>

    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button">&lt;</button>
    </div>

    <!-- 가로줄 -->
    <hr class="divider" />
    <div>
      <img
        src="@/assets/images/detailoffimg.jpg"
        alt="공식 이미지"
        width="380px"
      />
    </div>

    <div class="popup-title">
      <p>팝업제목</p> 
    </div>

    <div class="views">
      <i class="bi bi-eye-fill"></i>
      <span> 숫자 </span>
    </div>

    <!-- 별점 + 숫자  -->
    <div class="star-ratings">
	    <div 
        class="star-ratings-fill space-x-2 text-lg"
        :style="{ width: 30 + '%' }"
	      >
		    <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
	    </div>
	    <div class="star-ratings-base space-x-2 text-lg">
		    <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
        <span>(별점)</span>
	    </div>
    </div>

    <!--주소 -->
    <div>
      <i class="bi bi-geo-alt-fill"></i>
    <span>주소 ---------------------- 주소</span>
    </div>

      <p>시작하는날짜 - 종료하는 날짜</p>
    <!-- 카테고리 -->
    <div class="category">
      <button type="button" class="btn btn-danger">카테고리</button>
    </div>

    <!-- 해쉬태그 -->
    <div class="hashtag">
      <div class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="85" height="85" fill="currentColor" class="bi bi-p-circle" viewBox="0 0 16 16">
        <path d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8m15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.5 4.002h2.962C10.045 4.002 11 5.104 11 6.586c0 1.494-.967 2.578-2.55 2.578H6.784V12H5.5zm2.77 4.072c.893 0 1.419-.545 1.419-1.488s-.526-1.482-1.42-1.482H6.778v2.97z"/>
        </svg>
        <p>주차가능</p>
      </div>
      <div class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="85" height="85" fill="currentColor" class="bi bi-ticket-perforated" viewBox="0 0 16 16">
        <path d="M4 4.85v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9zm-7 1.8v.9h1v-.9zm7 0v.9h1v-.9z"/>
        <path d="M1.5 3A1.5 1.5 0 0 0 0 4.5V6a.5.5 0 0 0 .5.5 1.5 1.5 0 1 1 0 3 .5.5 0 0 0-.5.5v1.5A1.5 1.5 0 0 0 1.5 13h13a1.5 1.5 0 0 0 1.5-1.5V10a.5.5 0 0 0-.5-.5 1.5 1.5 0 0 1 0-3A.5.5 0 0 0 16 6V4.5A1.5 1.5 0 0 0 14.5 3zM1 4.5a.5.5 0 0 1 .5-.5h13a.5.5 0 0 1 .5.5v1.05a2.5 2.5 0 0 0 0 4.9v1.05a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-1.05a2.5 2.5 0 0 0 0-4.9z"/>
        </svg>
        <p>입장료 무료</p>
      </div>
      <div class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="85" height="85" fill="currentColor" class="bi bi-wifi" viewBox="0 0 16 16">
        <path d="M15.384 6.115a.485.485 0 0 0-.047-.736A12.44 12.44 0 0 0 8 3C5.259 3 2.723 3.882.663 5.379a.485.485 0 0 0-.048.736.52.52 0 0 0 .668.05A11.45 11.45 0 0 1 8 4c2.507 0 4.827.802 6.716 2.164.205.148.49.13.668-.049"/>
        <path d="M13.229 8.271a.482.482 0 0 0-.063-.745A9.46 9.46 0 0 0 8 6c-1.905 0-3.68.56-5.166 1.526a.48.48 0 0 0-.063.745.525.525 0 0 0 .652.065A8.46 8.46 0 0 1 8 7a8.46 8.46 0 0 1 4.576 1.336c.206.132.48.108.653-.065m-2.183 2.183c.226-.226.185-.605-.1-.75A6.5 6.5 0 0 0 8 9c-1.06 0-2.062.254-2.946.704-.285.145-.326.524-.1.75l.015.015c.16.16.407.19.611.09A5.5 5.5 0 0 1 8 10c.868 0 1.69.201 2.42.56.203.1.45.07.61-.091zM9.06 12.44c.196-.196.198-.52-.04-.66A2 2 0 0 0 8 11.5a2 2 0 0 0-1.02.28c-.238.14-.236.464-.04.66l.706.706a.5.5 0 0 0 .707 0l.707-.707z"/>
        </svg>
        <p>와이파이 가능</p>
      </div>
      <div class="hashtag-item centered-image">
        <svg xmlns="http://www.w3.org/2000/svg" width="85" height="85" fill="currentColor" class="bi bi-x-octagon" viewBox="0 0 16 16">
        <path d="M4.54.146A.5.5 0 0 1 4.893 0h6.214a.5.5 0 0 1 .353.146l4.394 4.394a.5.5 0 0 1 .146.353v6.214a.5.5 0 0 1-.146.353l-4.394 4.394a.5.5 0 0 1-.353.146H4.893a.5.5 0 0 1-.353-.146L.146 11.46A.5.5 0 0 1 0 11.107V4.893a.5.5 0 0 1 .146-.353zM5.1 1 1 5.1v5.8L5.1 15h5.8l4.1-4.1V5.1L10.9 1z"/>
        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
        </svg>
        <p>식음료 반입 금지</p>
      </div>
    </div>

    <!-- 운영시간 ~ 주의사항 -->
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
            운영시간
          </button>
        </h2>
        <div id="flush-collapseOne" class="accordion-collapse collapse show" >
          <div class="accordion-body">
            월 : 11:00 ~ 22:00
            화 : 11:00 ~ 22:00
            수 : 11:00 ~ 22:00
            목 : 11:00 ~ 22:00
            금 : 11:00 ~ 22:00
            토 : 10:30 ~ 22:00
            일 : 10:30 ~ 22:00
          </div>
        </div>
      </div> 
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed show" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
            팝업스토어 소개
          </button>
        </h2>
        <div id="flush-collapseTwo" class="accordion-collapse collapse show">
          <div class="accordion-body">
            팝업스토어 소개글 주르륵
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header">
          <button class="accordion-button collapsed show" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
            안내 및 주의사항
          </button>
        </h2>
        <div id="flush-collapseThree" class="accordion-collapse collapse show">
          <div class="accordion-body">
            <p>
              * 매장 운영 사정에 따라 웨이팅 등록이 조기 마감될 수 있습니다.
              * 더 퍼스트 슬램덩크 팝업스토어 웨이팅 등록은 대신 자리를 맡아 줄을 서거나 교대로 줄을 서는 행위를 금지하고 있습니다. 해당 행위가 발견되는 경우, 웨이팅 등록 대기 줄을 다시 서도록 조치할 예정입니다.
            </p>
          </div>
        </div>
      </div>
</div>



    <div id="map"></div>
    <hr>

  </div>
</template>

<script>
export default {
  mounted() {
    // 네이버 지도 API 로드
    const script = document.createElement("script");
    script.src =
      "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4khl77l611";
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);

    script.onload = () => {
      // 네이버 지도 생성
      new window.naver.maps.Map("map", {
        center: new window.naver.maps.LatLng(37.5670135, 126.9783740),
        zoom: 18
      });
    };
  },
  methods: {
      goBack() {
      // 뒤로가기 로직을 추가하세요
      // 예: this.$router.go(-1);
    },
      ratingToPercent() {
      // const score = +this.popup.averageScore * 20; 팝업평균별점
      // return score + 1.5;
    },
  },
};
</script>

<style scoped>
.centered-image {
  display: inline-block;
}
.star-ratings {
  color: #aaa9a9; 
  position: relative;
  unicode-bidi: bidi-override;
  width: max-content;
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
  margin-top: 80px; /*임의설정값, 나중에 css 건드리면 또 고쳐야됨 ㅠㅠ*/
} 
.star-ratings-fill {
  color: #fff58c;
  padding: 0;
  position: absolute;
  z-index: 1;
  display: flex;
  top: 0;
  left: 0;
  overflow: hidden;
  -webkit-text-fill-color: gold;
}
.star-ratings-base {
  z-index: 0;
  padding: 0;
}
.views {
  vertical-align: middle;
  float: left; width: 20%;
}

.popup-title {
  margin: 0;
  margin-left: 0px;
  font-size: 22px;
  font-weight: normal;
  float: left; width: 80%;
}
.hashtag-item {
  text-align: center;
}
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

.divider {
  width: 100%;
  border: 1px solid #ccc;
  margin-top: 10px; /* 위에 간격을 줍니다. */
}

.image-container {
  max-width: 400px;
  text-align: center;
  line-height: 0;
}

#map {
  width: 100%;
  height: 400px;
}
</style>