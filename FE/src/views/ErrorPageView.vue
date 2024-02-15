<template>
    <div class="m-3">
        <div class="row mb-3">
            <div class="col-3"></div>
            <div class="col-6">
            <img class="w-100 popcon-logo-size" src="@/assets/logo/POPCON_로고_배경제거.png" alt="">
            </div>
            <div class="col-3"></div>
        </div>
        <div class="d-flex justify-content-center">
            <h1>오류페이지입니다.</h1>
        </div>
        <div class="d-flex justify-content-center">
            <button type="button" class="btn BtnStyle2 d-flex justify-content-around" @click="goMainPage">
                <p></p>
                <p>메인페이지로</p>
                <div><i class="bi bi-arrow-right-circle-fill" style="color: #FFCC00"></i></div>
            </button>
        </div>
    </div>
    
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()

const goMainPage = function() {
    router.push({ name: 'home'})
}
</script>

<style scoped>
.popcon-logo-size{
    width:180px;
    height: 180px;
}
.BtnStyle2{
    height: 40px;
    flex-shrink: 0;
    background-color: #ff534C;
    color : white;
}
</style>