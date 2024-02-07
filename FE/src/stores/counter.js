import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { storeToRefs } from 'pinia'
import { useMemberStore } from './user'

export const useCounterStore = defineStore('counter', () => {
  const memberStore = useMemberStore()
  const { userInfo } = storeToRefs(memberStore)

  // const modifyUser = ref({
  //   userId: userInfo.value.userId,
  //   userType: userInfo.value.userType
  // })

  return { }
})
