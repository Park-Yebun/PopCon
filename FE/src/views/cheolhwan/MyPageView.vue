<template>

  <div>
    <i class="bi bi-arrow-left"></i>
  </div>
  <div class="m-3">
    <div class="mb-3">
      <span style="font-size: 20px;">마이페이지</span>
    </div>
    <div class="d-flex justify-content-center">
      <img
          src="@/assets/images/profile.png"
          alt="이미지 설명"
          style="width: 100px; height: 100px;"
        />
    </div>
    <div class="d-flex justify-content-center mb-3" style="font-weight: bold;">POPUP STORE MAN <i class="bi bi-pencil-square" style="color:#ff534c;"></i></div>
    <div class="d-flex justify-content-center">
      <button class="editBtn"><i class="bi bi-pencil-square"></i>회원정보수정</button>
    </div>
  </div>

  <div>
    <div class="d-flex justify-content-between m-3">
      <div style="font-weight: bold;">방문한 팝업스토어</div>
      <div>
        <button type="button" class="btnStyle">더보기</button>
      </div>
    
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
    <!-- <h3>방문한 팝업 스토어 이미지</h3>
    <div v-if="visitedPopUpStores.length > 0">
      <div v-for="(image, index) in visitedPopUpStores" :key="index">
        <img :src="image" alt="방문한 팝업 스토어 이미지">
      </div>
    </div>
    <div v-else>
      <p>방문한 팝업 스토어가 없습니다.</p>
    </div>
  </div> -->
  </div>

      
      <!-- 방문한 팝업 스토어 이미지 목록 -->

      <div>
      <!-- 작성한 리뷰 목록 -->
      <div>
        <h3>작성한 리뷰</h3>
        <div v-if="reviews.length > 0">
          <div v-for="(review, index) in reviews" :key="index">
            <p>{{ review }}</p>
          </div>
        </div>
        <div v-else>
          <p>작성한 리뷰가 없습니다.</p>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        profilePicture: '/path/to/default/profile_picture.jpg',
        nickname: '사용자 닉네임',
        visitedPopUpStores: ['/path/to/image1.jpg', '/path/to/image2.jpg', '/path/to/image3.jpg'],
        reviews: ['리뷰 내용 1', '리뷰 내용 2', '리뷰 내용 3'] // 사용자가 작성한 리뷰 목록
      };
    },
    methods: {
      editNickname() {
        // 닉네임 수정 기능을 구현하는 로직을 추가합니다.
        console.log('닉네임 수정하기');
      },
      editMemberInfo() {
        // 회원 정보 수정 기능을 구현하는 로직을 추가합니다.
        console.log('회원 정보 수정하기');
      }
    }
  };
  </script>
  
  <style scoped>
  .editBtn{
    background-color: white;
    border: 2px solid #ff534c;
    border-radius: 10px;
    color:#ff534c;
    padding: 5px 20px;
    font-size: 15px;
    font-weight: bold;
  }
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
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 5px;
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
  </style>