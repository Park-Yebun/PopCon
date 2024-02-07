<template>
  <div class="home-container">
    <!-- 지도 -->
    <div class="map" id="map"></div>
    
    <!-- 상단바 -->
    <div class="top-bar">
      <!-- 서치 창 -->
      <button @click="goMapSearch" type="button" class="btn btn-light search-btn">
        <div class="search-btn-content">
          <i class="bi bi-search"></i>
          <p>지역 혹은 이름을 검색해보세요.</p>
        </div>
      </button>

      <!-- 내위치 버튼 -->
      <button @click="myLocation" type="button" class="btn btn-light my-location-btn">
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-crosshair" viewBox="0 0 16 16">
          <path d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
        </svg>
      </button>
    </div>
      
      
      <!-- 카테고리별 스크롤 -->
      <div class="wrap">
        <div class="scroll__wrap">
          <button @click="goCategoryAll" type="button" class="btn btn-light category-btn scroll--element">
          ❤️ 전체
        </button>
        <button @click="goCategoryFashion" type="button" class="btn btn-light category-btn scroll--element">
          ✨ 패션/뷰티
        </button>
        <button @click="goCategoryFNB" type="button" class="btn btn-light category-btn scroll--element">
          🍐 식음료
        </button>
        <button @click="goCategoryContents" type="button" class="btn btn-light category-btn scroll--element">
          📽️ 콘텐츠
        </button>
        <button @click="goCategoryHobbies" type="button" class="btn btn-light category-btn scroll--element">
          🏓 취미/여가
        </button>
        <button @click="goCategoryFinance" type="button" class="btn btn-light category-btn scroll--element">
          💵 금융
        </button>
        <button @click="goCategoryCeleb" type="button" class="btn btn-light category-btn scroll--element">
          🎤 연예
        </button>
        <button @click="goCategoryDigital" type="button" class="btn btn-light category-btn scroll--element">
          📺 가전/디지털
        </button>
        <button @click="goCategoryLiving" type="button" class="btn btn-light category-btn scroll--element ">
          🛋️ 리빙
        </button>
        <button @click="goCategoryGame" type="button" class="btn btn-light category-btn scroll--element">
          🎮 게임
        </button>
        <button @click="goCategoryCharacter" type="button" class="btn btn-light category-btn scroll--element">
          🐰 캐릭터
        </button>
      </div>
    </div>   
    
      <!-- 임시버튼 -->
      <div>
      <button @click="getLocation()" id="find-me">내 위치 보기</button> {{ lat }}, {{ lng }}
      </div> 
    
    <div class="container pt-5 pb-5">
    
    <button class="btn btn-primary" type="button" @click="open">Open bottom sheet</button>
    <vue-bottom-sheet
      :overlay-click-close="clickToClose"
      :transition-duration="0.5"
      :max-width="maxWidth"
      :max-height="maxHeight"
      :overlay="overlay"
      :can-swipe="canSwipe"
      :overlay-color="overlayColorSelect"
      ref="myBottomSheet"
    >
      <div class="sheet-content">
        <div class="card" style="width: 360px; height: 275px; position: relative;">
          <img src="@/assets/images/poster_01.jpg" class="card-img-top" alt="posterimage" style=" width: 340px; height: 145px;">
          <div class="card-body">
            <h4 class="card-text" >팝업 제목이 들어감</h4>
            <p class="card-text" >24. 02. 01 - 24. 03. 01</p>
            <div class="location">
              <i class="bi bi-geo-alt-fill"></i>
              <span>주소 ---------------------- 주소</span>
            </div>
          </div>
          <h2 style="position: absolute; top: 0; left: 0;" ><span class="badge text-bg-light">2월 2일</span></h2>
          <!-- 좌측 상단 날짜 -->

          <p class="likes" style="position: absolute; top: 0; right: 0;">❤ +좋아요수</p> 
          <!-- 우측 상단 like수 -->
        </div>
        <div class="card" style="width: 360px; height: 275px; position: relative;">
          <img src="@/assets/images/poster_01.jpg" class="card-img-top" alt="posterimage" style=" width: 340px; height: 145px;">
          <div class="card-body">
            <h4 class="card-text" >팝업 제목이 들어감</h4>
            <p class="card-text" >24. 02. 01 - 24. 03. 01</p>
            <div class="location">
              <i class="bi bi-geo-alt-fill"></i>
              <span>주소 ---------------------- 주소</span>
            </div>
          </div>
          <h2 style="position: absolute; top: 0; left: 0;" ><span class="badge text-bg-light">2월 2일</span></h2>
          <!-- 좌측 상단 날짜 -->

          <p class="likes" style="position: absolute; top: 0; right: 0;">❤ +좋아요수</p> 
          <!-- 우측 상단 like수 -->
        </div>
        <div class="card" style="width: 360px; height: 275px; position: relative;">
          <img src="@/assets/images/poster_01.jpg" class="card-img-top" alt="posterimage" style=" width: 340px; height: 145px;">
          <div class="card-body">
            <h4 class="card-text" >팝업 제목이 들어감</h4>
            <p class="card-text" >24. 02. 01 - 24. 03. 01</p>
            <div class="location">
              <i class="bi bi-geo-alt-fill"></i>
              <span>주소 ---------------------- 주소</span>
            </div>
          </div>
          <h2 style="position: absolute; top: 0; left: 0;" ><span class="badge text-bg-light">2월 2일</span></h2>
          <!-- 좌측 상단 날짜 -->

          <p class="likes" style="position: absolute; top: 0; right: 0;">❤ +좋아요수</p> 
          <!-- 우측 상단 like수 -->
        </div>
        <div class="card" style="width: 360px; height: 275px; position: relative;">
          <img src="@/assets/images/poster_01.jpg" class="card-img-top" alt="posterimage" style=" width: 340px; height: 145px;">
          <div class="card-body">
            <h4 class="card-text" >팝업 제목이 들어감</h4>
            <p class="card-text" >24. 02. 01 - 24. 03. 01</p>
            <div class="location">
              <i class="bi bi-geo-alt-fill"></i>
              <span>주소 ---------------------- 주소</span>
            </div>
          </div>
          <h2 style="position: absolute; top: 0; left: 0;" ><span class="badge text-bg-light">2월 2일</span></h2>
          <!-- 좌측 상단 날짜 -->

          <p class="likes" style="position: absolute; top: 0; right: 0;">❤ +좋아요수</p> 
          <!-- 우측 상단 like수 -->
        </div>
        <div class="card" style="width: 360px; height: 275px; position: relative;">
          <img src="@/assets/images/poster_01.jpg" class="card-img-top" alt="posterimage" style=" width: 340px; height: 145px;">
          <div class="card-body">
            <h4 class="card-text" >팝업 제목이 들어감</h4>
            <p class="card-text" >24. 02. 01 - 24. 03. 01</p>
            <div class="location">
              <i class="bi bi-geo-alt-fill"></i>
              <span>주소 ---------------------- 주소</span>
            </div>
          </div>
          <h2 style="position: absolute; top: 0; left: 0;" ><span class="badge text-bg-light">2월 2일</span></h2>
          <!-- 좌측 상단 날짜 -->

          <p class="likes" style="position: absolute; top: 0; right: 0;">❤ +좋아요수</p> 
          <!-- 우측 상단 like수 -->
        </div>



        



        <button type="button" class="btn btn-danger" @click="close">Close</button>
      </div>
    </vue-bottom-sheet>
  </div>


  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { ref } from 'vue'
