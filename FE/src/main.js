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

axios.defaults.baseURL = "https://i10c211.p.ssafy.io:8085"
// axios.defaults.baseURL = "http://localhost:8080"
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
  
  // if ("serviceWorker" in navigator) {
  //       navigator.serviceWorker
  //         .register("firebase-messaging-sw.js")
  //         .then(function (registration) {
  //           console.log("ServiceWorker registration successful with scope: ");
  //         });
  //     }

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

