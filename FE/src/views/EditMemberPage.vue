<script setup>
import { ref, onMounted } from 'vue';
import { userModify } from "@/api/user";
import { useRouter } from 'vue-router';
import { storeToRefs } from 'pinia';
import { useMemberStore } from "@/stores/user";

const memberStore = useMemberStore();
const {userInfo} = storeToRefs(memberStore);

const router = useRouter();

const modifyUser = ref({
  "userId":userInfo.value.userId,
	"userPassword":"",
	"userNickname":userInfo.value.userNickname,
	"userPhone":userInfo.value.userPhone
});

const previewImage=ref('');

onMounted(() => {
  previewImage.value = userInfo.value.userImagePath;
})

// 패스워드 확인용 
const passwordInput=ref('')
const passwordCheckInput=ref('');

const isValidInput=ref('');
const isValidInputBoolean=ref(false);

// 수정 
const modify=function(){
  // 유효한 이메일, 아이디, 패스워드 일치 여부 확인 
  if(passwordInput.value=='' || passwordInput.value != passwordCheckInput.value || modifyUser.value.userNickname=='') {
    isValidInput.value="닉네임과 패스워드는 필수 입력값 입니다.";
    isValidInputBoolean.value=true;
  } else {
    modifyUser.value.userPassword=passwordInput.value;

    let formData = new FormData();

    formData.append("file",selectedFile.value);

    let userModifyDto = new Blob([JSON.stringify(modifyUser.value)], { type: "application/json" });
    formData.append("userModifyDto", userModifyDto);

    userModify(
      modifyUser.value.userId,
      formData,
      // modifyUser.value,
    ({data})=>{
      // console.log(data);
      router.push({ name: "home" });
    },
    ({ response }) => {
      // console.log(response);
    }
  )
  }
}

// 이미지 처리

const selectedFile = ref();
const image=ref("");

const getFileName = async (file) => {
  // 이미지 url 을 배열에 저장 , 사용자가 파일을 업로드할 때마다 실행

    selectedFile.value=file;

    const promise = new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = (e) => {
        image.value=e.target.result; // 이미지 URL을 배열에 추가하여 저장 
        previewImage.value=e.target.result;
        resolve(); // Promise 완료
      };
      reader.onerror = reader.onabort = reject; // 에러 발생 시 reject
      reader.readAsDataURL(file); // 파일을 읽어 이미지 URL로 변환
    });

  try {
    await Promise.all([promise]); // Promise가 완료될 때까지 기다림
    // console.log("파일 처리 완료");
  } catch (err) {
    // console.error("파일 처리 중 에러 발생:", err);
  }
};


</script>

<template>
  <div class="m-3">
    <div class="mb-3">
      <h5>정보수정</h5>
    </div>
    <div class="row">
      <div class="d-flex justify-content-center">
        <img
            :src="previewImage"
            style="width: 100px; height: 100px; border-radius: 50%; border-color: lightgray; border-style: solid;"
          />
      </div>
      <div class="d-flex justify-content-center" id="fileBox">
        <label for="file">
          <div class="btn-upload">이미지 등록</div>
        </label>
        <input type="file" name="file" id="file" @change="getFileName($event.target.files[0])" />
      </div>
      <div class="d-flex justify-content-center mb-3" style="font-weight: bold;">{{userInfo.userId}}</div>
    </div>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="NickName" v-model="modifyUser.userNickname">
      <label for="NickName">닉네임</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" v-model="passwordInput">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드</label>
    </div>
    <div class="form-floating mb-3">
      <input type="password" class="form-control" id="floatingPassword" v-model="passwordCheckInput">
      <label for="floatingPassword"><i class="bi bi-key"></i> 패스워드 확인</label>
    </div>
    <p v-show="isValidInputBoolean" style="color:red;">{{ isValidInput }}</p>
    <div class="form-floating mb-3">
      <input type="text" class="form-control" id="phoneNumber" v-model="modifyUser.userPhone">
      <label for="phoneNumber">휴대폰 번호</label>
    </div>

    <div class="row mb-3">
      <div class="col-2"></div>
      <div class="col-8">
        <div class="row">
          <button type="button" class="btn BtnStyle2 d-flex col-12 mx-auto mb-3 justify-content-around" @click="modify">
              <p></p>
              <p>수정하기</p>
              <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
      

  </template>
  
  
  <style scoped>
  .logo-size{
  width:20px;
  height: 20px;
}

.btnStyle{
    width: 88px;
    height: 56px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }
  .BtnStyle2{
    height: 40px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
  }

.btn-upload {
  margin-top:10%;
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

#file {
  display: none;
}
  </style>