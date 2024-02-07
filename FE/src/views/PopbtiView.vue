<template>
  <div class="container">
    <section v-show="showMain" id="main" class="mx-auto my-5 py-5 px-3" :class="{ 'fade-out': !showMain }">
      <h3 class="pt-5">✨POPBTI로 알아보는 나의 팝업 취향✨</h3>
      <div class="col-lg-6 col-md-6 col-sm-6 cpl-12 mx-auto">
        <img src="@/assets/images/pop-up-store.png" alt="mainImage" class="img-fluid">
      </div>
      <p>
       POPBTI로 당신의 취향 저격 팝업을 찾아보세요!
      </p>
      <button @click="begin" type="button" class="mt-2 btn btn-outline-danger">시작하기</button>
    </section>


    <section v-show="showQna" id="qna" :class="{ 'fade-in': showQna, 'fade-out': !showQna }" >
      <div class="status mx-auto mt-5">
        <div class="statusBar">
        </div>
      </div>
        <div class="qBox my-5 py-3 mx-auto">

        </div>
        <div class="answerBox">

        </div>
    </section>


    <section v-show="showResult" id="result" class="mx-auto my-5 py-5 px-3" :class="{ 'fade-in': showResult, 'fade-out': !showResult }">
      <p class="pt-5">당신의 결과는?!</p>
      <div class="resultname">
      </div>

      <div id="resultImg" class="my-3 col-lg-6 col-md-6 col-sm-6 cpl-12 mx-auto">
      </div>
      
      <div class="resultDesc">
      </div>

      <p>당신에게 추천드리는 팝업스토어!!</p>
      <img src="" alt="">
      <img src="" alt="">
      
      <button @click="setShare" type="button" class="kakao mt-3 py-2 px-3">공유하기</button>
    </section>

  </div>
</template>

<script setup>
import { ref } from 'vue';




const showMain = ref(true);
const showQna = ref(false);
const showResult = ref(false);
const endPoint = 4;
const select = [];
// endPoint는 질문의 개수


// meta tag
const name = 'MyComponent';
const metaInfo = {
  title: 'My Page Title',
  meta: [
    { charset: 'utf-8'},
    { name: 'viewport', content: 'width=device-width, initial-sacle=1'},
    { name: 'author', content: 'wiese0630'},
    { name: 'keywords', content: 'POPBTI'},
    { name: 'description', content: 'POPBTI'},
    
    // SNS share
    { property: 'og:url', content: 'https://popcon.netlify.app'},
    { property: 'og:title', content: 'POPBTI 테스트'},
    { property: 'og:type', content: 'website'},
    { property: 'og:image', content: 'img/share.png'},
    { property: 'og:description', content: 'POPBTI 테스트로 알아보는 팝업 취향'},
  ],
};



const qnaList = [
  {
    q: '1. 핫하다는 팝업은 다 모여있는 성수에 가려는 당신, 누구와 같이 갈까??',
    a: [
      { answer: 'a. 같이는 무슨~ 내 취향저격 팝업만 골라서 갈래!! 혼자 가는게 편해!!', type: ['I'] },
      { answer: 'b. 함께하면 행복이 N배! 다같이 모여서 인생네컷 찍고 맛집 뿌시자!!', type: ['E'] },
    ]
  },
  {
    q: '2. 약속 시간이 얼마 남지 않은 당신! 남은 시간 안에 둘 중 한군데만 방문할 수 있다면?? ',
    a: [
      { answer: 'a. 남는 건 사진뿐이야~ 사진이 잘 나오는 포토스팟이 많은 팝업!!', type: ['N'] },
      { answer: 'b. 실용적인게 최고! 특이한 경험을 하거나 굿즈를 받을 수 있는 팝업!!', type: ['S'] },
    ]
  },
  {
    q: '3. 갖고싶었던 굿즈가 있어 N시간 줄 섰는데 내 눈앞에서 품절됐다!! 이때 당신의 반응은??',
    a: [
      { answer: 'a. 내가이것때문에새벽같이일어나서N시간동안줄서서기다렸는데품절이라니말도안돼내세상이무너졌어... 절망한다.', type: ['F'] },
      { answer: 'b. 이거 재입고 되나요? 언제 되나요? 하루에 몇 개씩 입고되나요? 언제쯤 줄 서야 살 수 있어요? 질문한다.', type: ['T'] },
    ]
  },
  {
    q: '4. 가고 싶은 팝업이 예약 마감되어버리고 말았다. 남은 방법은 현장대기뿐! 당신의 선택은??',
    a: [
      { answer: 'a. 입장할 수 있을지 불확실하네 ㅠㅠ 그냥 다른 데 찾아보자! ', type: ['J'] },
      { answer: 'b. 기다려보고 못들어가면 그때 가서 생각하지 뭐~ 일단 가보자고! ', type: ['P' ] },
    ]
  },
]

