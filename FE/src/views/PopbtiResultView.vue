<template>
  <div class="container">
  <section id="shareResult" class="mx-auto my-5 py-5 px-3" :class="{ 'fade-in': showResult, 'fade-out': !showResult }">
    <h3 class="pt-5">당신의 결과는?!</h3>
    <div class="resultname">
      <p> {{ infoList[point].name}}</p>
    </div>

    <div id="resultImg" class="my-3 col-lg-6 col-md-6 col-sm-6 cpl-12 mx-auto">
      <img :src="'src/assets/images/popbti-img/popbti-img-' + point + '.svg'" class="img-fluid">
    </div>

    <div class="resultDesc">
      <P>{{ infoList[point].desc }}</P>
    </div>

    <p>당신에게 추천드리는 팝업스토어</p>
    <div title="recpopup" class="popup-group">
      <div v-for="popup in recpopup" class="popup" key="popup">
        <img @click=goPopupDetail(popup.popupId) :src="popup.previewImage" class="popup-img" alt="추천팝업이미지">
        <p class="popup-title">{{popup.popupName}}</p>
      </div>
    </div>

    <button @click="moveHome" type="button" class="mt-2 btn btn-outline-danger">나도 참여하기</button>
  </section>

  </div>

  
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { popbti } from '@/api/popup'

const route = useRoute()
const point = route.params.mbtiId
const recpopup = ref()

onMounted(() => {
  const param = { code: point };
  popbti(
    param,
    ({ data }) => {
      console.log(data);
      recpopup.value = data
      console.log(recpopup)
    },
    ({ response }) => {
      console.log(response);
    }
  );
})

const goPopupDetail = (popupId) => {
  router.push(`/popup/${popupId}`)
}

const moveHome = () => {
  window.location.href = '/popbti';
}

