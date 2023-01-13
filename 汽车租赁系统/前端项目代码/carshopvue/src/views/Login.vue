<template>
  <div class="login">
    <div class="login_context">
      <div class="login_logo">
        <img src="../assets/car.jpg">
      </div>
      <!--      头部图片-->

      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="login_box">

        <el-form-item prop="name">
           <span slot="label">
      	     <span style="color: white"><strong>管理员账号</strong></span>
           </span>
        <el-input v-model="ruleForm.name" placeholder="请输入账号"></el-input>
        </el-form-item>

        <el-form-item  prop="password" >
          <span slot="label">
      	     <span style="color: white"><strong>管理员密码</strong></span>
           </span>
          <el-input type="password" placeholder="请输入密码" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>


        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
          <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>

  import Cookies from 'js-cookie'
  import { mapMutations } from 'vuex';
  export default {
    data() {
      //表单验证方法
      const checkName =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >10){
          callback(new Error("姓名的长度范围是0-10，请输入正确的姓名！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

      const checkPwd = (rule,value,callback)=>{
        const regN = /^[0-9]*$/;

        if(value.length <0 || value.length >8){
          callback(new Error("密码长度范围 0-8！！！"))
        }else if(!regN.test(value)){
            callback(new Error("输入密码不合法，只能是纯数字！！！"))
        }else{
          callback()
        }
      }
      return {
        ruleForm: {
          name: '',
          password: ''

        }
        ,
        rules: {
          name: [
            {required: true, message: '请输入用户名', trigger: 'blur'},
            {validator:checkName,trigger:'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {validator:checkPwd,trigger:'blur'}
          ]

        }
      };
    },
    methods: {
      ...mapMutations(['changeLogin']),
      submitForm(formName) {

        const _this = this
        const that =this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            Cookies.set('name',this.ruleForm.name)
            axios({
              method:'post',
              url:'http://localhost:8282/user/login',
              data:this.ruleForm

            }).then(function (resp) {
              if(resp.data.code == 200){
                console.log(resp)
                _this.dialogVisible =false;
                //登陆成功  使用token
                const token = resp.data.data.result.token;
                Cookies.set('vip',resp.data.data.result.vip)
                _this.changeLogin({ Authorization: token });
                _this.$router.push('/home');
              }else{
                _this.changeLogin({ Authorization: null });
                alert("用户名或者密码不正确！！！")
              }

            })


          } else {
            // localStorage.removeItem('Authorization');
            // _this.$router.push('/login');
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped="scoped">


  .login {
    height: 100%;
    background-image: url("../assets/background.jpg");
  }

  .login_context {
    width: 450px;
    height: 300px;
    background-color: grey;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 10px;
    box-shadow: 0 0 5px 2px #ddd;
  }

  .login_logo {
    width: 150px;
    height: 150px;
    position: absolute;
    top: -75px;
    left: 50%;
    margin-left: -75px;
    border: 1px solid #eee;
    border-radius: 50%;
    background: #fff;
    padding: 10px;
    box-shadow: 0 0 3px 2px #fff;
  }

  .login_logo img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background: rgb(238, 238, 238);
  }

  .login_box {
    width: 100%;
    position: absolute;
    bottom: 0;
    padding: 0 20px;
    box-sizing: border-box;

  }


</style>

