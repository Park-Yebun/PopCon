<template>
 <!-- 서치 창 -->
 <div type="button" class="btn btn-light search-btn">
    <div class="search-btn-content">
      <i @click="goMapMain" class="bi bi-chevron-left"></i>
      <input class="search-input" v-model="searchTerm" @input="searchKeyword($event)" @keyup.enter="handleSearch" placeholder="지역 혹은 이름을 검색해보세요." />
    </div>
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

  <div>
    <ul class="search-list">
      <li v-for="search in searchList" :key="search" class="search-item">
        <!-- 지도 아이콘 -->
        <span>{{ search.distance }}</span>
        <span>{{ search.title }}</span>
        <span>{{ search.location }}</span>
      </li>
    </ul>
  </div>

</template>

<script setup>
import { ref } from 'vue'

const searchList = ref('dididididi')

function goMapMain() {
  window.location.href = '/map';
}

function searchKeyword(event) {
  console.log(event.target.value);

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
.search-input{
  font-size: 12px;
  width: 250px;
  background-color: transparent;
}
.search-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  font-size: 12px;
  width: 250px;
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

.wrap {max-width:400px; margin:10px auto; }

/* 가로 스크롤 적용 */
.scroll__wrap {overflow-x:auto; white-space:nowrap; font-size:0}
.scroll--element {display:inline-block; text-align:center;}
.scroll--element + .scroll--element {margin-left:15px;}

/* 최근검색어 */
.search-keywords {
    display: flex;
    align-items: center;
  }
.search-keywords i {
  margin-right: 10px; /* 원하는 간격 값으로 조절하세요. */
}
.search-keywords .bi-x-lg {
    margin-left: auto; /* 오른쪽으로 정렬 */
  }
</style>