<template>
    <i class="bi bi-arrow-left"></i>
    <div>
      <!-- 추천 유형 A -->
      <div>
        <div class="d-flex justify-content-between m-3">
          <div style="font-weight: bold;">팝BIT</div>
          <div>
            <button type="button" class="btnStyle">더보기</button>
          </div>
        
        </div>
  
        <div title="팝bit" class="popup-group">
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
  
      <!-- 추천 유형 B -->
      <div>
        <div class="d-flex justify-content-between m-3">
          <div style="font-weight: bold;">OO님을 위한 AI 추천</div>
          <div>
            <button type="button" class="btnStyle">더보기</button>
          </div>
        
        </div>
  
        <div title="팝bit" class="popup-group">
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

      <!-- 추천 유형 C -->
      <div>
        <div class="d-flex justify-content-between m-3">
          <div style="font-weight: bold;">OO님을 위한 맞춤 추천</div>
          <div>
            <button type="button" class="btnStyle">더보기</button>
          </div>
        
        </div>
  
        <div title="팝bit" class="popup-group">
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
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // 추천 유형 A의 이미지 데이터
        recommendationsA: [
          { name: '추천1', image: '/img/recommendation1.jpg' },
          { name: '추천2', image: '/img/recommendation2.jpg' },
          { name: '추천3', image: '/img/recommendation3.jpg' }
        ],
        // 추천 유형 B의 이미지 데이터
        recommendationsB: [
          { name: '추천4', image: '/img/recommendation4.jpg' },
          { name: '추천5', image: '/img/recommendation5.jpg' },
          { name: '추천6', image: '/img/recommendation6.jpg' }
        ],
        recommendationsC: [
          { name: '추천7', image: '/img/recommendation4.jpg' },
          { name: '추천8', image: '/img/recommendation5.jpg' },
          { name: '추천9', image: '/img/recommendation6.jpg' }
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  .recommendations {
    display: flex;
  }
  
  .recommendation {
    margin-right: 20px;
  }
  .btnStyle{
    width: 60px;
    height: 25px;
    font-size: 10px;
    border: #ff534C;
    border-radius: 20px; 
    text-align: center;
    background-color: #ff534C;
    color : white;
  }

  
  .popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 170px;
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
  width: 120px;
  height: 120px;
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 5px;
}
  </style>