<template>
  <div class="PersonInfo">
    <div class="zyz-personinfo">
      <div class="zyz-header">
        <span class="zyz-span">个人资料</span>
      </div>

      <div class="zyz-content">
        <div class="zyz-content-left">
          <el-form
            ref="UserForm"
            :model="UserForm"
            :rules="rules"
            label-width="80px"
            style="width: 80%; margin: auto"
          >
            <hr />
            <el-form-item label="用户名" prop="userName">
              <el-input
                maxlength="20"
                show-word-limit
                v-model="UserForm.userName"
              ></el-input>
            </el-form-item>
            <hr />
            <el-form-item label="昵称" prop="nickName">
              <el-input
                maxlength="20"
                show-word-limit
                v-model="UserForm.nickName"
              ></el-input>
            </el-form-item>
            <hr />
            <el-form-item label="身份证" prop="idCard">
              <el-input
                maxlength="18"
                show-word-limit
                v-model="UserForm.idCard"
              ></el-input>
            </el-form-item>

            <hr />
            <el-form-item label="地址" prop="address">
              <el-input
                maxlength="50"
                show-word-limit
                v-model="UserForm.address"
              ></el-input>
            </el-form-item>
            <el-form-item label="个人介绍" prop="textarea">
              <el-input
                type="textarea"
                maxlength="100"
                show-word-limit
                :rows="4"
                placeholder="填写兴趣爱好、擅长的事情、喜欢的事情等"
                v-model="UserForm.introduction"
              >
              </el-input>
            </el-form-item>

            <el-form-item label="账户余额" prop="money">
              <span style="float: left; color: red">
                ￥&nbsp;{{ UserForm.money }}</span
              >
              <el-button
                size="mini"
                style="float: left; margin-left: 10px; margin-top: 6px"
                round
                type="danger"
                @click="recharge()"
                >充值</el-button
              >
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="UpdateUserForm('UserForm')"
                >修改信息</el-button
              >
            </el-form-item>
          </el-form>
        </div>
        <div class="zyz-content-right">
          <el-avatar
            style="float: left; margin-left: 40px"
            :size="100"
            :src="circleUrl"
          ></el-avatar>
          <br /><br /><br /><br /><br /><br />
          <button style="margin-left: -350px">修改头像</button>
        </div>
      </div>
    </div>
    <el-dialog title="充值" :visible.sync="dialogVisible" width="20%">
      <div>
        <el-input placeholder="请输入金额" v-model="inputMoney">
          <template slot="prepend">金额</template>
        </el-input>
      </div>
      <span slot="footer">
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
</template>

<script>
import rules from "@/api/rules.js";
export default {
  name: "PersonInfo",
  data() {
    return {
      inputMoney: 100,
      dialogVisible: false,
      circleUrl: "http://localhost:8282/images/21667218837206.jpg",
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
        introduction: "",
      },
      rules: {
        nickName: [
          { message: "请输入昵称", trigger: "blur" },
          { validator: rules.checkNickName, trigger: ["change", "blur"] },
        ],
        userName: [
          { message: "请输入姓名", trigger: "change" },
          { validator: rules.checkUserName, trigger: ["blur", "change"] },
        ],
        gender: [
          { message: "请输入性别", trigger: "blur" },
          { validator: rules.checkGender, trigger: ["blur", "change"] },
        ],
        idCard: [
          { message: "请输入身份证号", trigger: "blur" },
          { validator: rules.checkIdCard, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    recharge() {
      this.dialogVisible = true;
    },
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
    //根据ID查找用户
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
    //判断是否登录
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
    //修改个人信息
    UpdateUserForm(UserForm) {
      const _this = this;
      this.$refs[UserForm].validate((valid) => {
        if (valid) {
          console.error("修改数据：");
          console.error(_this.UserForm);

          this.$axios.UpdateUserInfo(this.UserForm).then(function (resp) {
            if (resp.code == 200) {
              _this.$alert(
                "《" + _this.UserForm.userName + "》用户信息修改成功",
                "消息",
                {
                  confirmButtonText: "确定",
                  callback: (action) => {
                    _this.UserForm = resp.data.userInfo;
                  },
                }
              );
            } else {
              _this.$message.error(resp.data.errMessage);
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
  created() {
    this.isLogin();
  },
};
</script>

<style scoped>
.PersonInfo {
  background: white;
}
.zyz-content {
  background: white;
}
.zyz-personinfo {
  background-color: white;
}
.zyz-header {
  line-height: 48px;
  color: #333;
  overflow: hidden;

  background-color: white;
}
.el-form-item {
  margin-top: 20px;
}
.zyz-span {
  font-weight: 600;
  font-size: 30px;
  float: left;
  margin-top: 15px;
  margin-left: 15px;
}
/* div {
  display: block;
} */

.zyz-content-left {
  float: left;
  background-color: white;
  width: 50%;
}

.zyz-content-right {
  /* float: left; */
  padding-left: 74px;
  background-color: white;
  height: 630px;
}

/* 改变input框背景颜色 */
div /deep/ .el-input__inner {
  background-color: RGB(250, 250, 250);
}

div /deep/ .el-textarea__inner {
  background-color: RGB(250, 250, 250);
}

.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>
