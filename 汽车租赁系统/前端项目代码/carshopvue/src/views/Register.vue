<template>

  <div class="w">
    <header>
      <div class="logo">
        <a href="index.html"> <img src="images/logo.png" alt=""></a>
      </div>
    </header>
    <div class="registerarea">
      <h3>注册新用户
        <div class="login">我有账号，去<router-link to="/login">登陆</router-link></div>
      </h3>
      <div class="reg_form">

        <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
          <ul>
            <li>
              <el-form-item label="账号" prop="count" >
                <el-input v-model="ruleForm.count" placeholder="请输入账号" ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" placeholder="请输入姓名" ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="电话号码" prop="phone">
                <el-input v-model="ruleForm.phone" placeholder="请输入电话号码" ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码" ></el-input>
              </el-form-item>
            </li>
            <li>
              <el-form-item label="确认密码" prop="surepassword">
                <el-input type="password" v-model="ruleForm.surepassword" placeholder="请再次输入密码" ></el-input>
              </el-form-item>
            <li class="agree"><input type="checkbox" name="" id="">
              同意协议并注册 <a href="#">《知晓用户协议》</a>

            </li>
            <li>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </li>
          </ul>
        </el-form>
      </div>
    </div>
    <footer>
      <div class="mod_copyright">
        <div class="links">
          <a href="#">关于我们</a> | <a href="#">联系我们</a> | 联系客服 | 商家入驻 | 营销中心 | 手机品优购 | 友情链接 | 销售联盟 | 品优购社区 |
          品优购公益 | English Site | Contact U
        </div>
        <div class="copyright">
          地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100 邮箱: zhanghj+itcast.cn <br>
          京ICP备08001421号京公网安备110108007702
        </div>
      </div>
    </footer>
  </div>

</template>

<script>
    export default {
      data() {
        return {
          ruleForm: {
            count:'',
            name: '',
            phone:'',
            password: '',
            surepassword:''
          },
          rules: {
            count:[
              {required:true,message:'请输入用户名',trigger:'blur'},
            ],
            name: [
              {required: true, message: '请输入用户名', trigger: 'blur'},
            ],
            phone:[
              {required: true, message: '请输入电话号码', trigger: 'blur'},
            ],
            password: [
              {required: true, message: '请输入密码', trigger: 'blur'},
            ],
            surepassword:[
              {required:true,message:'请再次输入密码',trigger:'blur'}
            ]

          }
        };
      },
      methods: {
        submitForm(formName) {
          const _this = this
          this.$refs[formName].validate((valid) => {
            if (valid) {
              axios.post('http://local' +
                'host:8282/user/register', this.ruleForm).then(function (resp) {
                console.log(resp)
                if (resp.data.code == 200) {
                      _this.$alert('注册成功', '消息', {
                       confirmButtonText: '确定',
                   callback: action => {
                   _this.$router.push('/userlogin')
              }
            });



                
                } else {
                  alert('用户已注册！！!')
                }
              })
            } else {
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

<style scoped>
  @import "../assets/css/base.css";
  @import "../assets/css/register.css";
</style>
