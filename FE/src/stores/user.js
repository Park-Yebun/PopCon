import { ref } from "vue";
import { useRouter } from "vue-router";
import { defineStore, storeToRefs } from "pinia";
import { jwtDecode } from "jwt-decode";

import {userConfirm, findById} from "@/api/user.js";
import { httpStatusCode } from "@/util/http-status";

export const useMemberStore = defineStore (
  "memberStore",
  () => {
    const router = useRouter();

    const isLogin = ref(false);
    const isLoginError = ref(false);
    const userInfo = ref(null);
    const userType = ref(null);
    // const isValidToken = ref(false);

    const userLogin = async (loginUser) => {
      console.log("userLogin!!", loginUser);
      await userConfirm(
        loginUser,
        (response) => {
          console.log("response.status", response.status);
          console.log(response);
          if (response.status === httpStatusCode.OK) {
            let { data } = response;
            // console.log("data", data);
            let accessToken = response.headers["authorization"];

            console.log("accessToken", accessToken);

            isLogin.value = true;
            isLoginError.value = false;
            // isValidToken.value = true;
            localStorage.setItem("accessToken",accessToken);
          } else {
            isLogin.value = false;
            isLoginError.value = true;
            // isValidToken.value = false;
          }
        },
        (error) => {
          console.error(error);
          // ----------
        }
      );
    };

    const getUserInfo = async (token) => {  // 토큰이 있는 경우에 사용자 정보를 가져오기 위해 사용 , userInfo 저장함 
      // console.log("getUserInfo 토큰 !!!!! ", token);
      const accessToken=token.split(" ");
      // console.log("accessToken : ",accessToken);
      let decodeToken = jwtDecode(accessToken[1]);
      // 2. decodeToken {userId: 'tmddus', userType: 'GENERAL', iat: 1706765617, exp: 1707370417}

      await findById(
        decodeToken.userId,
        (response) => {
          console.log("findById 결과 >> ", response.data);
          userInfo.value = response.data; // <- 확인 후 등록 
          if(userInfo.value.userImagePath==null){
            userInfo.value.userImagePath="https://s3.ap-southeast-2.amazonaws.com/popcon.s3.bucket/noProfile.png";
          }
        },
        (error) => {
          console.log(error);
          // to do : 사용자 정보가 없습니다. 다시 로그인 하세요 ..
          router.push({name:"login"});
        }
      );
    };

    const tokenRegenerate = async () => {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("accessToken")
      );
      await tokenRegeneration(
        JSON.stringify(userInfo.value),
        (response) => {
          if (response.status === httpStatusCode.CREATE) {
            let accessToken = response.data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("accessToken", accessToken);
            // isValidToken.value = true;
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === httpStatusCode.UNAUTHORIZED) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              userInfo.value.userid,
              (response) => {
                if (response.status === httpStatusCode.OK) {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                isLogin.value = false;
                userInfo.value = null;
                // isValidToken.value = false;
                router.push({ name: "user-login" });
              },
              (error) => {
                console.error(error);
                isLogin.value = false;
                userInfo.value = null;
              }
            );
          }
        }
      );
    };

    const userLogout = async (userid) => {
      await logout(
        userid,
        (response) => {
          if (response.status === httpStatusCode.OK) {
            isLogin.value = false;
            userInfo.value = null;
            // isValidToken.value = false;
            localStorage.clear();
            // planBox.value = [];
            // storeBox.value = [];
          } else {
            console.error("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    };

    return {
      isLogin,
      isLoginError,
      userInfo,
    //   isValidToken,
      userLogin,
      getUserInfo,
      tokenRegenerate,
      userLogout,
    };
  },
  {
    persist: { storage: localStorage },
  }
);
