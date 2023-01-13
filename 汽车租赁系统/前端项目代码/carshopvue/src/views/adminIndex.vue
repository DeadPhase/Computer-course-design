
<template>
  <div class="personalCenter">
    <el-container>
      <el-header style="background-color: white"><span style="font-size: 50px;">数据统计</span></el-header>
      <el-container>

        <el-container>
          <el-main>
            <el-card class="box-card">
              <div slot="header" class="clearfix" >
                <div style="width: 100%;height: 80px;">
                  <div style="width: 300px;height: 50px;float: left" >
                    <span style="font-size: 24px">&nbsp;<strong>当日数据</strong>:&nbsp;</span>
                  </div>
                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;新增订单:&nbsp;</span>{{todayOrderNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(单)</span>
                  </div>

                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;新增用户:&nbsp;</span>{{todayUserNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(人)</span>
                  </div>
                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;上架汽车:&nbsp;</span>{{todayCarNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(辆)</span>
                  </div>

                </div>

                <div style="width: 100%;height: 50px;">
                  <div style="width: 300px;height: 50px;float: left" >
                    <span style="font-size: 24px">&nbsp;<strong>总数据</strong>:&nbsp;</span>
                  </div>
                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;注册用户:&nbsp;</span>{{allUserNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(人)</span>
                  </div>

                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;订单:&nbsp;</span>{{allOrderNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(单)</span>
                  </div>
                  &nbsp;&nbsp;&nbsp;
                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;上架汽车:&nbsp;</span>{{allCarNum}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(辆)</span>
                  </div>

                  <div style="margin-left: 20px ;width: 200px;height: 50px;float: left">
                    <span style="font-size: 20px">&nbsp;总收入:&nbsp;</span>{{total_price}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px">(元)</span>
                  </div>
                </div>


              </div>

            </el-card>

            <el-card class="box-card">
              <div>
                <div ref="chart2" style="width:100%;height:376px;"></div>
              </div>
            </el-card>
          </el-main>


        </el-container>
      </el-container>
    </el-container>




  </div>
</template>
<script>
  import Cookie from 'js-cookie';
      //局部引用
	const echarts = require('echarts');
  export default {
    name: 'PersonalCenter',
    data() {

      return {
        total_price:'',
        todayOrderNum:'',
        todayUserNum:'',
        todayCarNum:'',
        allUserNum:'',
        allOrderNum:'',
        allCarNum:''
      }
    },
    mounted() {
         this.initCharts2();
    },

    methods: {

       initCharts2(){
      const _this = this
      // 基于准备好的dom，初始化echarts实例
		let myChart2 = echarts.init(this.$refs.chart2);
    var names = []; //横坐标数组
    var values = [];//纵坐标数组

    //请求后台数据
      axios.get('/static/getcarranking').then(function (resp) {
          if(resp.data.code==200){
            console.log(resp.data.data.carRank.length)
            console.log(resp)
            for( var i =0;i<resp.data.data.carRank.length;i++){
                names.push(resp.data.data.carRank[i].name);
                values.push(resp.data.data.carRank[i].value);

            }

            		// 绘制图表
		myChart2.setOption({
      //加载数据图标
      title:{
        text:'租赁统计图'
      },

      legend:{
        data:['租赁数量']
      },
      xAxis:{
        data:names,
        axisLabel:{
          interval:0
        }
      },
      yAxis:{
        type:'value'
      },
             series: [
              {
                name: '汽车租赁数量',
                type: 'line',
                data: values
              }
            ]


			});
           
          }
        })

    },
 

    },

    created() {
      const _this = this
      axios.get('/static/adminStatic').then(function (resp) {
        console.log(resp)
        _this.todayOrderNum = resp.data.data.result.todayOrderNum
        _this.todayUserNum = resp.data.data.result.todayUserNum
        _this.todayCarNum = resp.data.data.result.todayCarNum
        _this.allUserNum = resp.data.data.result.allUserNum
        _this.allOrderNum = resp.data.data.result.allOrderNum
        _this.allCarNum = resp.data.data.result.allCarNum
        _this.total_price = resp.data.data.result.totalMoney

      })

    }

  }
</script>
<style>
  .el-header {
    line-height: 60px;
    text-align: center;
    background-color: #b3c0d1;
  }

  .el-main {
    line-height: 36px;
    text-align: center;
    background-color: #e9eef3;
  }

  .demo-basic--circle {
    align: 'center';
    margin-top: 30px;
    margin-left: 150px;
  }
  .block {
    margin-left: 25px;
    font-weight: bold;
  }
  .text {
    font-size: 14px;
  }
  .item {
    margin-bottom: 18px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }
  .clearfix:after {
    clear: both;
  }

  .box-card {
    width: 1768px;
    border-radius: 30px;
  }
</style>

