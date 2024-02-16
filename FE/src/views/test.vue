<template>
<div class="up-sensor"></div>
  <div id="bottomSheet" class="bottom-sheet">
    <div class="bottom-sheet-handle_wrap">
      <div class="bottom-sheet-handle"></div>
    </div>
    <div style="margin-bottom: 40px;"></div>
    <div class="bottom-box">
      <!-- 바텀시트 내용 -->
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import axios from 'axios';

onMounted(() => {
    axios({})
    .then((res) => {
        const handleWrap = document.querySelector('.bottom-sheet-handle-wrap');
const bottomSheet = document.querySelector('.bottom-sheet');
const upSensor = document.querySelector('.up-sensor');
let bottomTouchStart = 0;
let bottomScrollStart = 0;

//up_sensor에서 터치가 움직였을 경우 (바텀시트를 건드렸을 경우) -> 바텀시트를 올림
upSensor.addEventListener("touchmove", (e) => {
  bottomSheet.style.height = 70 + "%" //바텀시트 height를 올리기 10% -> 70%
  upSensor.style.height = 70 + "%"; //up_sensor도 따라가기
  setTimeout(function () {
    upSensor.style.display = "none";
  }, 1000); // 바텀시트가 올라간 후, up_sensor 사라지기
});


//맨 위에서 아래로 스크롤했을 경우
bottomSheet.addEventListener("touchstart", (e) => {
  bottomTouchStart = e.touches[0].pageY; // 터치가 시작되는 위치 저장
  bottomScrollStart = bottomSheet.scrollTop //터치 시작 시 스크롤 위치 저장
});

bottomSheet.addEventListener("touchmove", (e) => {
  //유저가 아래로 내렸을 경우 + 스크롤 위치가 맨 위일 경우
  if (((bottomTouchStart - e.touches[0].pageY) < 0) && (bottomScrollStart <= 0)) {
    //바텀시트 내리기
    bottomSheet.style.height = 10 + "%"
    upSensor.style.display = "block"; //up_sensor 다시 나타나기
    upSensor.style.height = "10%"; //up_sensor height 다시 지정
  };
});


//맨 위 핸들을 아래로 당겼을 경우
handleWrap.addEventListener("touchstart", (e) => {
  bottomTouchStart = e.touches[0].pageY; // 터치가 시작되는 위치 저장
});
handleWrap.addEventListener("touchmove", (e) => {
  //사용자가 아래로 내렸을 경우
  if ((bottomTouchStart - e.touches[0].pageY) < 0) {
    //바텀시트 내리기
    bottomSheet.style.height = 10 + "%"
    upSensor.style.display = "block"; //up_sensor 다시 나타나기
    upSensor.style.height = "10%"; //up_sensor height 다시 지정
  };
});
    })
})
</script>

<style>
.bottom-sheet {
   display: flex;
   position: fixed;
   bottom: 0;
   width: 100%;
   height: 10%;
   border-top-left-radius: 30px;
   border-top-right-radius: 30px;
   background-color: #FFFFFF;
   overflow-y: auto;
   flex-direction: column;
   align-items: center;
   transition-duration: 1s;
}

.bottom-sheet-handle-wrap {
   z-index: 1;
   background: linear-gradient(to bottom,
           rgba(255, 255, 255, 1) 20%,
           rgba(255, 255, 255, 0.75) 35%,
           rgba(255, 255, 255, 0.5) 60%,
           rgba(255, 255, 255, 0.25) 85%,
           rgba(255, 255, 255, 0) 100%);
   display: flex;
   justify-content: center;
   align-items: flex-start;
   width: 80%;
   height: 40px;
   margin-bottom: 10px;
   position: fixed;
}

.bottom-sheet-handle {
   width: 20%;
   height: 10px;
   background-color: #7c7979;
   border-radius: 30px;
   margin-top: 10px;
}

.bottom-box {
   width: 80%;
   height: auto;
   margin-top: 10px;
   margin-bottom: 10px;
   padding: 5px;
   border: 1px solid lightgray;
   border-radius: 10px;
   transition-duration: 2s;
   padding-top: 20px;
   padding-bottom: 20px;
}

.up-sensor {
   z-index: 1;
   position: absolute;
   background: transparent;
   width: inherit;
   height: 100%;
   bottom: 0;
   height: 10%;
   transition-duration: 1s;
}
</style>