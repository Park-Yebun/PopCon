<template>
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
</template>

<script setup>

</script>

<style scoped>
.home-container {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid gray; /* 테두리 스타일 설정 */
}

.star-ratings {
  color: #aaa9a9; 
  position: relative;
  unicode-bidi: bidi-override;
  width: max-content;
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
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

</style>