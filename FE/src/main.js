import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css';
import '@fortawesome/fontawesome-free/js/all.js'

import 'vue3-geolocation/dist/vue3-geolocation.js'


const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
