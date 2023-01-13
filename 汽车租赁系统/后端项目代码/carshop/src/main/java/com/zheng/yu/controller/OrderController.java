package com.zheng.yu.controller;


import com.zheng.yu.config.FormNoTypeEnum;
import com.zheng.yu.config.handler.BusinessException;
import com.zheng.yu.config.response.Result;
import com.zheng.yu.config.response.ResultCode;
import com.zheng.yu.pojo.Paylog;
import com.zheng.yu.pojo.Scoretable;
import com.zheng.yu.pojo.Shopping_car;
import com.zheng.yu.pojo.Wallet;
import com.zheng.yu.pojo.order.Order_item;
import com.zheng.yu.pojo.order.Order_shipping;
import com.zheng.yu.pojo.order.Orders;
import com.zheng.yu.service.*;
import com.zheng.yu.utils.CurrentTime;
import com.zheng.yu.utils.JWTUtil;
import com.zheng.yu.utils.TransformNum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/order")
@Api(value = "系统订单模块", tags = "系统订单接口")
public class OrderController {

    @Autowired
    DiscountCouponService discountCouponService;

    @Autowired
    ScoretableService scoretableService;

    @Autowired
    WalletService walletService;

    @Autowired
    CarService carService;

    @Autowired
    OrderService orderService;

    @Autowired
    ShoppingCarService shoppingCarService;

    @Autowired
    private FormNoGenerateService formNoGenerateService;

    @RequestMapping(value = "/order/getCar/{order_id}", method = RequestMethod.PUT)
    public Result getCar(@PathVariable("order_id") String order_id) {

        //在取车之前先判断是否已经取过车了
        Orders orders1 = orderService.findOneOrderById(order_id);

        int isGetCar = orders1.getStatus();
        if(isGetCar ==  0 ){
            //还未付款
            return Result.error().data("errMessage","还未付款，请付款后，在规定时间内取车");
        }
        if(isGetCar == 4){ //status= 4 代表已经取车了
            return Result.error().data("errMessage","您已经取过车了，请在规定时间内还车");
        }

        Orders orders = new Orders();
        orders.setOrder_id(order_id);
        int rs = orderService.getCar(orders);
        return Result.ok();

    }


