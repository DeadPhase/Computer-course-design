<template>
  <div>
    <h2 style="color: black;text-align: left;">优惠券列表</h2>

    <hr>
    <div style="width: 100%;height: 50px" >

      <div style="float: left">
        <form>
          <div>
            <div style="float: left"><el-input  placeholder="请输入优惠券编号"></el-input></div>
            <div style="float: left"><el-button icon="el-icon-search"  circle></el-button></div>
          </div>
        </form>
      </div>

    </div>

    <br>
  <div id="list">
    <!--优惠券-->
    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%" height="500">
      <el-table-column label="优惠券编号" width="100" prop="id"/>
      <el-table-column label="优惠券名称" width="100" prop="name"/>
      <el-table-column label="优惠券折扣" width="100" prop="type"/>
      <el-table-column label="优惠券数量" width="100" prop="num"/>
<!--      <el-table-column label="用户类型" width="100" prop="userType"/>-->
      <el-table-column label="领取数量" width="100" prop="receive"/>
      <el-table-column label="开始时间" width="200" prop="startTime"/>
      <el-table-column label="结束时间" width="200" prop="endTime"/>
      <el-table-column label="使用说明" width="200" prop="instructions"/>


      <el-table-column align="right" width="400">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" style="width: auto" placeholder="输入姓名关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="edit(scope.$index,scope.row)">编辑</el-button>
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除该优惠券吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button size="mini" type="danger" slot="reference">删除</el-button>
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
      edit(index, row) {

        this.$router.push({
          path:'/discountcouponupdate',
          query:{
            id:row.id
          }
        })
      },


      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller
        const _this =this
        axios.delete('/discount/deletediscountcoupon/'+row.id).then(res => {
          //判断执行状态
          if(res.data.code==200){
            _this.$alert('删除成功', '消息', {
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
        axios.get("/discount/lookdiscountcouponpage/" + currentPage + "/" + pageSize).then(res => {
          console.log(res)
          this.tableData = res.data.data.result.discounts;
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
