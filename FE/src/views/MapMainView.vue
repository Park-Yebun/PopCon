<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { map } from "@/api/popup";

const router = useRouter();
const overlay = ref(true);
const maxWidth = ref(360);
const maxHeight = ref(800);
const clickToClose = ref(true);
const overlayColorSelect = ref("#0000004D");
const canSwipe = ref(true);
const myBottomSheet = ref(null);

window.onload = function () {
  // body 요소의 스타일을 변경합니다.
  document.body.style.width = "300px";
};

const param = ref({
  lat: "",
  lng: "",
});
const currentlat = ref(0);
const currentlng = ref(0);
const centerlat = ref(0);
const centerlng = ref(0);
const markers = ref([]);
const popups = ref([]);

let mapRef = null; // 전역 변수로 선언

onMounted(async () => {
  try {
    await getLocation();
    await getNearbyPopups();
    // await loadMap(lat.value, lng.value);
  } catch (error) {
    console.error("위치 정보를 가져오는 동안 오류가 발생했습니다:", error);
  }
});

const goPopupDetail = (popupId) => {
  router.push(`/popup/${popupId}`);
};

const categoryClick = (event) => {
  // 현재 지도 화면의 중심 좌표 가져오기
  // console.log(mapRef + "밸류확인");
  const center = mapRef.getCenter();
  // console.log("현재 지도 화면의 중심 좌표:", center);
  centerlat.value = center.y;
  centerlng.value = center.x;

  if (event.target.dataset.category) {
    const category = event.target.dataset.category;
    if (category === "all") {
      goCategoryAll();
    } else {
      goCategory(category);
    }
  }
};
const goCategoryAll = () => {
  const param = { lat: currentlat.value, lng: currentlng.value };
  map(
    param,
    ({ data }) => {
      // console.log(data);
      data.sort((a, b) => a.distance - b.distance);
      popups.value = data;
      // 이전에 생성된 마커들을 제거
      markers.value.forEach((marker) => marker.setMap(null));
      markers.value = []; // 마커 배열 초기화

      // 데이터를 기반으로 마커 생성
      data.forEach((item) => {
        const marker = new window.naver.maps.Marker({
          map: mapRef,
          position: new window.naver.maps.LatLng(
            item.popupLatitude,
            item.popupLongitude
          ),
          icon: {
            content: CustomMapMarker(item),
            size: new window.naver.maps.Size(35, 35),
            scaledSize: new window.naver.maps.Size(35, 35),
          },
        });
        markers.value.push(marker); // 생성된 마커를 배열에 추가
      });
    },
    ({ response }) => {
      console.log(response);
    }
  );
};

const goCategory = (text) => {
  const param = { lat: currentlat.value, lng: currentlng.value };
  map(
    param,
    ({ data }) => {
      // 필터링된 데이터만 가져오기
      const filteredData = data.filter((item) =>
        item.popupCategory.includes(text)
      );
      // 이전에 생성된 마커들을 제거
      markers.value.forEach((marker) => marker.setMap(null));
      markers.value = []; // 마커 배열 초기화

      // 필터링된 데이터를 기반으로 마커 생성
      filteredData.forEach((item) => {
        const marker = new window.naver.maps.Marker({
          map: mapRef,
          position: new window.naver.maps.LatLng(
            item.popupLatitude,
            item.popupLongitude
          ),
          icon: {
            content: CustomMapMarker(item),
            size: new window.naver.maps.Size(35, 35),
            scaledSize: new window.naver.maps.Size(35, 35),
          },
        });
        markers.value.push(marker); // 생성된 마커를 배열에 추가
      });
    },
    ({ response }) => {
      console.log(response);
    }
  );
};

const getLocation = () => {
  // 현재위치 가져오기
  return new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        currentlat.value = position.coords.latitude;
        currentlng.value = position.coords.longitude;
        // console.log(lat.value);
        // console.log(lng.value);
        resolve();
      }, reject);
    }
  });
};

