<template>
  <div class="hello">
    <div style="float: left">
      <h1 style="padding-left: 1px">全部商品 ({{ number }})</h1>
    </div>
    <div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" v-if="false" label="编号" width="100">
        </el-table-column>
        <el-table-column prop="name" label="商品" width="180">
          <template width="100" slot-scope="scope">
            <el-popover placement="top-start" title="" trigger="click">
              <a
                :href="scope.row.fileName"
                target="_blank"
                title="查看最大化图片"
              >
                <img
                  :src="scope.row.fileName"
                  style="width: 300px; height: 300px"
                />
              </a>
              <img
                slot="reference"
                :src="scope.row.fileName"
                style="width: 100px; height: 100px; cursor: pointer"
              />
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="单价" width="180">
        </el-table-column>
        <el-table-column prop="count" label="数量" width="180">
        </el-table-column>
        <el-table-column prop="discount" label="折扣" width="180">
        </el-table-column>
        <el-table-column prop="totalPrice" label="小计"> </el-table-column>
        <el-table-column prop="description" label="描述"> </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="success" size="mini" @click="confirmOrder(scope.$index, scope.row)">购买</el-button>
            <el-popconfirm
              confirm-button-text="好的"
              cancel-button-text="不用了"
              icon="el-icon-info"
              icon-color="red"
              title="确定删除该吗？"
              @confirm="handleDelete(scope.$index, scope.row)"
            >
              <el-button type="danger" size="mini" slot="reference"
                >删除</el-button
              >
            </el-popconfirm>
            <el-button type="warning" size="mini">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "ShopCarInfo",
  data() {
    return {
      number: 0,
      user: {
        uid: "",
        userName: "",
        level: "",
      },
      tableData: [],
    };
  },

  methods: {
    //确认订单
    confirmOrder(index,row) {
      console.error("订单商品信息")
      console.error(row)
      this.$router.push({
        name: '确认订单',
        params: {
          goodsInfo :row
        }
      });
    },
    //删除商品
    handleDelete(index, row) {
      const _this = this;
      
      const params = {
        goodsId: row.id,
        userId: localStorage.getItem("uid"),
      };
      console.error("要删除商品id:" + row.id);
    
      this.$axios.deleteCartShopInfo(params).then((resp) => {
        if (resp.code == 200) {
          _this.$alert("删除商品成功", "消息", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.findCartShop();
            },
          });
        } else {
          _this.$message.error(resp.data.errMessage);
        }
      });
    },
    findCartShop() {
      const _this = this;
      const params = {
        userId: localStorage.getItem("uid"),
        level: localStorage.getItem("level"),
      };

      this.$axios.queryCartShopInfo(params).then((res) => {
        if (res.code === 200) {
          console.error(res);
          _this.tableData = res.data.goodsList;
          _this.number = res.data.goodsList.length;

          // localStorage.removeItem("uid");
          // localStorage.removeItem("level");
        } else {
          //  localStorage.removeItem("uid");
          // localStorage.removeItem("level");
        }
      });
    },

    isLogin() {
      const _this = this;
      const params = "";
      this.$axios.isLogin(params).then(function (res) {
        if (res.code == 200) {
          console.error(res.data.user);
          _this.user = res.data.user;
          localStorage.setItem("uid", _this.user.uid);
          localStorage.setItem("level", _this.user.level);
          
        } else {
          _this.$alert("您还未登录系统，请登录系统查看购物车！！！", {
            confirmButtonText: "确定",
            callback: (action) => {
              _this.$router.push("/UserLogin");
            },
          });
        }
      });
    },
  },
  created() {
    this.isLogin();
    this.findCartShop();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
