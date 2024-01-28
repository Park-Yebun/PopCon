<template>
  <div>
    <div title="carousels">
      <img src="#" alt="캐루셀">
      <div>
        <a @click="goSearch">검색 버튼</a>
        <a href="#">알람 버튼</a>
      </div>
    </div>

    <div title="magam-imbak">
      <div>
        <strong>마감임박</strong>
        <a href="#">더보기</a>
      </div>
      <div title="popup-group">
        <img src="#" alt="마감임박 팝업 목록">
      </div>
    </div>

    <div title="category">
      <div>
        <a href="#">패션/뷰티</a>
        <a href="#">식음료</a>
        <a href="#">콘텐츠</a>
        <a href="#">취미/여가</a>
        <a href="#">금융</a>
      </div>
      <div>
        <a href="#">연예</a>
        <a href="#">가전/디지털</a>
        <a href="#">리빙</a>
        <a href="#">게임</a>
        <a href="#">캐릭터</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSearch = function(category) {
  router.push({ name: 'search', params: category})
}

</script>

<style>

</style>