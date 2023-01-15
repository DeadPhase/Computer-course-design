package com.example.zheng.mapper;

import com.example.zheng.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper    //这个注解表示这个是mybatis的mapeper
@Repository
public interface BooksMapper {

    /**
     * 查询出siId
     */
    Shop_list_buylog lookscId(String bgId);

    /**
     * 增加购物日志和清单
     */
    int addListBuyLog(Shop_list_buylog listBuylog);

    /**
     * 先找出要评论的书籍
     */
    Books lookBookId(String scId);

    /**
     * 图书和购物车图书建立关系
     */
    int addBookCart(Book_cart_relation bookcart);

    /**
     * 查询图书
     */
    public List<Books> queryBookList(Books books);



    /**
     * 查询书籍的详细信息通过书籍编号
     */

    public Books queryBookById(String id);


    /**
     *增加书籍的信息
     */
    int addBook(Books books);

    /**
     *增加书籍信息和关联表的信息
     */
    int addBookRelation(Bkb bkb);

    /**
     *查询书籍的种类
     */
    public List<Books> queryBookListKind(Books books);



    //=============================书籍的评论======================

    //通过书籍的ID查询不同用户对该商品的评论
    public List<Comment> queryCommentList(String bookId);

    //增加书籍的评论
    int addBookComment(Comment comment);



}
