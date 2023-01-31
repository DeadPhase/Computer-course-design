<template>
  <div class="Phone">
    <div class="zyz-header">
      <div style="float: left">
        <span>
          <router-link
            class="normal"
            active-class="active"
            to="/UserInfoShow/AccountSettings"
            >账号设置</router-link
          >
        </span>
        /
        <span>修改号码</span>
      </div>

      <!-- <span style="color: red">修改密码:{{ this.$route.params.oldPwd }}</span> -->
    </div>
    <div class="zyz-content">
      <el-form
        ref="form"
        :rules="rules"
        :model="form"
        label-width="85px"
        style="width: 35%; margin: auto"
      >
        <div style="margin: 0px; padding: 0px; overflow: hidden">
          <span class="zyz-content-span"
            >验证码将发送到手机{{ phone | phoneFilter }}</span
          >
          <p>如果长时间未收到验证码，请重新发送验证码</p>
        </div>
        <el-form-item label="填写验证码">
          <el-input placeholder="填写验证码" v-model="code" maxlength="6">
            <el-button size="mini" @click="getCode" slot="append"
              >获取验证码</el-button
            >
          </el-input>
        </el-form-item>

        <el-form-item style="margin-top: 60px; margin-left: -180px">
          <el-button type="primary" @click="verifyCode()">下一步</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Phone",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };

    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else if (value !== this.form.newPwd) {
        callback(new Error("两次输入密码不一致!"));
      } else if (value == this.form.oldPwd) {
        callback(new Error("新密码不能等于旧密码"));
      } else {
        callback();
      }
    };
    return {
      code: "",
      phone: "",
      form: {
        oldPwd: "",
        newPwd: "",
        sureNewPwd: "",
      },

      rules: {
        oldPwd: [{ validator: validatePass, trigger: ["blur", "change"] }],
        newPwd: [{ validator: validatePass, trigger: ["blur", "change"] }],
        sureNewPwd: [{ validator: validatePass2, trigger: ["blur"] }],
      },
    };
  },
  methods: {
    //获取验证码
    getCode() {
      const _this = this;
      const params = {
        phone: this.phone,
      };

      this.$axios.GetCode(params).then(function (res) {
        if (res.code == 200) {
          console.error("获取验证码");
          console.error(res);
          alert(res.data.vcode);
          _this.code = res.data.vcode;
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },

    //验证验证码
    verifyCode() {
      const _this = this;
      const params = {
        phone: this.phone,
        code: this.code,
      };
      this.$axios.VerifyCode(params).then(function (res) {
        if (res.code == 200) {
          // _this.$router.push("/UserInfoShow/NewPhone");
          _this.$router.push({
            name: "修改新号码",
            params: {
              phone: _this.phone,
            },
          });
        } else {
          alert("验证失败");
        }
      });
    },
    onNext() {
      this.$router.push({
        name: "修改新号码",
        params: {
          mailbox: this.mailbox,
        },
      });
    },
  },
  created() {
    this.form.oldPwd = this.$route.params.oldPwd;
    this.phone = this.$route.params.phone;
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


<style scoped>
.el-form-item__content {
  margin-left: 120px;
}
.Phone {
  padding: 16px;
  background: #fff;
  height: 400px;
}

.zyz-header {
  color: #999aaa;
  font-size: 14px;
  height: 20px;
  line-height: 20px;
  font-weight: 400;
  margin-bottom: 32px;
}

.zyz-header span {
  cursor: pointer;
}

.normal {
  display: inline-block;
  text-decoration: none;
  color: #999aaa;
}
.normal:hover {
  color: red;
  font-weight: bold;
}

.zyz-header span:hover {
  color: red;
  font-weight: bold;
}
.zyz-content {
  padding: 28px 0 30px;
  margin: auto;
}
span {
  border: 0;
  margin: 0;
  padding: 0;
}

.zyz-content-span {
  float: left;
  display: block;
  margin-bottom: 3px;
  font-size: 16px;
  color: black;
  letter-spacing: 0;
  line-height: 22px;
}
p {
  float: left;
  font-size: 16px;
  color: #ccc;
  letter-spacing: 0;
  line-height: 22px;
  margin-bottom: 30px;
}
</style>
