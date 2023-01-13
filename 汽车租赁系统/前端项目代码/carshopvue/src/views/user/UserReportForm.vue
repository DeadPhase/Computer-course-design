<style scoped>
  #sector{
    width: 100%;
    height: 400px;
    margin: 0 auto;
  }
</style>
<template>
  <div>
    <div id="sector">

    </div>
  </div>

</template>

<script>
  import * as echarts from 'echarts'
  export default {
    name:'sector',
    data() {
      return {
        option:{
          title: {
            text: '汽车租赁系统的用户年龄分布',
            top:'15%',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c}'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            top:'20%',
            data: ['0-18岁', '18-22岁', '22-30岁', '30-60岁', '60-100岁']
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '60%',
              center: ['50%', '60%'],
              data: [
                {value: 335, name: '0-18岁'},
                {value: 310, name: '18-22岁'},
                {value: 234, name: '22-30岁'},
                {value: 135, name: '30-60岁'},
                {value: 1548, name: '60-100岁'}
              ],
              itemStyle:{
                normal:{
                  label:{
                    show: true,
                    formatter: '{b} : {c}'
                  },
                  labelLine :{show:true}
                }
              },
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)',
                }
              }
            }
          ]
        }

      }
    },
    mounted() {
      let this_ = this;
      let chart = echarts.init(document.getElementById('sector'));
      chart.setOption(this.option);
      //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
      window.addEventListener('resize',function() {chart.resize()});
    },
    methods: {


    },
    watch: {},
    created() {
    }
  }
</script>


