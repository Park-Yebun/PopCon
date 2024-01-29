<template>
    <div>
        <div> X <h2>신고하기</h2> </div>
      
  
      <!-- 리뷰 목록 -->
      <!-- <div v-if="reviews.length > 0">
        <h3>리뷰 목록</h3>
        <ul>
          <li v-for="(review, index) in reviews" :key="index">
            {{ review.text }} 
            <button @click="reportReview(review)">신고</button>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>리뷰가 없습니다.</p>
      </div> -->
      <div>
      <h3>신고 유형 선택</h3>
      <div>
        <label v-for="(type, index) in reportTypes" :key="index">
          <input type="checkbox" v-model="selectedTypes" :value="type">{{ type }}
          <br>
        </label>
      </div>
      <!-- 이벤트를 생각해보자. 또는 신고하기 체크박스에 기타를 체크하면 이곳을 작성해야만 신고할 수 있는 기능이라도,. -->
      <div><input type="text" placeholder="이곳에 작성해주세요"></div>
      <button @click="reportReview()">신고하기</button>
      <div>

      </div>
    </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
      // 신고 유형 목록
      reportTypes: [
        '팝업과 관련없는 내용',
        '음란성, 욕설 등 부적절한 내용',
        '부적절한 홍보 또는 광고',
        '팝업과 관련없는 사진 게시',
        '개인정보 유출 위험',
        '리뷰 작성 취지에 맞지 않는 내용',
        '저작권 도용 의심(사진 등)',
        '기타(하단 내용 작성)'
      ],
      // 선택된 신고 유형
      selectedTypes: []
      };
    },
    methods: {
        reportReview() {
            if (this.selectedTypes.length === 0) {
                alert('신고 유형을 선택해주세요.');
                return;
            }
        // 여기서는 간단히 콘솔에 신고한 리뷰의 텍스트와 선택된 신고 유형을 출력합니다.
        console.log('선택된 신고 유형:', this.selectedTypes);
        // 여기에 추가적인 신고 처리 로직을 작성할 수 있습니다.
        }
    }
  };
  </script>
  
  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>