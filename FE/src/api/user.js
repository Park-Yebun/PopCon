import { localAxios,localFileAxios } from "@/util/http-common";

const local = localAxios();
const localFile = localFileAxios();

async function dupCheck(param,success,fail) {
    // console.log("dupCheck param : ", param);
    await local.post("/users/duplicate",param).then(success).catch(fail);
    // console.log("duplication check end");
}

async function userJoin(param, success, fail) {
    // console.log("join user param :", param);
    await local.post("/users", param).then(success).catch(fail);
    // console.log("join user end");
  }

async function userConfirm(param, success, fail) {
  // console.log("userLogin param", param);
  await local.post("/users/login", param).then(success).catch(fail);
  // console.log("userLogin end");
}

async function searchIdPwd(param, success, fail) {
  await local.post("/users/search",param).then(success).catch(fail);
}

async function userModify(userId, param, success, fail) {
  localFile.defaults.headers["Authorization"] = localStorage.getItem("accessToken");
  await localFile.patch(`/users/${userId}`,param).then(success).catch(fail);
}

async function findById(userId, success, fail) {  // 아이디로 사용자 정보 조회 
  // console.log("findById start");
  await local.get(`/users/info/${userId}`).then(success).catch(fail);
  // console.log("findById end");
}

async function deleteUser(userId, success, fail) {
  await local.delete(`/users/${userId}`).then(success).catch(fail);
}

async function logout(success, fail) {
  local.defaults.headers["Authorization"]=localStorage.getItem("accessToken");
  await local.post("/users/logout").then(success).catch(fail);
}

export{
    dupCheck, userJoin, userConfirm, searchIdPwd, userModify, findById, deleteUser, logout
};
