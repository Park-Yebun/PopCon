import { ref,onMounted, computed } from 'vue'
import { defineStore } from 'pinia'
import { storeToRefs } from 'pinia'
import { useMemberStore } from './user'
import { useRouter } from 'vue-router'

import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken, onMessage  } from "firebase/messaging";
import { initializeApp } from "firebase/app";

export const useCounterStore = defineStore('counter', () => {
  const memberStore = useMemberStore()
  const router = useRouter()
  const fcmToken = ref()

  // 검색페이지, 지도페이지에는 현재 서버 주소를 확인하여 상단 검색 버튼 안보이게 처리
  const nowURL = ref(document.location.pathname)
  router.afterEach((to, from) => {
    setTimeout(() => {
      nowURL.value = to.path
    }, 0);
  })

  
  // 파이어베이스 생성 토큰 받아오기
  // onMounted(()=>{
  //   navigator.serviceWorker
  // .register("firebase-messaging-sw.js")
  // .then(function (registration) {
  //   console.log("Service worker successfully registered.");
  //   return registration;
  // })
  // .catch(function (err) {
  //   console.error("Unable to register service worker.", err);
  // });

  // const firebaseConfig = {
  //   apiKey: "AIzaSyDvNw-1CiYp-H2q_RTsM_uM778SsrWj2ZM",
  //   authDomain: "ssafypopcon.firebaseapp.com",
  //   projectId: "ssafypopcon",
  //   storageBucket: "ssafypopcon.appspot.com",
  //   messagingSenderId: "543956021805",
  //   appId: "1:543956021805:web:07b2670024d09e5ec9fea4",
  //   measurementId: "G-5QZKSRXRD2"
  // };
  
  
  // const app1 = initializeApp(firebaseConfig);
  // const analytics = getAnalytics(app1);
  
  // const messaging = getMessaging();

  // getToken(messaging, { vapidKey: 'BJK9lVeFIvJ5u3jvtWKGabTSNOqbX69MT2m2gbl110ZDyvUFsvpkKKHRKZRd4wEdjopFz_NxuGgfZoET1kTeqGs' }).then((currentToken) => {
  //     if (currentToken) {
  //         // Send the token to your server and update the UI if necessary
  //         fcmToken.value = currentToken
  //         console.log(currentToken)
  //         // ...
  //     } else {
  //         // Show permission request UI
  //         console.log('No registration token available. Request permission to generate one.');
  //         // ...
  //     }
  
  // }).catch((err) => {
  //     console.log('An error occurred while retrieving token. ', err);
  //     // ...
  // });

  // })


  return { nowURL, fcmToken  }
})
