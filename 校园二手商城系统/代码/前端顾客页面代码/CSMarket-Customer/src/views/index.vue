<template>
  <div>
    <el-container>
      <el-header style="background-color: pink; width: 80%; margin: auto">
        <div>
          <el-row :gutter="20" style="background-color: background-color: pink">
            <el-col :span="4"
              ><div class="grid-content bg-purple">
                <span style="font-size: 25px; color: red; font-weight: bold"
                  >校园二手市场</span
                >
              </div></el-col
            >
            <el-col :span="2">
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="18"
              ><div class="grid-content bg-purple">
                <el-menu
                  :default-active="activeIndex"
                  class="el-menu-demo"
                  style="background-color: pink"
                  mode="horizontal"
                  @select="handleSelect"
                >
                  <el-menu-item index="1">
                    <router-link
                      to="/HomePage"
                      style="color: black; text-decoration: none"
                      >首页</router-link
                    >
                  </el-menu-item>
                  <el-menu-item index="2">
                    <router-link
                      to="/AnnouncementInfo"
                      style="color: black; text-decoration: none"
                      >公告信息</router-link
                    >
                  </el-menu-item>
                  <el-menu-item index="3">
                    <router-link
                      to="/CommunicateOnline"
                      style="color: black; text-decoration: none"
                      >在线交流</router-link
                    >
                  </el-menu-item>
                  <el-menu-item index="4">
                    <router-link
                      to="/ShopCarInfo"
                      style="color: black; text-decoration: none"
                      >购物车信息</router-link
                    >
                  </el-menu-item>
                  <el-menu-item index="5">
                    <router-link
                      to="/OrderInfo"
                      style="color: black; text-decoration: none"
                      >订单信息</router-link
                    >
                  </el-menu-item>
                  <el-menu-item index="6">
                    <router-link
                      to="/ReviewInfo"
                      style="color: black; text-decoration: none"
                    >
                      评价信息
                    </router-link>
                  </el-menu-item>
                  <el-menu-item index="7">
                    <router-link
                      to="/UserInfoShow"
                      style="color: black; text-decoration: none"
                      >个人信息</router-link
                    >
                  </el-menu-item>

                  <el-menu-item index="8">
                    <span v-if="user.userName">欢迎 {{ user.userName }}</span>
                  </el-menu-item>
                </el-menu>
              </div></el-col
            >
          </el-row>
        </div>
      </el-header>
      <div class="myLine"></div>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import $ from "jquery";
// import Swiper from "swiper";
// import "swiper/css/swiper.min.css";
import MySwiper from "@/components/MySwiper";
export default {
  name: "HelloWorld",
  components: {
    MySwiper,
  },
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      user: {
        userName: "",
      },
    };
  },
  methods: {
    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          _this.user = res.data.user;
        } else {
          // _this.$message.error(res.data.errMessage);
          // _this.$router.push("/Login");
        }
      });
    },

    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
  },
  created() {
    this.isLogin();
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
.nav-item {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
a.nav-item-active {
  color: red;
}

.nav-item:hover {
  /*border-bottom: 4px solid #9098a7;*/
  color: blue;
  /*transition: all 0.2s;*/
}

/* .el-menu-item{
  flex:1;
  display: flex;
  justify-content: center;
  align-items: center;

} */
.el-main {
  background-color: #e6e6fa;
  width: 80%;
  margin: auto;
}
.myLine {
  width: 100%;
  height: 5px;
  border-top: solid #db7093 1px;
  margin-top: 1px;
  background-color: #db7093;
}

.el-menu.el-menu--horizontal {
  border: none;
}
.el-row {
  margin-bottom: 20px;
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
  background-color: pink;
  color: pink;
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
</style>

