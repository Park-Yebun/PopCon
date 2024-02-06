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
          <form @submit.prevent="uploadImage" enctype="multipart/form-data">
            <div>
              <input type="file" ref="fileInput" id="upload-image" hidden @change="getFileName($event.target.files)">
              <label for="upload-image" v-if="!inputImagebutton">
                <img src="../../assets/images/upload_image.png" class="popup-img"/>
              </label>
              <img v-if="imgPreview" :src="imgPreview" class="popup-img" id="preview">
              <img v-if="imageUrl" :src="fullImageUrl" class="popup-img">
            </div>
            <button type="submit" class="btnStyle">업로드</button>
            <!-- <input type="submit" value="업로드"> -->
          </form>
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
  
<script setup>
import { ref,computed } from 'vue';

const fileInput = ref(null);
const imageUrl = ref(null);
const yoloClassName = ref(null);
const inputImagebutton = ref();
const imgPreview = ref(null);
  
const getFileName = async (files) => {
  const fileName = files[0];
  inputImagebutton.value = true; // 이미지 레이블을 숨기기 위해 inputImagebutton을 true로 설정
  await base64(fileName);
};

const base64 = (file) => {
  return new Promise(resolve => {
    let reader = new FileReader();
    reader.onload = (e) => {
      resolve(e.target.result);
      imgPreview.value = e.target.result; // 이미지 URL 설정
    };
    reader.readAsDataURL(file);
  });
};


const uploadImage = async() => {
  const file = fileInput.value.files[0];
  imgPreview.value = null
  if (file) {
    const formData = new FormData();
    formData.append('file', file);
    try {
        const response = await fetch('http://localhost:5000/upload', {
          method: 'POST',
          body: formData
        });

        const responseData  = await response.json();
        if (responseData.success) {
          imageUrl.value = responseData.file_path.replace(/\\/g, '/'); // 받은 경로를 imageUrl에 설정
          yoloClassName.value = responseData.message; 
          console.log(yoloClassName)
        } else {
          console.error('Upload failed:', responseData.error);
        }
      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      } 
  };
}

const fullImageUrl = computed(() => {
  if (imageUrl.value) {
    return 'http://localhost:5000/' + imageUrl.value;
  }
  return null;
});

const recommendationsA = [
  { name: '추천1', image: '/img/recommendation1.jpg' },
  { name: '추천2', image: '/img/recommendation2.jpg' },
  { name: '추천3', image: '/img/recommendation3.jpg' }
];

const recommendationsB = [
  { name: '추천4', image: '/img/recommendation4.jpg' },
  { name: '추천5', image: '/img/recommendation5.jpg' },
  { name: '추천6', image: '/img/recommendation6.jpg' }
];

const recommendationsC = [
  { name: '추천7', image: '/img/recommendation4.jpg' },
  { name: '추천8', image: '/img/recommendation5.jpg' },
  { name: '추천9', image: '/img/recommendation6.jpg' }
];
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