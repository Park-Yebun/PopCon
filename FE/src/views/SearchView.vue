<template>
    <div>
        <div title="날짜 옵션">
          <div class="date title">날짜
            <ul class="button-group">
                <li class="">오늘</li>
                <li id="date" class="">+7일</li>
                <li id="date" class="">+2주</li>
                <li id="date" class="">직접 선택</li>
            </ul>
          </div>
        </div>
        <div title="지역 옵션">
            <div class="region title">지역
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
            <div class="status title">운영상태
                <ul class="button-group">
                    <li class="">진행중</li>
                    <li class="">오픈 예정</li>
                    <li class="">종료</li>
                </ul>
            </div>
        </div>
        <div title="카테고리 옵션">
            <div class="search-category-title">카테고리</div>
                <ul class="button-group">
                    <div v-for="(key, value) in categoryGroup" class="search-category-option">
                        <img v-if="category !== ''" @click="selectCategory(key)" class="search-category-img" :src="'/src/assets/images/option_' + getKeyByValue(categoryGroup, key) + '_a.png'" :alt="value + ' 아이콘'">
                        <img v-if="category == key" @click="selectCategory(key)" class="search-category-img" :src="'/src/assets/images/option_' + getKeyByValue(categoryGroup, key) + '_a.png'" :alt="value + ' 아이콘'">
                        <img v-else class="search-category-img" :src="'/src/assets/images/option_' + value + '.png'" :alt="key + ' 아이콘'">
                        <div class="search-category-text">{{ key }}</div>
                    </div>
                </ul>
        </div>
        <div title="팝업스토어 정렬">
            <div class="dropdown-li">
                <select name="" id="">
                    <option value="">최신순</option>
                    <option value="">리뷰 많은순</option>
                    <option value="">좋아요 순</option>
                    <option value="">조회수</option>
                    <option value="">마감순</option>
                </select>
            </div>
            <div class="search-popup-group">
                <div v-for="popup in popupList" :key="popup" class="search-popup">
                    <img class="search-popup-img" :src= popup.previewImagePath alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">{{ popup.popupName}}</div>
                        <div class="search-popup-info-content">{{ popup.startDate }} ~ {{ popup.endDate }}</div>
                        <div class="search-popup-info-content">{{ popup.popupLocation }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { ref, onMounted } from 'vue';
import { getSearchedPopup } from '@/api/popup';
import { useCounterStore } from '@/stores/counter';
import axios from 'axios';
// import { onMounted } from 'vue';
const route = useRoute()
const store = useCounterStore()


// 쿼리 매개변수로 리스트 가져오기 //

//매개변수
const startDate = ref(null)
const endDate = ref(null)
const area = ref(null)
const status = ref(null)

// 메인에서 클릭한 카테고리 옵션 값 변수에 저장하기
const category = route.params.category

// 팝업 리스트 담아둘 변수
const popupList = ref({})


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

// 클릭한 옵션값 감시
const selectedRegion = document.getElementsByClassName('region')
console.log(selectedRegion)

for (let i = 0; i < selectedRegion.length; i++) {
    selectedRegion[i].addEventListener('click', function(event) {
        // 이전에 선택된 요소의 스타일 초기화
        const previouslySelected = document.querySelector('.select');
        if (previouslySelected) {
            previouslySelected.classList.remove('select');
        }
        // 현재 클릭한 요소를 선택 상태로 변경
        console.log(this)
        this.classList.add('select');
        })
    }

const selectCategory = function(key) {
    if (category === key) {
        category == ''
    }
    category = key
}

const getKeyByValue = function(obj, value) {
      return Object.keys(obj).find(key => obj[key] === value)
    }


onMounted(async () => {
    await axios.get('/popups/search',{params : {
        startDate: startDate.value,
        endDate: endDate.value,
        area: area.value,
        status: status.value,
        category
    }})
        .then((response) => {
          popupList.value = response.data
        })
        .catch((error) => {
          console.log(error);
        })
    
    

})
</script>

<style scoped>
.date-title {
    margin-top: 52px;
    margin-left: 40px;

    font-size: 16px;
    font-family: Inter;
    font-weight: 400;
    line-height: 34px;
    word-wrap: break-word"
}

.region-title {
    margin-top: 25px;
    margin-left: 40px;

    font-size: 16px;
    font-family: Inter;
    font-weight: 400;
    line-height: 34px;
    word-wrap: break-word"
}

.status-title {
    margin-top: 25px;
    margin-left: 40px;

    font-size: 16px;
    font-family: Inter;
    font-weight: 400;
    line-height: 34px;
    word-wrap: break-word"
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
    margin-top: 5px;
    margin-left: 28px;
    margin-right: 28px;

    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    border-bottom: 1px solid #212121;
    word-break: keep-all;

    /* 가로 스크롤 설정 */
    overflow-x: scroll;
    white-space: nowrap;
}

.title>ul>li {
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

.select {
    background: var(--4, #616266);
    color: #FFF;
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

}

.search-popup-info-title{
    font-family: Inter;
    font-size: 16px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;

    margin-top: 13px;
    margin-left: 16px;
}

.search-popup-info-content{
    color: #6B7280;
    font-family: Inter;
    font-size: 10px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;

    margin-top: 6px;
    margin-left: 19px;
}
</style>