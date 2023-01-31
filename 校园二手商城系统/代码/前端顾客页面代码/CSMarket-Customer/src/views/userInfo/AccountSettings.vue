<template>
  <div class="PersonalData">
    <div class="header">
      <span class="header_shield">100</span>
      <span class="header_title">低风险</span>
    </div>

    <div class="content">
      <div style="overflow: hidden">
        <span
          style="
            float: left;
            font-size: 24px;
            margin-top: 20px;
            margin-left: 10px;
          "
          >账号设置</span
        >
      </div>
      <ul style="border: 0; font-size: 100%; vertical-align: baseline">
        <li
          style="
            height: 42px;
            padding: 32px 0 16px;
            color: #555666;
            border-bottom: 2px solid red;
          "
        >
          <span class="myspan"> 密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码 </span>
          <p
            style="
              float: left;
              color: #222226;
              margin-top: -22px;
              text-align: right;
              width: calc(100% - 52px);
            "
          >
            <button class="zyz-button" @click="changePwd()">修改密码</button>
          </p>
        </li>

        <li
          style="
            height: 42px;
            padding: 32px 0 16px;
            color: #555666;
            border-bottom: 2px solid red;
          "
        >
          <span class="myspan"> 手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机 </span>
          <p
            style="
              float: left;
              color: #222226;
              margin-top: -22px;
              text-align: right;
              width: calc(100% - 52px);
            "
          >
            <span class="zyz-button-span">{{ phone | phoneFilter }}</span>
            <button class="zyz-button">
              <button class="zyz-button" @click="changePhone()">
                修改号码
              </button>
            </button>
          </p>
        </li>
        <li style="height: 42px; padding: 32px 0 16px; color: #555666">
          <span class="myspan"> 邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱 </span>
          <p
            style="
              float: left;
              color: #222226;
              margin-top: -22px;
              text-align: right;
              width: calc(100% - 52px);
            "
          >
            <span class="zyz-button-span">{{ mailbox | mailboxFileter }}</span>
            <button class="zyz-button">
              <button class="zyz-button" @click="changeMailbox()">
                修改邮箱
              </button>
            </button>
          </p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "PersonalData",
  data() {
    return {
      phone: "",
      mailbox: "",

      user: {
        uid: "",
        phone: "",
        email: "",
      },
    };
  },
  methods: {
    //修改号码
    changePhone() {
      this.$router.push({
        name: "修改号码",
        params: {
          phone: this.phone,
        },
      });
    },
    changeMailbox() {
      this.$router.push({
        name: "修改邮箱",
        params: {
          mailbox: this.mailbox,
        },
      });
    },
    changePwd() {
      const oldPwd = "123456";
      this.$router.push({
        name: "修改密码",
        params: {
          oldPwd: oldPwd,
        },
      });
    },

    //判断用户是否登录
    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          console.error(res.data.user);
          // _this.user = res.data.user;
          _this.phone = res.data.user.phone;
          _this.mailbox = res.data.user.email
        } else {
          _this.$alert("您还未登录系统，请登录系统！！！", {
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
  filters: {
    //手机号过滤器
    phoneFilter(val) {
      let reg = /^(.{3}).*(.{4})$/;
      return val.replace(reg, "$1****$2");
    },
    //邮箱过滤器
    mailboxFileter(val) {
      let reg = /^(.{3}).*(.{9})$/;
      return val.replace(reg, "$1****$2");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.zyz-button-span {
  margin-right: 15px;
}
.zyz-button {
  border: none;
  color: #1989fa;
  background-color: white;
  cursor: pointer;
  font-size: 16px;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
.myspan {
  float: left;
  width: 56px;
  height: 24px;
  display: flex;
  justify-content: space-between;
  padding-left: -5px;
  box-sizing: content-box;
}

a {
  text-decoration: none;
  cursor: pointer;
}
ul,
li {
  list-style: none;
}
.header {
  width: 100%;
  height: 208px;
  padding-top: 24px;
  margin-bottom: 10px;
  background: url("../../assets/img/user_header_logo.png") center center
    no-repeat;
}

.header_shield {
  display: block;
  width: 83px;
  height: 100px;
  line-height: 100px;
  margin: auto;
  background: url("../../assets/img/logo_header.png") no-repeat 50%;
  background-size: cover;
  color: #fff;
  font-size: 40px;
}
.header_title {
  display: inline-block;
  min-width: 40px;
  padding: 0 8px;
  height: 20px;
  line-height: 20px;
  margin: 8px auto;
  background: #fc1944;
  border-radius: 12px;
  color: #fff;
}

.content {
  width: 100%;
  min-height: 200px;
  background: #fff;
  font-size: 14px;
}

.content-title {
  min-width: 72px;
  height: 24px;
  font-size: 18px;
  font-weight: 600px;
  color: #2e2e2e;
  line-height: 24px;
}
</style>
