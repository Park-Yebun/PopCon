<template>
    <div style="background-color: #f8f9fa;">
        <div title="뒤로가기 버튼" class="back-btn-container">
            <button type="button"
                    class="btn-close"
                    aria-label="Close"
                    style="height: 20px;
                    width: 40px;"></button>
        </div>
        <div title="해시태그 선택하기" class="hashtag-container">
            <div title="안내 멘트">
                <div class="hashtag-text1">어떤 점이 좋았나요?</div>
                <div class="hashtag-text2">이 가게에 어울리는 키워드를 골라주세요.</div>
            </div>
            <div title="선택 옵션" class="hashtag-option-group">
                <div title="상품">
                    <div class="hashtag-option-title">상품</div>
                    <div>
                        <div class="hashtag-option-content">품질이 좋아요</div>
                        <div class="hashtag-option-content">가성비가 좋아요</div>
                        <div class="hashtag-option-content">종류가 다양해요</div>
                        <div class="hashtag-option-content">독특한 디자인이 많아요</div>
                        <div class="hashtag-option-content">트렌디해요</div>
                        <div class="hashtag-option-content">신상품이 많아요</div>
                    </div>
                </div>
                <div title="서비스/기타">
                    <div class="hashtag-option-title">서비스/기타</div>
                    <div class="hashtag-option-content">
                        <div class="hashtag-option-content">친절해요</div>
                        <div class="hashtag-option-content">매장이 넓어요</div>
                        <div class="hashtag-option-content">주차하기 편해요</div>
                        <div class="hashtag-option-content">컨셉이 독특해요</div>
                        <div class="hashtag-option-content">시설이 깔끔해요</div>
                        <div class="hashtag-option-content">선물하기 좋아요</div>
                    </div>
                </div>
            </div>
        </div>
        <div title="이미지 첨부">
            <h3>이미지를 추가해 주세요</h3>
            <input type="file" name="" id="">
        </div>
        <form title="리뷰 작성">
            <div title="별점 레이팅">
                <fieldset>
                    <input type="radio" value="1">
                    <input type="radio" value="2">
                    <input type="radio" value="3">
                    <input type="radio" value="4">
                    <input type="radio" value="5">
                </fieldset>   
            </div>
            <div>
                <textarea name="" id="" cols="30" rows="10" placeholder="리뷰를 작성해주세요&#13;&#10;&#13;&#10;    
                리뷰를 보는 사용자와 사업자에게 상처가 되는욕설, 비방, 명예훼손성 표현은 사용하지 말아주세요."></textarea>
            </div>
        </form>
        <div title="하단 버튼">
            <a href="#">이전</a>
            <a href="#">등록</a>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
/* X 버튼 */
.btn-close {
    margin-top: 75px;
    margin-right: 30px;
}

/* 해쉬태그 선택 */
.hashtag-container {
    width: 330px;
    height: 420px;
    border-radius: 30px;
    background: #FFF;

    margin-top: 17px;
    margin-left: 15px;
}

.hashtag-text1 {
    font-family: Inter;
    font-size: 15px;
    font-style: normal;
    font-weight: 800;
    line-height: normal;
    letter-spacing: -0.3px;

    padding-top: 30px;
    padding-left: 30px;
}

.hashtag-text2 {
    color: #AFB1B4;
    font-family: Inter;
    font-size: 12px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    letter-spacing: -0.3px;

    margin-top: 10px;
    padding-left: 30px;
}

.hashtag-option-group {
    width: 258px;
    height: 290.732px;

    margin-top: 22px;
    padding-left: 30px;

    display: flex;
}

.hashtag-option-title {
    font-family: Inter;
    font-size: 10px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
    letter-spacing: -0.3px;
}

.hashtag-option-content {
    display: inline-block;
    height: 35.211px;
    border-radius: 16px;
    border: 1px solid rgba(175, 177, 180, 0.12);
    background: #FFF;
    box-shadow: 0px 8px 25px 0px rgba(83, 89, 144, 0.07);

    text-align: center;
    font-family: Inter;
    font-size: 10px;
    font-style: normal;
    font-weight: 500;
    line-height: normal;
    letter-spacing: -0.3px;
}
</style>