// import VueBottomSheet from "@webzlodimir/vue-bottom-sheet";
// import "@webzlodimir/vue-bottom-sheet/dist/style.css";

const overlay = ref(true)
const maxWidth = ref(360)
const maxHeight = ref(800)
const clickToClose = ref(true)
const overlayColorSelect = ref('#0000004D')
const canSwipe = ref(true)
const myBottomSheet = ref(null)
const lat = ref(0)
const lng = ref(0)

function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      lat.value = position.coords.latitude
      lng.value = position.coords.longitude
    })
  }
}

function goMapSearch() {
  window.location.href = '/map/search';
}

const open = () => {
  myBottomSheet.value.open()
}
const close = () => {
  myBottomSheet.value.close()
}

</script>


<script>
import { ref } from 'vue';

export default {
  async mounted() {
    const lat = ref(0);
    const lng = ref(0);

    if (navigator.geolocation) {
      await this.getLocation(lat, lng);
      this.loadMap(lat.value, lng.value);
    }
  },
  methods: {
    getLocation(lat, lng) {
      return new Promise((resolve, reject) => {
        navigator.geolocation.getCurrentPosition((position) => {
          lat.value = position.coords.latitude;
          lng.value = position.coords.longitude;
          resolve();
        }, reject);
      });
    },
    loadMap(lat, lng) {
      const script = document.createElement("script");
      script.src = "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4khl77l611";
      script.async = true;
      script.defer = true;

      script.onload = () => {
        const mapRef = new window.naver.maps.Map("map", {
          center: new window.naver.maps.LatLng(lat, lng),
          zoom: 13
        });

        new window.naver.maps.Marker({
          position: new window.naver.maps.LatLng(lat, lng),
          map: mapRef,
          icon: {
            url: "/src/assets/images/my-location.png",
            size: new window.naver.maps.Size(43, 43),
            scaledSize: new window.naver.maps.Size(43, 43),
          },
          zIndex: 999,
        });

        // 내 현재 위치에서 가장 가까운 100개만 마커 생성
        const markers = [];
        for (let i = 1; i < 100; i++) {
          const marker = new window.naver.maps.Marker({
            map: mapRef,
            position: new window.naver.maps.LatLng(sortedToiletData[i].Y_WGS84, sortedToiletData[i].X_WGS84),
            icon: {
              url: `${aroundToilet}`,
              size: new window.naver.maps.Size(35, 35),
              scaledSize: new window.naver.maps.Size(35, 35),
            },
          });

          markers.push(marker);
        }
      };

      document.head.appendChild(script);
    }
  }
};
</script>

