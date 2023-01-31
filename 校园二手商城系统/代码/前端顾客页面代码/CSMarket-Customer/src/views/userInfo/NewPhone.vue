<template>
  <div class="NewPhone">
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
    </div>
    <div class="zyz-content">
      <el-form
        ref="form"
        :rules="rules"
        :model="form"
        label-width="85px"
        style="width: 35%; margin: auto"
      >
        <el-form-item label="输入新手机">
          <el-input
            placeholder="请输入新手机号"
            v-model="newPhone"
            class="input-with-select"
            maxlength="11"
          >
            <el-select slot="prepend" placeholder="请选择">
              <el-option label="餐厅名" value="1"></el-option>
              <el-option label="订单号" value="1"></el-option>
              <el-option label="用户电话" value="1"></el-option>
            </el-select>
          </el-input>
        </el-form-item>
        <el-form-item label="填写验证码">
          <el-input placeholder="填写验证码" v-model="code" maxlength="6">
            <el-button size="mini" slot="append" @click="getCode"
              >获取验证码</el-button
            >
          </el-input>
        </el-form-item>

        <p>如果长时间未收到验证码，请重新发送验证码</p>

        <el-form-item style="margin-top: 60px; margin-left: -180px">
          <el-button type="primary" @click="verifyCode">确认修改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "NewPhone",
  data() {
    return {
      oldPhone: "",
      newPhone: "",
      code: "",
      form: {
        sureNewPwd: "",
      },
    };
  },
  methods: {
    //获取验证码
    getCode() {
      const _this = this;
      console.error("新手机号" + this.newPhone);
      if (this.newPhone == this.oldPhone) {
        this.$message.error("新旧手机号不能一样");
        return;
      }
      const params = {
        phone: this.newPhone,
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
        phone: this.newPhone,
        code: this.code,
      };
      this.$axios.VerifyCode(params).then(function (res) {
        if (res.code == 200) {
          //发送请求修改密码
          const parmam = {
            userId: localStorage.getItem("uid"),
            phone: _this.newPhone,
          };
          _this.$axios.UpdatePhone(parmam).then(function (res) {
            if (res.code == 200) {
              _this.$alert("手机号修改成功，请牢记！！！", {
                confirmButtonText: "确定",
                callback: (action) => {
                  _this.$router.push("/UserInfoShow/AccountSettings");
                },
              });

              // _this.$router.push("/UserInfoShow/AccountSettings");
            } else {
              alert("修改密码失败");
            }
          });
        } else {
          alert("验证失败");
        }
      });
    },
  },
  created() {
    this.oldPhone = this.$route.params.phone;
  },
};
</script>


<style scoped>
.el-form-item__content {
  margin-left: 120px;
}
.NewPhone {
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
