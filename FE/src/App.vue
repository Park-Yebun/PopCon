
<template>
  <div class="container">
    <RouterView class="router-view"/>
    <nav class="navbar">
      <div class="container-fluid">
        <div class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_home.png" alt="홈 아이콘">
          <div class="nav-text">Home</div>
        </div>
        <div class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_map.png" alt="지도 아이콘">
          <div class="nav-text">Map</div>
        </div>
        <div class="nav-item">
          <img class="nav-icon-camera" src="@/assets/images/nav_camera.png" alt="카메라 아이콘">
        </div>
        <div class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_rec.png" alt="추천 아이콘">
          <div class="nav-text">Rec</div>
        </div>
        <div class="nav-item">
          <img class="nav-icon" src="@/assets/images/nav_others.png" alt="더보기 아이콘">
          <div class="nav-text">Others</div>
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.container {
  max-width: 360px;
  position:relative;
  margin-bottom: 88px; /* navbar의 높이만큼 margin-bottom 추가 */
}


.navbar {
  width: 360px;
  height: 88px;
  box-shadow: 0px -3px 8px rgba(156.74, 177.69, 213.56, 0.13);

  position: fixed;
  width: 100%;
  bottom: 0;
  left: 0;
  z-index: 1000;
}

.container-fluid {
  width: 331.17px;
  height: 46px;
}

.nav-item {
  justify-content: center;
}
.nav-text {
  color: #2C3550;
  text-align: center;
  font-family: ABeeZee;
  font-size: 12px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;
}
.nav-icon {
  width: 25.15px;
  height: 24px;
}
.nav-icon-camera {
  width: 48.21px;
  height: 46px;
  filter: drop-shadow(0px 8px 20px rgba(74, 67, 236, 0.25));
}
</style>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { onMounted } from 'vue';

onMounted(()=>{

  navigator.serviceWorker
        .register("registerSW.js")
        .then(function (registration) {
          console.log("Service worker successfully registered.");
          return registration;
        })
        .catch(function (err) {
          console.error("Unable to register service worker.", err);
        });
})
</script>
