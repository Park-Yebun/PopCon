<template>
    <div>
      <!-- 프로필 사진 및 닉네임 -->
      <div>
        <img :src="profilePicture" alt="프로필 사진">
        <span>{{ nickname }}</span>
        <button @click="editNickname">수정하기</button>
      </div>
      
      <!-- 회원 정보 수정 -->
      <div>
        <h3>회원 정보 수정</h3>
        <!-- 필요한 입력 필드들을 추가하고 수정 버튼을 만듭니다. -->
        <button @click="editMemberInfo">수정하기</button>
      </div>
  
      <!-- 방문한 팝업 스토어 이미지 목록 -->
      <div>
        <h3>방문한 팝업 스토어 이미지</h3>
        <div v-if="visitedPopUpStores.length > 0">
          <div v-for="(image, index) in visitedPopUpStores" :key="index">
            <img :src="image" alt="방문한 팝업 스토어 이미지">
          </div>
        </div>
        <div v-else>
          <p>방문한 팝업 스토어가 없습니다.</p>
        </div>
      </div>
  
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
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>