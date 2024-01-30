<template>

<!-- 마감임박 팝업스토어 리스트 -->
<div class="deadline-container">
  <div class="deadline-text-group">
    <h5 style="opacity: 0.84;
        font-size: 16px;
        font-weight: 800;
        line-height: 30px;
        word-wrap: break-word">마감임박</h5>
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
  <div title="마감임박 팝업리스트" class="popup-group">
    <div class="popup">
      <img src="../../assets/images/poster_01.jpg" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="../../assets/images/poster_02.jpg" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="../../assets/images/poster_03.png" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="../../assets/images/poster_01.jpg" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
    <div class="popup">
      <img src="../../assets/images/poster_02.jpg" class="popup-img" alt="">
      <h5 class="popup-title">Card title</h5>
    </div>
  </div>
</div>

    <div class="m-3">
        <div class="row mb-3">
            <div class="col-3"></div>
            <div class="col-6">
            <img class="w-100 popcon-logo-size" src="../../assets/logo/POPCON_로고_배경제거.png" alt="">
            </div>
            <div class="col-3"></div>
        </div>
        <div class="d-flex justify-content-center">
            <h1>오류페이지입니다.</h1>
        </div>
        <div class="d-flex justify-content-center">
            <button type="button" class="btn BtnStyle2 d-flex mx-auto mb-3 justify-content-around">
                <p></p>
                <p>메인페이지로</p>
                <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
    </div>
    
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()

</script>

<style>
.popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 237px;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
}

/* 스크롤 안보이게 숨기기 */
.popup-group{
   -ms-overflow-style: none;
}
.popup-group::-webkit-scrollbar{
  display:none;
}

.popup-img {
  width: 146.56px;
  height: 170.89px;
}
</style>