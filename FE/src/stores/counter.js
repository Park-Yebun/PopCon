import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { storeToRefs } from 'pinia'
import { useMemberStore } from './user'

export const useCounterStore = defineStore('counter', () => {
  const memberStore = useMemberStore()
  const { userInfo } = storeToRefs(memberStore)

  const BASE_SERVER_URL = "http://localhost:8080"

  // const modifyUser = ref({
  //   userId: userInfo.value.userId,
  //   userType: userInfo.value.userType
  // })

  return { BASE_SERVER_URL  }
})
