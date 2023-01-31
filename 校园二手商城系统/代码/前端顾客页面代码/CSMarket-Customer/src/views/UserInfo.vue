<template>
  <div class="w">
    <header>
      <div class="logo">
        <a href="index.html"> <img src="images/logo.png" alt="" /></a>
      </div>
    </header>
    <div class="registerarea">
      <div class="reg_form">
        <el-form
          style="width: 60%; margin-left: 60px"
          :model="UserForm"
          ref="UserForm"
          label-width="100px"
        >
          <ul>
            <li>
              <el-form-item label="姓名" prop="userName">
                <el-input
                  v-model="UserForm.userName"
                  placeholder="请输入姓名"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="昵称" prop="nickName">
                <el-input
                  v-model="UserForm.nickName"
                  placeholder="请输入账号"
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
              <el-form-item label="地址" prop="address">
                <el-input
                  v-model="UserForm.address"
                  placeholder="请输入地址"
                ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="账户余额" prop="money">
                <span style="float: left; color: red">
                  ￥&nbsp;{{ UserForm.money }}</span
                >
              </el-form-item>
            </li>

            <li>
              <el-form-item>
                <el-button round type="primary" @click="recharge()"
                  >充值</el-button
                >
                <el-button round type="primary">保存</el-button>
              </el-form-item>
            </li>
          </ul>
        </el-form>
      </div>

      <el-dialog title="充值" :visible.sync="dialogVisible" width="20%">
        <div>
          <el-input placeholder="请输入金额" v-model="inputMoney">
            <template slot="prepend">金额</template>
          </el-input>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button round type="primary" size="mini" @click="confirmMoney()"
            >确 定</el-button
          >
          <el-button
            round
            type="danger"
            size="mini"
            @click="dialogVisible = false"
            >取 消</el-button
          >
        </span>
      </el-dialog>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      dialogVisible: false,
      inputMoney: 100,
      UserForm: {
        uid: "",
        nickName: "",
        userName: "",
        gender: "",
        idCard: "", //身份证
        email: "",
        phone: "",
        password: "",
        surepassword: "",
        address: "",
        money: 0,
        introduction:''
      },
    };
  },
  methods: {
    //充值
    confirmMoney() {
      const _this = this;
      const params = {
        money: this.inputMoney,
        preMoney: this.UserForm.money,
        userId: this.UserForm.uid,
      };

      this.$axios.rechargeMoney(params).then(function (res) {
        if (res.code == 200) {
          _this.dialogVisible = false;
          _this.findUserById();
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
    findUserById() {
      const _this = this;
      const params = {
        uid: this.UserForm.uid,
      };
      this.$axios.findUserById(params).then(function (res) {
        if (res.code == 200) {
          _this.UserForm = res.data.user;
        } else {
          _this.$message.error(res.data.errMessage);
        }
      });
    },
    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          
          console.error("用户信息界面：" + JSON.stringify(res.data.user));
          _this.UserForm = res.data.user;

          
        } else {
          _this.$message.error(res.data.errMessage);
          _this.$router.push("/Login");
        }
      });
    },

    recharge() {
      this.dialogVisible = true;
    },
  },
  created() {
    this.isLogin();
  },
};
</script>

<style scoped>
@import "../assets/css/base.css";
@import "../assets/css/register.css";
</style>
