import { localAxios,localFileAxios } from "@/util/http-common";

const local = localAxios();
const localFile = localFileAxios();

const url='/popups'

// 팝업 등록 (기업회원)
function registerPopup(popup,success,fail) {
    localFile.post(`${url}`,popup).then(success).catch(fail);
}

// 팝업 상세 조회 
function getPopup(popupId,success,fail) {
    local.get(`${url}/${popupId}`).then(success).catch(fail);
}


// 좋아요 클릭 여부 가져오기 
function getLikeOrNot(popupId,success,fail) {
    local.defaults.headers["Authorization"]=localStorage.getItem("accessToken");
    local.get(`${url}/${popupId}/like`).then(success).catch(fail);
}

// 팝업의 리뷰 조회 
function getPopupReviewsTop9(popupId,success,fail) {
    local.get(`${url}/${popupId}/reviews`).then(success).catch(fail)
}

// 통합 검색 팝업 조회 
function getSearchedPopup(params,success,fail) {
    local.get(`${url}/search`, params).then(success).catch(fail)
}

// // 홈
function home(success, fail) {
    local.get('/home').then(success).catch(fail);
}

// 지도 
function map(param, success, fail) {
    local.get('/maps/nearby', {params: param}).then(success).catch(fail);
}

// POPBTI 결과와 매칭
function popbti(param, success, fail) {
    local.get('/recommends/popbti', { params: param }).then(success).catch(fail);
}
// 지도화면에서 검색
function mapSearch(param, success, fail) {
    local.get('/maps/search', { params: param }).then(success).catch(fail);
}


export {
    registerPopup, getPopup, getPopupReviewsTop9, getSearchedPopup, home, map, popbti, mapSearch,getLikeOrNot
};
