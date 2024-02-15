<template>
  <!-- 서치 창 -->
  <div type="button" class="btn btn-light search-btn">
    <div class="search-btn-content">
      <i @click="goMapMain" class="bi bi-chevron-left"></i>
      <input
        class="search-input"
        v-model="searchTerm"
        @input="searchKeyword($event)"
        @keyup.enter="handleSearch"
        placeholder="지역 혹은 이름을 검색해보세요."
      />
    </div>
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

  <!-- 최근 검색어 -->
  <div class="search-keywords">
    <div style="padding: 10px">
      <i class="bi bi-clock"></i
      ><span style="font-weight: bold">최근 검색어</span>
    </div>
    <div class="keyword-item" v-if="recentSearches.length === 0">
      최근 검색어가 없습니다.
    </div>
    <div v-else>
      <div
        v-for="keyword in recentSearches"
        :key="keyword"
        class="keyword-item"
      >
        <span @click="searchKeywordFromHistory(keyword)">{{ keyword }}</span>
        <i @click="removeKeyword(keyword)" class="bi bi-x-lg"></i>
        <hr />
      </div>
    </div>
  </div>

  <!-- 검색 결과 -->
  <div>
    <ul class="search-list">
      <li
        @click="goPopupDetail(search.popupId)"
        v-for="search in searchList"
        :key="search"
        class="search-item"
      >
        <!-- 지도 아이콘 -->
        <div class="flex">
          <div class="flex1">
            <i class="bi bi-geo-alt-fill"></i>
          </div>
          <div class="flex2">
            <span id="flex2-name">{{ search.popupName }}</span>
            <span id="flex2-location">{{ search.popupLocation }}</span>
            <span id="flex2-like">좋아요 {{ search.popupLike }}</span>
          </div>
          <div class="flex3">
            <span id="flex3-category">{{ search.popupCategory[0] }}</span>
            <span id="flex3-distance"
              >{{
                (Math.round(search.distance * 100) / 100).toFixed(1)
              }}km</span
            >
          </div>
        </div>
        <hr />
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { mapSearch } from "@/api/popup";
import { useRouter } from "vue-router";

const router = useRouter();
const searchList = ref();
const recentSearches = ref(
  JSON.parse(localStorage.getItem("recentSearches")) || []
);
const searchTerm = ref("");
const param = ref({
  keyword: "",
  lat: "",
  lng: "",
});
const lat = ref(0);
const lng = ref(0);

onMounted(async () => {
  try {
    await getLocation();
  } catch (error) {
    console.error("위치 정보를 가져오는 동안 오류가 발생했습니다:", error);
  }
});

const handleSearch = () => {
  // 검색어 저장
  saveSearchTerm();
};

const saveSearchTerm = () => {
  const term = searchTerm.value.trim();
  if (term === "") return;

  // 최근검색어 저장
  recentSearches.value.unshift(term);
  // 최근검색어는 10개까지만
  recentSearches.value = recentSearches.value.slice(0, 10);
  // 로컬스토리지 집어넣기
  localStorage.setItem("recentSearches", JSON.stringify(recentSearches.value));
};

// 최근검색어 삭제
const removeKeyword = (keyword) => {
  const index = recentSearches.value.indexOf(keyword);
  if (index !== -1) {
    recentSearches.value.splice(index, 1);
    // 최근검색어 하나 빼고 로컬스토리지 저장
    localStorage.setItem(
      "recentSearches",
      JSON.stringify(recentSearches.value)
    );
  }
};

// Function to search using a keyword from recent searches
const searchKeywordFromHistory = (keyword) => {
  // 검색어가 입력되면 최근 검색어를 숨깁니다.
  document.querySelector(".search-keywords").style.display = "none";

  if (keyword === "") {
    // 검색어가 비어있을 때는 최근 검색어를 보여줍니다.
    document.querySelector(".search-keywords").style.display = "flex";
  } else {
    // 검색어가 입력되면 최근 검색어를 숨깁니다.
    document.querySelector(".search-keywords").style.display = "none";
  }
  const param = { keyword, lat: lat.value, lng: lng.value };
  mapSearch(
    param,
    ({ data }) => {
      console.log(data);
      data.sort((a, b) => a.distance - b.distance);
      searchList.value = data;
      console.log(searchList);
    },
    ({ response }) => {
      console.log(response);
    }
  );
};

function hideRecentSearches() {
  // 최근 검색어를 숨기는 함수
  document.querySelector(".search-keywords").style.display = "none";
}