<style scoped>
.location {
  color: gray
}
.likes {
  color: red;
}
.sheet-content {
  padding: 20px;
}

.top-bar {
  position: absolute;
  top:0px;
  display: flex;
  gap: 5px;
  justify-content: space-between; /* 두 요소를 각각 양쪽 끝에 정렬합니다. */
  align-items: center; 
  z-index: 100;
}
 /* 기본 구조 */
.home-container{
  position: relative;
}
 .wrap {max-width:400px; margin:10px auto; 
  position: absolute; 
  top: 50px
}

/* 가로 스크롤 적용 */
.scroll__wrap {overflow-x:auto; white-space:nowrap; font-size:0}
.scroll--element {display:inline-block; text-align:center;}
.scroll--element + .scroll--element {margin-left:15px;}

.map {
  width: 360px;
  height: 800px;
  z-index: 0;
}
.category-btn,
.my-location-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
}

.search-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  font-size: 12px;
  width: 280px;
}
.search-btn-content {
  display: flex; 
  align-items: center;
}

.search-form {
  top: 10px;
  left: 10px;
  width: 230px;
  height: 40px;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  display: flex;
  z-index: 1;
  
}

.search-input {
  position: absolute;
  top: 10px;
  left: 38px;
  font-size: 10px;
  background: none;
  color: #5a6674;
  width: 90%;
  height: 20px;
  border: none;
  appearance: none;
  outline: none;
  z-index: 1;

  &::-webkit-search-cancel-button {
    appearance: none;
  }
}

.search-button {
  top: 10px;
  left: 15px;
  height: 20px;
  width: 20px;
  padding: 0;
  margin: 0;
  border: none;
  background: none;
  outline: none!important;
  cursor: pointer;
  
  & svg {
    width: 20px;
    height: 20px;
    fill: #5a6674;
  }
}


</style>