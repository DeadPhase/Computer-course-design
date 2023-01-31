<template>
  <div class="UserLogin">
    <div class="header">
      <img style="float: left;" src="../assets/img/schoollogo.png"/>
      <img style="margin-left: -600px" src="../assets/img/school.png" />
      <router-link to="/HomePage">回到首页</router-link>
      <img style="float: right" src="../assets/img/myone.png" />
    </div>
    <div class="main">
      <div class="main-left">左边轮播图</div>

      <div class="main-right">
        <div class="main-right-top-logo">
          <div class="my-icon"></div>
        </div>
        <div class="main-right-logo">
          <h1 style="margin-left: -300px">登录</h1>
        </div>
        <div class="main-right-content">
          <el-input placeholder="请输入账号" v-model="username">
            <template slot="prepend">
              <i class="el-icon-user-solid"></i>
            </template>
          </el-input>

          <el-input type="password" placeholder="请输入密码" v-model="password">
            <template slot="prepend">
              <i class="el-icon-lock"></i>
            </template>
          </el-input>

          <div class="footer-login">
            <button class="login_btn" @click="Login()">登录</button>
          </div>
          <div class="setting">
            <a href="javascript:void(0)" class="downapp">下载应用</a>
            &nbsp;|&nbsp;
            <a href="javascipt:void(0)">忘记密码</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserLogin",
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    Login() {
      const _this = this;
      const params = {
        username: this.username,
        password:this.password
      }
      console.error("登录用户名和账号："+this.username+","+this.password)
      this.$axios.Login(params).then(function (res) {
        if (res.code == 200) {
          console.error(res.data);
          localStorage.setItem("user", JSON.stringify(res.data.user));

          _this.$router.push("/HomePage");
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.UserLogin {
  background: url("../assets/img/schoolm2.jpg") center center no-repeat;
  width: 100%;
  min-height: 100vh;
  background-size: 100% 100%;

}

.header {
  /* width: 100%;
  height: 117px;
  background: white; */

  background: #fff;
  padding: 20px 20px 20px 50px;
}
.main {
  width: 100%;
  display: flex;
  justify-content: right;
  position: relative;
}
.main-left {
  float: left;
}

.main-right-top-logo {
  position: absolute;
  left: 5px;
  top: 5px;
  width: 46px;
  height: 46px;
  z-index: 2;
  cursor: pointer;
}
.my-icon {
  display: inline-block;
  vertical-align: middle;
  width: 40px;
  height: 40px;
  background: url(../assets/img/lqrcode.png) 50% no-repeat;
  -webkit-background-size: 100% 100%;
  background-size: 100% 100%;
}
.main-right {
  border: 2px solid beige;
  float: right;
  height: 380px;
  width: 430px;
  margin-top: 250px;
  margin-right: 50pt;
  background-color: white;
  border-radius: 4px;
  position: absolute;
  opacity: 0.9;
}

.footer-login {
  display: flex;
  justify-content: center;

  width: 80%;
  height: 46px;
  margin-top: 30px;
  margin-left: 10%;
  position: relative;
  background-color: #367bf0;
}

.login_btn {
  width: 100%;
  height: 46px;
  letter-spacing: 10px;
  font-size: 20px;
  background: #367bf0;
  border: none;
  outline: none;
  color: white;
  cursor: pointer;
}

.setting {
  text-align: center;
  /* margin: 5px 7%; */
  height: 38px;
  line-height: 35px;
  color: #d2d2d2;
  font-size: 14px;
  position: relative;
}

a {
  color: #007bff;
  text-decoration: none;
  background-color: transparent;
}

.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.el-input-group {
  width: 80%;
}
.el-input {
  margin-top: 20px;
}
</style>
