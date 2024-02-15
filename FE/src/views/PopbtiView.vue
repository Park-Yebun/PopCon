<template>
  <div class="container">
    <section v-show="showMain" id="main" class="mx-auto my-5 py-5 px-3" :class="{ 'fade-out': !showMain }">
      <div class="main-inner-container">
        <p style="font-size:large; font-weight:bold;"> POPBTI로 알아보는</p>
        <p style="font-size:large; font-weight:bold;">✨ 나의 팝업 취향 ✨</p>
        <div class="col-lg-6 col-md-6 col-sm-6 cpl-12 mx-auto" style="margin-top:40px;">
          <img src="@/assets/images/pop-up-store.png" alt="mainImage" class="img-fluid">
        </div>
        <p style="margin-top: 40px; ">
          POPBTI로 당신의 취향 저격 팝업을
        </p>
        <p> 찾아보세요!</p>
        <!-- <div @click="begin" class="startBtn">시작하기</div> -->
      </div>
      <button @click="begin" class="startBtn">시작하기</button>
    </section>


    <section v-show="showQna" id="qna" :class="{ 'fade-in': showQna, 'fade-out': !showQna }" >
      <div class="status mx-auto">
        <div class="statusBar">
        </div>
      </div>
        <div class="qBox my-5 py-3 mx-auto">

        </div>
        <div class="answerBox">

        </div>
    </section>


    <section v-show="showResult" id="result" class="mx-auto py-5 px-3" :class="{ 'fade-in': showResult, 'fade-out': !showResult }">
      <p style="font-size:25px; font-weight:bold;" >당신의 결과는?! 🍭</p>
      <div class="resultname">
      </div>

      <div id="resultImg" class="my-3 col-lg-6 col-md-6 col-sm-6 cpl-12 mx-auto">
      </div>
      
      <div class="resultDesc">
      </div>

      <p style="font-weight:bold; font-size:15.5px">🏬 당신에게 추천드리는 팝업스토어 🏬</p>
      <div>
      <div title="recpopup" class="popup-group">
        <div v-for="popup in recpopup" class="popup" key="popup">
          <img @click=goPopupDetail(popup.popupId) :src="popup.previewImage" class="popup-img" alt="추천팝업이미지">
          <p class="popup-title" style="font-weight:bold; padding:5px">{{popup.popupName}}</p>
        </div>
      </div>
    </div>

    <div class="buttons">
      <button @click="setShare" type="button" class="kakao mt-3 py-2 px-3" style="background-color: #FF534C;">공유하기</button>
      <button @click="goBack" type="button" class="kakao mt-3 py-2 px-3">돌아가기</button>
    </div>
    </section>


  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { popbti } from '@/api/popup'
import { getMessaging, getToken, onMessage  } from "firebase/messaging";
import axios from 'axios';
import { walkIdentifiers } from 'vue/compiler-sfc';

const router = useRouter()
const showMain = ref(true);
const showQna = ref(false);
const showResult = ref(false);
const endPoint = 4;
const select = [];
// endPoint는 질문의 개수

const recpopup = ref()

const fcmToken = ref()



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
  