const getNearbyPopups = () => {
  // 주변팝업 가져오기
  // console.log('니어바이 실행')
  param.value.lat = currentlat.value;
  param.value.lng = currentlng.value;

  map(
    param.value,
    ({ data }) => {
      // console.log("정상!");
      // console.log(data);
      data.sort((a, b) => a.distance - b.distance);
      popups.value = data;
      console.log(popups.value, "팝업 데이터 이렇게 들어옴");
      loadMap(currentlat.value, currentlng.value);
    },
    ({ response }) => {
      console.log("error");
    }
  );
};

const loadMap = (lat, lng) => {
  const script = document.createElement("script");
  script.src =
    "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4khl77l611";
  script.async = true;
  script.defer = true;

  script.onload = () => {
    mapRef = new window.naver.maps.Map("map", {
      center: new window.naver.maps.LatLng(lat, lng),
      zoom: 15,
    });

    new window.naver.maps.Marker({
      position: new window.naver.maps.LatLng(
        currentlat.value,
        currentlng.value
      ),
      map: mapRef,
      //   icon: {
      //     url: "/src/assets/images/marker_yellow.png",
      //     size: new window.naver.maps.Size(20, 20),
      //     scaledSize: new window.naver.maps.Size(20, 20),
      //   },
      zIndex: 999,
    });

    // 내 현재 위치에서 가장 가까운 100개만 마커 생성
    // const markers = [];
    for (let i = 0; i < popups.value.length; i++) {
      // console.log("마커를 만들자!!");
      // console.log(popups.value);
      // console.log(popups.value[i].popupLatitude);
      const popupId = popups.value[i].popupId;
      const marker = new window.naver.maps.Marker({
        map: mapRef,
        position: new window.naver.maps.LatLng(
          popups.value[i].popupLatitude,
          popups.value[i].popupLongitude
        ),
        icon: {
          //   url: 'https://www.naver.com',
          content: CustomMapMarker(popups.value[i]),
          size: new window.naver.maps.Size(35, 35),
          scaledSize: new window.naver.maps.Size(35, 35),
        },
        // zIndex:999,
      });

      marker.addListener("click", () => {
        window.location.href = `/popup/${popupId}`;
        // 클릭할 때마다 해당 상세페이지로 이동
      });

      markers.value.push(marker);
    }
  };

  document.head.appendChild(script);
};

const CustomMapMarker = function (data) {
  // console.log("custom marker !!!!");
  // console.log(data);

  //   const mobileContentArray = [
  //   '<div style="margin: 0; display: table; padding: 0.5rem; table-layout: auto; border-radius: 2.3rem; border: 0.2rem solid darkgreen; background: white; cursor: pointer; position: relative; z-index: 2">',
  //     '<div style="display: table-cell; display: inline-block; width: 4rem; height: 4rem; background-image: url(Images/markerIcon.svg); background-size: cover; background-position: center; background-repeat: no-repeat;"></div>',
  //     '<div style="max-width: 13rem; height: 2rem; padding: 0 0.8rem 0 0.8rem; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; display: table-cell; vertical-align: middle; cursor: pointer; font-size: 1.5rem; letter-spacing: -0.04rem; font-weight: 600; line-height: 4rem;">',
  //             data.popupName,
  //     '</div>',
  //     '<span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: #ffffff transparent; display: block; width: 0; z-index: 1; top: 4.8rem; left: 1.4rem;"></span>',
  //     '<span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: var(--color--darkgreen) transparent; display: block; width: 0; z-index: 0; top: 5.05rem; left: 1.4rem;"></span>',
  //     '</div>',
  //   ];

  const mobileContentArray = [
    '<div class=bubble style="background-color:white;">',
    // '<div style="max-width: 7rem; height: 1rem;  overflow: hidden; text-overflow: ellipsis; white-space: nowrap; vertical-align: middle; cursor: pointer; font-size: 0.7rem; letter-spacing: -0.04rem; font-weight: 600; line-height: 1rem;">',
    // data.popupName,
    // "</div>",
    "</div>",
    '<div style="margin: 0; display: table; padding: 0.5rem; table-layout: auto; border-radius: 2.3rem; border: 0.2rem solid #FF534C; background: white; cursor: pointer; position: relative; z-index: 2">',
    '<div style="max-width: 7rem; height: 1rem;  overflow: hidden; text-overflow: ellipsis; white-space: nowrap; display: table-cell; vertical-align: middle; cursor: pointer; font-size: 0.7rem; letter-spacing: -0.04rem; font-weight: 600; line-height: 1rem;">',
    data.popupName,
    "</div>",
    // '<span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: #ffffff transparent; display: block; width: 0; z-index: 1; top: 4.8rem; left: 1.4rem;"></span>',
    // '<span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: blue transparent; display: block; width: 0; z-index: 0; top: 5.05rem; left: 1.4rem;"></span>',
    "</div>",
  ];

  return mobileContentArray.join("");
};

