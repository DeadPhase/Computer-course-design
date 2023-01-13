<template>
  <div>

      <h2 style="color: black;text-align: left;">用户信息管理</h2>

    <hr>
    <div style="width: 100%;height: 50px" >
      <div style="float: left">
      <form>
<!--        <input type="text" v-model="loginForm.username" style="width: 100px;height: 20px"  placeholder="用户名"/>-->
        账号：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.count" placeholder="账号"   ></input>&nbsp;
        &nbsp; 姓名：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.name" placeholder="姓名"></input>
        &nbsp;&nbsp; 电话号码：<input type="text" style="width: 100px;height: 20px" v-model="loginForm.phone" placeholder="电话号码"></input>

        <el-button icon="el-icon-search" @click="research" circle></el-button>
      </form>
      </div>
      <div style="float: right">
        
        <!-- <el-button type="primary" round @click="addUser">增加用户</el-button>        -->
        <el-button type="primary" round @click="findAllCar">显示全部用户</el-button>
      </div>
    </div>
  <div id="list">
    <!--员工信息列表-->
    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%" height="500">
      <el-table-column label="用户编号" width="100" prop="user_id"/>
      <el-table-column label="账号" width="200" prop="count"/>
      <el-table-column label="用户姓名" width="200" prop="name"/>
      <el-table-column label="密码" width="200" prop="password"/>
      <el-table-column label="电话号码" width="200" prop="phone"/>
      <el-table-column label="注册时间" width="200" prop="time"/>


      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" style="width: auto" placeholder="输入姓名关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button type="primary"  @click="editUser(scope.$index,scope.row)" icon="el-icon-edit" circle>编辑</el-button>
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除该用户吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button type="danger" icon="el-icon-delete" slot="reference" circle>删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

<!--    ========================================修改用户信息=============================-->
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false">
      <el-form :model="editForm" :rules="rules" label-width="80px"  ref="editForm">
        <el-form-item label="用户编号" prop="user_id">
          <el-input v-model="editForm.user_id" disabled></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="count">
          <el-input v-model="editForm.count"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="update('editForm')">确 定</el-button>
  </span>
    </el-dialog>

<!--    ====================================增加新用户====================================-->
    <el-dialog
      title="添加新用户"
      :visible.sync="adddialogVisible"
      width="30%"
      :close-on-click-modal="false">
      <el-form style="width: 100%" :model="editForm" :rules="rules" label-width="100px"  ref="editForm" class="demo-ruleForm">

        <el-form-item label="账号" prop="count">
          <el-input v-model="editForm.count"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>

      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="resetForm('editForm')">重置</el-button>
    <el-button @click="adddialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitForm('editForm')">确 定</el-button>
  </span>
    </el-dialog>


    <!--分页组件-->
    <el-row>
      <!--      offset="12"-->
      <el-col :span="12" offset="12">
        <el-pagination
          style="margin: 15px 0px"
          background
          layout="prev, pager, next, jumper, total, sizes"
          :page-size="size"
          :current-page="now"
          :page-sizes="[2,4,6,8,10,12]"
          @current-change="findPage"
          @size-change="findSize"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>

  </div>
  </div>
</template>

