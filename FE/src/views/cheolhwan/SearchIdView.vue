<script setup>
import { ref } from 'vue';
import {searchIdPwd} from '@/api/user'

const searchUser = ref({
  "type":"userId",
	"value":""
});

const isValidEmail=ref('');
const isValid=ref(false);

const searchId = function(){
  searchIdPwd(
    searchUser.value,
    ({data})=>{
      isValid.value=true;
      isValidEmail.value=data;
    },
    ({response}) => {
      isValid.value=true;
      isValidEmail.value=response.data;
    }
  )
}

</script>

<template>

  <div class="container">
    <h1 class="text-center mt-5">아이디 찾기</h1>
    <form class="mt-5">
      <div class="mb-3">
        <label for="email" class="form-label">이메일 주소</label>
        <input type="email" class="form-control" id="email" placeholder="example@example.com" v-model="searchUser.value">
      </div>
      <p v-show="isValid" style="color:red;">{{ isValidEmail }}</p>
      <button type="button" class="btn btn-primary" @click="searchId">아이디 찾기</button>
    </form>
  </div>
  </template>


  <style scoped>
  /* 필요한 스타일링을 추가할 수 있습니다. */
  </style>