function goMapSearch() {
  window.location.href = "/map/search";
}

const open = () => {
  myBottomSheet.value.open();
};
const close = () => {
  myBottomSheet.value.close();
};

onMounted(() => {
  var handle_wrap = document.getElementsByClassName(
    "bottom_sheet_handle_wrap"
  )[0];
  var bottom_sheet = document.getElementsByClassName("bottom_sheet")[0];
  var up_sensor = document.getElementsByClassName("up_sensor")[0];
  let bottom_touch_start = 0;
  let bottom_scroll_start;

  // up_sensor에서 터치가 시작되었을 때
  up_sensor.addEventListener("touchstart", (e) => {
    bottom_touch_start = e.touches[0].pageY;
  });

  //up_sensor에서 터치가 움직였을 경우 (바텀시트를 건드렸을 경우) -> 바텀시트를 올림
  up_sensor.addEventListener("touchmove", (e) => {
    bottom_sheet.style.height = 70 + "%"; //바텀시트 height를 올리기 10% -> 70%
    up_sensor.style.height = 70 + "%"; //up_sensor도 따라가기
    setTimeout(function () {
      up_sensor.style.display = "none";
    }, 1000); // 바텀시트가 올라간 후, up_sensor 사라지기
  });

  //맨 위에서 아래로 스크롤했을 경우
  bottom_sheet.addEventListener("touchstart", (e) => {
    bottom_touch_start = e.touches[0].pageY; // 터치가 시작되는 위치 저장
    bottom_scroll_start = bottom_sheet.scrollTop; //터치 시작 시 스크롤 위치 저장
  });

  bottom_sheet.addEventListener("touchmove", (e) => {
    //유저가 아래로 내렸을 경우 + 스크롤 위치가 맨 위일 경우
    if (
      bottom_touch_start - e.touches[0].pageY < 0 &&
      bottom_scroll_start <= 0
    ) {
      //바텀시트 내리기
      bottom_sheet.style.height = 15 + "%";
      up_sensor.style.display = "block"; //up_sensor 다시 나타나기
      up_sensor.style.height = "10%"; //up_sensor height 다시 지정
    }
  });

  bottom_sheet.addEventListener("touchmove", (e) => {
    //유저가 위로 올렸을 경우 + 스크롤 위치가 맨 위일 경우
    if (
      bottom_touch_start - e.touches[0].pageY > 0 &&
      bottom_scroll_start >= 0
    ) {
      //바텀시트 올리기
      bottom_sheet.style.height = 80 + "%";
      up_sensor.style.display = "block"; //up_sensor 다시 나타나기
      up_sensor.style.height = "10%"; //up_sensor height 다시 지정
    }
  });

  //맨 위 핸들을 아래로 당겼을 경우
  handle_wrap.addEventListener("touchstart", (e) => {
    bottom_touch_start = e.touches[0].pageY; // 터치가 시작되는 위치 저장
  });
  handle_wrap.addEventListener("touchmove", (e) => {
    //사용자가 아래로 내렸을 경우
    if (bottom_touch_start - e.touches[0].pageY < 0) {
      //바텀시트 내리기
      bottom_sheet.style.height = 10 + "%";
      up_sensor.style.display = "block"; //up_sensor 다시 나타나기
      up_sensor.style.height = "10%"; //up_sensor height 다시 지정
    }
  });
});
</script>

