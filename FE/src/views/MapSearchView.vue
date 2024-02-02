<template>
  <!-- 서치 창 -->
  <button @click="goMapSearch" type="button" class="btn btn-light search-btn">
    <div class="search-btn-content">
      <p style="font-size: 30px;"><</p> 
      <p>지역 혹은 이름을 검색해보세요.</p>
    </div>
  </button>
 
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
  <p>최근검색</p>
  <hr>
  <div class="search-keywords">
    <i class="bi bi-search"></i>
    <span>최근검색어</span>
    <i class="bi bi-x-lg"></i>
  </div>
  <hr>
  <div class="search-keywords">
    <i class="bi bi-geo-alt-fill"></i>
    <span>최근검색팝업</span>
    <i class="bi bi-x-lg"></i>
  </div>
  <hr>

</template>

<script setup>

</script>

<style scoped>
.search-btn {
  background-color: #fff;
  border-radius: 40px;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
  background: #fff;
  transition: all 0.3s ease;
  font-size: 12px;
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