<template>
  <div class="PassWord">
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
        <span>修改密码</span>
      </div>

      <!-- <span style="color: red">修改密码:{{ this.$route.params.oldPwd }}</span> -->
    </div>
    <div class="zyz-content">
      <el-form
        ref="form"
        :rules="rules"
        :model="form"
        label-width="80px"
        style="width: 28%; margin: auto"
      >
        <el-form-item label="旧密码" prop="oldPwd">
          <el-input
            placeholder="请输入旧密码"
            v-model="form.oldPwd"
            show-password
          ></el-input>
        </el-form-item>

        <el-form-item label="新密码" prop="newPwd">
          <el-input
            placeholder="6-12位数字和字母组合"
            v-model="form.newPwd"
            show-password
          ></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="sureNewPwd">
          <el-input
            placeholder="确认新密码"
            v-model="form.sureNewPwd"
            show-password
          ></el-input>
        </el-form-item>

        <el-form-item>
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
  name: "PassWord",
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
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.newPwd) {
        callback(new Error("两次输入密码不一致!"));
      } else if (value == this.form.oldPwd) {
        callback(new Error("新密码不能等于旧密码"));
      } else {
        callback();
      }
    };
    return {
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
.PassWord {
  padding: 16px;
  background: #fff;
  height:400px;
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