const infoList = [
  {
    name: 'ISTJ : 청렴결백한 논리주의자, 현실주의자',
    desc: '연애의 프로재능러인 당신! 쥐에 해당하는 당신은 연애의 모든 것들을 알고 있어, 쉽게 연애에 성공하는 스타일이랍니다. 주로 포부가 크고 성격이 낙천적인 성향을 가지고 있고, 논리적이면서 두뇌회전이 매우 빠르다고 할 수 있어요. 말재주 또한 좋아 사교성이 좋고 유쾌하지만 화려한 것을 좋아해 허영심을 부리거나 사치를 부리기 때문에 이 점들을 주의해야 돼요. 뛰어난 능력에 자만하는 경우도 종종 있기 때문에 겸손이 필요하답니다! '
  },
  {
    name: 'ISTP : 만능 재주꾼, 장인',
    desc: '연애 유형의 진국인 당신! 소에 해당하는 당신은 상대방만 바라보는 해바라기 연애 스타일이랍니다. 매사에 끈기가 있어 연애를 할 때 성실하고 진심을 다하는 성향을 가지고, 온화하며 따뜻한 마음을 가지고 있어요. 한 번 좋아하면 끝까지 좋아하는 의리파지만 자존심이 쌔 은근히 고집이 강하답니다. 또한 욱하는 성질이 있어 이런 부분들을 조심해야 하고, 질투심이 많아 사랑이 집착으로 바뀔 수 있어 주의해야 된답니다! '
  },
  {
    name: 'ISFJ : 용감한 수호자, 실용적인 조력가',
    desc: '한 번 좋아하는 사람이 있으면 내 사람으로 만드는 당신! 호랑이에 해당하는 당신은 썸부터 사랑까지 한 번에 직진하는 스타일이랍니다. 연애할 때 주로 이끄는 성향을 가지고 있고, 시원시원한 성격과 유쾌한 성격을 가지고 있어요. 대쪽같은 성격에다 실수를 용납하는 대인배지만 유혹에 약하고 허영때문에 들뜨는 성격이라 다른 이성의 접근에 주의해야 돼요. 또한 내가 한 말로 인해 상대방이 상처를 받을 수 있기 때문에, 항상 말 조심이 필요하답니다!'
  },
  {
    name: 'ISFP : 호기심 많은 예술가, 성인군자형',
    desc: '상대방을 홀리게 만드는 당신! 신비롭고 독특한 매력을 가져, 상대방이 푹 빠지게 만드는 스타일이에요. 직감이 발달되어 손재주가 뛰어난 편이고, 연애할 때 통통 튀는 모습들을 많이 보여준답니다. 유순하고 낙천적인 성향이지만, 자칫잘못하면 상대방에게 천진난만한 아이처럼 보일 수 있는 점을 주의해야 돼요. 또한 감정 기복이 심한 편이라 변덕스러운 마음을 가질 수 있어, 이런 부분들에 신경을 쓴다면 좋은 연애를 할 수 있어요.'
  },
  {
    name: 'INTJ : 용의주도한 전략가, 과학자형',
    desc: '상대방과의 미래를 꿈꾸는 당신! 현실적인 연애보다는, 미래에 대한 이상적인 연애관을 가지고 있어요. 매일매일 사랑하는 사람과 보낼 미래를 생각하기 때문에, 좋은 신랑감이 될 수 있는 자질이 있답니다. 또한 일적으로나 사랑으로나 야망이 커, 크게 성공할 확률이 높고 리더십이 매우 뛰어나답니다. 다만 높은 이상에 비해서 현실이 자신의 마음에 들지 않으면 허세를 부리거나 사람들을 무시하는 경우가 많으니 이점들을 주의하면 좋을 것 같아요!'
  },
  {
    name: 'INTP : 논리적인 사색가, 논리술사',
    desc: '상대방의 기념일을 챙기며 깜짝 놀라게 하는 당신!, 아이디어가 뛰어나고 남다른 감각이 있어, 상대방에게 많은 이벤트를 해주는 스타일이에요. 연애할 때 눈치도 빠르고, 감동을 줄 수 있어 상대방이 매일 행복하답니다. 전체적으로 화려한 연애를 하지만, 은근히 비밀이 많고 우유부단한 성격을 가지고 있답니다. 또한 귀가 매우 얇아 남의 이야기를 곧이 곧대로 믿어, 상대방을 의심하기도 하기 때문에 조심해야 된답니다!'
  },
  {
    name: 'INFJ : 통찰력 있는 선지자, 예언자형',
    desc: '연애할 때, 센스가 넘쳐나는 당신! 말재주가 뛰어나며 처세술이 뛰어나, 상대방의 호감을 금방 얻는답니다. 낙천적이고 유연한 성격을 가지고 있으며, 구속받는 것을 싫어하는 스타일이에요. 소중한 사람들을 위해 희생할 줄도 알지만, 쾌략 지향적인 경우가 많아 사치와 허영에 물드는 경우가 다반수입니다. 한탕 주의에 빠져 쉽게 망가질 수 있기 때문에 이 점을 주의해야 된답니다!'
  },
  {
    name: 'INFP : 중재자, 잔 다르크형',
    desc: '연애할 때 모든 걸 맞춰주려고 하는 당신! 다정한 느낌과 상냥한 모습을 가지고 있어, 상대방 입장에서 편안한 느낌을 받을 수 있답니다. 타오르는 연애보다는 안정적인 연애를 추구하고, 상대방에게 마음을 여는 순간 진심으로 사랑에 푹 빠질 수 있습니다. 또한 강인한 생존력과 수완이 좋으나, 한편으로는 자신이 원하는 바를 모두 쟁취하려는 고집이 있어요. 이 고집이 심해지면 다른 사람 눈에서는 계산적인 사람처럼 보여 매력을 잃을 수 있습니다!'
  },
  {
    name: 'ESTJ : 엄격한 관리자, 경영자',
    desc: '지치고 힘들 때 언제나 행복을 주는 당신! 정말 끼가 많고 말재주가 많다보니 팔방미인이라는 소리를 많이 듣는 편이랍니다. 또한 다재다능하고 총명하여 어떤 일이든지 잘할 수 있고, 유머 감각도 뛰어나 주변 사람들이 많이 따르는 마당발 타입입니다. 연애할 때 상대방의 기분을 잘 맞춰주고 즐거운 분위기를 유지하나, 다소 본인의 체면을 중시하는 경향이 있어요. 이 점 때문에 잔머리를 굴리다 타인에게 교활하다는 인상을 주게 될 수도 있기 때문에 조금 조심하면 좋을 것 같아요!'
  },
  {
    name: 'ESTP : 모험을 즐기는 사업가, 수완 좋은 활동가형',
    desc: '깔끔한 연애를 추구하며 맺고 끊는 것이 확실한 당신! 단아하고 고고한 이미지를 가지고 있어 우아한 매력을 표출하는 편이랍니다. 집중력과 기억력이 좋아 상대방과 있었던 일들을 다 기억해주는 편이고, 겉으로는 무뚝뚝하나 은근히 감수성이 풍부하답니다. 다만 자신이 생각한 선을 벗어나면 냉소적으로 바뀌고, 평소 직설적인 편이라 차갑다는 인상을 많이 줄 수 있어요. 섬세한 사람이기 때문에 예민한 경우가 많으니 마음을 평안히 다스리고, 상대방에게 좀만 더 따뜻하게 대하면 좋을 것 같아요!'
  },
  {
    name: 'ESFJ : 사교적인 외교관, 친선도모형',
    desc: '애교쟁이 그 자체인 당신! 둥글둥글하게 처세를 잘 하고, 붙임성이 매우 좋아 사교성이 매우 뛰어난 편이랍니다. 또한 궁금한 것을 참지 못해 호기심이 많고, 강아지같이 상대방에게 찰싹 달라붙는 것을 좋아하는 스타일이에요. 성격이 매우 쾌활하나, 감정의 기복이 은근히 심해 상대방이 하는 말에 대해 크게 상처받는 편입니다. 또한 성격이 급한 경우가 많아, 차분하게 행동을 하지 못하는 편이기 때문에 이 점을 좀만 조심하면 좋을 것 같아요! '
  },
  {
    name: 'ESFP : 자유로운 영혼의 연예인, 슈퍼스타형',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  },
  {
    name: 'ENTJ : 대담한 통솔자',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  },
  {
    name: 'ENTP : 뜨거운 논쟁을 즐기는 변론가, 발명가형',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  },
  {
    name: 'ENFJ : 선도자, 언변능숙형',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  },
  {
    name: 'ENFP : 재기발랄한 활동가, 스파크형',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  },
]

