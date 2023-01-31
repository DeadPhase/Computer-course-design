<template>
  <div class="NewMaibox">
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
        <el-form-item label="输入新邮箱">
          <el-input
            placeholder="请输入新邮箱"
            v-model="form.newMaibox"
          ></el-input>
        </el-form-item>
        <el-form-item label="填写验证码" prop="sureNewPwd">
          <el-input
            placeholder="填写验证码"
            v-model="form.sureNewPwd"
            maxlength="6"
          >
            <el-button size="mini" slot="append">获取验证码</el-button>
          </el-input>
        </el-form-item>

        <p>如果长时间未收到验证码，请重新发送验证码</p>

        <el-form-item style="margin-top: 60px; margin-left: -180px">
          <el-button type="primary" @click="onSubmit('form')"
            >确认修改</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewMaibox",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入验证码"));
      } else {
        callback();
      }
    };
    return {
      newPhone: "",
      form: {
        sureNewPwd: "",
        newMaibox: "",
      },

      rules: {
        sureNewPwd: [{ validator: validatePass2, trigger: ["blur"] }],
      },
    };
  },
  methods: {
    onSubmit(form) {
      const _this = this;
      this.$refs[form].validate((valid) => {
        if (valid) {
          const params = {
            uid: localStorage.getItem("uid"),
            password: this.form.sureNewPwd,
            oldPwd: this.form.oldPwd,
            username: "武松",
          };
          this.$axios.onChangePWD(params).then(function (res) {
            if (res.code == 200) {
              console.error("修改密码成功");
              _this.$alert("修改密码成功、请重新登录系统！！！", {
                confirmButtonText: "确定",
                callback: (action) => {
                  _this.$router.push("/UserLogin");
                },
              });
            } else {
              _this.$message.error(res.data.errMessage);
            }
          });
        } else {
          return false;
        }
      });
    },
  },
  created() {
    this.form.oldPwd = this.$route.params.oldPwd;
  },
};
</script>


<style scoped>
.el-form-item__content {
  margin-left: 120px;
}
.NewMaibox {
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
</style>
