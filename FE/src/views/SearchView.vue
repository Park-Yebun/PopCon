<template>
    <VDatePicker borderless v-model.range="range" mode="dateTime" :initial-page="{ month: 2, year: 2024 }" />
    <div>
        <div title="날짜 옵션">
          <div class="title">날짜
            <ul class="button-group">
                <li class="date select">오늘</li>
                <li class="date">+7일</li>
                <li class="date">+2주</li>
                <li class="date">직접 선택</li>
            </ul>
          </div>
        </div>
        <div title="지역 옵션">
            <div class="title">지역
                <ul class="button-group">
                    <li class="region select">전체</li>
                    <li class="region">서울특별시</li>
                    <li class="region">광주광역시</li>
                    <li class="region">경기도</li>
                    <li class="region">대구광역시</li>
                </ul>
            </div>
        </div>
        <div title="운영상태 옵션">
            <div class="title">운영상태
                <ul class="button-group">
                    <li class="status select">진행중</li>
                    <li class="status">오픈 예정</li>
                    <li class="status">종료</li>
                </ul>
            </div>
        </div>
        <div title="카테고리 옵션">
            <div class="search-category-title">카테고리</div>
                <ul class="button-group" style="margin-left: 40px;">
                    <div v-for="(value, key) in categoryGroup" class="search-category-option">
                        <!-- 카테고리 변수 안에 값이 들어있고 key값과 같으면 select이미지로 대체 -->
                        <img v-if="category !== '' && category == value " class="search-category-img" :src="'/src/assets/images/option_' + getKeyByValue(categoryGroup, value) + '_a.png'" :alt="value + ' 아이콘'">
                        <img v-else @click="selectCategory(value)" class="search-category-img" :src="'/src/assets/images/option_' + key + '.png'" :alt="value + ' 아이콘'">
                        <div class="search-category-text">{{ value }}</div>
                    </div>
                </ul>
        </div>

        <div class="button-container">
            <button @click="goSearch" type="button" class="btn btn-light">검색</button>
        </div>

        <div title="팝업스토어 정렬">
            <div class="dropdown-li">
                <select name="selectBox" id="selectBox" @change="changeSelect()">
                    <option value="">최신순</option>
                    <option value="">리뷰 많은순</option>
                    <option value="">좋아요 순</option>
                    <option value="">조회수</option>
                    <option value="">마감순</option>
                </select>
            </div>
            <div v-for="popup in popupList" :key="popup" class="search-popup-group">
                <div @click="goDetail(popup.popupId)" class="search-popup">
                    <img class="search-popup-img" :src= popup.previewImagePath alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">{{ popup.popupName }}</div>
                        <div class="search-popup-info-content">{{ popup.popupStart }} ~ {{ popup.popupEnd }}</div>
                        <div class="search-popup-info-content">{{ popup.popupLocation }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted, watch, computed } from 'vue';
import { getSearchedPopup } from '@/api/popup';
import { useCounterStore } from '@/stores/counter';
import axios from 'axios';
import { walkIdentifiers } from 'vue/compiler-sfc';

import 'v-calendar/style.css';
// import Calendar from 'v-calendar/lib/components/calendar.umd'
// import DatePicker from 'v-calendar/lib/components/date-picker.umd'

const route = useRoute()
const router = useRouter()
const store = useCounterStore()

const range = ref({
  start: new Date(2024, 2, 6),
  end: new Date(2024, 3, 10),
});


// 쿼리 매개변수로 리스트 가져오기 //
//매개변수
const startDate = ref(null)
const endDate = ref(null)
const area = ref("전체")
const status = ref(null)

// 메인에서 클릭한 카테고리 옵션 값 변수에 저장하기
const category = ref(route.params.category)

// 카테고리 버튼을 선택하면 라우트 매개변수와 함께 카테고리 값이 저장된 변수를 변경시키기
const selectCategory = function(v) {
    this.router.replace({ params: { category: v }})
    category.value = v
}

// 카테고리 버튼 //
const categoryGroup = ref({
    'beauty': '패션뷰티',
    'foods': '식음료', 
    'content': '콘텐츠', 
    'hobby': '취미여가',
    'finance': '금융',
    'entertain': '연예',
    'digital': '가전/디지털',
    'living': '리빙',
    'game': '게임',
    'character': '캐릭터'})


const getKeyByValue = function(obj, value) {
      return Object.keys(obj).find(key => obj[key] === value)
    }

// 팝업 리스트 담아둘 변수
const popupList = ref()
watch(popupList, () => {
    console.log('popupList가 변경되었습니다.')
})