const setResult = () => {
  let point = calResult();
  const resultName = document.querySelector('.resultname');
  resultName.innerHTML = infoList[point].name;

  var resultImg = document.createElement('img');
  const imgDiv = document.querySelector('#resultImg');
  var imgURL = '/src/assets/images/popbti-img/popbti-img-' + point + '.svg';
  
  console.log(imgURL)
  resultImg.src = imgURL;
  resultImg.alt = point;
  resultImg.classList.add('img-fluid');
  imgDiv.appendChild(resultImg);


  const resultDesc = document.querySelector('.resultDesc');
  resultDesc.innerHTML = infoList[point].desc;



}

const calResult = () => {
  const mbti = [{0:'I', 1:'E'}, {0:'N', 1:'S'}, {0:'F', 1:'T'}, {0:'J', 1:'P'}]
  var result = ''
  result = mbti[0][select[0]] + mbti[1][select[1]] + mbti[2][select[2]] + mbti[3][select[3]]
  // console.log(result)
  // result 값으로 mbti 타입 나옴 ok
  const mbtimatch = {'ISTJ':0, 'ISTP':1, 'ISFJ':2, 'ISFP':3,
                    'INTJ':4, 'INTP':5, 'INFJ':6, 'INFP':7,
                    'ESTJ':8, 'ESTP':9, 'ESFJ':10, 'ESFP':11,
                    'ENTJ':12,'ENTP':13,'ENFJ':14, 'ENFP':15}
  return mbtimatch[result];  
}

