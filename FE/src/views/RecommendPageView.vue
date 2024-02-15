<template>
  <i class="bi bi-arrow-left"></i>
  <div>
    <!-- 추천 유형 A -->
    <div>
      <div v-if="isHaveCookie == false" @click="goTest" >
        <div class="d-flex justify-content-between m-3">
          <div style="font-weight: bold;"> {{ loginuserId }}님을 위한 POPBTI 추천</div>
        </div>
        <p class="center" style="color: gray;">검사 결과가 없습니다</p>
        <div class="center">
          <button class="btn btn-warning center" type="button">팝BTI 검사하러 가기</button>
        </div>
      </div>
      <div v-else class="d-flex justify-content-between m-3">
        <div style="font-weight: bold;">{{ loginuserId }}님을 위한 팝BTI</div>
      </div>
      <div title="POPBTI 추천" class="popup-group">
        <div v-for="a in AList" :key="a">
          <div class="popup">
            <img :src="a.previewImage" class="popup-img" alt="">
            <h5 class="popup-title">{{ a.popupName }}</h5>
          </div>
        </div>
      </div>
    </div>
  
      <!-- 추천 유형 B -->
      <div>
      <div class="d-flex justify-content-between m-3">
        <div style="font-weight: bold;">{{ loginuserId }}님을 위한 AI 추천</div>
      </div>
      
      <div title="AI 추천" class="popup-group">
        <div class="popup">
          <!-- <form @submit.prevent="uploadImage" enctype="multipart/form-data"> -->
            <div>
              <input type="file" ref="fileInput" id="upload-image" hidden @change="getFileName($event.target.files)">
              <label for="upload-image" v-if="!inputImagebutton">
                <img src="@/assets/images/upload_image.png" class="popup-img"/>
              </label>
              <img v-if="imgPreview" :src="imgPreview" class="popup-img" id="preview">
              <img v-if="imageUrl" :src="imageAI" class="popup-img">
            </div>
            <button type="submit" class="btnStyle" @click="uploadImage">업로드</button>
            <!-- <input type="submit" value="업로드"> -->
          <!-- </form> -->
        </div>
        <div v-for="b in BList" :key="b">
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
        <div style="font-weight: bold;">{{ loginuserId }}님을 위한 맞춤 추천</div>
      </div>

      <div title="좋아요 추천" class="popup-group">
        <div v-for="c in CList" :key="c" title="좋아요추천">
          <div class="popup">
            <img :src="c.popupImagePath" class="popup-img" alt="">
            <h5 class="popup-title">{{ c.popupName }}</h5>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import router from '@/router';
import { jwtDecode } from "jwt-decode";

const loginuserId = ref(null)
const fileInput = ref(null);
const getImage = ref(null);
const imageUrl = ref(null);
const yoloClassName = ref(null);
const inputImagebutton = ref();
const imgPreview = ref(null);
const imageAI = ref(null);


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
  const reader = new FileReader();
  reader.onload = (e) => {
    imgPreview.value = e.target.result;
  };
  reader.readAsDataURL(file);
 
};


const uploadImage = async() => {
  const file = fileInput.value.files[0];
  imgPreview.value = null
  if (file) {
    console.log(`파일은 있음${file}`)
    const formData = new FormData();
    formData.append('file', file);


    try {
      const response = await axios.post('https://i10c211.p.ssafy.io:5005/upload', formData);

      getImage.value = response.data.file_path
      if (getImage.value) {
        imageAI.value = `data:image/jpeg;base64,${getImage.value}`;
        imageUrl.value = true
      }
      yoloClassName.value = response.data.message

      console.log(`클래스 네임: ${yoloClassName.value}`)
      // 이미지 분석 후 클래스 네임이 올바르게 들어온다면, api 요청을 통해 팝업스토어 매칭하기
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
      BList.value = response.data
    })
    .catch((error) => {
      console.log("요청실패")
    })

    } catch (error) {
      console.error('Upload error:', error);
      // 업로드 중 오류 발생 시 처리
    }
   
  };
}



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

// 좋아요 api 요청 
const accessToken = localStorage.getItem("accessToken")
console.log(accessToken)

axios.get('/recommends/good', { headers: {
  Authorization: accessToken
}
})
.then((response) => {
  console.log("좋아요 데이터 요청 완료!")
  CList.value = response.data
  console.log(CList.value, '이렇게들어와요')
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
  .popup {
  width: 130px;
  height: 195.78px;
  margin-right: 10px;
  border-radius: 10%;
  display:flex;
  flex-direction:column;
  padding: 0px 5px;

  }

  .center {
    display: flex;
    justify-content: center;
  }

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
  align-items: flex-start;
  -ms-overflow-style: none;
}



/* ai추천 결과 리스트 정렬 */

.popup-title {
  width: 120px;
  margin-top: 5px;
  font-size: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

}

.popup-group::-webkit-scrollbar{
  display:none;
}

.popup-img {
  width: 120px;
  height: 120px;
  border-radius: 20px;
}
  </style>