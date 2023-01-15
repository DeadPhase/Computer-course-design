package com.example.zheng.controller;


import com.example.zheng.Utils.CurrentTime;
import com.example.zheng.Utils.UUIDutils;
import com.example.zheng.mapper.CustomerMapper;
import com.example.zheng.pojo.*;
import com.example.zheng.service.BooksService;
import com.example.zheng.service.ShopCartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static java.lang.System.out;

@Controller
@RequestMapping("/shop")
public class ShopCartController {

    @Autowired
    ShopCartService shopCartService;

    @Autowired
    BooksService booksService;//查询书籍

    @Autowired
    private CustomerMapper customerMapper;


    //将某一个订单的收货地址显示到前台。通过点击订单旁边的地址按钮、跳转到前台

    @RequestMapping("/toOrderAddress")
    public String toOrderAddress(String usId,Model model){

        Hvaddress hvaddress = null;
        try {
            hvaddress = shopCartService.getOrderAddress(usId);

            if(hvaddress != null){
                model.addAttribute("orderAddress",hvaddress);
                return "orderAddress";
            }else{
                //错误的情况、跳转到其他页面
                return "customerIndex";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


      return "customerIndex";
    }

    //选择收货地址、将收货地址和当前的订单进行关联
    @RequestMapping("/chooseAddress")
    public String chooseAddress(String haId,Model model){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        //首先判断是否需要生成临时订单、先便利订单表
        UserShopList userShopList = shopCartService.queryUserShopList(parent.getUsercount());

        if(userShopList == null){
            return "customerIndex";
        }else{
            String usId = userShopList.getUsId();
            String oaId = UUIDutils.getUUID();
            String haI =haId;
            Order_address order_address = new Order_address(oaId,usId,haI);
            int rs = shopCartService.addOrderAddress(order_address);

            if(rs > 0){

                UserShopList userShopLists = shopCartService.queryUserShopList(parent.getUsercount());
                if(userShopList == null){
                    return "redirect:/shop/shopList";//购物车页面
                }else{
                    model.addAttribute("userlist11",userShopLists);
                }

                try {
                    List<Shoplist> shoplists = shopCartService.shopList(parent.getUsercount());
                    if(shoplists == null){
                        model.addAttribute("error","查询失败");
                        return "customerIndex";
                    }
                    model.addAttribute("shopList",shoplists);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }



        return "list_of_goods";//返回当前的订单
    }




    //跳转到评论页面
    @RequestMapping("/toComment")
    public String toComment(Model model,String bgId){

        Shop_list_buylog listBuylog = booksService.lookscId(bgId);
        String scId = listBuylog.getSlId();

        Books book = null;
        try {
            book = booksService.lookBookId(scId);

            if(book != null){
                //2、根据书籍iD 、查看书籍的详细信息。额外评论
                model.addAttribute("books",book);
                model.addAttribute("success","查询书籍成功");
                return "bookcomment";
            }else{
                model.addAttribute("error","查询书籍失败");
                return "customerIndex";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "customerIndex";

    }

    //修改发货的状态
    @RequestMapping("/fahuo")
    public String fahuo(String usId){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        shopCartService.updateFahuo(parent.getUsercount(),usId);
        return "redirect:/shop/status";//返回首页
    }


    @Mylog(value = "收货")  //这里添加了AOP的自定义注解
    //修改收货的状态
    @RequestMapping("/gain")
    public String finishGain(String usId){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        shopCartService.updateGain(parent.getUsercount(),usId);
        return "redirect:/shop/status";//返回首页
    }

    //查看商品的订单状态
    @RequestMapping("/status")
    public String buyBookStatus(Model model){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        try {
            List<UserShopList> list = shopCartService.queryUserShopListBy(parent.getUsercount());
            if(list != null){
                model.addAttribute("orderList",list);
                model.addAttribute("成功");
            }else{
                model.addAttribute("失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "bookstatus";
    }

    //跳转到一个账单对应的详细信息
    @RequestMapping("/buylogdetail")
    public String toBuyLogDetail(Model model,String usId){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        try {
            List<Buglog> list=  shopCartService.buylogDetail(parent.getUsercount(),usId);
            if(list != null){
                model.addAttribute("buylogdetail",list);
                model.addAttribute("正确");
                model.addAttribute("usId",usId);
            }else{
                model.addAttribute("错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "buylogdetail";
    }

    //购买的订单记录

    @RequestMapping("/buylog")
    public String toBuyLog(Model model){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        try {
            List<UserShopList> list = shopCartService.queryUserShopListBy(parent.getUsercount());
            if(list != null){
                model.addAttribute("buylogist",list);
                model.addAttribute("成功");
            }else{
                model.addAttribute("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "buylog";
    }

    //跳转到当前的订单P
    @RequestMapping("/goodsList")
    public  String toshopcartList(Model model){


        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        UserShopList userShopList = shopCartService.queryUserShopList(parent.getUsercount());
        if(userShopList == null){
            return "redirect:/shop/shopList";//购物车页面
        }else{
            model.addAttribute("userlist11",userShopList);
        }

        try {
            List<Shoplist> shoplists = shopCartService.shopList(parent.getUsercount());
            if(shoplists == null){
                model.addAttribute("error","查询失败");
                return "newIndex";
            }
            model.addAttribute("shopList",shoplists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list_of_goods";
    }


    @Mylog(value = "购买操作")  //这里添加了AOP的自定义注解
    //购买操作
    @RequestMapping("/tobuyBook")
    public String buyBooks(){

        //判断是否添添加收货地址
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        //首先判断是否需要生成临时订单、先便利订单表
        UserShopList userShopList = shopCartService.queryUserShopList(parent.getUsercount());

        //查询订单和地址联合表、是否添加购买地址

        Order_address order_address = shopCartService.queryOrderAddress(userShopList.getUsId());

        if(order_address == null){
            //还未添加收货地址
            return "addOrderAddress";
        }else{
            //采用逻辑删除
            List<Shoplist> list = shopCartService.buyShopList(parent.getUsercount());
            double sumprice =0;
            double price=0;
            int count =0;
            for(Shoplist shopList:list){
                count =shopList.getCount();
                price =shopList.getPrice();
                price=count*price;//得到总价格
                sumprice=sumprice+price;
            }

            //查看钱包的前够不够
            Wallet wallet =  customerMapper.querywallet(parent.getUsercount());
            Double money =wallet.getBalance();//
            if(money<sumprice){
                //钱不够
                return "redirect:/shop/notEnougthMoney";
            }else{
                //修改购物车中商品的状态
                //余额减少
                Double newMoney = money-sumprice;//将新的金额设置位剩余金额
                customerMapper.updateWallet(newMoney,parent.getUsercount());//金额增加

                //同时、购物车中商品减少、直接修改状态
                List<Shoplist> cartList = shopCartService.querySomeBook(parent.getUsercount());
                int len =  cartList.size();
                for(Shoplist shoplist:cartList){
                    String scId = shoplist.getSlId();
                    shopCartService.deleteShop(scId);

                }

                for(Shoplist shopList:list){
                    String slId = shopList.getSlId();
                    shopCartService.updateShopList(slId);//遍历循环修改购买状态

                }

                //同时修改该订单的状态为已提交
                shopCartService.updateUserShopList(parent.getUsercount());
                shopCartService.updatePay(parent.getUsercount());//更改付款的信息

//                return "redirect:/shop/shopList";

            }

        }


        return "redirect:/shop/shopList";


    }

    //跳转到钱不够的页面
    @RequestMapping("/notEnougthMoney")
    public String notEnougthMoney(){
        return "notEnougthMoney";
    }



    //购买商品
    @RequestMapping("/tobuy")
    public String buyBook(String scId,Model model){

        /**
         * 1、首先根据scId查询出商品、放入临时的表。
         * 2、此时生成一个临时订单、设置订单的状态为0。代表未结账
         * 3、如果继续添加购买的书籍、再次根据scId查询所要购买的商品信息，此时需要根据临时订单的状态。是放入当前的临时订单还是生成新的订单
         *
          */

        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        //首先判断是否需要生成临时订单、先便利订单表
        UserShopList userShopList = shopCartService.queryUserShopList(parent.getUsercount());

        if(userShopList != null){
            //存在临时订单表、并且还未提交

            //首先将需要购买的商品信心存入一张订单表中
            //查询出当前商品的信息
            ShopCart shopCart =shopCartService.shopById(scId);
            //首先看购物单中的商品是否已经存在
            String scID =shopCart.getScId();
            Shoplist rss = shopCartService.queryShopListById(scID);

            if(rss != null){
                //已经存在数据，跳转 页面。提示信息
                return "alreayBuy";
            }else{

                //将该商品放入临时购物单中
                Shoplist shoplist = new Shoplist();
                shoplist.setSlId(shopCart.getScId());
                shoplist.setBookName(shopCart.getBookName());
                shoplist.setCount(shopCart.getCount());
                shoplist.setPrice(shopCart.getPrice());
                shoplist.setIs_buy(0);
                int rs = shopCartService.addShopList(shoplist);//将商品放入临时购物单中


                //记录日志
                Buglog buglog = new Buglog();
                String bgId =UUIDutils.getUUID();
                buglog.setBgId(bgId);
                buglog.setBookName(shopCart.getBookName());
                buglog.setCount(shopCart.getCount());
                buglog.setPrice(shopCart.getPrice());
                buglog.setUsId(userShopList.getUsId());
                shopCartService.addBuyLog(buglog);

                Shop_list_buylog shop_list = new Shop_list_buylog();
                String slId =UUIDutils.getUUID();
                shop_list.setSlbId(slId);
                shop_list.setSlId(shopCart.getScId());
                shop_list.setBgId(bgId);
                booksService.addListBuyLog(shop_list);

                //此时需要将购物单和临时商品表建立关系
                UserShopRelation userShopRelation = new UserShopRelation();
                String usrId = UUIDutils.getUUID();
                userShopRelation.setUsrId(usrId);
                userShopRelation.setUsercount(parent.getUsercount());
                userShopRelation.setSlId(shopCart.getScId());

                int rsss = shopCartService.addUserShopRelation(userShopRelation);//购买的商品

                //然后将查询到的数据显示在前台中
                List<Shoplist> shoplists = shopCartService.shopList(parent.getUsercount());
                model.addAttribute("shopList",shoplists);
                model.addAttribute("userlist11",userShopList);
                return "list_of_goods";


            }


        }else{
            //需要新创建一个订单表
            UserShopList userShopList1 = new UserShopList();
            String usId = UUIDutils.getUUID();
            String time = CurrentTime.getCurrentTime();
            userShopList1.setUsId(usId);
            userShopList1.setUsercount(parent.getUsercount());
            userShopList1.setIs_status(0);
            userShopList1.setIs_time(time);
            userShopList1.setIs_pay(0);
            userShopList1.setIs_fahuo(0);
            userShopList1.setIs_gain(0);
            userShopList1.setIs_comment(0);
            userShopList1.setIs_all(0);
            //将数据插入新的订单表中购物单
            shopCartService.addUserShopList(userShopList1);

            //查询出当前商品的信息
            ShopCart shopCart =shopCartService.shopById(scId);

            //首先看购物单中的商品是否已经存在
            String scID =shopCart.getScId();
            Shoplist rss = shopCartService.queryShopListById(scID);
            if(rss != null){
                //已经存在数据，跳转 页面。提示信息
                return "alreayBuy";
            }else{


                //将该商品放入临时购物单中
                Shoplist shoplist = new Shoplist();
//          String slId = UUIDutils.getUUID();//加入临时购物车的商品主键

                shoplist.setSlId(shopCart.getScId());
                shoplist.setBookName(shopCart.getBookName());
                shoplist.setCount(shopCart.getCount());
                shoplist.setPrice(shopCart.getPrice());
                shoplist.setIs_buy(0);
                int rs = shopCartService.addShopList(shoplist);//将商品放入临时购物单中

                //记录日志
                Buglog buglog = new Buglog();
                String bgId =UUIDutils.getUUID();
                buglog.setBgId(bgId);
                buglog.setBookName(shopCart.getBookName());
                buglog.setCount(shopCart.getCount());
                buglog.setPrice(shopCart.getPrice());
                buglog.setUsId(usId);
                shopCartService.addBuyLog(buglog);

                Shop_list_buylog shop_list = new Shop_list_buylog();
                String slId =UUIDutils.getUUID();
                shop_list.setSlbId(slId);
                shop_list.setSlId(shopCart.getScId());
                shop_list.setBgId(bgId);
                booksService.addListBuyLog(shop_list);

                //此时需要将购物单和临时商品表建立关系
                UserShopRelation userShopRelation = new UserShopRelation();
                String usrId = UUIDutils.getUUID();
                userShopRelation.setUsrId(usrId);
                userShopRelation.setUsercount(parent.getUsercount());
                userShopRelation.setSlId(shopCart.getScId());

                int rssss = shopCartService.addUserShopRelation(userShopRelation);

                //然后将查询到的数据显示在前台中
                List<Shoplist> shoplists = shopCartService.shopList(parent.getUsercount());
                model.addAttribute("shopList",shoplists);
                model.addAttribute("userlist11",userShopList1);
                return "list_of_goods";

            }





        }


    }


    @Mylog(value = "撤销订单")  //这里添加了AOP的自定义注解
    //撤销一次购买
    @RequestMapping("/cancleOrder")
    public String cancleBuyBook(String usId){
        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        //根据订单主键删除
        shopCartService.cancleBuyOrder(usId);
        List<UserShopRelation> list = shopCartService.userShopRelation(parent.getUsercount());
        //删除临时表
        shopCartService.deleteList(parent.getUsercount());
        //删除联合表
        for(UserShopRelation usr:list){
            String usrId = usr.getUsrId();
            shopCartService.deleteUSR(usrId);
        }


        return "customerIndex";

    }


    @Mylog(value = "修改书籍信息")  //这里添加了AOP的自定义注解
    //修改购物车中的书籍信息
    @RequestMapping("/updateShop")
    public String updateShop(Model model,int count,String scId){
        try {
            ShopCart shopCart =new ShopCart();//传入整个对象的目的是：防止后来的需求该。现在只是修改了数量
            shopCart.setCount(count);
            shopCart.setScId(scId);
            int rs = shopCartService.updateShop(shopCart);
            if(rs > 0){
                model.addAttribute("success");
                return "redirect:/shop/shopList";
            }else{
                model.addAttribute("failure");
                return "redirect:/shop/shopList";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/shop/shopList";

    }
    //跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdateShop(Model model,String scId){
        try {
            ShopCart shopCart =shopCartService.shopById(scId);
            if(shopCart != null){
                model.addAttribute("shopcart",shopCart);
                return "updateshopcart";
            }else{
                model.addAttribute("failure");
                return "redirect:/shop/shopList";//查询不成功、返回购物车页面
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/shop/shopList";//其他情况、返回购物车页面

    }



    @Mylog(value = "删除购物车商品")  //这里添加了AOP的自定义注解
    //删除购物车里边的商品
    @RequestMapping("/deleteShop")
    public String deleteShop(String scId){

        try {
            int i=shopCartService.deleteShop(scId);
            if(i > 0){
                //删除成功、重新刷新购物车
                return "redirect:/shop/shopList";
            }else{
                //提示错误信息
                return "redirect:/shop/shopList";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/shop/shopList";
    }

    @Mylog(value = "添加商品到购物车")  //这里添加了AOP的自定义注解
    //添加商品到购物车
    @RequestMapping("/addshop")
    public String addShop(String bookId,Model model){
        //封装购买商品的信息
        Books bookss = booksService.queryBookById(bookId);

        ShopCart shopCart = new ShopCart();
        String uuid = UUIDutils.getUUID();
        shopCart.setScId(uuid);

        //购物车和书籍建立联合
        Book_cart_relation relation = new Book_cart_relation();
        String reId = UUIDutils.getUUID();
        relation.setBcrId(reId);
        relation.setBookId(bookss.getBookId());
        relation.setScId(uuid);
        booksService.addBookCart(relation);


        shopCart.setBookName(bookss.getBookName());
        shopCart.setPrice(bookss.getPrice());
        shopCart.setStore("北航邮电");
        shopCart.setCount(1);
        shopCart.setIs_buyshop(0);
        //获取当前用户信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        shopCart.setUsercount(parent.getUsercount());

        try {
            int i =shopCartService.addShop(shopCart);
            if(i > 0){
                out.println("success");
                return "redirect:/shop/shopList";
            }else{
                out.println("failure");
                return "redirect:/book/bookList";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/book/bookList";
    }

    //查询购物车包含的商品
    @RequestMapping("/shopList")
    public String ShopList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,String queryBookName,String queryBookStore, Model model){
        PageHelper.startPage(pageNum,10);
        try {

            //根据当前登录用户查询购物车
            //拿到当前用户的信息
            Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
            ShopCart shopCart =new ShopCart();
            shopCart.setUsercount(parent.getUsercount());
            shopCart.setBookName(queryBookName);
            shopCart.setStore(queryBookStore);
//            List<ShopCart> list = shopCartService.queryCartList(parent.getUsercount());
            List<ShopCart> list = shopCartService.queryShopByOption(shopCart);

            PageInfo<ShopCart> pageInfo = new PageInfo<ShopCart>(list);
            model.addAttribute("pageInfo",pageInfo);
            if(list == null){
                model.addAttribute("error","查询购物车失败");
            }else{
                model.addAttribute("shopList",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "shopcart";

    }

}
