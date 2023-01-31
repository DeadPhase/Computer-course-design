<template>
  <div class="homePage">
    <!-- 搜索开始 -->

    <div class="container">
      <div style="height: 80px; display: flex; align-items: center">
        <input
          type="text"
          style="
            border: 1px solid black;
            height: 10px;
            width: 30%;
            padding: 8px 4px;
            margin-left: 80px;
            font-size: 12px;
            border-radius: 5px;
          "
          placeholder="搜索化妆品"
        />
        <button
          style="
            padding: 5px;
            background-color: black;
            color: white;
            border: none;
            border-radius: 5px;
            margin-left: 5px;
          "
          @click="searchGoods(searchText)"
        >
          搜索化妆品
        </button>
        <span style="margin-left: 100px"
          ><b style="color: black; font-size: 14px"
            >24小时客服热线：400-880-9988</b
          ></span
        >
      </div>
    </div>
    <!-- 搜索结束 -->

    <!-- 主题开始 -->
    <div style="background-color: pink">
      <!-- 主体开始 -->
      <!-- 轮播图组件 -->
      <MySwiper></MySwiper>
      <!-- <div class="swiper-container">
          <div class="swiper-wrapper">
            <div class="swiper-slide" v-for="(item, i) in images" :key="i">
              <img class="carousel-img" :src="item.img" alt="" />
            </div>
          </div> -->
      <!-- 如果需要分页器 -->
      <!-- <div class="swiper-pagination"></div> -->
      <!-- 如果需要导航按钮 -->
      <!-- <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div> -->

      <el-row>
        <el-col :span="12"
          ><div class="grid-content bg-purple">
            <!-- 热卖 -->
            <div>
              <div
                style="
                  color: rebeccapurple;
                  border-bottom: 1px solid #ccc;
                  font-size: 20px;
                  padding: 10px;
                  margin-left: -500px;
                "
              >
                <b>热卖化妆品</b>
              </div>
              <ul
                class="goods-box"
                style="padding: 0; margin: 0; overflow: hidden;"
              >
                <li
                  v-for="item in hots"
                  :key="item.id"
                  style="
                    width: 20%;
                    padding: 10px;
                    margin: 0;
                    display: inline-block;
                    float: left;
                  "
                >
                  <router-link
                    :to="{
                      name: '商品详情',
                      params: {
                        id: item.id,
                        name: item.name,
                      },
                    }"
                  >
                    <img
                      :src="item.fileName"
                      style="
                        width: 100%;
                        display: flex;
                        justify-content: right;

                        height: 110px;
                      "
                    />
                    <p style="text-align: center; color: #666; font-size: 12px">
                      {{ item.name }}
                    </p>
                    <div
                      style="text-align: center; color: red; font-size: 12px"
                    >
                      ￥ {{ item.price }}
                    </div>
                  </router-link>
                </li>
              </ul>
            </div>
            <!-- 推荐 -->
            <div >
              <div
                style="
                  color: darkorange;
                  border-bottom: 1px solid #ccc;
                  font-size: 20px;
                  padding: 10px;
                  margin-left: -530px;
                "
              >
                <b>系统推荐</b>
              </div>
         
              <ul
                class="goods-box"
                style="padding: 0; margin: 0; overflow: hidden;"
              >
                <li
                  v-for="item in hots"
                  :key="item.id"
                  style="
                    width: 20%;
                    margin: 0;
                    padding: 10px;
                    display: inline-block;
                    float: left;
                  "
                >
                  <a
                    :href="'/front/goodsInfo.html?goodsId=' + item.id"
                    target="_blank"
                  >
                    <img
                      :src="item.fileName"
                      style="width: 100%; height: 110px"
                    />
                    <p style="text-align: center; color: #666; font-size: 12px">
                      {{ item.name }}
                    </p>
                    <div
                      style="text-align: center; color: red; font-size: 12px"
                    >
                      ￥ {{ item.price }}
                    </div>
                  </a>
                </li>
              </ul>
            </div>
          </div></el-col
        >
        <el-col :span="12"
          ><div class="grid-content bg-purple-light">
            <div
              style="
                color: orangered;
                border-bottom: 1px solid #ccc;
                font-size: 20px;
                padding: 10px;
                margin-left: -510px;
              "
            >
              <b>化妆品分类</b>
            </div>
            <div>
              <div
                style="
                  flex: 1;
                  text-align: center;
                  cursor: pointer;
                  border-radius: 5px;
                  border-right: 1px solid white;
                "
                v-for="(item, index) in typeObjs"
                :key="item.id"
                :class="{ active: activeIndex === index }"
                @click="getIndexAndGoods(item.id, index)"
              >
                {{ item.name }}
              </div>
            </div>
            <ul
              class="goods-box"
              style="padding: 0; margin: 0; overflow: hidden"
            >
              <li
                v-for="item in hots"
                :key="item.id"
                style="
                  width: 20%;
                  margin: 0;
                  padding: 10px;
                  display: inline-block;
                  float: left;
                "
              >
                <a
                  :href="'/front/goodsInfo.html?goodsId=' + item.id"
                  target="_blank"
                >
                  <img
                    :src="item.fileName"
                    style="width: 100%; height: 110px"
                  />
                  <p style="text-align: center; color: #666; font-size: 12px">
                    {{ item.name }}
                  </p>
                  <div style="text-align: center; color: red; font-size: 12px">
                    ￥ {{ item.price }}
                  </div>
                </a>
              </li>
            </ul>
          </div></el-col
        >
      </el-row>
    </div>
    <!-- 主题结束 -->
  </div>
</template>

<script>
import $ from "jquery";
// import Swiper from "swiper";
// import "swiper/css/swiper.min.css";
import MySwiper from "@/components/MySwiper";
export default {
  name: "HomePage",
  components: {
    MySwiper,
  },
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      hots: [],
      recommends: [],
      typeObjs: [],
      goodsList: [],
      // images: [
      //   { img: "https://www.baidu.com/img/baidu_jgylogo3.gif" },
      //   { img: "http://localhost:8282/images/21667218837206.jpg" },
      //   { img: "http://localhost:8282/images/21667218837206.jpg" },
      // ],
    };
  },
  methods: {
    showGoods() {
      const _this = this;
      this.$axios.findAllGoodsInfo().then((res) => {
        if (res.code === 200) {
          console.log("findAllGoodsInfo");
          console.log(res.data.result.list);
          this.hots = res.data.result.list;
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
  },
  created() {
    this.showGoods();
  },
};
</script>

<style scoped>
/* .swiper-container .swiper-button-hidden {
  opacity: 0;
} */
/* .carousel-img {
  width: 100%;
  height: 100%;
}
.swiper-container {
  height: 350px;
  width: 95%;
} */
/*中间的过度的横线*/

.el-menu.el-menu--horizontal {
  border: none;
}
.el-row {
  margin-bottom: 20px;
  background-color: pink;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
}

.el-header,
.el-footer {
  background-color: #b3c0d1;

  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.clearfix:after {
  /*伪元素是行内元素 正常浏览器清除浮动方法*/
  content: "";
  display: block;
  height: 0;
  clear: both;
  visibility: hidden;
}
</style>