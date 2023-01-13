<template>
	<div>
     <h3 style="color: black;text-align: left">汽车租赁情况数据分析</h3>
    <hr>
    <div ref="chart2" style="width:50%;height:376px; float: left"></div>
		<div style="width:50%;height:376px;float: right" ref="chart"></div>

       <div style="width:100%;height:376px;float: left">
      <div style="margin-top: 5px">
        <hr>
        <h1 style="float: left">友情提示:</h1><br><br><br><br>
        <span style="float: left">1、商家可以根据租赁汽车的排名情况、加大对热门汽车的推广和上新</span>
        <br><br>
        <span style="float: left">2、根据各类汽车租赁情况的总体展示，及时调整汽车商品的管理</span>
        <br><br>
        <span style="float: left">3、商家可以及时了解用户对租赁汽车商品的租赁情况</span>

      </div>
    </div>
	</div>
</template>

<script>
    //局部引用
	const echarts = require('echarts');
	export default{
		data(){
		return {
			
		}
		},
	methods: {

    initCharts2(){
      const _this = this
      // 基于准备好的dom，初始化echarts实例
		let myChart2 = echarts.init(this.$refs.chart2);
    var values =[];
    //请求后台数据
      axios.get('/static/getcarranking').then(function (resp) {
          if(resp.data.code==200){
            console.log(resp.data.data.carRank.length)
            console.log(resp)
            for( var i =0;i<resp.data.data.carRank.length;i++){
              var test={"value":resp.data.data.carRank[i].value,"name":resp.data.data.carRank[i].name}
              values.push(test);

            }

            		// 绘制图表
		myChart2.setOption({
          title: {
              text: '汽车租赁排名前六展示',
              left:'center'
            },
            tooltip: {
              trigger: 'item'
            },
            legend: {
              // top: '5%',
              orient:'vertical',
              left: 'left',
              data:values
            },
             series: [
              {
                name: '数据来源',
                type: 'pie',
                radius:'60%',
                avoidLabelOverlap: false,
                label: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  label: {
                    show: true,
                    fontSize: '40',
                    fontWeight: 'bold'
                  }
                },
                labelLine: {
                  show: false
                },
                data: values
              }
            ]


			});
           
          }
        })

    },
		initCharts(){
      const _this =this
		// 基于准备好的dom，初始化echarts实例
		let myChart = echarts.init(this.$refs.chart);
    var names=[];  //横坐标数组
    var values =[]; //纵坐标数组

  
  //请求后台数据
      axios.get('/static/getcartypenum').then(function (resp) {
          if(resp.data.code==200){
            console.log(resp.data.data.carTypeNum.length)
            console.log(resp)
            for( var i =0;i<resp.data.data.carTypeNum.length;i++){
              names.push(resp.data.data.carTypeNum[i].name);
              values.push(resp.data.data.carTypeNum[i].value);

            }

            		// 绘制图表
		myChart.setOption({
			title: { text: '不同类型汽车租赁情况' },
			tooltip: {},
			xAxis: {
			data: names
			},
			yAxis: {},
			series: [{
			name: '租赁数量',
			type: 'bar',
			data: values
			}]
			});
           
          }
        })



		}
	},
	//一加载页面就调用
	mounted () {
	this.initCharts();
  this.initCharts2();
	}
	}
</script>
<style>
</style>

