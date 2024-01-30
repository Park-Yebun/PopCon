import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import SearchView from '@/views/SearchView.vue'
import ReviewView from '@/views/ReviewView.vue'


// 철환페이지 import
import EditMemberPageVue from '@/views/cheolhwan/EditMemberPage.vue'
import EditCompanyPageVue from '@/views/cheolhwan/EditCompanyPage.vue'
import LoginPageViewVue from '@/views/cheolhwan/LoginPageView.vue'
import MyPageViewVue from '@/views/cheolhwan/MyPageView.vue'
import RecommendPageViewVue from '@/views/cheolhwan/RecommendPageView.vue'
import ReportReviewPageViewVue from '@/views/cheolhwan/ReportReviewPageView.vue'
import SearchIdViewVue from '@/views/cheolhwan/SearchIdView.vue'
import SearchPasswordViewVue from '@/views/cheolhwan/SearchPasswordView.vue'
import SignUpCheckViewVue from '@/views/cheolhwan/SignUpCheckView.vue'
import SignUpCompanyViewVue from '@/views/cheolhwan/SignUpCompanyView.vue'
import SignUpMemberViewVue from '@/views/cheolhwan/SignUpMemberView.vue'
import ErrorPageViewVue from '@/views/cheolhwan/ErrorPageView.vue'

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
      path: '/error',
      name: 'error',
      component: ErrorPageViewVue
    },
    {
      path: '/edit/member',
      name: 'editMember',
      component: EditMemberPageVue
    },
    {
      path: '/edit/company',
      name: 'editCompany',
      component: EditCompanyPageVue
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
      path: '/signUp/Company',
      name: 'signUpCompany',
      component: SignUpCompanyViewVue
    },
    {
      path: '/signUp/Member',
      name: 'signUpMember',
      component: SignUpMemberViewVue
    },
    {
      path: '/review',
      name: 'review',
      component: ReviewView

    },
  ]
})

export default router
