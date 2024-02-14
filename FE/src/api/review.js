import { localAxios,localFileAxios } from "@/util/http-common";

const local = localAxios();
const localFile = localFileAxios();

const url='/reviews'


// 댓글 좋아요 클릭 
function addLikeReview(popupId,reviewId,success,fail){ 
    // /{popupId}/{reviewId}/recommend")
    local.defaults.headers["Authorization"]=localStorage.getItem("accessToken");
    local.post(`${url}/${popupId}/${reviewId}/recommend`).then(success).catch(fail);
}

// 댓글 좋아요 취소 
function cancelLikeReview(popupId,reviewId,success,fail){
    local.defaults.headers["Authorization"]=localStorage.getItem("accessToken");
    local.delete(`${url}/${popupId}/${reviewId}/recommend`).then(success).catch(fail);
}


export {
    addLikeReview, cancelLikeReview
};
