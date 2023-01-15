package com.example.zheng.controller;


import com.example.zheng.Utils.CurrentTime;
import com.example.zheng.Utils.UUIDutils;
import com.example.zheng.pojo.*;
import com.example.zheng.service.BooksService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BooksController {

    @Autowired
    BooksService booksService;

    //查询某一类书籍的信息
    @RequestMapping("/book/bookListKind")
    public String bookListKind(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,String bbId,Double minPrice,Double maxPrice, String queryBookAuthor, String queryBookAddress, String queryBookName, Model model){
        PageHelper.startPage(pageNum,10);
        Books books =new Books();
        books.setBookAuthor(queryBookAuthor);
        books.setBookName(queryBookName);
        books.setAddress(queryBookAddress);
        books.setMinPrice(minPrice);
        books.setMaxPrice(maxPrice);
        books.setBkId(bbId);

        try {
            List<Books> list = booksService.queryBookListKind(books);
            PageInfo<Books> pageInfo = new PageInfo<Books>(list);
            model.addAttribute("pageInfo",pageInfo);
            if(list == null){
                model.addAttribute("error","书籍列表为空");
            }else{
                model.addAttribute("bookList",list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "allBook";

    }


    //查询所有的书籍信息
    @RequestMapping("/book/bookList")
    public String bookList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Double minPrice,Double maxPrice, String queryBookAuthor, String queryBookAddress, String queryBookName, Model model){
        PageHelper.startPage(pageNum,10);
        Books books =new Books();
        books.setBookAuthor(queryBookAuthor);
        books.setBookName(queryBookName);
        books.setAddress(queryBookAddress);
        books.setMinPrice(minPrice);
        books.setMaxPrice(maxPrice);

        try {
            List<Books> list = booksService.queryBookList(books);
            PageInfo<Books> pageInfo = new PageInfo<Books>(list);
            model.addAttribute("pageInfo",pageInfo);
            if(list == null){
                model.addAttribute("error","书籍列表为空");
                return "customerIndex";
            }else{
                model.addAttribute("bookList",list);
                return "allBook";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "customerIndex";

    }

    @RequestMapping("/book/todetail")
    public String bookDetail(String bookId,Model model){
        try {
            Books books = booksService.queryBookById(bookId);
            if(books == null){
                model.addAttribute("error","查询书籍详细信息失败");
            }else{
                model.addAttribute("books",books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "bookDetail";


    }


    //=============================书籍的评论======================

    @Mylog(value = "添加书籍评论信息")  //这里添加了AOP的自定义注解
    //添加书籍的评论
    @RequestMapping("/book/submitcomment")
    public String addBookComment(String bookId,String comment){

        //拿到当前用户的信息
        Customer parent  = (Customer) SecurityUtils.getSubject().getPrincipal();
        Comment comment1 = new Comment();
        String cId = UUIDutils.getUUID();
        String time = CurrentTime.getCurrentTime();
        comment1.setcId(cId);
        comment1.setUsercount(parent.getUsercount());
        comment1.setDiscuss(comment);
        comment1.setBookId(bookId);
        comment1.setTimes(time);
        booksService.addBookComment(comment1);

        //修改账单的评论状态




        return "customerIndex";
    }

    @RequestMapping("/book/toComment")
    public String queryCommentList(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,Model model,String bookId){
        PageHelper.startPage(pageNum,10);
        try {
            List<Comment> commentList = booksService.queryCommentList(bookId);

            PageInfo<Comment> pageInfo = new PageInfo<Comment>(commentList);
            model.addAttribute("pageInfo",pageInfo);
            if(commentList !=null){
                model.addAttribute("commentList",commentList);//将数据封装传送到前台
                return "comment";
            }else{
                return "redirect:/book/bookList";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "comment";//跳转到商品评论页面
    }

    @Mylog(value = "增加书籍")  //这里添加了AOP的自定义注解
    //增加书籍信息
    @RequestMapping("/addbook")
    public String addBook(String bookName,String bookAuthor,Double price,String address,String impression,String detail,String kind ){

        String bookId = UUIDutils.getUUID();//书籍信息的主键
        String bbId =UUIDutils.getUUID();//书籍种类和书籍信息的联合主键
        Bkb bkb = new Bkb();

        if(kind.equals("科幻类")){
            bkb.setBkId("BK001");
        }else if(kind.equals("IT类")){
            bkb.setBkId("BK002");
        }else if(kind.equals("文学类")){
            bkb.setBkId("BK003");
        }else if(kind.equals("艺术类")){
            bkb.setBkId("BK004");
        }

        bkb.setBbId(bbId);
        bkb.setBookId(bookId);

        //插入联合表中的数据
       int rss = booksService.addBookRelation(bkb);

       Books books =  new Books(bookId,bookName,bookAuthor,price,address,impression,detail);

       int rs = booksService.addBook(books);


     return "index";

    }




}
