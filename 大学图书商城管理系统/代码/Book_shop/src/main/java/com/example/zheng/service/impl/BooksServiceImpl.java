package com.example.zheng.service.impl;

import com.example.zheng.mapper.BooksMapper;
import com.example.zheng.pojo.*;
import com.example.zheng.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksMapper booksMapper;


    //查询出siId
    @Override
    public Shop_list_buylog lookscId(String bgId) {
        return booksMapper.lookscId(bgId);
    }

    //增加购物日志和清单
    @Override
    public int addListBuyLog(Shop_list_buylog listBuylog) {
        return booksMapper.addListBuyLog(listBuylog);
    }

    //    先找出要评论的书籍
    @Override
    public Books lookBookId(String scId) {
        return booksMapper.lookBookId(scId);
    }

    //图书和购物车图书建立关系
    @Override
    public int addBookCart(Book_cart_relation bookcart) {
        return booksMapper.addBookCart(bookcart);
    }

    //查询书籍的种类
    @Override
    public List<Books> queryBookListKind(Books books) {
        return booksMapper.queryBookListKind(books);
    }

    //增加书籍的信息
    @Override
    public int addBook(Books books) {
        return booksMapper.addBook(books);
    }

//    增加书籍信息和关联表的信息
    @Override
    public int addBookRelation(Bkb bkb) {
        return booksMapper.addBookRelation(bkb);
    }

    //查询所有书籍
    @Override
    public List<Books> queryBookList(Books books) {
        return booksMapper.queryBookList(books);
    }

    //根据id查询具体书籍信息
    @Override
    public Books queryBookById(String bookId) {
        return booksMapper.queryBookById(bookId);

    }



    //=============================书籍的评论======================

    //通过书籍的ID查询不同用户对该商品的评论
    @Override
    public List<Comment> queryCommentList(String bookId) {
        return booksMapper.queryCommentList(bookId);
    }

    //增加书籍的评论
    @Override
    public int addBookComment(Comment comment) {
        return booksMapper.addBookComment(comment);
    }

}
