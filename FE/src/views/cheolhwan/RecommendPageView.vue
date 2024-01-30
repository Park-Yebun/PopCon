<template>
    <div>뒤로가기</div>
    <div>
      <h2>추천 페이지</h2>
  
      <!-- 추천 유형 A -->
      <div>
        <div>
            <h3>팝BTI</h3> <button>더보기</button>
        </div>
  
        <div class="recommendations">
          <div v-for="(recommendation, index) in recommendationsA" :key="index" class="recommendation">
            <img :src="recommendation.image" :alt="recommendation.name">
            <p>{{ recommendation.name }}</p>
          </div>
        </div>
      </div>
  
      <!-- 추천 유형 B -->
      <div>
        <div>
            <h3>OO님을 위한 AI 추천</h3> <button>더보기</button>
        </div>
        <div class="recommendations">
          <div v-for="(recommendation, index) in recommendationsB" :key="index" class="recommendation">
            <img :src="recommendation.image" :alt="recommendation.name">
            <p>{{ recommendation.name }}</p>
          </div>
        </div>
      </div>

      <!-- 추천 유형 C -->
      <div>
        <div>
            <h3>OO님을 위한 맞춤 추천</h3> <button>더보기</button>
        </div>
        <div class="recommendations">
          <div v-for="(recommendation, index) in recommendationsC" :key="index" class="recommendation">
            <img :src="recommendation.image" :alt="recommendation.name">
            <p>{{ recommendation.name }}</p>
          </div>
        </div>
      </div>

    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        // 추천 유형 A의 이미지 데이터
        recommendationsA: [
          { name: '추천1', image: '/img/recommendation1.jpg' },
          { name: '추천2', image: '/img/recommendation2.jpg' },
          { name: '추천3', image: '/img/recommendation3.jpg' }
        ],
        // 추천 유형 B의 이미지 데이터
        recommendationsB: [
          { name: '추천4', image: '/img/recommendation4.jpg' },
          { name: '추천5', image: '/img/recommendation5.jpg' },
          { name: '추천6', image: '/img/recommendation6.jpg' }
        ],
        recommendationsC: [
          { name: '추천7', image: '/img/recommendation4.jpg' },
          { name: '추천8', image: '/img/recommendation5.jpg' },
          { name: '추천9', image: '/img/recommendation6.jpg' }
        ]
      };
    }
  };
  </script>
  
  <style scoped>
  .recommendations {
    display: flex;
  }
  
  .recommendation {
    margin-right: 20px;
  }
 
  </style>