const setResult = () => {
  let point = calResult();
  const resultName = document.querySelector('.resultname');
  resultName.innerHTML = infoList[point].name;
  resultName.style.fontWeight='bold';

  // mbti 포인트 쿠키 세션에 저장하기
  const setCookie = function(name, value, exp) { 
    var date = new Date()
    date.setTime(date.getTime() + exp*24*60*60*1000)
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/'
  }
  // 쿠키이름, 쿠키값, 기간 설정
  setCookie("mbtiResult", point, 1)

  var resultImg = document.createElement('img');
  const imgDiv = document.querySelector('#resultImg');
  var imgURL = 'https://popcon-s3-bucket.s3.ap-southeast-2.amazonaws.com/mbti/popbti-img-' + point + '.svg';
  
  // console.log(imgURL)
  resultImg.src = imgURL;
  resultImg.alt = point;

  
// 이미지 스타일 설정
  resultImg.style.width = '150px'; // 이미지 너비를 200px로 설정
  resultImg.style.height = '150px'; // 이미지 높이를 자동으로 설정

  // resultImg.classList.add('img-fluid');
  imgDiv.appendChild(resultImg);

  const resultDesc = document.querySelector('.resultDesc');
  resultDesc.innerHTML = infoList[point].desc;
  resultDesc.style.margin='30px 10px';

  console.log(point)
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

const goPopupDetail = (popupId) => {
  router.push(`/popup/${popupId}`)
}
 
const addAnswer = (answerText, qIdx, idx) => {
  var a = document.querySelector('.answerBox');
  var answer = document.createElement('button');
  answer.classList.add('my-3');
  answer.classList.add('py-3');
  // answer.classList.add('mx-auto');
  answer.classList.add('answerList');
  answer.classList.add('fade-in');

  // 스타일을 추가합니다.
  answer.style.backgroundColor = 'whitesmoke'; // 배경색 지정
  // answer.style.color = 'gray'; // 글자색 지정
  answer.style.padding = '10px 20px'; // 내부 여백 지정
  answer.style.border = 'none'; // 테두리 없애기
  answer.style.borderRadius = '20px'; // 모서리 둥글게 만들기
  answer.style.cursor = 'pointer'; // 마우스 커서 모양 변경
  
  answer.addEventListener('mouseenter', function() {
    answer.style.border = '2px solid #FF534C';
  });


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

// FCM 메세지 수신 설정
const messaging = getMessaging();
onMessage(messaging, (payload) => {
    console.log('Message received. ', payload);
      const notificationTitle = payload.notification.title;
      const notificationOptions = {
        body: payload.notification.title.body,
      };
    
      self.registration.showNotification(notificationTitle, notificationOptions);
  
  
    let notificationPermission = Notification.permission;
  
    if (notificationPermission === "granted") {
              //Notification을 이미 허용한 사람들에게 보여주는 알람창
             new Notification(payload.notification.title,{
                  body:payload.notification.body,
                  icon: '/icon.png',
                  image:payload.notification.image
                      });
          } else if (notificationPermission !== 'denied') {
              //Notification을 거부했을 경우 재 허용 창 띄우기
              Notification.requestPermission(function (permission) {
                  if (permission === "granted") {
                    new Notification(payload.notification.title, {
                  body:payload.notification.body
                      });
                  }else {
                      alert("알람 허용이 거부되었습니다.")
                  }
              });
          }
  });
  
  getToken(messaging, { vapidKey: 'BJK9lVeFIvJ5u3jvtWKGabTSNOqbX69MT2m2gbl110ZDyvUFsvpkKKHRKZRd4wEdjopFz_NxuGgfZoET1kTeqGs' }).then((currentToken) => {
      if (currentToken) {
          // Send the token to your server and update the UI if necessary
          fcmToken.value = currentToken
          // ...
      } else {
          // Show permission request UI
          console.log('토큰 없음');
          // ...
      }
  
  }).catch((err) => {
      console.log('토큰 가져오기 오류', err);
      // ...
  });
  // if ("serviceWorker" in navigator) {
  //       navigator.serviceWorker
  //         .register("firebase-messaging-sw.js")
  //         .then(function (registration) {
  //           console.log("ServiceWorker registration successful with scope: ");
  //         });
  //     }


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

const url = 'https://i10c211.p.ssafy.io'
// 배포 후 URL 수정
const setShare = () => {
  console.log("setShare");
  var resultImg = document.querySelector('#resultImg');
  var resultAlt = resultImg.firstElementChild.alt;
  // resultAlt는 MBTI 코드
  const shareTitle = 'POPBTI 테스트 결과'
  const shareDes = infoList[resultAlt].name;
  // console.log(resultAlt + '**') 
  const shareImage = '/@/assets/images/popbti-img/popbti-img-' + resultAlt + '.svg';
  const shareURL = url + '/popbti/' + resultAlt;
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

  setTimeout(function(){
    axios.post("https://fcm.googleapis.com/fcm/send",
      {
        "notification": {
          "body": "테스트입니다. 내용",
          "title": "테스트입니다. 제목",
          "click_action": "https://i10c211.p.ssafy.io/",
        },
        "to": fcmToken.value,
      },
      {
        headers: {
          "Content-type": "application/json",
          "Authorization": "Bearer AAAAfqZOMi0:APA91bHvDTS4BwItOV4czAPBV-me02F6I2LsBPY7qgnDROrC0kkj6wsHWz6Y4YI2YBVsizzWV7mOXxWcNvV47gL9WLIRr-NvhdQOXEWOoVxs1pth4dGXUx7t2J-krufxZJbYzegpmHIt"
        },
      }
    )
    .then((response) => {
      console.log(response.data, response.config.data);
    })
  }, 15000)
}


// 결과 화면 나온 후 다시 추천 페이지로 돌아가기
const goBack = function() {
  router.push({ name: 'recommend' })
}

</script>


<style scoped>
.popup-group {
  margin-top: 5.31px;
  min-width: 201.78px;
  height: 170px;
  overflow-x: scroll;
  white-space: nowrap;
  display: flex;
  -ms-overflow-style: none;
}

.popup-group::-webkit-scrollbar{
  display:none;
}
.popup-title{
  width: 120px;
  font-size: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color:gray;
}
.popup-img {
  width: 120px;
  height: 120px;
  border-radius: 20px;
  padding-left: 10px;
  padding-right: 5px;
}
.container {
  width: 360px;
  height: 800px;
  background-color: pink;
  position: relative; 
}

#main {
  background-color: whitesmoke;
  margin-top:10%;
  width: 90%;
  height: 800px;
  text-align: center;
  /* border-radius: 20px; */
  opacity: 1;
  /* height:90%; */
  /* position:absolute; */
}

#result {
  background-color: whitesmoke;
  width: 90%;
  height: 800px;
  text-align: center;
  /* border-radius: 20px; */
  opacity: 1;
  /* display:flex;
  flex-direction: column; */
}
.resultname{
  font-size: 16px;
  margin: 10px;
}
.resultDesc{
  font-size: 14px;
}
.kakao{
  color: white;
  background-color: #FEE500;
  font-size: 15px;
  border: 0px;
  border-radius: 20px;

}

/* .kakao:hover, .kakao:focus{
  background-color: whitesmoke;
  color: #FEE500;
  width: 80%;

} */
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
  font-size: 20px;
  padding: 20px;
  font-weight: 600;
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
  margin-top: 10%;
  height: 100%;
  border-radius: 20px;
  /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#febbbb+0,fe9090+45,ff5c5c+100 */
  background: linear-gradient(to bottom,  #febbbb 0%,#fe9090 45%,#ff5c5c 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */

}

.startBtn{
  background-color: #ff534c;
  border: none;
  border-radius: 20px;
  color:white;
  padding: 5px 10px;
  font-size: 15px;
  width:100px;
  height: 40px;
  /* display: flex; */
  align-items: center;
  justify-content: center;
  /* font-weight: bold; */
}

.buttons {
  display:flex;
  flex-direction: row;
  justify-content: space-evenly; 
}

#qna{
  /* background-color: green; */
  padding: 10px;
}


.router-view{
  margin:0;
}
</style>