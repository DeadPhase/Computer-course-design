<template>
  <div>
    <h2 style="color: black;text-align: left;">订单管理</h2>

    <hr>
    <div style="width: 100%;height: 50px" >

    <div style="float: left">
      <form>
        <div>
          <div style="float: left"><el-input v-model="editForm.order_id" placeholder="请输入订单号"></el-input></div>
          <div style="float: left"><el-button icon="el-icon-search" @click="research" circle></el-button></div>
        </div>

      </form>
    </div>

  </div>

 
  <div id="list">
    <!--订单管理-->
    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%" height="500px">
      <el-table-column label="订单编号" width="200" prop="order_id"/>
      <el-table-column label="订单创建时间" width="200" prop="create_time"/>
      <el-table-column label="租车时间" width="200" prop="usetime"/>
      <el-table-column label="用户姓名" width="100" prop="username"/>
      <el-table-column label="联系电话" width="150" prop="phone"/>
      <el-table-column label="用户地址" width="200" prop="address"/>


      <el-table-column
        prop="status"
        label="状态"
      />

      <el-table-column  width="200"
        prop="user_cancle"
        label="用户是否取消订单"
      />
      <el-table-column align="right" width="600" >



        <template slot-scope="scope">
          <el-button type="primary" @click="waitGetCar"  icon="el-icon-edit" circle >待取车</el-button>
          <el-button type="primary" @click="overTime" icon="el-icon-edit" circle >超时</el-button>
<!--          <el-button type="primary" @click="edit(scope.row)" icon="el-icon-edit" circle >完成</el-button>-->
          <el-button type="primary" @click="waitRuturnCar" icon="el-icon-edit" circle >待还车</el-button>

          <el-button type="primary" @click="edit(scope.row)" icon="el-icon-edit" circle >详情</el-button>

          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定取消该用户订单吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button type="danger"   slot="reference" icon="el-icon-delete" circle >取消</el-button>
          </el-popconfirm>
        </template>

      </el-table-column>
    </el-table>
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
      return {

        editForm: {
          order_id:'',

        },
        tableData: [],
        search: '',
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

      waitGetCar(){
        this.$alert('提醒用户取车成功', '提示信息', {
          confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     // type: 'info',
          //     // message: `action: ${ action }`
          //   });
          // }
        });
      },
      overTime(){
        this.$alert('提醒用户已经超时取车', '提示信息', {
          confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     // type: 'info',
          //     // message: `action: ${ action }`
          //   });
          // }
        });
      },
      waitRuturnCar(){
        this.$alert('提醒用户还车成功', '提示信息', {
          confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     // type: 'info',
          //     // message: `action: ${ action }`
          //   });
          // }
        });
      },
      edit(row) {
        this.$router.push({
          path:'/orderitem',
          query:{
            id:row.order_id
          }
        })
      },

      research(){


        axios.post('/order/conditionorders',this.editForm).then(res => {
          if(res.data.code==200){

            console.log(res)
            this.tableData = res.data.data.result.orders;
            this.tableData.map(function (val) {

              if(val.user_cancle === 0){
                val.user_cancle ="未取消"
              }else if(val.user_cancle === 1){
                val.user_cancle ="已取消"
              }

              if(val.status === 0){
                val.status = '未支付'
              }else if(val.status === 1){
                val.status = '已支付'
              }else if(val.status===2){
                val.status = '待取车'
              }else if(val.status===3){
                val.status = '已取车'
              }
              else if(val.status === 4){
                val.status = '待还车'
              }else if(val.status === 5){
                val.status = '完成'
              }else if(val.status === 6){
                val.status = '超时'
              }

            })
            this.total = res.data.data.result.totals;
          }else{
            alert("未查到")
          }

        });
      },


      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller
      const _this =this
        axios.delete('/order/deleteOrderById/'+row.order_id).then(res => {
          //判断执行状态
          console.log(res)
          if(res.data.code==200){
            _this.$alert('取消订单成功', '消息', {
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


      showAllUsers(currentPage, pageSize) {  //异步请求显示所有数据

        currentPage = currentPage ? currentPage : this.now;
        pageSize = pageSize ? pageSize : this.size;
        axios.get("/order/queryAllOrders/" + currentPage + "/" + pageSize).then(res => {
          console.log(res)

          this.tableData = res.data.data.result.orders;
          this.tableData.map(function (val) {

            if(val.user_cancle === 0){
              val.user_cancle ="未取消"
            }else if(val.user_cancle === 1){
              val.user_cancle ="已取消"
            }
             
            if(val.status === 0){
              val.status = '待支付'
            }else if(val.status === 1){
              val.status = '已支付'
            }else if(val.status===2){
              val.status = '待取车'
            }else if(val.status===3){
              val.status = '已取车'
            }
            else if(val.status === 4){
              val.status = '待还车'
            }else if(val.status === 5){
              val.status = '完成'
            }else if(val.status === 6){
              val.status = '超时'
            }

          })
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