<script>
  export default {
    name: "list",
    data() {

          // 验证账号
       const checkCount =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value.length <0 || value.length >10){
          callback(new Error("账号的长度范围是0-10，请输入正确的账号！！！"))
        }else if(regN.test(value)){
            callback(new Error("输入的内容不能是纯数字！！！"))
        }else{
          callback()
        }
      }

      // 验证姓名
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
      
      //验证密码
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
      //验证手机
       const checkMobile = (rule, value, callback) => {
      const regCN = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!regCN.test(value)) {
        callback(new Error('手机号格式不正确'))
      } else {
        callback()
      }
    }

      return {
        editForm: {
          user_id:'',
          count:'',
          name:'',
          password:'',
          phone:''
        },
        rules: {
          user_id:[
            { required: true, message: '请输入用户编号', trigger: 'blur' }
          ],
          count:[
            { required: true, message: '请输入用户账号', trigger: 'blur' },
            {validator:checkCount,trigger:'blur'}

          ],
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            {validator:checkName,trigger:'blur'}
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            {validator:checkPwd,trigger:'blur'}
          ],
          phone:[
            { required: true, message: '请输入号码', trigger: 'blur' },
            { validator:checkMobile,trigger:'blur'}
          ]
        },
        loginForm: {
          count: '',
          name: '',
          phone:''
        },
        tableData: [],
        search: '',
        dialogVisible:false,//编辑用户弹窗
        adddialogVisible:false,//增加用户弹窗
        total: 0,
        now: 1,
        size: 8
      }
    },
    methods: {
      findPage(now_page) {//用来处理当前页的方法,page为当前页
        this.now = now_page;
        this.showAllUsers(now_page, this.size);
      },
      findSize(now_size) {//用来处理每页条数的方法,size为当前页条数
        this.size = now_size;
        this.showAllUsers(this.now, now_size);
      },
      addUser(){
        this.adddialogVisible = true;//dialog对话窗口打开
      },
      editUser(index, row) {
        this.dialogVisible = true;//dialog对话窗口打开
        this.editForm = Object.assign({}, row);//将数据传入dialog页面
        this.editForm.index=index;//传递当前index

      },
      submitForm(formName) {
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.post('/user/register',this.editForm).then(function (resp) {
              if(resp.data.code==200){
                _this.adddialogVisible = false;//dialog对话窗口关闭
                // alert("添加成功") 跳转的路由
                _this.$alert('《'+_this.editForm.name+'》添加成功', '消息', {
                  confirmButtonText: '确定',
                  callback: action => {
                    _this.showAllUsers();
                  }
                });
                _this.showAllUsers();
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
      },
      update(formName) {
        const _this = this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/user/updateoneuser',this.editForm).then(function (resp) {
              if(resp.data.code==200){
                _this.dialogVisible = false;//dialog对话窗口关闭
                // _this.$router.push('/usermanage/userlistshow')
                // alert("添加成功") 跳转的路由
                _this.$alert('《'+_this.editForm.name+'》修改成功', '消息', {
                  confirmButtonText: '确定',

                  callback: action => {
                    // _this.$router.push('/usermanage/userlistshow')
                    _this.showAllUsers();
                  }
                });
                _this.showAllUsers();

              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      research(){

        axios.post('/user/findsomeuser',this.loginForm).then(res => {
          console.log(res)
          this.tableData = res.data.data.result.users;
          this.total = res.data.data.result.totals;
        });
      },


      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller
          const _this =this
        axios.delete('/user/deleteoneuser/'+row.user_id).then(res => {
          //判断执行状态
          console.log(res)
          if(res.data.code==200){
            _this.$alert('删除用户成功', '消息', {
              confirmButtonText: '确定',
              callback: action => {
                _this.adddialogVisible=false
                _this.showAllUsers();
              }
            });
          } else
            this.$message.error(res.data.message);
          this.showAllUsers();//重新渲染数据
        });
      },

      findAllCar(){
        this.showAllUsers()
      },


      showAllUsers(currentPage, pageSize) {  //异步请求显示所有数据
        currentPage = currentPage ? currentPage : this.now;
        pageSize = pageSize ? pageSize : this.size;
        axios.get("/user/findAll/" + currentPage + "/" + pageSize).then(res => {
          console.log(res)
          this.tableData = res.data.data.result.users;
          this.total = res.data.data.result.totals;
        });
      }
    },
    created() {
      this.showAllUsers();
    }
  }
</script>

<style scoped>
  .transition-box {
    margin-bottom: 10px;
    width: 100%;
    height: 400px;
    border-radius: 4px;
    background-color: #ffffff;
    text-align: left;
    color: #fff;
    padding: 0px 10px;
    box-sizing: border-box;
    margin-right: 20px;
  }
</style>