// 현재 파일이 생성되면 api요청을 통해 팝업리스트 데이터를 가져온 후, 옵션을 클릭할때 실시간으로 활성/비활성 시키기
onMounted(() => {
    axios.get('/popups/search', {params : {
    startDate: null,
    endDate: null,
    area: null,
    status: null,
    category: null
}})
    .then((response) => {
        popupList.value = response.data
    })
    .then((response) => {
        // 클릭한 옵션값 감시
        const selectedRegion = document.querySelectorAll('.region')
        const selectedDate = document.querySelectorAll('.date')
        const selectedStatus = document.querySelectorAll('.status')

    for (let i = 0; i < selectedRegion.length; i++) {
        selectedRegion[i].addEventListener('click', function(event) {
        // 이전에 선택된 요소의 스타일 초기화
        const previouslySelected = document.querySelector('.region.select')
        if (previouslySelected) {
            previouslySelected.classList.remove('select')
        }
        // 현재 클릭한 요소를 선택 상태로 변경
        this.classList.add('select')
        area.value = event.target.innerText
        })
    }

    for (let i = 0; i < selectedStatus.length; i++) {
        selectedStatus[i].addEventListener('click', function(event) {
        // 이전에 선택된 요소의 스타일 초기화
        const previouslySelected = document.querySelector('.status.select')
        if (previouslySelected) {
            previouslySelected.classList.remove('select')
        }
        // 현재 클릭한 요소를 선택 상태로 변경
        this.classList.add('select')
        status.value = event.target.innerText
        })
    }

    for (let i = 0; i < selectedDate.length; i++) {
        selectedDate[i].addEventListener('click', function(event) {
        // 이전에 선택된 요소의 스타일 초기화
        const previouslySelected = document.querySelector('.date.select')
        if (previouslySelected) {
            previouslySelected.classList.remove('select')
        }
        // 현재 클릭한 요소를 선택 상태로 변경
        this.classList.add('select')
        startDate.value = event.target.innerText
        })
    }

    })
    .catch((error) => {
        console.log(error);
    })
})

// 팝업리스트 정렬 변경!!
const changeSelect = function() {
    console.log("정렬함수 실행됨")
    const selectedOption = document.querySelector('#selectBox > option:checked')
    if (selectedOption.innerText == "최신순") {
        popupList.value.sort((a, b) => a.popupId - b.popupId)
    }
    else if (selectedOption.innerText == "리뷰 많은순") {
        popupList.value.sort((a, b) => b.reviewCnt - a.reviewCnt)
    }
    else if (selectedOption.innerText == "좋아요 순") {
        popupList.value.sort((a, b) => b.popupLike - a.popupLike)
    }
    else if (selectedOption.innerText == "조회수") {
        popupList.value.sort((a, b) => b.popupView - a.popupView)
    }
    else if (selectedOption.innerText == "마감순") {
        popupList.value.sort((a, b) => a.popupEnd - b.popupEnd)
    }
}

// 검색버튼 누르면 결과 새로고침
const goSearch = function() {
    axios.get('/popups/search', {params : {
    startDate: startDate.value,
    endDate: endDate.value,
    area: area.value,
    status: status.value,
    category: category.value
}})
    .then((response) => {
        popupList.value = response.data
        console.log("검색버튼 요청완료!!")
    })
    .catch((error) => {
        console.log(error)
    })
}

// 팝업스토어 상세페이지로 이동
const goDetail = (popupId) => {
  router.push({ name: 'popup-detail', params: { popupId }})
}

</script>

<style scoped>
.title {
    margin-top: 25px;
    margin-left: 40px;

    font-size: 16px;
    font-family: Inter;
    font-weight: 400;
    line-height: 34px;
    word-wrap: break-word"
}

.title:first-child {
    margin-top: 52px;
}

.search-category-title {
    margin-top: 25px;
    margin-left: 40px;

    font-size: 16px;
    font-family: Inter;
    font-weight: 400;
    line-height: 34px;
    word-wrap: break-word"
}


.button-group {
    list-style: none;
    margin-right: 28px;

    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    word-break: keep-all;

    /* 가로 스크롤 설정 */
    overflow-x: scroll;
    white-space: nowrap;
}

.select {
    background: var(--4, #616266);
    color: #FFF;
}

div>ul>li {
    width: 100%;
    white-space: nowrap;
    border: 1px solid #E6E6E6;
    border-radius: 18px;
    padding: 8px 22px;
    padding-inline: 1.5rem;
    background: none;
    font-size: 14px;
    color: #807A7A;
}

li + li {
    margin-left: 12px;
}

/* 스크롤 안보이게 숨기기 */
.button-group {
   -ms-overflow-style: none;
}
.button-group::-webkit-scrollbar {
  display: none;
}
/*  */

.search-category-option {
    text-align: center;
}

.search-category-option + .search-category-option {
    margin-left: 12px;
}

.search-category-img{
    width: 63.20px;
    height: 63.29px;
}

.search-category-text {
    margin-top: 10.74px;
    
    font-family: "Airbnb Cereal App";
    font-size: 14px;
    font-style: normal;
    font-weight: 400;
    line-height: 23px;
}


/* 정렬 결과 */
.dropdown-li {
    margin-top: 48.97px;
    margin-left: 36px;
}

.search-popup-group {
    margin-top: 26px;
    display: flex;
    flex-direction: column;
}

.search-popup {
    width: 340px;
    height: 88px;
    flex-shrink: 0;
    box-shadow: 0px 1px 4px rgba(34, 34, 34, 0.06);
    border-radius: 10px;
    align-content: center;

    position: relative;
    margin-left: 12px;
}

.search-popup + .search-popup {
    margin-top: 10px;
}

.search-popup-img {
    width: 64px;
    height: 64px;
    border-radius: 8px;

    position: absolute;
    top: 11px;
    left: 12px;
}

.search-popup-info {
    position: absolute;
    left: 91px;
    width: 230px;

    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap; 

}

.search-popup-info-title{
    font-family: Inter;
    font-size: ;
    font-style: normal;
    font-weight: 600;
    line-height: normal;

    margin-top: 13px;
}

.search-popup-info-content{
    color: #6B7280;
    font-family: Inter;
    font-size: 10px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;

    margin-top: 6px;
}

/* 검색 버튼 오른쪽으로 정렬하기 */
.button-container {
    display: flex;
    justify-content: end;
    margin-right: 28px;
}


</style>