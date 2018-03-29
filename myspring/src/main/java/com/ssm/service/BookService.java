package com.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.bean.Book;
import com.ssm.dao.BookDAO;

@Service
public class BookService{
 
    @Autowired
    BookDAO bookDao;
     
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
     
}