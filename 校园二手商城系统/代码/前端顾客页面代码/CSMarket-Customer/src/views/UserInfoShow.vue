<template>
  <div class="HomePage">
    <div>
      <el-container>
        <el-aside width="200px">
          <el-aside width="200px" style="background-color: pink">
            <el-menu style="margin-right: 15px">
              <el-menu-item>
                <router-link
                  style="color: black; text-decoration: none"
                  to="/UserInfoShow/PersonalData"
                  >个人资料</router-link
                >
              </el-menu-item>
              <el-menu-item>
                <router-link
                  style="color: black; text-decoration: none"
                  to="/UserInfoShow/AccountSettings"
                  >账号设置</router-link
                >
              </el-menu-item>
              <el-menu-item>隐私设置</el-menu-item>
              <el-menu-item>
                <router-link
                  to="/UserInfoShow/PersonInfo"
                  style="color: black; text-decoration: none"
                >
                  个人信息
                </router-link>
              </el-menu-item>
              <el-menu-item>我的收藏</el-menu-item>
            </el-menu>
          </el-aside>
        </el-aside>
        <el-main style="margin: 0; padding: 0; color: pink">
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄",
    };
    return {
      tableData: Array(20).fill(item),
      user: {
        uid: "",
        userName: "",
        level: "",
      },
    };
  },
  methods: {
    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          console.error(res.data.user);
          _this.user = res.data.user;
          localStorage.setItem("uid", _this.user.uid);
          localStorage.setItem("level", _this.user.level);
        } else {
          _this.$alert("您还未登录系统，请登录系统后查看个人信息！！！", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.$router.push("/UserLogin");
            },
          });
        }
      });
    },
  },
  created() {
    this.isLogin();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-aside {
  background-color: pink;
  color: #333;
  text-align: center;
  height: 800px;
}

.el-main {
  background-color: pink;
  color: #333;
  text-align: center;
  height: 800px;
}

body > .el-container {
  margin-bottom: 80px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
