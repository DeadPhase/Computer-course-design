package com.example.zheng;


import com.example.zheng.pojo.*;
import com.example.zheng.service.UserService;
import com.example.zheng.service.impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    BooksServiceImpl booksService;


    @Autowired
    ShopCartServiceImpl shopCartService;



    @Autowired
    UserService userService;

    @Autowired//注入用户角色
    RoleServiceImpl roleService;

    @Autowired//注入角色权限
    AuthorityServiceImpl authorityService;


    @Test
    void test(){
        String str1 = "hello";
        String str2 = new String("hello");
        String str3 = str2;//引用传递
        System.out.println("123");
        System.out.println("(str1 == str2):"+(str1 == str2));//false
        System.out.println("(str1 == str3):"+(str1 == str3));//false
        System.out.println("(str2 == str3):"+(str2 == str3));//true
        System.out.println("str1.equals(str2):"+str1.equals(str2));//true
        System.out.println("str1.equals(str3):"+str1.equals(str3));//true
        System.out.println("str2.equals(str3):"+str2.equals(str3));//true
    }

//    //测试插入数据
//    @Test
//    void testLookBuy(){
//        Shop_list_buylog shop_list_buylog = new Shop_list_buylog("A", "B", "C");
//        booksService.addListBuyLog(shop_list_buylog);
//        int a=3;
//    }

    //测试查找书籍主键
//    @Test
//    void testLookBookId(){
////        Book_cart_relation book = booksService.lookBookId("a9d9020d-4774-4d20-a576-14b1ab0dc5bd");
//
//        Shop_list_buylog shop_list_buylog =booksService.lookscId("76cdc305-ea85-42a6-a0df-07501cc67527");
//        int a=9;
//    }
    //测试书籍和购物车联合
//    @Test
//    void testAddBokkCart(){
//        Book_cart_relation relation = new Book_cart_relation("A","B","C");
//        booksService.addBookCart(relation);
//        int a=4;
//
//    }

//    //updateShopList
//    @Test
//    void testupdateShopList(){
//        List<UserShopList> list = shopCartService.queryBuyLog("xio");
//
//        int a=4;
//
//
//    }

//    //测试查询所要购买的商品
//    @Test
//    void testBuyShopList(){
//        List<Shoplist> list = shopCartService.buyShopList("xio");
//
//        double sumprice =0;
//        double price;
//        int count =0;
//        for(Shoplist shopList:list){
//             count =shopList.getCount();
//             price =shopList.getPrice();
//             price=count*price;//得到总价格
//             sumprice=sumprice+price;
//        }
//
//        double rs =sumprice;
//        int a =5;
//    }



    //测试将插入的数据显示在前台
//    @Test
//    void testShopList(){
//        List<Shoplist> list = shopCartService.shopList("xio");
//
//        int a =list.size();
//        int c=4;
//    }

//    //测试增加商品到临时购物车
//    @Test
//    void testAddShopList(){
//        Shoplist shoplist = new Shoplist();
//        shoplist.setSlId("Sl001");
//        shoplist.setBookName("java");
//        shoplist.setCount(3);
//        shoplist.setPrice(34.5);
//
//
//        int rs = shopCartService.addShopList(shoplist);
//
//        int a =8;
//    }

//    //测试查询购物单
//    @Test
//    void testUserShopList(){
//        UserShopList userShopList = shopCartService.queryUserShopList("xio");
//
//        int b=4;
//    }


////    测试收货地址的
//    @Test
//    void addressList(){
//        Hvaddress hvaddress = new Hvaddress();
//        List<Hvaddress> list = customerService.addressList(hvaddress);
//
//        int len = list.size();
//
//    }
//
//    //增加一个收货地址
//    @Test
//    void testAdd(){
//        Hvaddress hvaddress = new Hvaddress("HV001", "小红", "湖南长沙", "13423432342");
//        int a = customerService.addAddress(hvaddress);
//        int c=4;
//    }
//
//    //修改收获地址
//    @Test
//    void testupdate(){
//        Hvaddress hvaddress = new Hvaddress("HV001", "小红花", "湖南长沙", "13423432342");
//
//        int a = customerService.updateAddress(hvaddress);
//        int b=3;
//
//    }
//
//    //删除地址
//    @Test
//    void testDelete(){
//        int a = customerService.deleteAddress("HV001");
//        int c=4;
//
//    }

    //查询数据
    //测试查询到的用户列表