const categoryClick = (event) => {
  // 최근 검색어 숨기기
  hideRecentSearches();
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
  const param = { keyword: "", lat: lat.value, lng: lng.value };
  mapSearch(
    param,
    ({ data }) => {
      data.sort((a, b) => a.distance - b.distance);
      // 거리 가까운 순서로 정렬
      searchList.value = data;
      console.log(searchList);
    },
    ({ response }) => {
      console.log(response);
    }
  );
};

const goCategory = (text) => {
  const param = { keyword: "", lat: lat.value, lng: lng.value };
  mapSearch(
    param,
    ({ data }) => {
      console.log(data);
      data.sort((a, b) => a.distance - b.distance);
      const Data = data.filter((item) => item.popupCategory.includes(text));
      searchList.value = Data;
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
        lat.value = position.coords.latitude;
        lng.value = position.coords.longitude;
        // console.log(lat.value);
        // console.log(lng.value);
        resolve();
      }, reject);
    }
  });
};

const goPopupDetail = (popupId) => {
  router.push(`/popup/${popupId}`);
};

function goMapMain() {
  window.location.href = "/map";
}

function searchKeyword(event) {
  const keyword = event.target.value.trim(); // 입력된 검색어
  console.log(keyword);

  // 검색어가 입력되면 최근 검색어를 숨깁니다.
  document.querySelector(".search-keywords").style.display = "none";

  if (keyword === "") {
    // 검색어가 비어있을 때는 최근 검색어를 보여줍니다.
    document.querySelector(".search-keywords").style.display = "flex";
  } else {
    // 검색어가 입력되면 최근 검색어를 숨깁니다.
    document.querySelector(".search-keywords").style.display = "none";
  }

  if (keyword === "") {
    searchList.value = []; // 검색어가 비어있을 때는 검색 결과를 초기화
    return;
  }

  const param = { keyword, lat: lat.value, lng: lng.value };
  mapSearch(
    param,
    ({ data }) => {
      console.log(data);
      data.sort((a, b) => a.distance - b.distance);
      searchList.value = data;
      console.log(searchList);
    },
    ({ response }) => {
      console.log(response);
    }
  );

  const len = this.searchList.length;

  for (let i = 0; i < len; i++) {
    if (
      this.searchList[i].popupName.includes(event.target.value) === false &&
      this.searchList[i].popupLocation.includes(event.target.value) === false
    ) {
      document.querySelectorAll(".search-item")[i].style.display = "none";
    } else {
      document.querySelectorAll(".search-item")[i].style.display = "flex";
    }
  }
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
.search-input {
  font-size: 12px;
  width: 250px;
  background-color: transparent;
  margin-left: 3px;
  margin-top: -3px;
}
.flex {
  display: flex;
  height: 90px;
}

.flex1 {
  width: 10%;
  text-align: center;
  padding-top: 10px;
}

.flex2 {
  width: 75%;
  display: flex;
  flex-direction: column;
  align-self: center;
}

#flex2-name {
  font-weight: bold;
  font-size: large;

  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

#flex2-location {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: gray;
  font-size: smaller;
}

#flex2-like {
  font-size: small;
  color: dimgrey;
  font-weight: 600;
}

.flex3 {
  width: 20%;
  display: flex;
  flex-direction: column;
  align-self: center;
}

#flex3-category,
#flex3-distance {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: dimgray;
  font-size: small;
}

.search-list {
  margin: 0 auto;
  width: 360px;
}

.search-list li {
  list-style-type: none;
}

.search-list span {
  display: block;
}

.search-btn {
  margin-top: 8px;
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  font-size: 12px;
  width: 280px;
  height: 28px;
}
.search-btn-content {
  display: flex;
  align-items: center;
}
.category-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
}

.wrap {
  max-width: 400px;
  margin: 10px auto;
}

/* 가로 스크롤 적용 */
.scroll__wrap {
  overflow-x: auto;
  white-space: nowrap;
  font-size: 0;
  margin-top: 25px;
}
.scroll--element {
  display: inline-block;
  text-align: center;
}
.scroll--element + .scroll--element {
  margin-left: 15px;
}

/* 최근검색어 */
.search-keywords {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
}
.search-keywords i {
  margin-right: 10px; /* 원하는 간격 값으로 조절하세요. */
}
.search-keywords .bi-x-lg {
  margin-left: auto; /* 오른쪽으로 정렬 */
}

.keyword-item {
  border-bottom: 2px solid lightgray;
  display: flex;
  justify-content: space-between;
  padding: 5px;
}
</style>
