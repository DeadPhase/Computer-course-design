<template>
  <div>
    <el-container class="home_container" style="height: 800px">
      <el-header>
        <div>
          <span class="header-left">后台管理系统</span>
        </div>
        <div class="header-right" style="margin-right: -70%">
          <div class="header-right-left" style="float: left">
            <span>当前登录用户</span>：
          </div>

          <div class="header-right-rigth" style="float: right">
            <el-popconfirm
              confirm-button-text="确定"
              cancel-button-text="取消"
              icon="el-icon-info"
              icon-color="red"
              title="确定退出账号吗？"
              @confirm="logout()"
            >
              <el-button slot="reference" size="medium" type="primary"
                >退出登录</el-button
              >
            </el-popconfirm>
          </div>
        </div>
        <div></div>
      </el-header>

      <el-container>
        <el-aside width="150px">
          <el-menu :default-openeds="['1', '2']">
            <el-submenu index="1">
              <template slot="title"
                ><i class="el-icon-s-home"></i>首页信息</template
              >
              <el-menu-item index="1-1">
                <router-link
                  to="/HomePage"
                  style="color: black; text-decoration: none"
                  >首页信息</router-link
                >
              </el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"
                ><i class="el-icon-menu"></i>信息展示</template
              >
              <el-menu-item index="2-1">
                <router-link
                  to="/AdminInfo"
                  style="color: black; text-decoration: none"
                  >管理员信息</router-link
                >
              </el-menu-item>
              <el-menu-item index="2-2">
                <router-link
                  to="/UserInfo"
                  style="color: black; text-decoration: none"
                  >用户信息</router-link
                >
              </el-menu-item>
              <el-menu-item index="2-3">
                <router-link
                  to="/TypeInfo"
                  style="color: black; text-decoration: none"
                  >化妆品种类</router-link
                >
              </el-menu-item>
              <el-menu-item index="2-4">
                <router-link
                  to="/GoodsInfo"
                  style="color: black; text-decoration: none"
                  >商品详情</router-link
                >
              </el-menu-item>
                  <el-menu-item index="2-5">
                <router-link
                  to="/OrderInfo"
                  style="color: black; text-decoration: none"
                  >订单详情</router-link
                >
              </el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"
                ><i class="el-icon-setting"></i>库存管理</template
              >

              <el-menu-item index="3-1">货号信息</el-menu-item>
              <el-menu-item index="3-2">入库信息</el-menu-item>
              <el-menu-item index="3-3">入库详情</el-menu-item>

              <el-menu-item index="3-4">出库信息</el-menu-item>
              <el-menu-item index="3-4">出库详情</el-menu-item>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title"
                ><i class="el-icon-unlock"></i>辅助管理</template
              >

              <el-menu-item index="3-1"
                ><span><i class="el-icon-unlock"></i></span>
                修改密码</el-menu-item
              >
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      isCollapse: false,
    };
  },
  methods: {
    logout() {
      this.$store.commit("logout");
      this.$router.push("/login");
    },
  },

  computed: {},
};
</script>



<style scoped>
/* 去除router-link的下划线 */
/* .router-link-active {
  text-decoration: none;
} */
@import "../../assets/css/base.css";
</style>
