import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import SearchView from '@/views/SearchView.vue'


// 철환페이지 import
import EditPageVue from '@/views/cheolhwan/EditPage.vue'
import LoginPageViewVue from '@/views/cheolhwan/LoginPageView.vue'
import MyPageViewVue from '@/views/cheolhwan/MyPageView.vue'
import RecommendPageViewVue from '@/views/cheolhwan/RecommendPageView.vue'
import ReportReviewPageViewVue from '@/views/cheolhwan/ReportReviewPageView.vue'
import SearchIdViewVue from '@/views/cheolhwan/SearchIdView.vue'
import SearchPasswordViewVue from '@/views/cheolhwan/SearchPasswordView.vue'
import SignUpCheckViewVue from '@/views/cheolhwan/SignUpCheckView.vue'
import SignUpCompanyViewVue from '@/views/cheolhwan/SignUpCompanyView.vue'
import SignUpMemberViewVue from '@/views/cheolhwan/SignUpMemberView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: MainView
    },
    {
      path: '/search',
      name: 'search',
      component: SearchView
    },
    //철환 라우터 리스트
    {
      path: '/edit',
      name: 'edit',
      component: EditPageVue
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPageViewVue
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageViewVue
    },
    {
      path: '/recommend',
      name: 'recommend',
      component: RecommendPageViewVue
    },
    {
      path: '/reportreview',
      name: 'reportreview',
      component: ReportReviewPageViewVue
    },
    {
      path: '/searchId',
      name: 'searchId',
      component: SearchIdViewVue
    },
    {
      path: '/searchPassword',
      name: 'searchPassword',
      component: SearchPasswordViewVue
    },
    {
      path: '/signUp',
      name: 'signUp',
      component: SignUpCheckViewVue
    },
    {
      path: '/signUpCompany',
      name: 'signUpCompany',
      component: SignUpCompanyViewVue
    },
    {
      path: '/signUpMember',
      name: 'signUpMember',
      component: SignUpMemberViewVue
    },
  ]
})

export default router