    //用户所有用户已经完成的订单
    @RequestMapping(value = "/order/queryAllFinishOrders", method = RequestMethod.GET)
    public Result queryAllFinishOrders() {

        List<Orders> ordersList = orderService.queryAllFinishOrders();

        Integer totals = ordersList.size();
        HashMap<String, Object> result = new HashMap<>();
        result.put("orders", ordersList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    @Transactional(rollbackFor = Exception.class)//设置检查时异常时回滚事务
    @RequestMapping(value = "/order/returnCar/{order_id}", method = RequestMethod.PUT)
    public Result returnCar(HttpServletRequest request,@PathVariable("order_id") String order_id) {
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println("归还汽车，积分增加" + username);

//        //在进行还车之前需要确定是否已经还车.自动进入历史帝订单\不会存在二次还车
//        Orders orders1 = orderService.findOneOrderById(order_id);
//        if(orders1.getStatus() == 5){
//            return Result.error();//提示已经完成还车
//
//        }
        //1、还车前进行支付判断
        Orders orders1 = orderService.findOneOrderById(order_id);

        int isGetCar = orders1.getStatus();
        if(isGetCar == 0){
            return Result.error().data("errMessage","还未付款，请付款");
        }else if(isGetCar == 1){  //status=1,已经付款，未取车
            return Result.error().data("errMessage","还未取车，请在规定时间取车");
        }

        //1、更具订单找到汽车id
        Order_item order_item = orderService.queryOneOrderItem(order_id);
        if (order_item == null) {
            return Result.error();
        }
        //2、根据汽车id查找汽车数量
        int car_num = carService.findCarNum(order_item.getCar_id());
        int updateCarNum = car_num + order_item.getNum();
        //3、修改汽车数量
        int rss = carService.updateCarNum(order_item.getCar_id(), updateCarNum);
        if (rss < 0) {
            return Result.error();
        }
        Orders orders = new Orders();
        orders.setOrder_id(order_id);
        int rs = orderService.returnCar(orders);
        if(rs < 0){
            return Result.error();
        }
        //用户积分增加
        Scoretable scoretable = scoretableService.findOneScoreById(username);
        Scoretable addScore = new Scoretable();
        int afterScore = scoretable.getScore()+10;//每次10积分
        addScore.setScore(afterScore);
        addScore.setUsername(username);
        int addScoreRs = scoretableService.updateScore(addScore);
        return Result.ok();

    }


    /**
     * 订单支付
     */
    @Transactional(rollbackFor = Exception.class)//设置检查时异常时回滚事务
    @RequestMapping(value = "/order/paymoney", method = RequestMethod.POST)
    public Result payMoney(HttpServletRequest request,@RequestBody Order_item order_item) {
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println("订单支付" + username);
        //1、支付之前首先需要判断是否已经支付过
        Orders orders = orderService.findOneOrderById(order_item.getOrder_id());
        int isPay = orders.getStatus();

        if (isPay != 0) {
            //已经支付过了
            return Result.error().data("errMessage","已支付，请在规定时间内取车");
        } else {
            //还未进行支付
            //查看用户积分，100积分兑换5块钱
            Scoretable scoretable = scoretableService.findOneScoreById(username);//查询积分
//
            //调用工具类进行准换。积分大于100，有抵押金，小于100 则是0
            int diya = TransformNum.getMoney(scoretable.getScore());
            if(diya > 0){
                //进行了积分兑换、需要修改用户的积分
                int updateScore = scoretable.getScore()-diya*20;
                Scoretable scoretable1 = new Scoretable();
                scoretable1.setUsername(username);
                scoretable1.setScore(updateScore);
                int rs = scoretableService.updateScore(scoretable1);
                if(rs < 0){
                    return  Result.error();
                }

            }
            //调用工具类进行优惠券的使用
            if(order_item.getDiscountType() == null){
                order_item.setDiscountType(0.0);
            }
            Double usedDiscountneedPay = TransformNum.useDiscountNeedPay(order_item.getTotal_price(),order_item.getDiscountType());

            Double needPayMoney = usedDiscountneedPay - diya;
            //同时删除用户的优惠券
//            int discountId = order_item.getDiscoundId();//
//            int rs = discountCouponService.deletePersionDiscountById(discountId);
//            if(rs < 0 ){
//                return Result.error();
//            }


//            Order_item order_item1 = orderService.queryOneOrderItem(order_item.getOrder_id());
//            Double needPayMoney = order_item1.getTotal_price();

            //查看钱包的钱是否足够
            Wallet walletuser = walletService.querywallet(username);
            Double beforemoney = walletuser.getMoney();
            if (beforemoney < needPayMoney) {
                //钱不够
                return Result.error().data("errMessage","余额不够！！！");

            } else {
                Double updateMoney = beforemoney - needPayMoney;//扣除金额后
                Wallet wallet = new Wallet();
                wallet.setMoney(updateMoney);
                wallet.setUsername(username);
                //用户钱包减少
                int rs1 = walletService.updateWallet(wallet);
                if (rs1 < 0) {
                    return Result.error();
                }

                //商家钱包增加
                Wallet admin = walletService.querywallet("admin");
                Double adminBeforeMoney = admin.getMoney();
                Double adminAfterMoney = adminBeforeMoney + needPayMoney;

                Wallet adminWallet = new Wallet();
                adminWallet.setMoney(adminAfterMoney);
                adminWallet.setUsername("admin");
                //商家方面的钱增加
                int rs11 = walletService.updateWallet(adminWallet);
                if (rs11 < 0) {
                    return Result.error();
                }

                //记录购买日志
                Paylog paylog = new Paylog();
                String pay_time = CurrentTime.getCurrentTime();
                paylog.setUsername(username);
                paylog.setMoney(needPayMoney);
                paylog.setPay_time(pay_time);
                int rs2 = walletService.addPaylog(paylog);
                if(rs2 < 0) return Result.error();
                //汽车库存数量减少,订单生成的时候已经减少。
                //修改汽车的状态
                int result = orderService.updateCarPayStatus(order_item.getOrder_id());
                if (result > 0) {
                    return Result.ok();
                } else {
                    return Result.error();
                }


            }


        }


    }


    /**
     * 订单信息表的增删改查
     */
    @ApiOperation(value = "生成订单表", notes = "订单信息表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Shopping_car")
    })
    @Transactional(rollbackFor = Exception.class)//设置检查时异常时回滚事务
    @RequestMapping(value = "/order/addorders", method = RequestMethod.POST)
    public Result addOrders(HttpServletRequest request, @RequestBody Shopping_car shopping_car) throws Exception {
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username);

