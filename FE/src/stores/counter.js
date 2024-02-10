import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { storeToRefs } from 'pinia'
import { useMemberStore } from './user'
import { useRouter } from 'vue-router'

export const useCounterStore = defineStore('counter', () => {
  const memberStore = useMemberStore()
  const { userInfo } = storeToRefs(memberStore)

  const BASE_SERVER_URL = "http://localhost:8080"
  const router = useRouter()

  // 검색페이지, 지도페이지에는 현재 서버 주소를 확인하여 상단 검색 버튼 안보이게 처리
  const nowURL = ref(document.location.pathname)
  router.afterEach((to, from) => {
    setTimeout(() => {
      nowURL.value = to.path
    }, 0);
  })


  // const modifyUser = ref({
  //   userId: userInfo.value.userId,
  //   userType: userInfo.value.userType
  // })

  return { BASE_SERVER_URL, nowURL  }
})
