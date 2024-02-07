<script setup>
import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { home } from "@/api/popup";

const router = useRouter();

const popupend = ref();
const popupgood = ref();

onMounted(async () => {
  await home(
    ({ data }) => {
      console.log(data);
      console.log(data.popupend);
      popupend.value = data.popupend;
      popupgood.value = data.popupgood;
    },
    ({ response }) => {
      console.log(response);
    }
  );
});

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSearch = function (categoryName) {
  router.push({
    name: "popup-search-category",
    params: { category: "categoryName" },
  });
};
const lat = ref(0);
const lng = ref(0);

function getLocaion() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      lat.value = position.coords.latitude;
      lng.value = position.coords.longitude;
    });
  }
}

function openCamera() {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then((stream) => {
      const video = document.createElement("video");
      video.srcObject = stream;
      video.autoplay = true;
      video.onloadedmetadata = () => {
        const canvas = document.createElement("canvas");
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        canvas.getContext("2d").drawImage(video, 0, 0);
        this.imageUrl = canvas.toDataURL("image/png");
        stream.getTracks().forEach((track) => track.stop());
      };
    })
    .catch((error) => {
      console.error("카메라 액세스 거부:", error);
    });
}

function openGallery() {
  const input = document.createElement("input");
  input.type = "file";
  input.accept = "image/*";
  input.onchange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  };
  input.click();
}
</script>

<template>
  <!-- 캐루셀 -->
  <div
    title="캐루셀"
    id="carouselExampleIndicators"
    class="carousel slide h-250"
  >
    <div class="carousel-indicators">
      <button
        class="active"
        id="button1"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="0"
        aria-current="true"
        aria-label="Slide 1"
      ></button>
      <button
        id="button2"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="1"
        aria-label="Slide 2"
      ></button>
      <button
        id="button3"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="2"
        aria-label="Slide 3"
      ></button>
    </div>
    <div v-if="popupgood" class="carousel-inner">
      <div class="carousel-item active">
        <img
          :src="popupgood[0].popupImagePath"
          class="d-block w-100 rounded-bottom-4"
          alt=""
        />
      </div>
      <div class="carousel-item">
        <img
          :src="popupgood[1].popupImagePath"
          class="d-block w-100 rounded-bottom-4"
          alt=""
        />
      </div>
      <div class="carousel-item">
        <img
          :src="popupgood[2].popupImagePath"
          class="d-block w-100 rounded-bottom-4"
          alt=""
        />
      </div>
    </div>
  </div>
<!-- 마감임박 팝업스토어 리스트 -->
<div class="deadline-container">
  <div class="deadline-text-group">
    <h5 style="opacity: 0.84;
        font-size: 16px;
        font-weight: 800;
        line-height: 30px;
        word-break: keep-all;">마감임박</h5>
    <div href="#"
        style="width: 100%;
        height: 100%;
        text-align:right;
        color: #747688;
        font-size: 13px;
        font-weight: 400;
        line-height: 23px;
        word-wrap: break-word">더보기</div>
  </div>
  <div title="마감임박 팝업리스트" class="deadline-popup-group">
    <div v-for="popup in popupend" class="deadline-popup">
      <img :src= "popup.previewImagePath" class="deadline-popup-img" :alt="popup.popupName + '사진'">
      <h5 class="deadline-popup-titdeadline-popuple">{{ popup.popupName }}</h5>

    </div>
    <div title="마감임박 팝업리스트" class="deadline-popup-group">
      <template v-for="(popup, key) in popupend">
        <div class="deadline-popup">
          <img
            :src="popupend[key].previewImagePath"
            class="deadline-popup-img"
            alt=""
          />
          <p class="deadline-popup-titdeadline-popuple">
            {{ popup.popupName }}
          </p>
          <p>{{ popup.popupEnd }}</p>
        </div>
      </template>
    </div>
  </div>

  <!-- 카테고리 -->
  <div title="main-category">
    <div class="main-category-container">
      <div class="main-category-text">
        <img
          @click="goSearch('패션뷰티')"
          class="main-category-logo"
          src="@/assets/images/category_beauty.png"
          alt="패션/뷰티 아이콘"
        />패션/뷰티
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('식음료')"
          class="main-category-logo"
          src="@/assets/images/category_foods.png"
          alt="식음료 아이콘"
        />식음료
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('콘텐츠')"
          class="main-category-logo"
          src="@/assets/images/category_content.png"
          alt="콘텐츠 아이콘"
        />콘텐츠
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('취미여가')"
          class="main-category-logo"
          src="@/assets/images/category_hobby.png"
          alt="취미/여가 아이콘"
        />취미/여가
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('금융')"
          class="main-category-logo"
          src="@/assets/images/category_finance.png"
          alt="금융 아이콘"
        />금융
      </div>
    </div>
    <div class="main-category-container">
      <div class="main-category-text">
        <img
          @click="goSearch('연예')"
          class="main-category-logo"
          src="@/assets/images/category_entertain.png"
          alt="연예 아이콘"
        />연예
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('가전/디지털')"
          class="main-category-logo"
          src="@/assets/images/category_digital.png"
          alt="가전/디지털 아이콘"
        />가전/디지털
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('리빙')"
          class="main-category-logo"
          src="@/assets/images/category_living.png"
          alt="리빙 아이콘"
        />리빙
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('게임')"
          class="main-category-logo"
          src="@/assets/images/category_game.png"
          alt="게임 아이콘"
        />게임
      </div>
      <div class="main-category-text">
        <img
          @click="goSearch('캐릭터')"
          class="main-category-logo"
          src="@/assets/images/category_character.png"
          alt="캐릭터 아이콘"
        />캐릭터
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { compileScript } from 'vue/compiler-sfc';

