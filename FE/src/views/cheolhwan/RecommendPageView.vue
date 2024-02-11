<template>
  <i class="bi bi-arrow-left"></i>
  <div>
    <!-- 추천 유형 A -->
    <div>
      <div v-if="isHaveCookie == false" @click="goTest" >
        <div class="d-flex justify-content-between m-3">
          <div style="font-weight: bold;">팝BTI 추천</div>
        </div>
        <h3>검사 결과가 없어요~</h3>
        <button class="btn btn-warning" type="button">팝BTI 검사하러 가기</button>
      </div>
      <div v-else class="d-flex justify-content-between m-3">
        <div style="font-weight: bold;">OO님을 위한 팝BTI</div>
        <div>
          <button type="button" class="btnStyle">더보기</button>
        </div>
      </div>
      <div v-for="a in AList" :key="a" title="팝bti" class="popup-group">
        <div class="popup">
          <img :src="a.previewImage" class="popup-img" alt="">
          <h5 class="popup-title">{{ a.popupName }}</h5>
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
      
      <div title="AI추천" class="popup-group">
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
        <div v-for="b in BList" :key="b" class="popup-group-child">
          <div class="popup">
            <img :src="b.previewImage" class="popup-img" alt="">
            <h5 class="popup-title">{{ b.popupName }}</h5>
          </div>
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

      <div v-for="c in CList" :key="c" title="좋아요추천" class="popup-group">
        <div class="popup">
          <img :src="c.previewImage" class="popup-img" alt="">
          <h5 class="popup-title">{{ c.popupName }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import router from '@/router';


const fileInput = ref(null);
const imageUrl = ref(null);
const yoloClassName = ref(null);
const inputImagebutton = ref();
const imgPreview = ref(null);

const AList = ref()
const isHaveCookie = ref(false)

const BList = ref()
const CList = ref()


// ai추천 이미지 플라스크로 보내서 검사하기
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
    console.log(`파일은 있음${file}`)
    const formData = new FormData();
    formData.append('file', file);

    axios({
      method: 'post',
      url: 'http://localhost:5000/upload',
      data: formData
    })
    .then((response) => {
      console.log("요청성공")
      imageUrl.value = response.data.file_path.replace(/\\/g, '/')
      yoloClassName.value = response.data.message
      console.log(`클래스 네임: ${yoloClassName.value}`)
    })
    //이미지 분석 후 클래스 네임이 올바르게 들어온다면, api 요청을 통해 팝업스토어 매칭하기
    .then((response) => {
          const accessToken = localStorage.getItem("accessToken")
          axios({
            method: 'get',
            url: "/recommends/ai",
            headers: {
              Authorization: accessToken
            },
            params: {
              className: yoloClassName.value
            }
          })
          .then((response) => {
            console.log("ai 매칭완료!!")
            BList.value = response.data
          })
          .catch((error) => {
            console.log(error)
          })
    })
    .catch((error) => {
      console.log("요청실패")
    })
    // try {
    //     const response = await fetch('https://localhost:5000/upload', {
    //       method: 'POST',
    //       body: formData
    //     });
    //     console.log('요청보냄')
    //     const responseData  = await response.json();
    //     if (responseData.success) {
    //       imageUrl.value = responseData.file_path.replace(/\\/g, '/'); // 받은 경로를 imageUrl에 설정
    //       yoloClassName.value = responseData.message; 
    //       console.log(`클래스 네임: ${yoloClassName}`)

    //       // 이미지 분석 후 클래스 네임이 올바르게 들어온다면, api 요청을 통해 팝업스토어 매칭하기
    //       axios({
    //         method: 'get',
    //         url: "/recommends/ai",
    //         headers: {
    //           Authorization: "Bearer " + store.personalToken
    //         },
    //         params: {
    //           className: yoloClassName.value
    //         }
    //       })
    //       .then((response) => {
    //         console.log("ai 매칭완료!!")
    //         BList.value = response.data
    //       })
    //       .catch((error) => {
    //         console.log(error)
    //       })


    //     } else {
    //       console.error('Upload failed:', responseData.error);
    //     }
    //   } catch (error) {
    //     console.error('There was a problem with your fetch operation:', error);
    //   } 
  };
}

const fullImageUrl = computed(() => {
  if (imageUrl.value) {
    return 'http://localhost:5000/' + imageUrl.value;
  }
  return null;
});
///////////////////////////////////////////////////////////////////////////////////


// popbti 쿠키 확인하고 있으면 추천리스트 가져오기, 없으면 검사페이지로 라우팅
onMounted(() => {
  const getCookie = function(name) {
  const value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)')
  return value? value[2] : null
}
  if (getCookie("mbtiResult")) {    /// 만약 쿠키가 존재한다면 변수값 true로 바꿔주고 DB에 요청 보내기
    isHaveCookie.value = true
    const personalCookie = getCookie("mbtiResult")

    axios.get('/recommends/popbti', {params : {
    code: personalCookie
    }
  })
  .then((response) => {
    AList.value = response.data
  })
  .catch((error) => {
    console.log(error)
  })
  }

const accessToken = localStorage.getItem("accessToken")
axios.get('/recommends/good', { headers: {
  Authorization: accessToken
}
})
.then((response) => {
  console.log("좋아요 데이터 요청 완료!")
  CList.value = response.data
})
.catch((error) => {
  console.log("좋아요 데이터 요청 실패..")
})
})

// popbti 검사하러가기 버튼 누르면 검사페이지로 라우팅
const goTest = function() {
  router.push({ name: 'popbti' })
}




// 좋아요 알고리즘
// 유저가 좋아요한 팝업을 좋아요한 다른 사용자 정보 가져오기

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

/* ai추천 결과 리스트 정렬 */
.popup-group-child {
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