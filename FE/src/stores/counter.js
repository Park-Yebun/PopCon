import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { storeToRefs } from 'pinia'
import { useMemberStore } from './user'
import { useRouter } from 'vue-router'

import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken, onMessage  } from "firebase/messaging";
import { initializeApp } from "firebase/app";

export const useCounterStore = defineStore('counter', () => {
  const memberStore = useMemberStore()
  const { userInfo } = storeToRefs(memberStore)

  const router = useRouter()
  const PersonalToken = ref()

  // 검색페이지, 지도페이지에는 현재 서버 주소를 확인하여 상단 검색 버튼 안보이게 처리
  const nowURL = ref(document.location.pathname)
  router.afterEach((to, from) => {
    setTimeout(() => {
      nowURL.value = to.path
    }, 0);
  })

  return { nowURL }
})
