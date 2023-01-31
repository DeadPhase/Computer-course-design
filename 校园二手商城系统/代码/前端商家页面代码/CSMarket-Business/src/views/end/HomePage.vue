<template>
  <div id="main" style="padding: 0 20px">
    <div>
      <!-- 色块 -->
      <div style="border: 1px solid #ccc; margin-top: 10px">
        <div
          style="
            padding: 10px;
            border-bottom: 1px solid #ccc;
            color: #00ba8b;
            font-size: 20px;
            text-align: left;
            background-image: linear-gradient(to bottom, #f9f6f1, #eee);
          "
        >
          <b>数据概览</b>
        </div>
        <div
          style="
            padding: 10px;
            background: #ffffff;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-start;
          "
        >
          <div style="width: 10%; margin-right: 10px; margin-bottom: 10px">
            <div style="font-size: 16px; text-align: center; color: #f9f6f1">
              <div
                style="
                  width: 100%;
                  padding: 10px 0;
                  background-color: #00ba8b;
                  border-radius: 5px;
                "
              >
                用户总数<br />{{ totalUser }}
              </div>
            </div>
          </div>
          <div style="width: 10%; margin-right: 10px; margin-bottom: 10px">
            <div style="font-size: 16px; text-align: center; color: #f9f6f1">
              <div
                style="
                  width: 100%;
                  padding: 10px 0;
                  background-color: #00ba8b;
                  border-radius: 5px;
                "
              >
                总销售额<br />{{ totalPrice }}
              </div>
            </div>
          </div>
          <div style="width: 10%; margin-right: 10px; margin-bottom: 10px">
            <div style="font-size: 16px; text-align: center; color: #f9f6f1">
              <div
                style="
                  width: 100%;
                  padding: 10px 0;
                  background-color: #00ba8b;
                  border-radius: 5px;
                "
              >
                总销量<br />{{ totalShopping }}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- echarts -->
    <div class="row">
      <div style="margin-top: 20px">
        <div
          class="panel-heading"
          style="
            color: goldenrod;
            padding: 10px;
            border-bottom: 1px solid #ccc;
            color: #00ba8b;
            font-size: 20px;
            text-align: left;
            background-image: linear-gradient(to bottom, #f9f6f1, #eee);
          "
        >
          数据分析
        </div>
        <div
          class="panel-body"
          style="
            padding: 10px;
            background: #ffffff;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-start;
          "
        >
          <div
            ref="chart1"
            style="width: 50%; height: 450px; float: left"
          ></div>

          <div
            ref="chart2"
            style="width: 50%; height: 450px; float: right"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//局部引用
const echarts = require("echarts");
export default {
  data() {
    return {
      totalUser: 20,
      totalPrice: 569,
      totalShopping: 89,
    };
  },
  methods: {
    initChart1() {
      const _this = this;
      // 基于准备好的dom，初始化echarts实例
      let myChart1 = echarts.init(this.$refs.chart1);

      myChart1.setOption({
        title: {
          text: "某站点用户访问来源",
          top: "5%",
          left: "center",
        },
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c}",
        },
        legend: {
          orient: "vertical",
          left: "left",
          top: "15%",
          data: ["直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"],
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            center: ["50%", "60%"],
            data: [
              { value: 335, name: "直接访问" },
              { value: 310, name: "邮件营销" },
              { value: 234, name: "联盟广告" },
              { value: 135, name: "视频广告" },
              { value: 1548, name: "搜索引擎" },
            ],
            itemStyle: {
              normal: {
                label: {
                  show: true,
                  formatter: "{b} : {c}",
                },
                labelLine: { show: true },
              },
            },
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
    },

    initChart2() {
      const _this = this;
      // 基于准备好的dom，初始化echarts实例
      let myChart2 = echarts.init(this.$refs.chart2);
      myChart2.setOption({
        title: {
          text: "Stacked Line",
        },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["Email", "Union Ads", "Video Ads", "Direct", "Search Engine"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        toolbox: {
          feature: {
            saveAsImage: {},
          },
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: [
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月",
          ],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            name: "Email",
            type: "line",
            stack: "Total",
            data: [
              120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230,
            ],
          },
          {
            name: "Union Ads",
            type: "line",
            stack: "Total",
            data: [
              220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330,
            ],
          },
          {
            name: "Video Ads",
            type: "line",
            stack: "Total",
            data: [
              150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190, 330,
            ],
          },
          {
            name: "Direct",
            type: "line",
            stack: "Total",
            data: [
              320, 332, 301, 334, 390, 330, 320, 320, 332, 301, 334, 390, 330,
            ],
          },
          {
            name: "Search Engine",
            type: "line",
            stack: "Total",
            data: [
              820, 932, 901, 934, 1290, 1330, 1320, 820, 932, 901, 934, 1290,
              1330,
            ],
          },
        ],
      });
    },
  },
  //一加载页面就调用
  mounted() {
    this.initChart1();
    this.initChart2();
  },
};
</script>

<style scoped="scoped">
</style>

