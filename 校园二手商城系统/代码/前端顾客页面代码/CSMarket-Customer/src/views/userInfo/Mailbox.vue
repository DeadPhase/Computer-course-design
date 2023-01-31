<template>
  <div class="MailBox">
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
        <span>修改邮箱</span>
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
            >验证码将发送到29***967@qq.com邮箱</span
          >
          <p>如果长时间未收到验证码，请重新获取验证码</p>
        </div>
        <el-form-item label="填写验证码" prop="sureNewPwd">
          <el-input
            placeholder="填写验证码"
            v-model="form.sureNewPwd"
            maxlength="6"
          >
            <el-button size="mini" slot="append">获取验证码</el-button>
          </el-input>
        </el-form-item>

        <el-form-item style="margin-top: 60px; margin-left: -180px">
          <el-button type="primary" @click="onNext()">下一步</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "MailBox",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        callback();
      }
    };
    return {
      form: {
        sureNewPwd: "",
      },

      rules: {
        sureNewPwd: [{ validator: validatePass2, trigger: ["blur"] }],
      },
    };
  },
  methods: {
    onNext() {
      this.$router.push({
        name: "修改新邮箱",
        params: {
          mailbox: this.mailbox,
        },
      });
    },
  },
  created() {

  },
};
</script>


<style scoped>
.el-form-item__content {
  margin-left: 120px;
}
.MailBox {
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
