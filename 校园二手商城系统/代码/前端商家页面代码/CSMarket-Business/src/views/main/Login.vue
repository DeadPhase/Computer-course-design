<template>
  <div class="login">
    <div class="login_context">
      <h1 style="margin-top: 50px">服装库存管理后台系统</h1>
      <!-- <div class="login_logo">
        <img src="../../assets/image/back.jpg">
          
      </div> -->
      <!--      头部图片-->

      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="login_box"
      >
        <el-form-item prop="userName">
          <span slot="label">
            <span style="color: white"><strong>姓名</strong></span>
          </span>
          <el-input
            v-model="ruleForm.userName"
            maxlength="10"
            show-word-limit
            style="width: 70%; margin-left: -80px"
            placeholder="请输入姓名"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <span slot="label">
            <span style="color: white"><strong>密码</strong></span>
          </span>
          <el-input
            v-model="ruleForm.password"
            type="password"
            style="width: 70%; margin-left: -80px"
            placeholder="请输入密码"
            autocomplete="off"
            maxlength="10"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            style="margin-top: 20px; margin-left: -80px"
            @click="submitForm('ruleForm')"
            >登录
          </el-button>
          <el-button
            type="primary"
            style="margin-top: 20px"
            @click="restForm('ruleForm')"
            >重置</el-button
          >
          <el-button type="primary" style="margin-top: 20px">
            <router-link to="/">
              <span style="color: white"><strong>注册</strong></span>
            </router-link>
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import rules from "@/api/rules.js";
import { Message } from "element-ui";
import { mapMutations } from "vuex";
export default {
  data() {
    return {
      ruleForm: {
        userName: "",
        password: "",
      },
      rules: {
        userName: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { validator: rules.checkUserName, trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: rules.checkPwd, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //简化操作
    ...mapMutations(["changeLogin"]),
    submitForm(ruleForm) {
      const _this = this;

      this.$refs[ruleForm].validate((valid) => {
        if (valid) {
          this.$axios.Login(this.ruleForm).then(function (res) {
            if (res.code == 200) {
              console.log(res.data);
              const token = res.data.result.token;
              console.log("===" + token);
              _this.changeLogin({ Authorization: token });
              _this.$router.push("/Home");
            } else {
              _this.$message.error(res.data.errMessage);
            }
          });
        } else {
          return false;
        }
      });
    },
    restForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
<style>
@import "../../assets/css/login.css";
</style>

<style scoped="scoped">
.router-link-active {
  text-decoration: none;
}
</style>

