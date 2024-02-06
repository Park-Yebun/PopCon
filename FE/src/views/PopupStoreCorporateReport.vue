<script setup>
import {ref, onMounted, watch} from 'vue';
import {registerPopup} from '@/api/popup'
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/user";

const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);

const router = useRouter();


const PopupDto =ref({ // dto 속성명과 일치하게 만들기 
  "popupName": "",
  "popupBrand": "",
  "popupContent": "",
  "popupLocation": "",
  "popupStart": "",
  "popupEnd": "",
  "popupOperating": "",
  "popupEvent": "",
  "popupCar": "",
  "popupEntryFee": "",
  "popupWifi": "",
  "popupEat": "",
  "popupSite": "",
  "popupInstar": "",
  "popupNotice": "",
  "popupPhone": "",
  "popupHoney": "",
  "userId":userInfo.value.userId,
  "popupCategory":""
})

// 사진 업로드 
const selectedFiles = ref([]);
const getFileName = async (files) => {

  // console.log("추가");
  // console.log(selectedFiles.value.length)

  // const promises = []; // FileReader의 작업을 담을 배열
  selectedFiles.value = [];

  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    selectedFiles.value.push(file);
  }
};

// 카테고리 
const selectedOptions=ref([]);  // 카테고리 선택한 값들이 들어감 
const checkboxOptions= ref([
  { label: '패션뷰티', value: '패션뷰티' },
  { label: '식음료', value: '식음료' },
  { label: '콘텐츠', value: '콘텐츠' },
  { label: '취미여가', value: '취미여가'},
  { label: '금융', value:'금융'},
  { label: '연예', value:'연예'},
  { label: '가전/디지털', value:'가전/디지털'},
  { label: '리빙', value:'리빙'},
  { label: '게임', value:'게임'},
  { label: '캐릭터', value:'캐릭터'},
]);


// 버튼 클릭 관련 이벤트들 
// 1. 뒤로 가기 
const goBack=function() { // 뒤로가기 
  router.push({name:"other-corporate"});
}

// 2. 팝업 등록 
const isValid=ref(false);
const isValidText=ref("");

const popupRegister=function() {
  // console.log(PopupDto.value.popupCar);
  PopupDto.value.popupCategory=selectedOptions.value;

  // not null 값 확인 
  if (
    selectedFiles.value.length==0 ||
    PopupDto.value.popupName.trim() === "" ||
    PopupDto.value.popupBrand.trim() === "" ||
    PopupDto.value.popupContent.trim() === "" ||
    PopupDto.value.popupLocation.trim() === "" ||
    PopupDto.value.popupStart.trim() === "" ||
    PopupDto.value.popupEnd.trim() === "" ||
    PopupDto.value.popupOperating.trim() === "" ||
    PopupDto.value.popupCategory==0
  ) {
    isValid.value=true;
    isValidText.value="이미지, 제목, 카테고리, 브랜드명, 소개글, 위치, 날짜, 시간 입력은 필수입니다.";
  } else {
    // 모두 채워져 있는 경우 -> 등록 
    let formData = new FormData();

    for (let i = 0; i < selectedFiles.value.length; i++) {
      formData.append("images", selectedFiles.value[i]);
    }

    // 카테고리 등록 
    // PopupDto.value.popupCategory.push(selectedOptions.value);

    // 옵션들 Y/N 로 수정 
    if(PopupDto.value.popupCar){
      PopupDto.value.popupCar='Y';
    } else PopupDto.value.popupCar='N';

    if(PopupDto.value.popupEntryFee){
       PopupDto.value.popupEntryFee='Y';
    } else PopupDto.value.popupEntryFee='N';
    
    if(PopupDto.value.popupWifi) {
      PopupDto.value.popupWifi='Y';
    } else PopupDto.value.popupWifi='N';
    if(PopupDto.value.popupEat) {
      PopupDto.value.popupEat='Y';
    } else PopupDto.value.popupEat='N';

    let popupDto = new Blob([JSON.stringify(PopupDto.value)], { type: "application/json" });
    formData.append("popupDto", popupDto);

    console.log("요청 보내")

    registerPopup(
      formData, 
      ({data})=>{
        console.log(data);
        // 메인 페이지로 ? 
        router.push({name: "home"});
      },
      ({ response }) => {
        console.log(response);
      } 
    )
  }
}