<template>
  <div class="home-container">
    <!-- 지도 -->
    <div class="map" id="map"></div>

    <!-- 상단바 -->
    <div class="top-bar">
      <!-- 서치 창 -->
      <button
        @click="goMapSearch"
        type="button"
        class="btn btn-light search-btn"
      >
        <div class="search-btn-content">
          <i class="bi bi-search"></i>
          <p class="search-btn-content2">지역 혹은 이름을 검색해보세요.</p>
        </div>
      </button>

      <!-- 내위치 버튼 -->
      <button
        @click="getNearbyPopups"
        type="button"
        class="btn btn-light my-location-btn"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="25"
          height="25"
          fill="currentColor"
          class="bi bi-crosshair"
          viewBox="0 0 16 16"
        >
          <path
            d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"
          />
        </svg>
      </button>
    </div>

    <!-- 카테고리별 스크롤 -->
    <div class="wrap" @click="categoryClick">
      <div class="scroll__wrap">
        <button
          data-category="all"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          ❤️ 전체
        </button>
        <button
          data-category="패션뷰티"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          ✨ 패션/뷰티
        </button>
        <button
          data-category="식음료"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🍐 식음료
        </button>
        <button
          data-category="콘텐츠"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          📽️ 콘텐츠
        </button>
        <button
          data-category="취미여가"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🏓 취미/여가
        </button>
        <button
          data-category="금융"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          💵 금융
        </button>
        <button
          data-category="연예"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🎤 연예
        </button>
        <button
          data-category="가전디지털"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          📺 가전/디지털
        </button>
        <button
          data-category="리빙"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🛋️ 리빙
        </button>
        <button
          data-category="게임"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🎮 게임
        </button>
        <button
          data-category="캐릭터"
          type="button"
          class="btn btn-light category-btn scroll--element"
        >
          🐰 캐릭터
        </button>
      </div>
    </div>

    <!-- 임시버튼 -->
    <!-- <div>
      <button @click="getLocation()" id="find-me">내 위치 보기</button>
      {{ currentlat }}, {{ currentlng }}
    </div> -->
  </div>

  <div class="up_sensor"></div>
  <div id="bottomSheet" class="bottom_sheet">
    <div class="bottom_sheet_handle_wrap">
      <div class="bottom_sheet_handle"></div>
    </div>
    <div style="margin-bottom: 40px"></div>
    <div class="bottom_box">
      <div
        v-for="popup in popups"
        @click="goPopupDetail(popup.popupId)"
        :key="popup"
        class="card"
        style="width: 276px; height: 400px; position: relative"
      >
        <img
          :src="popup.previewImagePath"
          class="card-img-top"
          alt="posterimage"
          style="width: 274px; height: 300px; object-fit: cover"
        />
        <div class="card-body">
          <p class="card-text" style="font-weight: bold; margin-bottom: 2px">
            {{ popup.popupName }}
          </p>
          <p class="card-text" style="font-size: 14px; margin-bottom: 2px">
            {{ popup.popupStart }} - {{ popup.popupEnd }}
          </p>
          <div class="card-text" style="color: gray; font-size: 12px">
            <i class="bi bi-geo-alt-fill"></i>
            <span>{{ popup.popupLocation }}</span>
          </div>
        </div>

        <p class="likes" style="position: absolute; top: 0; right: 0">
          ❤+{{ popup.popupLike }}
        </p>
        <!-- 우측 상단 like수 -->
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-text {
  width: 250px; /* 부모 요소인 .deadline-popup의 너비에 맞추어 조정 */
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.card {
  width: 300px;
  margin-bottom: 20px; /* 각 카드 아래에 20px 여백 추가 */
}

.likes {
  color: red;
  background-color: whitesmoke;
}
.sheet-content {
  padding: 20px;
}

.top-bar {
  position: absolute;
  top: 5px;
  display: flex;
  gap: 5px;
  justify-content: space-between; /* 두 요소를 각각 양쪽 끝에 정렬합니다. */
  align-items: center;
  z-index: 100;
}
/* 기본 구조 */

.home-container {
  position: relative;
}
.wrap {
  max-width: 330px;
  margin: 10px auto;
  position: absolute;
  top: 50px;
}

/* 가로 스크롤 적용 */
.scroll__wrap {
  overflow-x: auto;
  white-space: nowrap;
  font-size: 0;
}
.scroll--element {
  display: inline-block;
  text-align: center;
}
.scroll--element + .scroll--element {
  margin-left: 15px;
}

.map {
  width: 345px;
  height: 700px;
  z-index: 0;
}
.category-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  height: 35px;
}
.my-location-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  height: 35px;
  width: 35px;
  margin-left:5px;
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
  height: 15px;
}

