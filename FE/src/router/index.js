import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import SearchView from '@/views/SearchView.vue'
import ReviewView from '@/views/ReviewView.vue'

import EditMemberPageVue from '@/views/EditMemberPage.vue'
import EditCompanyPageVue from '@/views/EditCompanyPage.vue'
import LoginPageViewVue from '@/views/LoginPageView.vue'

import MyPageViewVue from '@/views/MyPageView.vue'
import RecommendPageViewVue from '@/views/RecommendPageView.vue'
import MapMainView from '@/views/MapMainView.vue'
import ReportReviewPageViewVue from '@/views/ReportReviewPageView.vue'
import PopupStoreCorporateReport from '@/views/PopupStoreCorporateReport.vue'
import SearchIdViewVue from '@/views/SearchIdView.vue'
import SearchPasswordViewVue from '@/views/SearchPasswordView.vue'
import SignUpCheckViewVue from '@/views/SignUpCheckView.vue'
import SignUpCompanyViewVue from '@/views/SignUpCompanyView.vue'
import SignUpMemberViewVue from '@/views/SignUpMemberView.vue'
import ErrorPageViewVue from '@/views/ErrorPageView.vue'

import MemberOtherPage from '@/views/MemberOtherPage.vue'
import ReportPopupStore from '@/views/ReportPopupStore.vue'
import AdminOtherPage from '@/views/AdminOtherPage.vue'
import BusinessOtherPage from '@/views/BusinessOtherPage.vue'
import PersonalOtherPage from '@/views/PersonalOtherPage.vue'

import NoticeList from '@/views/NoticeList.vue'
import FAQ from '@/views/FAQ.vue'
import onetoone from '@/views/1to1.vue'

import MapSearchView from '@/views/MapSearchView.vue'
import PopupDetailView from '@/views/PopupDetailView.vue'
import PopbtiViewVue from '@/views/PopbtiView.vue'
import PopbtiResultViewVue from '@/views/PopbtiResultView.vue'
// import GibonView2 from '@/views/GibonView2.vue'

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/user";

import { useRouter } from 'vue-router';
const router2 = useRouter()



const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = localStorage.getItem("accessToken");

  if(token!=null){  // 토큰이 있으면 아이디 찾아오기
    // console.log(1); 
    await getUserInfo(token);
    next();
  } else {  // 없으면 로그인 시키기 
    // next({name:"user-login"});
    router.push({name:"user-login"});
  }

};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: MainView,
      beforeRouteEnter (to, from, next) {
        window.location.reload();
      }
    },
    {
      path: '/error',
      name: 'error',
      component: ErrorPageViewVue
    },
    {
      path: '/join',
      name: 'join',
      component: SignUpCheckViewVue // 개인, 기업 회원 가입 선택 페이지 
    },

    // 추천 페이지 라우터    
    {
      path: '/recommend',
      name: 'recommend',
      component: RecommendPageViewVue,
      // children: [
      //   { //
      //     path: '/popbti',
      //     name: 'recommend-popbiti',
      //     component: PopbtiView
      //   }
      // ]
    },
    { //지도 페이지 
      path: '/map',
      name: 'map',
      component: MapMainView
    },
    { //지도 검색 페이지 
      path: '/map/search',
      name: 'mapsearch',
      component: MapSearchView
    },

    { //검색 페이지 
      path: '/search/:category?',
      name: 'search',
      component: SearchView
    },
    { //팝비티아이 페이지 
      path: '/popbti',
      name: 'popbti',
      component: PopbtiViewVue
    },
    { //팝비티아이 결과 페이지 
      path: '/popbti/:mbtiId',
      name: 'popbtiresult',
      component: PopbtiResultViewVue
    },

    // 유저 기능 라우터 
    {
      path:"/user",
      name:"user",
      redirect: {name:"user-login"},
      children:[
        {
          path: 'login',
          name: 'user-login',
          component: LoginPageViewVue
        },
        {
          path: 'searchId',
          name: 'user-search-id',
          component: SearchIdViewVue
        },
        {
          path: 'searchPassword',
          name: 'user-search-password',
          component: SearchPasswordViewVue
        },
        {
          path: 'company/join',
          name: 'company-join',
          component: SignUpCompanyViewVue
        },
        {
          path: 'join',
          name: 'user-join',
          component: SignUpMemberViewVue
        },
        { 
          path: 'edit',
          name: 'edit-member',
          beforeEnter: onlyAuthUser,
          component: EditMemberPageVue
        },
        {
          path: 'edit/company',
          name: 'edit-company',
          beforeEnter: onlyAuthUser,
          component: EditCompanyPageVue
        },
        {
          path: 'mypage',
          name: 'user-mypage',
          beforeEnter: onlyAuthUser,
          component: MyPageViewVue
        },
        {
          path: 'other/member',
          name: 'other-member',
          beforeEnter: onlyAuthUser,
          component: MemberOtherPage
        },
        {
          path: 'other/admin',
          name: 'other-admin',
          component: AdminOtherPage
        },
        {
          path: 'other/corporate',
          name: 'other-corporate',
          component: BusinessOtherPage
        },
        {
          path: 'other',
          name: 'other',
          component: PersonalOtherPage
        },
      ]
    },
    
    // 팝업 기능 라우터
    {
      path:"/popup",
      name:"popup",
      redirect: {name:"popup-search"},
      children:[
        {   // 제보 ; 회원만 접근 가능 
          path: '/popup/report',
          name: 'popup-report',
          component: ReportPopupStore,
          beforeEnter : onlyAuthUser
        },
        { // 제보 ; 기업 회원만 접근 가능 -> beforeEnter 변경 
          path: '/popup/register',
          name: 'popup-register',
          component: PopupStoreCorporateReport,
          beforeEnter : onlyAuthUser
        },
        { // 상세보기 
          path:"/popup/:popupId",
          name: 'popup-detail',
          component: PopupDetailView
        },
      ]
    },

    {
      path: '/popup/:popupId/report/:reviewId',  // 댓글 신고 
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
    {
      path: '/popup/:popupId/review',
      name: 'review',
      component: ReviewView
    },
    {
      path: '/notice',
      name: 'notice',
      component: NoticeList
    },
    {
      path: '/onetoone',
      name: 'onetoone',
      component: onetoone
    },
    {
      path: '/FAQ',
      name: 'FAQ',
      component: FAQ
    },

  ]
})

export default router
