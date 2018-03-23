package com.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.BookDao;
import com.ssm.entity.Book;
import com.ssm.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;  

	public List<Book> getAllBooks() {  
		List<Book> list = bookDao.getAllBooks();  
		return list;  
	}  
}