</script>

<template>
  <div class="home-container">
    <br />
    <br />
    <div class="header-container">
      <!-- 뒤로가기 버튼 -->
      <button @click="goBack" class="back-button">&lt;</button>

      <!-- 오른쪽에 공지사항 텍스트 -->
      <h1 class="notice-title">팝업스토어 등록</h1>

      <!-- 가로줄 -->
      <hr class="divider" />
      <p class="contact-info">
        팝플리에 등록되어 있지 않은 팝업스토어나 오픈 예정인 스토어가 있다면 팝업스토어 제보로 알려주세요.</p>
      <p class="contact-info">확인 후 등록하도록 하겠습니다!</p>
      <hr class="divider" />

      <!-- Registration Form -->
      <form @submit.prevent="submitForm" class="registration-form">
        <label for="file" class="btn-upload"> 이미지 등록 </label>
        <input multiple type="file" name="file" id="file" @change="getFileName($event.target.files)" />
        <template v-if="selectedFiles.length>0">
          <span style="color:blue;">{{ selectedFiles.length }}장 업로드</span>
        </template>

        <label for="name">팝업 제목:</label>
        <input
          type="text"
          id="popup_name"
          v-model="PopupDto.popupName"
          placeholder="Ex : 슬램덩크 20주년 팝업스토어"
          required
        />
        <label>카테고리: </label>
        <template class="checkbox-label">
          <label v-for="(item, index) in checkboxOptions" :key="index">
          <input
            type="checkbox"
            :value="item.value"
            v-model="selectedOptions"
          />
          {{ item.label }}
        </label>
        </template>


        <label for="brand">브랜드 명:</label>
        <div class="input-with-icon">
          <input
            type="text"
            id="popup_brand"
            v-model="PopupDto.popupBrand"
            placeholder="Ex : (주) 팝콘"
            required
          />
        </div>

        <label for="content">팝업 내용(소개글):</label>
        <textarea
          id="popup_content"
          v-model="PopupDto.popupContent"
          placeholder="게시글에 대한 소개글을 작성해주세요."
          required
        ></textarea>

        <label for="location">팝업 위치:</label>
        <input
          type="text"
          id="popup_location"
          v-model="PopupDto.popupLocation"
          placeholder="Ex : 주소를 입력해주세요"
        />

        <label for="start">팝업 시작일:</label>
        <input type="date" id="popup_start" v-model="PopupDto.popupStart" required />

        <label for="end">팝업 종료일:</label>
        <input type="date" id="popup_end" v-model="PopupDto.popupEnd" required />

        <label for="operating">운영 시간:</label>
        <textarea
          id="popup_operating"
          v-model="PopupDto.popupOperating"
          placeholder="월~일 운영시간을 입력해주세요"
          required
        ></textarea>

        <label for="event">안내 및 주의사항:</label>
        <textarea
          id="popup_event"
          v-model="PopupDto.popupNotice"
          placeholder="안내 및 주의사항을 입력해주세요"
          required
        ></textarea>

        <!-- 추가: 주차 여부 -->
        <label for="car">주차 가능 여부:</label>
        <input type="checkbox" id="popup_car" v-model="PopupDto.popupCar" />
        <!-- <label for="car">주차 가능</label> -->

        <!-- 추가: 입장료 여부 -->
        <label for="entryfee">입장료 유무:</label>
        <input
          type="checkbox"
          id="popup_entryfee"
          v-model="PopupDto.popupEntryFee"
        />

        <!-- 추가: 와이파이 여부 -->
        <label for="wifi">와이파이 유무:</label>
        <input type="checkbox" id="popup_wifi" v-model="PopupDto.popupWifi" />

        <!-- 추가: 식음료 여부 -->
        <label for="eat">식음료 판매:</label>
        <input
          type="checkbox"
          id="popup_eat"
          v-model="PopupDto.popupEat"
        />

        <label for="site">브랜드 공식사이트:</label>
        <input
          type="text"
          id="popup_site"
          v-model="PopupDto.popupSite"
          placeholder="Ex : www.yourwebsite.com"
        />

        <label for="instar">브랜드 공식인스타그램:</label>
        <input
          type="text"
          id="popup_instar"
          v-model="PopupDto.popupInstar"
          placeholder="Ex : https://www.instagram.com"
        />

        <label for="notice">추가 공지사항:</label>
        <textarea
          id="popup_notice"
          v-model="PopupDto.popupNotice"
          placeholder="추가 공지사항을 입력해주세요"
          required
        ></textarea>

        <!-- Contact Information -->
        <label for="phone">담당자 연락처:</label>
        <input
          type="text"
          id="popup_phone"
          v-model="PopupDto.popupPhone"
          placeholder="연락처"
          required
        />

        <!-- <label for="honey">꿀팁:</label>
        <textarea
          id="popup_honey"
          v-model="formData.honey"
          placeholder="꿀팁을 입력해주세요"
          required
        ></textarea> -->
        <span v-show="isValid" style="color:red;">{{ isValidText }}</span>
        <button type="button" @click="popupRegister">제출하기</button>
      </form>
    </div>
    <br />
  </div>