//    @Test
//    void testCustomer(){
//        List<Customer> customers = customerService.queryCustomerList();
//
//        int a =4;
//    }

//    @Test//测试书籍评论
//    void testComment(){
//        List<Comment> comments = booksService.queryCommentList("B001");
//
//        int a=4;
//    }


//    @Test//测试角色具有的权限
//    void role_authority(){
//        Set<Authority> authority = authorityService.queryAuthority("R003");
//
//        int a=4;
//    }

//    //测试用具有的角色
//    @Test
//    void  user_role(){
//        Set<Role> role =roleService.queryRole("xio");
//
//        int a=4;
//    }

//    //测试登录
//    @Test
//    void testLogin(){
//        User user = userService.userlogin("Lisi","123456");
//
//        int a=4;
//    }

   // 测试查询当前用户所具有的权限
//    @Test
//    void testRole(){
//        Set<Roles> list=rolesService.queryRoles("xio");
//        int len =list.size();
//        System.out.println(len);
//    }

    //测试商品添加到购物车
//    @Test
//    void addShop(){
//        ShopCart shopCart = new ShopCart("A01","B01","php",4,23.56,"长沙大学");
//        int  i= shopCartService.addShop(shopCart);
//        if(i>0){
//            System.out.println("success");
//        }else{
//            System.out.println("failure");
//        }
//    }
    //测试删除购物车里的商品
//    @Test
//    void testDeleteShop(){
//
//        int i=shopCartService.deleteShop("B01");
//        if(i>0){
//            System.out.println("success");
//        }else{
//            System.out.println("failure");
//        }
//    }

    //测试购物车商品查询
//    @Test
//    void textList(){
//        List<ShopCart> list = shopCartService.queryCartList("A01");
//        System.out.println(list);
//    }

//    @Test //测试查询书籍
//    void textList(){
//        List<Books> list =booksService.queryBookList();
//        System.out.println(list);
//
//    }

//    @Test
//    void contextLoads() {
//        //查看默认的数据源
//        System.out.println(dataSource.getClass());
//
//        //获得数据库连接
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//
//        //关闭连接
//        connection.close();

//        List list = customerService.queryCustomerList();
//        int a = list.size();
//        System.out.println(list);
//        Customer customer=customerService.queryById("li");
//        System.out.println(customer.getName()+customer.getAge());
//


        //删除学生

        //修改学生

        //查询学生信息

//    }
//    @Test
//    void testadd(){
//        //增加顾客
//        Customer customer1= new Customer("zzz","张张张","男","23","343@qq.com","12343456543","海南岛","111");
//        int re=customerService.addCustomer(customer1);
//        if(re>0){
//            System.out.println("添加成功");
//        }else{
//            System.out.println("添加失败");
//        }
//
//
//    }
//
//    @Test
//    void testdelete(){
//        //删除顾客
//        int re = customerService.deleteCustomer("Zhengyuzhu");
//        if(re>0){
//            System.out.println("删除成功");
//        }else{
//            System.out.println("删除失败");
//        }
//    }
//
//    //查看所有的用户信息
//    @Test
//    void testQueryAllCustomer(){
//        List list = customerService.queryCustomerList();
//        System.out.println("查询到的学生数是："+list.size());
//        System.out.println(list);
//    }
//
//    //修改学生的信息
//    @Test
//    void testupdate(){
//        Customer customer1= new Customer("zzz","张张张","男","23","343@qq.com","12343456543","海南岛","111");
//        int res = customerService.updateCustomer(customer1);
//        if(res > 0){
//            System.out.println("修改成功");
//        }else{
//            System.out.println("修改失败");
//        }

//    }





}
