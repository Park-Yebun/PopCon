<script setup>
import { ref } from 'vue';
import {searchIdPwd} from '@/api/user'

const searchUser = ref({
  "type":"userPassword",
	"value":""
});

const isValidId=ref('');
const isValid=ref(false);

const searchPassword = function(){
  searchIdPwd(
    searchUser.value,
    ({data})=>{
      isValid.value=true;
      isValidId.value=data;
    },
    ({response}) => {
      isValid.value=true;
      isValidId.value=response.data;
    }
  )
}

</script>

<template>

<div class="container">
    <h1 class="text-center mt-5">비밀번호 찾기</h1>
    <form class="mt-5">
      <div class="mb-3">
        <label for="id" class="form-label">아이디입력</label>
        <input type="text" class="form-control" id="id" v-model="searchUser.value">
      </div>
      <p v-show="isValid" style="color:red">{{ isValidId }}</p>
      <button type="button" class="btn btn-primary" @click="searchPassword">비밀번호 찾기</button>
    </form>
  </div>
</template>
  

  
  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>