<template>
  <div>
    <h2 style="color: black;text-align: left;">积分兑换管理</h2>

    <hr>
    <div style="width: 100%;height: 50px" >

      <div style="float: left">
        <form>
          <div>
            <div style="float: left"><el-input  placeholder="请输入用户姓名"></el-input></div>
            <div style="float: left"><el-button icon="el-icon-search"  circle></el-button></div>
          </div>
        </form>
      </div>

  
    </div>

    <br>
  <div id="list">
    <!--员工信息列表-->
    <el-table :data="tableData.filter(data => !search || data.name.toLowerCanamese().includes(search.toLowerCase()))"
              style="width: 100%" height="500">
      <el-table-column label="积分兑换编号" width="200" prop="id"/>
      <el-table-column label="用户姓名" width="200" prop="username"/>
      <el-table-column label="兑换积分" width="200" prop="exchanged_credit"/>
      <el-table-column label="兑换商品编号" width="200" prop="product_id"/>


      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除该用户所有的积分兑换记录吗？"
            @confirm="deletedAll(scope.$index, scope.row)">
            <el-button size="mini" type="danger" slot="reference">全部删除</el-button>
          </el-popconfirm>        </template>
        <template slot-scope="scope">
          <!-- <el-button size="mini" @click="edit(scope.row)">编辑</el-button> -->
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除这条兑换记录吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button size="mini" type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>


</div>
  </div>
</template>

<script>
  export default {
    name: "list",
    data() {
      return {
        tableData: [],
        search: '',

      }
    },
    methods: {

      deletedAll(index,row){
        axios.delete('/score/deleteUserScoreExchange/'+row.user_count).then(res => {
          //判断执行状态
          console.log(res)
          if (res.data.status) {
            this.$message({
              message: res.data.message,
              type: 'success'
            });
          } else
            this.$message.error(res.data.message);
          this.showAllScoreExchange();//重新渲染数据
        });
      },

      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller
        axios.delete('/score/deleteScoreExchange/'+row.id).then(res => {
          //判断执行状态
          console.log(res)
          if (res.data.status) {
            this.$message({
              message: res.data.message,
              type: 'success'
            });
          } else
            this.$message.error(res.data.message);
          this.showAllScoreExchange();//重新渲染数据
        });
      },

      showAllScoreExchange(){
        const _this = this
       
        axios.get('/score/findAllScoreExchange/'+this.$route.query.username).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.data.scoreList;
        })
      }


    },
    created() {
     this.showAllScoreExchange()
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