.search-btn-content2 {
  margin-top: 16px;
  margin-left: 5px;
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
  outline: none !important;
  cursor: pointer;

  & svg {
    width: 20px;
    height: 20px;
    fill: #5a6674;
  }
}

/* .bubble
{
position: relative;
width: 105px;
height: 35px;
padding: 0px;
background: #FFFFFF;
-webkit-border-radius: 10px;
-moz-border-radius: 10px;
border-radius: 10px;
border: #FF534C solid 4px;
} */

.bubble {
  position: relative;
  width: 105px;
  height: 35px;
  padding: 0px;
  background: #ffffff;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  border: #ff534c solid 4px;
  display: flex; /* flex 컨테이너 설정 */
  justify-content: center; /* 수평 가운데 정렬 */
  align-items: center; /* 수직 가운데 정렬 */
}

.bubble:after {
  content: "";
  position: absolute;
  border-style: solid;
  border-width: 9px 14px 0;
  border-color: #ffffff transparent;
  display: block;
  width: 0;
  z-index: 1;
  bottom: -9px;
  left: 16px;
}

.bubble:before {
  content: "";
  position: absolute;
  border-style: solid;
  border-width: 12px 17px 0;
  border-color: #ff534c transparent;
  display: block;
  width: 0;
  z-index: 0;
  bottom: -16px;
  left: 13px;
}

.bottom_sheet {
  display: flex;
  position: fixed;
  bottom: 0;
  width: 100%;
  /* width: 380px; 카드랑 딱 맞아떨어지는 크기 */
  height: 15%;
  min-height: 14%; /* 최소 높이 설정 */
  border-top-left-radius: 30px;
  border-top-right-radius: 30px;
  background-color: #ffffff transparent;
  overflow-y: auto;
  flex-direction: column;
  align-items: center;
  transition-duration: 1s;
}

.bottom_sheet_handle_wrap {
  z-index: 1;
  background: white transparent;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  width: 80%;
  height: 40px;
  margin-bottom: 10px;
  position: fixed;
}

.bottom_sheet_handle {
  width: 20%;
  height: 10px;
  background-color: #7c7979;
  border-radius: 30px;
  margin-top: 10px;
}

.bottom_box {
  width: 80%;
  height: auto;
  margin-top: 10px;
  margin-bottom: 10px;
  padding: 5px;
  border: 1px solid lightgray;
  border-radius: 10px;
  transition-duration: 2s;
  background-color: #ffffff;
  padding-top: 20px;
  padding-bottom: 20px;
}

.up_sensor {
  z-index: 1;
  position: absolute;
  background: transparent;
  width: inherit;
  height: 100%;
  bottom: 0;
  height: 10%;
  transition-duration: 1s;
}

a {
  padding: 0; /* 좌우 패딩 제거 */
}

.bi-crosshair {
  margin-left: -8px; /* 원하는 마진 값을 설정합니다. */
  margin-top: -2px;
}
</style>