</template>

<style scoped>
.checkbox-label {
  display: inline-block;
  margin-right: 20px; /* 각 체크박스 사이의 간격 조절 */
}

.home-container {
  max-width: 400px;
  margin: 0 auto;
  border: 2px solid gray; /* 테두리 스타일 설정 */
}

.header-container {
  display: flex;
  flex-direction: column; /* 세로 방향으로 배치합니다. */
  align-items: flex-start; /* 세로로 정렬합니다. */
  padding: 10px;
  background-color: transparent;
}

.back-button {
  background: transparent;
  border: none; /* 테두리 제거 */
  height: 30px; /* 높이 조절 */
  font-size: 30px; /* 폰트 크기 조절 */
  margin-right: 10px; /* 오른쪽 여백 추가 */
}

.notice-title {
  margin: 0;
  margin-top: -30px; /* 위에 간격을 줍니다. */
  margin-left: 40px;
  font-size: 22px;
  font-weight: normal;
}

.divider {
  width: 100%;
  border: 1px solid #ccc;
  margin-top: 10px; /* 위에 간격을 줍니다. */
}

.contact-info {
  margin: 0; /* 위아래 여백을 제거합니다. */
  font-size: 14px;
}

/* Registration Form Styles */
.form-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.registration-form {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 300px;
}

.registration-form label {
  margin-bottom: 5px;
  font-size: 12px; /* Adjust the font size as needed */
}
.registration-form input[type=date] {
  height: 30px;
  margin-bottom: 10px;
  border: 1px solid #ccc; /* Border color */
  border-radius: 10px; /* Rounded corners */
  padding: 5px; /* Padding for space inside the input field */
  background-color: transparent;
}

.registration-form input[type=text] {
  height: 40px;
  margin-bottom: 10px;
  border: 1px solid #ccc; /* Border color */
  border-radius: 10px; /* Rounded corners */
  padding: 5px; /* Padding for space inside the input field */
  background-color: transparent;
}

.registration-form input[type=checkbox] {
  height: 20px;
  margin-bottom: 10px;
  border: 1px solid #ccc; /* Border color */
  border-radius: 10px; /* Rounded corners */
  padding: 5px; /* Padding for space inside the input field */
  background-color: transparent;
}


.registration-form textarea {
  height: 100px;
  margin-bottom: 10px;
  border: 1px solid #ccc; /* Border color */
  border-radius: 10px; /* Rounded corners */
  padding: 5px; /* Padding for space inside the input field */
  background-color: transparent;
}

.registration-form button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px;
  cursor: pointer;
}

.registration-form button:hover {
  background-color: #45a049;
}

.btn-upload {
  width: 100px;
  height: 30px;
  background: #fff;
  border: 1px solid gray;
  border-radius: 10px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  &:hover {
    background: rgb(77,77,77);
    color: #fff;
  }
}

#file{
  display:none;
}
</style>