        //查看汽车库存数量是否足够
        int car_num = carService.findCarNum(shopping_car.getCar_id());
        System.out.println(shopping_car.getCar_id());
        if (car_num == 0 || (car_num < shopping_car.getNum())) {
            System.out.println("汽车数量不足");
            return Result.error();//汽车数量不足
        }

        //汽车数量足够,减去租掉的车辆
        int updateCarNum = car_num - shopping_car.getNum();
        int updateCarRs = carService.updateCarNum(shopping_car.getCar_id(), updateCarNum);
        if (updateCarRs < 0) {
            return Result.error();
        }


        //设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //开始时间
        Date startDate = sdf.parse(shopping_car.getStartTime());
        //结束时间
        Date endDate = sdf.parse(shopping_car.getEndTime());

        String usetime = shopping_car.getStartTime() + "至" + shopping_car.getEndTime();
        System.out.println(usetime);

        //相差天数
        int betweenDate = (int) ((endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000));


//         Wallet walletuser = walletService.querywallet(username);
//         Double beforemoney = walletuser.getMoney();
//        System.out.println("租借天数"+betweenDate);
//        //租借消耗的钱 ：数量*天数*押金
        Double updateMoney = shopping_car.getGuarantee_money() * betweenDate * shopping_car.getNum();
//        if(updateMoney > beforemoney){
//            //不够充值
//            return Result.error();
//        }else{
//            Double aftermoney = beforemoney-updateMoney;
//
//            Wallet wallet= new Wallet();
//            wallet.setMoney(aftermoney);
//            wallet.setUsername(username);
//            //用户钱包减少
//            int rs1 = walletService.updateWallet(wallet);

//            //商家钱包增加
//            Wallet admin = walletService.querywallet("admin");
//            Double adminBeforeMoney = admin.getMoney();
//            Double adminAfterMoney = adminBeforeMoney + updateMoney;
//
//            Wallet adminWallet= new Wallet();
//            adminWallet.setMoney(adminAfterMoney);
//            adminWallet.setUsername("admin");
//            //商家方面的钱增加
//            int rs11 = walletService.updateWallet(adminWallet);
//            if(rs11 < 0){
//                return Result.error();
//            }
//
//            //记录购买日志
//            Paylog paylog = new Paylog();
//            String pay_time = CurrentTime.getCurrentTime();
//            paylog.setUsername(username);
//            paylog.setMoney(updateMoney);
//            paylog.setPay_time(pay_time);
//            int rs2 = walletService.addPaylog(paylog);


        Orders orders = new Orders();
        //订单id
        String order_id = formNoGenerateService.generateFormNo(FormNoTypeEnum.FK_ORDER);
        //订单创建时间
        String create_time = CurrentTime.getCurrentTime();

        orders.setUsetime(usetime);
        orders.setOrder_id(order_id);
//        orders.setPrepayment(updateMoney);
        orders.setCreate_time(create_time);
        orders.setUsername(username);
        orders.setStatus(0);//未支付
        orders.setUser_cancle(0);//用户是否取消订单0:未取消。1：取消
        orders.setUser_log_cancle(0);//用户是否删除历史订单记录（0：未删除。1：删除）
        orders.setAddress(shopping_car.getAddress());
        orders.setPhone(shopping_car.getPhone());


