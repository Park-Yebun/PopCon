import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css';
import '@fortawesome/fontawesome-free/js/all.js'

import 'vue3-geolocation/dist/vue3-geolocation.js'

import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken, onMessage  } from "firebase/messaging";
import axios from 'axios'
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
  // navigator.serviceWorker
  // .register("registerSW.js")
  // .then(function (registration) {
  //   console.log("Service worker successfully registered.");
  //   return registration;
  // })
  // .catch(function (err) {
  //   console.error("Unable to register service worker.", err);
  // });

  navigator.serviceWorker
      .register("firebase-messaging-sw.js")
      .then(function (registration) {
        console.log("Service worker successfully registered.");
        return registration;
      })
      .catch(function (err) {
        console.error("Unable to register service worker.", err);
      });

  
  const firebaseConfig = {
    apiKey: "AIzaSyDvNw-1CiYp-H2q_RTsM_uM778SsrWj2ZM",
    authDomain: "ssafypopcon.firebaseapp.com",
    projectId: "ssafypopcon",
    storageBucket: "ssafypopcon.appspot.com",
    messagingSenderId: "543956021805",
    appId: "1:543956021805:web:07b2670024d09e5ec9fea4",
    measurementId: "G-5QZKSRXRD2"
  };
  
  
  const app1 = initializeApp(firebaseConfig);
  const analytics = getAnalytics(app1);
  
  const messaging = getMessaging();

  axios.defaults.baseURL = "http://localhost:8080"
  
  onMessage(messaging, (payload) => {
    console.log('Message received. ', payload);
  
      //  // Customize notification here
      // const notificationTitle = payload.notification.title;
      // const notificationOptions = {
      //   body: payload.notification.title.body,
      //   icon: '/icon.png'
      // };
    
      // self.registration.showNotification(notificationTitle, notificationOptions);
  
  
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
          console.log("Token is:",currentToken);
          // ...
      } else {
          // Show permission request UI
          console.log('No registration token available. Request permission to generate one.');
          // ...
      }
  
  }).catch((err) => {
      console.log('An error occurred while retrieving token. ', err);
      // ...
  });
  if ("serviceWorker" in navigator) {
        navigator.serviceWorker
          .register("firebase-messaging-sw.js")
          .then(function (registration) {
            console.log("ServiceWorker registration successful with scope: ");
          });
      }



const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