const goResult = () => {
  showQna.value = false;
  showResult.value = true;
  // console.log(select);
  setResult();

}
 
const addAnswer = (answerText, qIdx, idx) => {
  var a = document.querySelector('.answerBox');
  var answer = document.createElement('button');
  answer.classList.add('my-3');
  answer.classList.add('py-3');
  answer.classList.add('mx-auto');
  answer.classList.add('answerList');
  answer.classList.add('fade-in');

  a.appendChild(answer);
  answer.innerHTML = answerText;
  answer.addEventListener("click", function(){
    var children = document.querySelectorAll('.answerList');
    for(let i = 0; i < children.length; i++){
      children[i].disabled = true;
      children[i].style.WebkitAnimation = "fadeOut 0.5s";
      children[i].style.animation = "fadeOut 0.5s";
    }
    setTimeout(() => {
      select[qIdx] = idx;
      for(let i = 0; i < children.length; i++){
        children[i].style.display = 'none';
      }
      goNext(++qIdx);
    },450)
  }, false);
}

const goNext = (qIdx) => {
  if(qIdx === endPoint){
    goResult();
    calResult();
    return;
  }

  var q = document.querySelector('.qBox');
  q.innerHTML = qnaList[qIdx].q;
  for(let i in qnaList[qIdx].a){
    addAnswer(qnaList[qIdx].a[i].answer, qIdx, i);
  }
  var status = document.querySelector('.statusBar');
  status.style.width = (100/endPoint) * (qIdx+1) + '%';
}

