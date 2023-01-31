<template>
  <div class="w">
    <header>
      <div class="logo">
        <a href="index.html"> <img src="images/logo.png" alt="" /></a>
      </div>
    </header>
    <div class="registerarea">
      <h3>
        注册新用户
        <div class="login">
          我有账号，去<router-link :to="{ path: '/login' }">登陆</router-link>
        </div>
      </h3>
      <div class="reg_form">
        <el-form
          style="width: 60%; margin-left: 60px"
          :model="UserForm"
          :rules="rules"
          ref="UserForm"
          label-width="100px"
        >
          <ul>
            <li>
              <el-form-item label="昵称" prop="nickName">
                <el-input
                  v-model="UserForm.nickName"
                  placeholder="请输入账号"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="姓名" prop="userName">
                <el-input
                  v-model="UserForm.userName"
                  placeholder="请输入姓名"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="性别" prop="gender">
                <el-input
                  v-model="UserForm.gender"
                  placeholder="请输入性别"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="身份证" prop="idCard">
                <el-input
                  v-model="UserForm.idCard"
                  placeholder="请输入身份证"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="邮箱" prop="email">
                <el-input
                  v-model="UserForm.email"
                  placeholder="请输入邮箱"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="手机号" prop="phone">
                <el-input
                  v-model="UserForm.phone"
                  placeholder="请输入电话号码"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="密码" prop="password">
                <el-input
                  type="password"
                  v-model="UserForm.password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="确认密码" prop="surepassword">
                <el-input
                  type="password"
                  v-model="UserForm.surepassword"
                  placeholder="请再次输入密码"
                ></el-input>
              </el-form-item>
            </li>

            <li class="agree">
              <input type="checkbox" name="" id="" /> 同意协议并注册
              <a href="#">《知晓用户协议》</a>
            </li>
            <li>
              <el-form-item>
                <el-button round type="primary" @click="submitForm('UserForm')"
                  >注册</el-button
                >
                <el-button round type="primary" @click="resetForm('UserForm')"
                  >重置</el-button
                >
              </el-form-item>
            </li>
          </ul>
        </el-form>
      </div>
    </div>
    <footer>
      <div class="mod_copyright">
        <div class="links">
          <a href="#">关于我们</a> | <a href="#">联系我们</a> | 联系客服 |
          友情链接 | English Site | Contact U
        </div>
      </div>
    </footer>
  </div>
</template>


<script>
import rules from "@/api/rules.js";
export default {
  data() {
    //验证第二次输入的密码
    const checkPwdSure = (rule, value, callback) => {
      const regN = /^[0-9]*$/;

      if (value.length < 0 || value.length > 8) {
        callback(new Error("密码长度范围 0-8！！！"));
      } else if (!regN.test(value)) {
        callback(new Error("输入密码不合法，只能是纯数字！！！"));
      } else if (value !== this.UserForm.password) {
        callback(new Error("两次输入的密码不一致，请重新输入！！！"));
      } else {
        callback();
      }
    };

    return {
      UserForm: {
        nickName: "",
        userName: "",
        gender: "",
        idCard: "", //身份证
        email: "",
        phone: "",
        password: "",
        surepassword: "",
      },
      rules: {
        nickName: [
          { required: true, message: "请输入昵称", trigger: "blur" },
          { validator: rules.checkNickName, trigger: ["change", "blur"] },
        ],
        userName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { validator: rules.checkUserName, trigger: ["blur", "change"] },
        ],
        gender: [
          { required: true, message: "请输入性别", trigger: "blur" },
          { validator: rules.checkGender, trigger: ["blur", "change"] },
        ],
        idCard: [
          { required: true, message: "请输入身份证号", trigger: "blur" },
          { validator: rules.checkIdCard, trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { validator: rules.checkEmail, trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入电话号码", trigger: "blur" },
          { validator: rules.checkPhone, trigger: "blur" },
          // {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur"}
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { validator: rules.checkPwd, trigger: "blur" },
        ],
        surepassword: [
          { required: true, message: "请再次输入密码", trigger: "blur" },
          { validator: checkPwdSure, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    submitForm(UserForm) {
      const _this = this;

      this.$refs[UserForm].validate((valid) => {
        if (valid) {
          this.$axios.Register(this.UserForm).then(function (resp) {
            console.log(resp);
            if (resp.code == 200) {
              _this.$alert("恭喜您注册成功，现在就去登录吧", "提示", {
                confirmButtonText: "确定",
                callback: (action) => {
                  _this.$router.push("/login");
                },
              });
            } else {
              _this.$message.error(resp.data.errMessage);
            }
          });
        } else {
          console.log(valid);
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
@import "../../assets/css/base.css";
@import "../../assets/css/register.css";
</style>