        //创建订单
        int rs = orderService.addOrders(orders);

        //创建订单详情

        Order_item order_item = new Order_item();
        String id = formNoGenerateService.generateFormNo(FormNoTypeEnum.YF_ORDER);
        order_item.setId(id);
        order_item.setOrder_id(order_id);
        order_item.setCar_id(shopping_car.getCar_id());
        order_item.setNum(shopping_car.getNum());//租借数量
        order_item.setDaynum(betweenDate);    //租借天数
        order_item.setName(shopping_car.getName());
        order_item.setColor(shopping_car.getColor());
        order_item.setType(shopping_car.getType());
        order_item.setImageUrl(shopping_car.getImageUrl());
        order_item.setPrice(shopping_car.getGuarantee_money());//一天押金
        order_item.setTotal_price(updateMoney);//总押金


        int rss = orderService.addOrderItem(order_item);

        if ((rs > 0) && (rss > 0)) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.ADD_ORDERS_ERROR.getCode(),
                    ResultCode.ADD_ORDERS_ERROR.getMessage());
        }

//        }


    }

    //查询订单信息
    @ApiOperation(value = "条件查询", notes = "条件查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Orders")
    )
    @RequestMapping(value = "/order/conditionorders", method = RequestMethod.POST)
    public Result ConditionOneOrders(@RequestBody Orders orders) {
        List<Orders> ordersList = orderService.ConditionOneOrders(orders);

        Integer totals = ordersList.size();
        HashMap<String, Object> result = new HashMap<>();
        result.put("orders", ordersList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }


    @ApiOperation(value = "删除订单信息表", notes = "根据订单id删除订单信息表")
    @ApiImplicitParam(name = "id", value = "订单信息表id", required = true, dataType = "Integer")
    @RequestMapping(value = "/order/deleteOrderById/{id}", method = RequestMethod.DELETE)
    public Result deleteOrderById(@PathVariable String id) {
        int rs = orderService.deleteOrderById(id);//删除订单
        int rss = orderService.deleteOrderItemById(id);//删除订单详情

        if (rs > 0 && rss > 0) {
            return Result.ok();
        } else {
            throw new BusinessException(ResultCode.DELETE_ORDERSBYID_ERROR.getCode(),
                    ResultCode.DELETE_ORDERSBYID_ERROR.getMessage());
        }
    }

    @RequestMapping(value = "/order/deleteOrderLogical/{id}", method = RequestMethod.PUT)
    public Result delete_Persion_Order_Logical(HttpServletRequest request, @PathVariable String id) {

        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println("用户取消订单" + username);
        //1、更具订单找到汽车id
        Order_item order_item = orderService.queryOneOrderItem(id);
        if (order_item == null) {
            return Result.error();
        }
        //2、根据汽车id查找汽车数量
        int car_num = carService.findCarNum(order_item.getCar_id());
        //取消订单、首先判断用户是否已经付款要归还汽车，要将钱退回，同时从商家的账户退回钱
        Orders orders = orderService.findOneOrderById(id);//查找汽车订单
        int status = orders.getStatus();
        if (status == 0) {
            //用户未支付，只需要修改汽车库存
            int updateCarNum = car_num + order_item.getNum();
            //3、修改汽车数量
            int rss = carService.updateCarNum(order_item.getCar_id(), updateCarNum);

            if (rss < 0) {
                return Result.error();
            }

            //未购买取消订单
            int rs2 = orderService.delete_Persion_Order_Logical(id);
            if (rs2 > 0) {
                return Result.ok();
            } else {
                return Result.error();
            }
        } else {
            //已购买取消订单
            int updateCarNum = car_num + order_item.getNum();
            //3、修改汽车数量
            int rss = carService.updateCarNum(order_item.getCar_id(), updateCarNum);
            if (rss < 0) {
                return Result.error();
            }

            //将钱重新退回到用户账户
            Wallet walletuser = walletService.querywallet(username);
            Double beforemoney = walletuser.getMoney();
            Double aftermoney = beforemoney + order_item.getTotal_price();
            Wallet wallet = new Wallet();
            wallet.setMoney(aftermoney);
            wallet.setUsername(username);
            //用户钱包的钱退回
            int rs1 = walletService.updateWallet(wallet);
            if (rs1 < 0) {
                return Result.error();
            }
            //商家的钱扣除
            Wallet admin = walletService.querywallet("admin");
            Double adminBeforeMoney = admin.getMoney();
            Double adminAfterMoney = adminBeforeMoney - order_item.getTotal_price();
            Wallet adminWallet = new Wallet();
            adminWallet.setMoney(adminAfterMoney);
            adminWallet.setUsername("admin");
            //商家方面的钱退回
            int rs11 = walletService.updateWallet(adminWallet);
            if (rs11 < 0) {
                return Result.error();
            }

            int rs = orderService.delete_Persion_Order_Logical(id);
            if (rs > 0) {
                return Result.ok();
            } else {
                return Result.error();
            }

        }


    }

    @ApiOperation(value = "查询某一个订单信息表", notes = "根据订单id查询订单信息表")
    @RequestMapping(value = "/order/queryOneOrders", method = RequestMethod.GET)
    public Result queryOneOrders(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username + "1234");
        Orders orders = orderService.queryOneOrders(username);
        if (orders != null) {
            return Result.ok().data("oneOrder", orders);
        } else {
            throw new BusinessException(ResultCode.QUERY_ONEORDER_ERROR.getCode(),
                    ResultCode.QUERY_ONEORDER_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "查询所有的订单", notes = "查询所有的订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "当前页大小", required = true, dataType = "Integer")
    }
    )
    @RequestMapping(value = "/order/queryAllOrders/{page}/{size}", method = RequestMethod.GET)
    public Result queryAllOrders(@PathVariable("page") Integer Page, @PathVariable("size") Integer size) {

        //准备数据 通过这两个参数，可以算出start   计算方法 start=size(page-1)
        int currentPage = Page;//当前是第几页
        int pageSize = size; //页面大小
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("startIndex", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Orders> orderList = orderService.queryAllOrders(map);
        HashMap<String, Object> result = new HashMap<>();
        Long totals = orderService.findOrderTotals();
//        int totals = orderList.size();
        result.put("orders", orderList);
        result.put("totals", totals);
        return Result.ok().data("result", result);


    }

    /**
     * 订单详情表的增删改查
     */

    @ApiOperation(value = "新增订单详情表", notes = "新增加订单详情表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Order_item")
    )
    @RequestMapping(value = "/order/addOrderItem", method = RequestMethod.POST)
    public Result addOrderItem(@RequestBody Order_item order_item) {
        String id = formNoGenerateService.generateFormNo(FormNoTypeEnum.YF_ORDER);
        order_item.setId(id);
        String order_id = formNoGenerateService.generateFormNo(FormNoTypeEnum.FK_ORDER);
        order_item.setOrder_id(order_id);
        int rs = orderService.addOrderItem(order_item);
        return Result.ok();

    }


    @ApiOperation(value = "查询所有的订单详情", notes = "查询所有的订单详情")
    @RequestMapping(value = "/order/queryAllOrderItem", method = RequestMethod.GET)
    public Result queryAllOrderItem() {
        List<Order_item> order_itemList = orderService.queryAllOrderItem();
        return Result.ok().data("orderItemList", order_itemList);
    }

    @ApiOperation(value = "查询某一个订单详情表", notes = "根据订单id查询订单详情表")
    @ApiImplicitParam(name = "id", value = "订单信息表id", required = true, dataType = "Integer")
    @RequestMapping(value = "/order/queryOneOrderItem/{id}", method = RequestMethod.GET)
    public Result queryOneOrderItem(@PathVariable String id) {
        Order_item order_item = orderService.queryOneOrderItem(id);
        return Result.ok().data("orderItem", order_item);
    }

    /**
     * 订单配送表的增删改查
     */

    @ApiOperation(value = "新增订单配送表", notes = "新增加订单配送表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Order_item")
    )
    @RequestMapping(value = "/order/Order_shipping", method = RequestMethod.POST)
    public Result addOrderShipping(@RequestBody Order_shipping order_shipping) {
        int rs = orderService.addOrderShipping(order_shipping);
        return Result.ok();
    }

    @ApiOperation(value = "删除订单配送表", notes = "根据订单id删除订单配送表")
    @ApiImplicitParam(name = "id", value = "订单信息表id", required = true, dataType = "Integer")
    @RequestMapping(value = "/order/deleteOrderShipping/{id}", method = RequestMethod.DELETE)
    public Result deleteOrderShipping(@PathVariable String id) {
        int rs = orderService.deleteOrderShipping(id);
        return Result.ok();
    }


    @ApiOperation(value = "修改配送表", notes = "根据id修改配送表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Order_shipping")
    )
    @RequestMapping(value = "/order/updateOrderShipping", method = RequestMethod.PUT)
    public Result updateOrderShipping(@RequestBody Order_shipping order_shipping) {
        int rs = orderService.updateOrderShipping(order_shipping);
        return Result.ok();
    }

    @ApiOperation(value = "查询所有的配送信息", notes = "查询所有的配送信息")
    @RequestMapping(value = "/order/queryAllOrderShipping", method = RequestMethod.GET)
    public Result queryAllOrderShipping() {
        List<Order_shipping> order_shippingList = orderService.queryAllOrderShipping();
        return Result.ok().data("order_shippingList", order_shippingList);
    }

    @ApiOperation(value = "查询某一个订单配送表", notes = "根据订单id查询订单配送表")
    @ApiImplicitParam(name = "id", value = "订单信息表id", required = true, dataType = "Integer")
    @RequestMapping(value = "/order/queryOneOrderShipping/{id}", method = RequestMethod.GET)
    public Result queryOneOrderShipping(@PathVariable String id) {
        Order_shipping order_shipping = orderService.queryOneOrderShipping(id);
        return Result.ok().data("order_shipping", order_shipping);
    }


    //用户查询个人订单信息表
    @RequestMapping(value = "/order/findOrderOnlyPersion", method = RequestMethod.GET)
    public Result findOrderOnlyPersion(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        List<Orders> ordersList = orderService.findOrderOnlyPersion(username);

        Integer totals = ordersList.size();
        HashMap<String, Object> result = new HashMap<>();
        result.put("orders", ordersList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    //用户查询个人订单信息表
    @RequestMapping(value = "/order/findHistoryOrderPersion", method = RequestMethod.GET)
    public Result findHistoryOrderPersion(HttpServletRequest request) {
        //解析token
        String token = request.getHeader("Authorization");
        String username = JWTUtil.getUsername(token);
        System.out.println(username);

        List<Orders> ordersList = orderService.findHistoryOrderPersion(username);

        Integer totals = ordersList.size();
        HashMap<String, Object> result = new HashMap<>();
        result.put("orders", ordersList);
        result.put("totals", totals);
        return Result.ok().data("result", result);

    }

    //逻辑删除历史订单记录
    @Transactional(rollbackFor = Exception.class)//设置检查时异常时回滚事务
    @RequestMapping(value = "/order/deleteHistoryOrderPersion/{order_id}", method = RequestMethod.DELETE)
    public Result deleteHistoryOrderPersion(@PathVariable String order_id) throws Exception {
        int rs = orderService.deleteHistoryOrderPersion(order_id);
        return Result.ok();
    }

    //查询商品销售前三
    @RequestMapping(value = "/order/carReformShow", method = RequestMethod.GET)
    public Result carReformShow() {

        List<HashMap<String, Object>> list = orderService.saleInFirstThree();
        if (list != null) {
            return Result.ok().data("list", list);
        } else {
            return Result.error();
        }


    }


}
