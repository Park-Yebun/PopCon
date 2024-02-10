import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getMessaging, getToken, onMessage  } from "firebase/messaging";

import App from './App.vue'
import router from './router'
import axios from 'axios'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css';
import '@fortawesome/fontawesome-free/js/all.js'

import 'vue3-geolocation/dist/vue3-geolocation.js'


axios.defaults.baseURL = "http://i10c211.p.ssafy.io:8080"

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
