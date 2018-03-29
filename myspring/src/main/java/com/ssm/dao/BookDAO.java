package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.bean.Book;

/**
 * 图书数据访问接口
 */
public interface BookDAO {
	/**
     * 获得所有图书
     */
    public List<Book> getAllBooks();
   
}
