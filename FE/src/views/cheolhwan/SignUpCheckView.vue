<!-- router 회원가입 페이지로 갈 수 있게 만들어줘야함 -->

<template>
    <div class="m-3">
        <div class="row mb-5">
            <div class="col-3"></div>
            <div class="col-6">
                <img class="w-100 popcon-logo-size" src="../../assets/logo/POPCON_로고_배경제거.png" alt="">
            </div>
            <div class="col-3"></div>
        </div>
    
        <div class="text-center mb-5">
            <h4>독특한 경험과</h4>
            <h4>핫한 트렌드를 한눈에!</h4>
            <h4>POPCON과 함께하세요</h4>
        </div>
        <div class="d-flex justify-content-evenly m-3">
            <button type="button" class="btn custom-btn personal-member" @click="goSignupMember"><i class="bi bi-person-fill"></i><br>개인회원</button>
            <button type="button" class="btn custom-btn business-member" @click="goSignupCompany"><i class="bi bi-buildings-fill"></i><br>기업회원</button>
        </div>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter()

// 버튼 클릭하면 통합검색 링크 바로가기
// 카테고리 버튼 클릭할 경우 인자 값으로 클릭한 카테고리 정보값 넘겨주기
const goSignupMember = function() {
    router.push({ name: 'signUpCompany'})
}
//철환 라우터
const goSignupCompany = function() {
    router.push({ name: 'signUpMember'})
}

</script>

<style scoped>
    .membership-selection {
    display: flex;
    justify-content: space-between;
    width: 200px; /* 원형 아이콘 간격 조절을 위한 너비 조정 */
    }
    .icon {
    width: 150px; /* 아이콘 크기 */
    height: 150px;
    border-radius: 5%; /* 원형 모양 만들기 */
    cursor: pointer; /* 포인터 커서 추가 */
    padding: 10%;
    margin: 10%;
    }
    .personal-member {
    background-color: #ff534c; 
    color: white;
    }

    .business-member {
    background-color: #ffcc00;
    color: white;
    }
    .custom-btn {
        width: 100px;
        height: 100px;
        }

    .popcon-logo-size{
        width:180px;
        height: 180px;
    }

</style>