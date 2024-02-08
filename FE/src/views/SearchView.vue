<template>
    <div>
        <div title="날짜 옵션">
          <div class="date-title">날짜</div>
            <div class="button-group">
                <button type="button" class="btn btn-outline-secondary"
                        data-bs-toggle="button"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">오늘</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">+7일</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">+2주</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">직접 선택</button>
            </div>
        </div>
        <div title="지역 옵션">
            <div class="region-title">지역</div>
            <div class="button-group">
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">전체</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">서울특별시</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">경기도</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">광주광역시</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">인천광역시</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">강원도</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">부산광역시</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">대전광역시</button>
            </div>
        </div>
        <div title="운영상태 옵션">
            <div class="status-title">운영상태</div>
            <div class="button-group">
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">진행중</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">오픈 예정</button>
                <button type="button" class="btn btn-outline-secondary"
                        style="padding-inline: 1.5rem;
                        --bs-btn-padding-y: 0.5rem;
                        --bs-btn-line-height: 28.5px;
                        border-radius: 10px;">종료</button>
            </div>
        </div>
        <div title="카테고리 옵션">
            <div class="search-category-title">카테고리</div>
            <div class="button-group">
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_beauty.png" alt="패션/뷰티 아이콘">
                    <div class="search-category-text">패션/뷰티</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_foods.png" alt="식음료 아이콘">
                    <div class="search-category-text">식음료</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_content.png" alt="콘텐츠 아이콘">
                    <div class="search-category-text">콘텐츠</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_hobby.png" alt="취미/여가 아이콘">
                    <div class="search-category-text">취미/여가</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_finance.png" alt="금융 아이콘">
                    <div class="search-category-text">금융</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_entertain.png" alt="연예 아이콘">
                    <div class="search-category-text">연예</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_digital.png" alt="가전/디지털 아이콘">
                    <div class="search-category-text">가전/디지털</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_living.png" alt="리빙 아이콘">
                    <div class="search-category-text">리빙</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_game.png" alt="게임 아이콘">
                    <div class="search-category-text">게임</div>
                </div>
                <div class="search-category-option">
                    <img class="search-category-img" src="@/assets/images/option_character.png" alt="캐릭터 아이콘">
                    <div class="search-category-text">캐릭터</div>
                </div>
            </div>
        </div>
        <div title="팝업스토어 정렬">
            <div class="dropdown-button">
                <select name="" id="">
                    <option value="">최신순</option>
                    <option value="">리뷰 많은순</option>
                    <option value="">좋아요 순</option>
                    <option value="">조회수</option>
                    <option value="">마감순</option>
                </select>
            </div>
            <div class="search-popup-group">
                <div class="search-popup">
                    <img class="search-popup-img" src="@/assets/images/popup_01.png" alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">도구리 생일파티 in 성수</div>
                        <div class="search-popup-info-content">23.11.03 - 24.02.11</div>
                        <div class="search-popup-info-content">서울특별시 성동구 서울숲2길 19-8 1층</div>
                    </div>
                </div>
                <div class="search-popup">
                    <img class="search-popup-img" src="@/assets/images/popup_01.png" alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">도구리 생일파티 in 성수</div>
                        <div class="search-popup-info-content">23.11.03 - 24.02.11</div>
                        <div class="search-popup-info-content">서울특별시 성동구 서울숲2길 19-8 1층</div>
                    </div>
                </div>
                <div class="search-popup">
                    <img class="search-popup-img" src="@/assets/images/popup_01.png" alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">도구리 생일파티 in 성수</div>
                        <div class="search-popup-info-content">23.11.03 - 24.02.11</div>
                        <div class="search-popup-info-content">서울특별시 성동구 서울숲2길 19-8 1층</div>
                    </div>
                </div>
                <div class="search-popup">
                    <img class="search-popup-img" src="@/assets/images/popup_01.png" alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">도구리 생일파티 in 성수</div>
                        <div class="search-popup-info-content">23.11.03 - 24.02.11</div>
                        <div class="search-popup-info-content">서울특별시 성동구 서울숲2길 19-8 1층</div>
                    </div>
                </div>
                <div class="search-popup">
                    <img class="search-popup-img" src="@/assets/images/popup_01.png" alt="정렬된 팝업 목록">
                    <div class="search-popup-info">
                        <div class="search-popup-info-title">도구리 생일파티 in 성수</div>
                        <div class="search-popup-info-content">23.11.03 - 24.02.11</div>
                        <div class="search-popup-info-content">서울특별시 성동구 서울숲2길 19-8 1층</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';

const route = useRoute()
const category = route.params.category


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
    margin-top: 5px;
    margin-left: 28px;
    margin-right: 28px;

    display: flex;
    justify-content: space-between;

    /* 가로 스크롤 설정 */
    overflow-x: scroll;
    white-space: nowrap;
}

button {
    font-size: 15px;
    font-family: Inter;
    font-weight: 400;
    line-height: 25px;
    word-wrap: break-word;
}

button + button {
    margin-left: 12px;
}

/* 스크롤 안보이게 숨기기 */
.button-group{
   -ms-overflow-style: none;
}
.button-group::-webkit-scrollbar{
  display:none;
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
.dropdown-button {
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