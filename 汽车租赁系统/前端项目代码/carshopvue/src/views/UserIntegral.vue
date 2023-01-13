<template>
  <div>
    <h2 style="color: black;text-align: left;">用户积分管理</h2>
    <hr>
    <!-- <div style="width: 100%;height: 50px" >

      <div style="float: left">
        <form>
          <div>
            <div style="float: left"><el-input  placeholder="请输入用户姓名"></el-input></div>
            <div style="float: left"><el-button icon="el-icon-search"  circle></el-button></div>
          </div>
        </form>
      </div>


    </div> -->

  <div id="list">

    <!--用户积分信息列表-->
    <el-table  :data="tableData.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
               style="width: 100%" height="500">
      <el-table-column v-mode="showEl" v-if="showEl==true" label="编号" width="100" prop="id"/>
      <el-table-column label="用户姓名" width="200" prop="username"/>
      <el-table-column label="用户积分" width="200" prop="score"/>


      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" style="width: auto" placeholder="输入姓名关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="edit(scope.$index,scope.row)">编辑</el-button>
          <el-button size="mini" @click="showscore(scope.row)">查看用户积分兑换记录</el-button>


          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除该用户积分吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button size="mini" type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      title="修改积分"
      :visible.sync="dialogVisible"
      width="30%"
      :close-on-click-modal="false">
      <el-form :model="editForm" :rules="rules" label-width="80px"  ref="editForm">
        <el-form-item label="用户姓名">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户积分" prop="score">
          <el-input v-model="editForm.score"></el-input>
        </el-form-item>


      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitForm('editForm')">确 定</el-button>
  </span>
    </el-dialog>

    <!--分页组件-->
    <el-row>
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

      
      //验证积分
       const checkScore =(rule,value,callback)=>{
        const regN = /^[0-9]*$/;
   
        if(value  < 0 ){
          callback(new Error("积分不能为负！！！"))
        }else if(!regN.test(value)){
            callback(new Error("请输入合法的数据！！！"))
        }else{
          callback()
        }
      }
      return {
        num:1,
        //是否展示id
        showEl:false,
        dialogVisible: false,//选购商品弹窗
        tableData: [],
        show: false,
        show2: false,
        search: '',
        editForm: {
          id:'',
          username: '',
          score:''
        },
        rules:{
          score:[
            { required: true, message: '请输入积分', trigger:'blur' },
            {validator:checkScore,trigger:'blur'}
          ]
        }
        ,
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
      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller

        axios.delete('/score/deleteScore/'+row.user_id).then(res => {
          //判断执行状态
          console.log(res)
          if (res.data.status) {
            this.$message({
              message: res.data.message,
              type: 'success'
            });
          } else
            this.$message.error(res.data.message);
          this.showAllUsers();//重新渲染数据
        });
      },
      submitForm(formName) {
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios.put('/score/updateScore',this.editForm).then(function (resp) {
              if(resp.data.code==200){

                _this.dialogVisible = false;//dialog对话窗口关闭
                _this.showAllUsers();

              }
            })
          } else {
            _this.dialogVisible = false;//dialog对话窗口关闭
            alert('error submit!!');
            return false;
          }
        });
      },

      edit(index, row) {
        this.dialogVisible = true;//dialog对话窗口打开
        this.editForm = Object.assign({}, row);//将数据传入dialog页面
        this.editForm.index=index;//传递当前index

      },

      //跳转到用户积分兑换记录表
      showscore(row) {

        this.$router.push({

          path:'/scoreexchangeshow',//跳转的页面
          query:{
            username:row.username
          }
        })
      },




      showAllUsers(currentPage, pageSize) {  //异步请求显示所有数据
        currentPage = currentPage ? currentPage : this.now;
        pageSize = pageSize ? pageSize : this.size;
        axios.get("/score/findAll/" + currentPage + "/" + pageSize).then(res => {
          console.log(res)
          this.tableData = res.data.data.result.score;
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