const router = useRouter()

const popupend = ref({})

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSearch = function(categoryName) {
  router.push({ name: 'search', params: {'category': categoryName}})
}

onMounted(async () => {
  // axios({
  //   method: 'get',
  //   url: `http://localhost:8080/popups/search?startDa&endDate=~~&area=~~&status=~~&category=~ `,
  //   data: {
  //     title: threadTitle.value,
  //     content: threadContent.value,
  //     user: store.userId,
  //     review: reviewId.value
  //   }
  // })
  // .then((response) => {
  //   console.log('타래 작성 완료')
  //   router.push({ name: 'ThreadListPage', params: { reviewId: reviewId.value } })
  // })
  // .catch((error) => {
  //   console.log('타래 작성 실패!')
  // })

  // 마감임박 리스트 가져오기
  // 3일 뒤 날짜를 가져오는 코드
  const date = new Date()
  date.setDate(date.getDate() + 3)
  const year = date.getFullYear()
  const month = ('0' + (date.getMonth() + 1)).slice(-2)
  const day = ('0' + (date.getDate())).slice(-2)
  const endDate = `${year}-${month}-${day}`
  console.log(endDate)

  axios.get('/popups/search',{params : {
        startDate: null,
        endDate: endDate,
        area: null,
        status: "진행중",
        category: null,
    }})
        .then((response) => {
          popupend.value = response.data
        })
        .catch((error) => {
          console.log(error);
        })

})
</script>

<style scoped>
/* 이미지랑 캐러셀 세로 사이즈 통일(전체 길이의 약 1/3) */
img {
  height: 279px;
}
.carousel {
  height: 279px;
  position: relative;
}

/* [수정중] 인디케이터 동그랗게 만들기 */
.carousel-indicators {
  position: absolute;
  bottom: 0%;
}

/* #button1 {
  width: 3px;
  height: 3px;
  left: 50%;
  top: 0px;
  position: absolute;
  background: #616266;
  border-radius: 9999px;
}

#button2 {
  width: 9px;
  height: 9px;
  left: 13px;
  top: 0px;
  position: absolute;
  background: #E2E2E2;
  border-radius: 9999px;
}

#button3 {
  width: 9px;
  height: 9px;
  left: 26px;
  top: 0px;
  position: absolute;
  background: #E2E2E2;
  border-radius: 9999px;
} */

.deadline-text-group {
  display: flex;
  justify-content: space-between;
  width: 316px;
  height: 30px;
  flex-shrink: 0;
}

.deadline-container {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 3.19px;
}

.main-category-logo {
  width: 42px;
  height: 42px;
}

.main-category-text {
  display: flex;
  flex-direction: column;
  font-size: 10px;
  font-family: ABeeZee;
  font-weight: 400;
  line-height: 22px;
  word-wrap: break-word;
}

.main-category-container {
  width: 282px;
  display: flex;
  text-align: center;
  justify-content: space-between;
  margin-left: 40px;
}

.deadline-popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 237px;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
  align-items: flex-start; /* 자식 요소가 부모 요소의 크기를 벗어나지 않도록 수정 */
}

.deadline-popup {
  width: 200px;
  height: 170.89px;
  margin-right: 20px;
  border-radius: 10%;
}

.deadline-popup p {
  width: 146.56px; /* 부모 요소인 .deadline-popup의 너비에 맞추어 조정 */
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.deadline-popup-img {
  width: 146.56px;
  height: 170.89px;
  border-radius: 20%;
}

/* 스크롤 안보이게 숨기기 */
.deadline-popup-group {
  -ms-overflow-style: none;
}
.deadline-popup-group::-webkit-scrollbar {
  display: none;
}
</style>