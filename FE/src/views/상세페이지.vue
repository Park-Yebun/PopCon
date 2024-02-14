<template>
    <div>

    </div>
</template>

<script setup>
import axios from 'axios';

// onMounted 훅 사용
onMounted(async () => {

axios({
    if(localStorage.getItem("accessToken")!=null) {
  await getUserInfo(localStorage.getItem("accessToken"));
}

getPopup(
  route.params.popupId,

  async ({data})=>{

    popupId.value=route.params.popupId;

    console.log(data);  // data 에 팝업스토어 정보 존재 
    popup.value=data;

    // console.log(popup.value.popupLatitude + '위도확인')
    // console.log(popup.value.popupLongitude + '경도확인')

    lat.value = popup.value.popupLatitude
    lng.value = popup.value.popupLongitude

    // console.log('1')
    // console.log(lat.value + '찐위도확인' )
    // console.log(lng.value + '찐경도확인' )

    popup.value.popupOperating = popup.value.popupOperating.replace(/\n/g, '<br>');
    popup.value.popupContent = popup.value.popupContent.replace(/\n/g, '<br>');
    if(popup.value.popupNotice!=null){
      popup.value.popupNotice=popup.value.popupNotice.replace(/\n/g, '<br>');      
    }
    reviewSummary.value=popup.value.reviewTagSummary.reviewSummary;
    
    // console.log("리뷰 요약 !!");
    // console.log(reviewSummary.value);
})
.then((res) => {
     // 해시맵을 배열로 변환
   if(reviewSummary.value!=null) {
    let entries = Object.entries(reviewSummary.value);

    entries.sort((a, b) => {
        return b[1] - a[1];
    });
   }
.then((res) => {
    // 정렬된 배열을 다시 해시맵으로 변환

    const sortedHashmap = {};
    for (const [key, value] of entries) {
        sortedHashmap[key] = value;
    }

    reviewSummary.value = sortedHashmap;
})
.then((res) => {
    if(localStorage.getItem("accessToken")!=null) {
      isLogin.value=true;
      getLikeOrNot(
        route.params.popupId,
        ({data})=>{
          console.log("좋아요 여부!!!");
          // console.log(data);
          if(data==1) {
            isLike.value=true;
          } else {
            isLike.value=false;
          }
          console.log(isLike.value);
        },
        (error)=>{
          console.log(error);
        }
      )}
})
.then((res) => {
    // 리뷰 데이터 최신순 9개만 가져오기 가져오기 
    getPopupReviewsTop9(
      route.params.popupId,
      ({data})=>{
        console.log(data);  // data 에 팝업스토어 리뷰 정보 존재 
        // console.log(data[0]);

        let cnt=0;
        for(var i=0;i<data.length;i++) {
          if(data[i].reviewImagePath!=null) {
            photoReview.value.push(data[i]);
            cnt++;
          }
          if(cnt==9) break;
        }

        popupReviews.value=data;  // 팝업스토어 리뷰 전체 담기 
        popupLoaded.value=true;

        // 데이터를 모두 받은 후에 loadNaverMapScript 호출
        loadNaverMapScript(lat.value, lng.value);
      },
      (error)=>{
        console.log(error);
      }
    )
})
.catch((err) => {
    console.log(err)
})
})
</script>

<style>

</style>