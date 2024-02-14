importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

firebase.initializeApp({
    apiKey: "AIzaSyDvNw-1CiYp-H2q_RTsM_uM778SsrWj2ZM",
    authDomain: "ssafypopcon.firebaseapp.com",
    projectId: "ssafypopcon",
    storageBucket: "ssafypopcon.appspot.com",
    messagingSenderId: "543956021805",
    appId: "1:543956021805:web:07b2670024d09e5ec9fea4",
    measurementId: "G-5QZKSRXRD2"
});

const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
    // Customize notification here
    // const notificationTitle = payload.notification.title;
    // const notificationOptions = {
    //     body: payload.notification.body,
    //     icon: '/icon.png'
    // };

    // self.registration.showNotification(notificationTitle, notificationOptions);
});

// 추가코드
self.addEventListener("install", function (e) {
    self.skipWaiting();
});
  
self.addEventListener("activate", function (e) {
    console.log("fcm sw activate..");
});

self.addEventListener("notificationclose", function (event) {
    console.log("Notification closed");
    // Add your custom handling for notification close event here
});


self.addEventListener('notificationclick', function(event) {
    event.notification.close(); // 알림 닫기
  
    // 알림 클릭 후 이동할 URL 설정
    var urlToOpen = 'https://i10c211.p.ssafy.io';
  
    // 클라이언트 창이 열려 있으면 포커스를 해당 창으로 이동
    event.waitUntil(
      clients.matchAll({
        type: 'window'
      })
      .then(function(clientList) {
        for (var i = 0; i < clientList.length; i++) {
          var client = clientList[i];
          if (client.url === urlToOpen && 'focus' in client) {
            return client.focus();
          }
        }
        // 클라이언트 창이 없으면 새 창을 열고 해당 URL로 이동
        if (clients.openWindow) {
          return clients.openWindow(urlToOpen);
        }
      })
    );
  });