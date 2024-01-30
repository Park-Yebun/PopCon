<template>
  <div class="container">
    <div class="mb-3" style="font-size: 25px;">
      <div class="d-flex align-items-center justify-content-start">
        <i class="bi bi-x m-3"></i>
      </div>
      <div>
        <div class="centered-text" style="font-weight: bold;">신고하기</div>
      </div>
    </div>
    <div class="m-2">

      <div>
        <p style="font-size: 20px; font-weight: bold;" class="m-0">
          신고하는<br>이유를 알려주세요!
        </p>
        <p style="font-size: 9px; font-weight: bold;">타당한 근거 없이 신고된 내용은 관리자 확인 후 반영되지 않을 수 있습니다.</p>
      
      </div>

      <form>
      <div v-for="(item, index) in items" :key="index">
        <div class="form-group">
          <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" :id="'checkbox'+index">
            <label class="form-check-label" :for="'checkbox'+index">
              {{ item }}
            </label>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="additionalComments"></label>
        <textarea class="form-control" id="additionalComments" rows="3"></textarea>
      </div>
      <button type="submit" class="btn btnStyle">신고하기</button>
    </form>
    </div>
</div>
  </template>
  
  <script setup>
import { ref } from 'vue';

const items = ref([
        '팝업과 관련없는 내용',
        '음란성, 욕설 등 부적절한 내용',
        '부적절한 홍보 또는 광고',
        '팝업과 관련없는 사진 게시',
        '개인정보 유출 위험',
        '리뷰 작성 취지에 맞지 않는 내용',
        '저작권 도용 의심(사진 등)',
        '기타(하단 내용 작성)'
      ]);

  //     // 선택된 신고 유형
  //     selectedTypes: []
  //     };
  //   },
  //   methods: {
  //       reportReview() {
  //           if (this.selectedTypes.length === 0) {
  //               alert('신고 유형을 선택해주세요.');
  //               return;
  //           }
  //       // 여기서는 간단히 콘솔에 신고한 리뷰의 텍스트와 선택된 신고 유형을 출력합니다.
  //       console.log('선택된 신고 유형:', this.selectedTypes);
  //       // 여기에 추가적인 신고 처리 로직을 작성할 수 있습니다.
  //       }
  //   }
  // };
  </script>
  
  <style scoped>
  .btnStyle{
      width: 317px;
      height: 40px;
      flex-shrink: 0;
      background-color: #AFB1B4;
      color : white;
    }

    .centered-text {
      position: absolute;
      top: 5%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-size: 20px;
    }
  </style>