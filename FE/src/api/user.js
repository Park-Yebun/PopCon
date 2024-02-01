import { localAxios } from "@/util/http-common";

const local = localAxios();

async function dupCheck(param,success,fail) {
    console.log("dupCheck param : ", param);
    await local.post("/users/duplicate",param).then(success).catch(fail);
    console.log("duplication check end");
}

async function userJoin(param, success, fail) {
    console.log("join user param :", param);
    await local.post("/users", param).then(success).catch(fail);
    console.log("join user end");
  }

async function userConfirm(param, success, fail) {
  console.log("userLogin param", param);
  await local.post("/users/login", param).then(success).catch(fail);
  console.log("userLogin end");
}

async function searchIdPwd(param, success, fail) {
  await local.post("/users/search",param).then(success).catch(fail);
}

export{
    dupCheck, userJoin, userConfirm, searchIdPwd
};