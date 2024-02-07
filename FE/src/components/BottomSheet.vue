<!-- 수정 전 기본 1트 -->
<!-- <template>
  <div class="wrapper" ref="sheet">
    <BottomSheetHeader />
  </div>
</template>

<script setup>
import BottomSheetHeader from './BottomSheetHeader.vue';
import { ref, onMounted, onBeforeUnmount } from 'vue';
const MIN_Y = 60; // 바텀시트가 최대로 높이 올라갔을 때의 y 값
const MAX_Y = window.innerHeight - 80; // 바텀시트가 최소로 내려갔을 때의 y 값
const BOTTOM_SHEET_HEIGHT = window.innerHeight - MIN_Y; // 바텀시트의 세로 길이

function useBottomSheet(){

  const sheet = ref(null);
  
  const metrics = ref({
    touchStart: {
      sheetY: 0,
      touchY: 0,
    },
    touchMove: {
      prevTouchY: 0,
      movingDirection: "none",
    },
  }),
  
  // Touch Event 핸들러들을 등록한다. 
  onMounted(() => {
    const handleTouchStart = (e: TouchEvent) => {
    const { touchStart, touchMove } = metrics.value;

    touchStart.sheetY = sheet.value.getBoundingClientRect().y;
    touchStart.touchY = e.touches[0].clientY;
}
    const handleTouchMove = (e: TouchEvent) => {
      e.preventDefault();
      
      const { touchStart, touchMove } = metrics.value;
      const currentTouch = e.touches[0];

      if (touchMove.prevTouchY === undefined) {
        touchMove.prevTouchY = touchStart.touchY;
      }

      if (touchMove.prevTouchY < currentTouch.clientY) {
        touchMove.movingDirection = 'down';
      }
                                                      
      if (touchMove.prevTouchY > currentTouch.clientY) {
        touchMove.movingDirection = 'up';
      }
      
      // 터치 시작점에서부터 현재 터치 포인트까지의 변화된 y값
      const touchOffset = currentTouch.clientY - touchStart.touchY;
      let nextSheetY = touchStart.sheetY + touchOffset;
      
      // nextSheetY 는 MIN_Y와 MAX_Y 사이의 값으로 clamp 되어야 한다
      if (nextSheetY <= MIN_Y) {
        nextSheetY = MIN_Y;
      }
                              
      if (nextSheetY >= MAX_Y) {
        nextSheetY = MAX_Y;
      }
        
      // sheet 위치 갱신. 
      sheet.value.style.setProperty('transform', `translateY(${nextSheetY - MAX_Y}px)`);
    }

    const handleTouchEnd = (e: TouchEvent) => {
      const { touchMove } = metrices.current;
      
      // Snap Animation
      const currentSheetY = sheet.current.getBoudingClientRect().y;

      if (currentSheetY !== MIN_TOP) {
        if (touchMove.movingDirection === 'down') {
          sheet.current.style.setProperty('transform', 'translateY(0)');
        }

        if (touchMove.movingDirection === 'up') {
          sheet.current.style.setProperty('transform', `translateY(${MIN_Y - MAX_Y}px)`);
        }
      }

      // metrics 초기화.
      metrics.current = {
        touchStart: {
          sheetY: 0,
          touchY: 0,
        },
        touchMove: {
          prevTouchY: 0,
          movingDirection: ["none", "up", "down"]
        },
      };
    }
    sheet.value.addEventListener('touchstart', handleTouchStart);
    sheet.value.addEventListener('touchmove', handleTouchMove);
    sheet.value.addEventListener('touchend', handleTouchEnd);
    
    onBeforeUnmount(() => {
      sheet.value.removeEventListener('touchstart', handleTouchStart);
      sheet.value.removeEventListener('touchmove', handleTouchMove);
      sheet.value.removeEventListener('touchend', handleTouchEnd);
    });
  });
  return {
    sheet
  }
} 
const sheet = useBottomSheet();
  
</script>

<style scoped>
.wrapper{
  display: flex;
  flex-direction: column;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  right: 0;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  background-color: #fff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.6);
  transition: transform 150ms ease-out;
}

</style> -->






<template>
  <div class="wrapper" ref="sheet">
    <BottomSheetHeader />
    <BottomSheetContent />

  </div>