const mbtimatch = {'ISTJ':0, 'ISTP':1, 'ISFJ':2, 'ISFP':3,
                    'INTJ':4, 'INTP':5, 'INFJ':6, 'INFP':7,
                    'ESTJ':8, 'ESTP':9, 'ESFJ':10, 'ESFP':11,
                    'ENTJ':12,'ENTP':13,'ENFJ':14, 'ENFP':15}

                    const infoList = [
  {
    name: 'ISTJ : 성실한 관리자',
    desc: '당신은 실용적이고 조직적인 성향을 가진 사람입니다. 일 처리에 있어서 신중하고 책임감이 강하며 안정적인 모습을 보입니다. 미루지 않고 철저하게 과제를 완수하는 모습이 돋보이는군요! 어울리는 팝업 스토어는 사무용품이나 조직용품을 판매하는 곳일 겁니다.'
  },
  {
    name: 'ISTP : 만능형 탐험가',
    desc: '당신은 호기심이 많고 논리적인 성향을 가진 사람입니다. 문제에 직면했을 때 창의적이고 실용적인 해결책을 찾는 것을 즐기며, 변화를 즐기는 모습이 돋보입니다. 어울리는 팝업 스토어는 액티브 웨어나 아웃도어 장비를 전시하는 곳일 겁니다.'
  },
  {
    name: 'ISFJ : 용감한 수호자',
    desc: '당신은 친절하고 따뜻한 성향을 가진 사람입니다. 타인을 돕고자 하는 마음이 크며, 주변 사람들에게 안정감을 줍니다. 조용하고 신중한 모습을 보이지만 내적으로는 강인한 인내력을 가졌군요! 어울리는 팝업 스토어는 헤어케어나 스킨케어 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'ISFP : 호기심 많은 예술가',
    desc: '당신은 예술적이고 자유로운 성향을 가진 사람입니다. 감각적인 면모를 가지고 있으며, 자연의 아름다움을 즐기는 모습이 돋보입니다. 창의적이고 자유로운 생각을 추구하는군요! 어울리는 팝업 스토어는 공예품이나 핸드메이드 아이템을 전시하는 곳일 겁니다.'
  },
  {
    name: 'INTJ : 용의주도한 전략가',
    desc: '당신은 혁신적이고 목표지향적인 성향을 가진 사람입니다. 비전을 실현하기 위해 논리적인 계획을 세우고 실행하는 능력이 뛰어나네요. 독립적이고 결단력 있는 모습이 주변 사람들에게 믿음을 줄 것입니다! 어울리는 팝업 스토어는 혁신적인 기술 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'INTP : 논리적인 사색가',
    desc: '당신은 분석적이고 창의적인 성향을 가진 사람입니다. 호기심이 많고 논리적인 사고를 바탕으로 문제를 해결하는 데 능숙하며, 새로운 아이디어를 탐구하는 것을 즐깁니다. 어울리는 팝업 스토어는 과학기기나 첨단 기술 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'INFJ : 선의의 옹호자',
    desc: '당신은 이상적이고 이해심 많은 성향을 가진 사람입니다. 타인의 감정을 민감하게 캐치하고, 그들을 도울 방법을 찾는 것을 즐깁니다. 비전과 목표를 가지고 미래를 향해 나아가는 모습이 인상적입니다! 어울리는 팝업 스토어는 예술작품이나 수공예품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'INFP : 열정적인 중재자',
    desc: '당신은 이상적이고 열정적인 성향을 가진 사람입니다. 타인의 감정을 공감하고 이해하는 데 능숙하며, 자신의 가치와 이상을 추구하는 모습이 돋보입니다. 어울리는 팝업 스토어는 친환경 제품이나 천연 화장품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'ESTJ : 엄격한 관리자',
    desc: '당신은 현실적이고 실용적이며 조직적인 성향을 가진 사람입니다. 원칙을 중시하고 안정적인 환경을 선호하며, 목표를 달성하기 위해 계획을 세우고 실행하는 데 능숙합니다. 사회적이고 친화력이 있어 주변 사람들과 원활하게 소통하며 협력하는 데 능숙합니다. 어울리는 팝업 스토어는 업무용품이나 리더십 교육 자료를 전시하는 곳일 겁니다.'
  },
  {
    name: 'ESTP : 활동적인 사업가',
    desc: '당신은 대담하고 실행력이 있는 성향을 가진 사람입니다. 도전을 즐기고 적응력이 뛰어나며, 문제에 대한 신속한 대처 능력이 인상적입니다. 어울리는 팝업 스토어는 스포츠 용품이나 활동적인 라이프스타일 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'ESFJ : 사교적인 외교관',
    desc: '당신은 사교적이고 따뜻하며 협조하는 성향을 가진 사람입니다. 타인의 감정을 이해하고 지지하는 데 능숙하며, 주변 사람들과의 관계를 중요시합니다. 안정적이고 활기찬 분위기를 선호하며, 타인을 돕는 데에 큰 만족을 느낍니다. 어울리는 팝업 스토어는 인테리어 소품이나 홈 데코 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'ESFP : 즉흥적인 연예인',
    desc: '당신은 사교적이고 즉흥적인 성향을 가진 사람입니다. 새로운 경험을 추구하고 즉흥적으로 행동하는 것을 즐기며, 주변 사람들에게 긍정적인 에너지를 전파합니다. 어울리는 팝업 스토어는 음악 앨범이나 아티스트 굿즈를 전시하는 곳일 겁니다.'
  },
  {
    name: 'ENTJ : 대담한 통솔자',
    desc: '당신은 대담하고 통솔력이 있으며 목표 지향적인 성향을 가진 사람입니다. 비전을 가지고 목표를 설정하고, 결정을 내리고 실행하는 데 능숙합니다. 타인을 동기부여하고 이끌어 나가는 데 뛰어난 리더십 능력을 갖추고 있습니다. 어울리는 팝업 스토어는 성공적인 경력 개발을 위한 서적이나 강연 자료를 전시하는 곳일 겁니다.'
  },
  {
    name: 'ENTP : 논쟁을 즐기는 변론가',
    desc: '당신은 독창적이고 혁신적이며 명랑한 성향을 가진 사람입니다. 새로운 아이디어를 탐구하고 독창적인 방식으로 문제를 해결하는 데 능숙하며, 변화와 도전을 즐깁니다. 사고력과 통찰력이 뛰어나며, 다른 사람들과의 논쟁을 통해 새로운 관점을 탐구하는 것을 즐깁니다. 어울리는 팝업 스토어는 혁신적인 제품이나 아이디어를 전시하는 곳일 것입니다.'
  },
  {
    name: 'ENFJ : 정의로운 사회운동가',
    desc: '당신은 열정적이고 친절하며 이타적인 성향을 가진 사람입니다. 타인을 돕고 지원하는 것을 즐기며, 사회적 변화를 이끄는 데 관심이 많습니다. 리더십과 조화력을 갖추고 있어 다양한 사람들을 이끌어 나가는 데 탁월합니다. 어울리는 팝업 스토어는 사회적 문제에 관심을 가진 브랜드의 제품을 전시하는 곳일 겁니다.'
  },
  {
    name: 'ENFP : 재기발랄한 탐험가',
    desc: '당신은 열정적이고 창의적인 성향을 가진 사람입니다. 새로운 아이디어를 탐구하고 다양한 가능성을 모색하는 것을 즐기며, 타인과의 유대를 중요시합니다. 어울리는 팝업 스토어는 예술작품이나 창작물을 전시하는 곳일 겁니다.'
  },
]  

console.log(point)
</script>

<style scoped>

.popup-img {
  width: 120px;
  height: 120px;
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 5px;
}
.popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 170px;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
  -ms-overflow-style: none;
}

.popup-title{
  font-size: 10px;
}
.popup-group::-webkit-scrollbar{
  display:none;
}

.resultDesc{
  font-size: 14px;
}
.resultname{
  font-size: 14px;
}

.container {
  width: 360px;
  height: 800px;
  background-color: pink;
}

#shareResult {
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
  opacity: 1;
}

.goHome{
  color: white;
  background-color: pink;
  font-size: 20px;
  border: 0px;
  border-radius: 20px;

}

.goHome:hover, .goHome:focus{
  background-color: whitesmoke;
  color: pink;
}
</style>