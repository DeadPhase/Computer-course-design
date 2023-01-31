<template>
  <div class="swiper-container">
    <div class="swiper-wrapper">
      <div class="swiper-slide" v-for="(item, i) in images" :key="i">
        <img class="carousel-img" :src="item.img" alt="" />
      </div>
    </div>
    <!-- 如果需要分页器 -->
    <div class="swiper-pagination"></div>
    <!-- 如果需要导航按钮 -->
    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>
  </div>
</template>

<script>
import Swiper from "swiper";
import "swiper/css/swiper.min.css";
export default {
  name: "MySwiper",
  data() {
    return {
      images: [
        { img: "https://www.baidu.com/img/baidu_jgylogo3.gif" },
        { img: "http://localhost:8282/images/21667218837206.jpg" },
        { img: "http://localhost:8282/images/21667218837206.jpg" },
      ],
    };
  },
  mounted() {
    var mySwiper = new Swiper(".swiper-container", {
      autoplay: {
        delay: 5000,
        disableOnInteraction: false,
      }, //可选选项，自动滑动

      loop: true, // 循环模式选项
      speed: 4000,

      // 如果需要分页器
      pagination: {
        el: ".swiper-pagination",
        clickable: true,
      },

      // 如果需要前进后退按钮
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
        disabledClass: "my-button-disabled",
      },
    });
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.carousel-img {
  width: 100%;
  height: 100%;
}

.swiper-container {
  height: 350px;
  width: 98%;
}
</style>