</template>

<script setup>
import BottomSheetHeader from './BottomSheetHeader.vue';
import BottomSheetContent from './BottomSheetContent.vue';
import { ref, onMounted, onBeforeUnmount } from 'vue';

const MIN_Y = 60; // 바텀시트가 최대로 높이 올라갔을 때의 y 값
const MAX_Y = window.innerHeight - 100; // 바텀시트가 최소로 내려갔을 때의 y 값
const BOTTOM_SHEET_HEIGHT = window.innerHeight - MIN_Y; // 바텀시트의 세로 길이

function useBottomSheet() {
  const sheet = ref(null);
  const metrics = ref({
    touchStart: {
      sheetY: 0,
      touchY: 0,
    },
    touchMove: {
      prevTouchY: 0,
      movingDirection: "none",
    },
  });

  // Touch Event 핸들러들을 등록한다. 
  onMounted(() => {
    const handleTouchStart = (e) => {
      const { touchStart, touchMove } = metrics.value;
      touchStart.sheetY = sheet.value.getBoundingClientRect().y;
      touchStart.touchY = e.touches[0].clientY;
    }

    const handleTouchMove = (e) => {
      e.preventDefault();
      const { touchStart, touchMove } = metrics.value;
      const currentTouch = e.touches[0];

      if (touchMove.prevTouchY === undefined) {
        touchMove.prevTouchY = touchStart.touchY;
      }

      if (touchMove.prevTouchY < currentTouch.clientY) {
        touchMove.movingDirection = 'down';
      } else if (touchMove.prevTouchY > currentTouch.clientY) {
        touchMove.movingDirection = 'up';
      }
      
      // 터치 시작점에서부터 현재 터치 포인트까지의 변화된 y값
      const touchOffset = currentTouch.clientY - touchStart.touchY;
      let nextSheetY = touchStart.sheetY + touchOffset;
      
      // nextSheetY 는 MIN_Y와 MAX_Y 사이의 값으로 clamp 되어야 한다
      if (nextSheetY <= MIN_Y) {
        nextSheetY = MIN_Y;
      } else if (nextSheetY >= MAX_Y) {
        nextSheetY = MAX_Y;
      }
        
      // sheet 위치 갱신. 
      sheet.value.style.setProperty('transform', `translateY(${nextSheetY - MAX_Y}px)`);
    }

    const handleTouchEnd = (e) => {
      const { touchMove } = metrics.value;
      
      // Snap Animation
      const currentSheetY = sheet.value.getBoundingClientRect().y;

      if (currentSheetY !== MAX_Y) {
        if (touchMove.movingDirection === 'down') {
          sheet.value.style.setProperty('transform', 'translateY(0)');
        } else if (touchMove.movingDirection === 'up') {
          sheet.value.style.setProperty('transform', `translateY(${MIN_Y - MAX_Y}px)`);
        }
      }

      // metrics 초기화.
      metrics.value = {
        touchStart: {
          sheetY: 0,
          touchY: 0,
        },
        touchMove: {
          prevTouchY: 0,
          movingDirection: "none",
        },
      };
    }
    
    sheet.value.addEventListener('touchstart', handleTouchStart);
    sheet.value.addEventListener('touchmove', handleTouchMove);
    sheet.value.addEventListener('touchend', handleTouchEnd);
    
    onBeforeUnmount(() => {
      sheet.value.removeEventListener('touchstart', handleTouchStart);
      sheet.value.removeEventListener('touchmove', handleTouchMove);
      sheet.value.removeEventListener('touchend', handleTouchEnd);
    });
  });

  return { sheet };
} 

const { sheet } = useBottomSheet();
</script>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  position: fixed;
  z-index: 1;
  top: auto; /* 이전 코드: top: 0; */
  bottom: 0; /* 이전 코드: top: 0; */
  left: 0;
  right: 0;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  background-color: #fff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.6);
  height: auto; /* 이전 코드: height: ${BOTTOM_SHEET_HEIGHT}px; */
  max-height: calc(100% - ${MIN_Y}px); /* 바텀시트의 최대 높이는 화면의 높이에서 MIN_Y를 뺀 값 */
  transition: transform 150ms ease-out;
}
</style>