import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

export const usePopbtiStore = defineStore('popbti', () => {
  const API_URL = 'http://127.0.0.1:8000'
  const accountStore = useAccountStore()
  const router = useRouter()

  const articles = ref(null)
  const article = ref(null)
  const categoryId = ref(null)
  const commentSet = ref(null)


  // 게시글 리스트 조회
  const getArticles = function () {
    axios({
      method: 'get',
      url: `${API_URL}/articles/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      }
    })
    .then(res => {
      articles.value = res.data
      // console.log(res.data[res.data.length-1])
      // router.push({name:'ArticleMain'})
    })
    .catch(err => console.log(err))
  }

  // 카테고리별 게시글 리스트 조회
  const getCategory = function(category_id){
    if (category_id ===1){
      axios({
        method: 'get',
        url:`${API_URL}/articles/category/${category_id}/`,
      })
      .then (res => {
        articles.value = res.data
        categoryId.value = category_id
      })
      .catch (err => console.log(err))
    }
  }


  // 카테고리별 게시글 리스트 조회
  const goCategory = function(category_id){
    if (category_id ===1){
      axios({
        method: 'get',
        url:`${API_URL}/articles/category/${category_id}/`,
      })
      .then (res => {
        articles.value = res.data
        categoryId.value = category_id
        router.push({name:'ArticleMain'})
      })
      .catch (err => console.log(err))
    } else {
      axios({
        method: 'get',
        url:`${API_URL}/articles/category/${category_id}/`,
        headers: {
          Authorization: `Token ${accountStore.token}`
        }
      })
      .then (res => {
        articles.value = res.data
        categoryId.value = category_id
        router.push({name:'ArticleMain'})
      })
      .catch (err => console.log(err))
    }
  }


  
  // 게시글 단일 조회 + 이동
  const goDetail = function(article_id){    
    axios({
      method: 'get',
      url: `${API_URL}/articles/${article_id}/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      }
    })
    .then (res => {
      article.value = res.data
      commentSet.value = res.data.comment_set
      router.push({name:'ArticleDetail', params:{articleId: article_id}})
    })
    .catch (err=>console.log(err))
  }


  // 게시글 단일 조회 (onMounted용)
  const getDetail = function(article_id){
    axios({
      method: 'get',
      url: `${API_URL}/articles/${article_id}/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      }
    })
    .then (res => {
      article.value = res.data
      commentSet.value = res.data.comment_set
    })
    .catch (err=>console.log(err))
  }


  // 게시글 생성
  const createArticle = function(payload){
    const { title, category, movie, content, isSpoiler, isNotice} = payload
    axios({
      method: 'post',
      url: `${API_URL}/articles/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
      data : {
        title, category, movie, content, 
        is_spoiler:isSpoiler, 
        is_notice: isNotice
      }
    })
    .then (res => {
      console.log(res.data.id)
      article.value = res.data
      axios({
        method: 'get',
        url: `${API_URL}/articles/${res.data.id}`,
        headers: {
          Authorization: `Token ${accountStore.token}`
        },
      })
      .then(res=>{
        console.log(res)
        router.push({name:'ArticleDetail', params:{articleId: res.data.id}})
      })
    }) 
    .catch (err=>console.log(err))
  }


  // 게시글 수정
  const updateArticle = function(payload){
    const { title, category, movie, content, isSpoiler, isNotice, articleId} = payload
    console.log( title, category, movie, content, isSpoiler, isNotice)
    axios({
      method: 'put',
      url: `${API_URL}/articles/${articleId}/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
      data : {
        title, category, movie, content, 
        is_spoiler:isSpoiler, 
        is_notice: isNotice
      }
    })
    .then (res => {
      article.value = res.data
      router.push({name:'ArticleDetail', params:{articleId: articleId}})
    }) 
    .catch (err=>console.log(err))
  }


  // 게시글 제거
  const deleteArticle = function(articleId){
    axios({
      method:'delete',
      url: `${API_URL}/articles/${articleId}/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
    })
    .then(res =>{
      getArticles()
      router.push({name:'ArticleMain'})
    })
  }

  // 게시글 좋아요
  const likeArticle = function(articleId){
    axios({
      method:'post',
      url:`${API_URL}/articles/${articleId}/likes/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
    })
    .then (res=>{
      goDetail(articleId)
    })
  }


  // 댓글 생성
  const commentCreate = function(payload) {
    const { content, articleId } = payload
    axios({
      method:'post',
      url: `${API_URL}/articles/${articleId}/comments/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
      data : {
        content
      }
    })
    .then(res =>{
      goDetail(articleId)
    })
  }


  // 댓글 제거 
  const commentDelete = function(payload) {
    const {commentId, articleId} = payload
    axios({
      method:'delete',
      url: `${API_URL}/articles/${articleId}/comments/${commentId}/`,
      headers: {
        Authorization: `Token ${accountStore.token}`
      },
    })
    .then(res =>{
      getDetail(articleId)
    })
  }
  
  return { articles, article, categoryId, commentSet, 
    getArticles, getCategory, goCategory, goDetail, getDetail, 
    createArticle, updateArticle, deleteArticle, likeArticle,
    commentCreate, commentDelete,}
})