const begin = () => {
  showMain.value = false;
  showQna.value = true;
  let qIdx = 0;
  goNext(qIdx);
};

const url = 'https://popcon.netlify.app'

const setShare = () => {
  var resultImg = document.querySelector('#resultImg');
  var resultAlt = resultImg.firstElementChild.alt;
  // resultAlt는 MBTI 코드
  const shareTitle = 'POPBTI 테스트 결과'
  const shareDes = infoList[resultAlt].name;
  console.log(resultAlt + '**')
  const shareImage = url + 'img/image-' + resultAlt + '.png';
  const shareURL = url + 'page/result-' + resultAlt + '.html';
  Kakao.Share.sendDefault({
    objectType: 'feed',
    content: {
      title: shareTitle,
      description: shareDes,
      imageUrl: shareImage, 
      link: {
        mobileWebUrl: shareURL,
        webUrl: shareURL,
      },
    },
    buttons: [
      {
        title: '결과확인하기',
        link: {
          mobileWebUrl: shareURL,
          webUrl: shareURL,
        },
      },
    ]
  });
}


</script>

<style scoped>
.container {
  width: 360px;
  height: 800px;
  background-color: pink;
}

#main {
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
  opacity: 1;
}

#result {
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
  opacity: 1;
}
.resultname{
  font-size: 14px;
}
.resultDesc{
  font-size: 14px;
}
.kakao{
  color: white;
  background-color: #FEE500;
  font-size: 20px;
  border: 0px;
  border-radius: 20px;

}

.kakao:hover, .kakao:focus{
  background-color: whitesmoke;
  color: #FEE500;
  width: 80%;

}
.fade-in {
  animation: fadeIn 0.5s;
  
}

.fade-out {
  animation: fadeOut 0.5s;
}

@keyframes fadeIn {
  from { opacity: 0;}
  to { opacity: 1;}
}

@keyframes fadeOut {
  from { opacity: 1;}
  to { opacity: 0;}
}

@-webkit-keyframes fadeIn {
  from { opacity: 0;}
  to { opacity: 1;}
}

@-webkit-keyframes fadeOut {
  from { opacity: 1;}
  to { opacity: 0;}
}


.qBox{
  background-color: whitesmoke;
  text-align: center;
  border-radius: 20px;
  width: 80%;
}

.answerList{
  background-color: whitesmoke;
  border-radius: 20px;
  display: block;
  width: 100%;
  border: 0px;
  width: 80%;
}

.answerList:hover, .answerList:focus{
  background-color: pink;
  color: whitesmoke;
  width: 80%;

}

.status{
  height: 20px;
  width: 80%;
  background-color: white;
  border-radius: 20px;
}

.statusBar{
  height: 100%;
  border-radius: 20px;
  /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#febbbb+0,fe9090+45,ff5c5c+100 */
  background: linear-gradient(to bottom,  #febbbb 0%,#fe9090 45%,#ff5c5c 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */

}



</style>