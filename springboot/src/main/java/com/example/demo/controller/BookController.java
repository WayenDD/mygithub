package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BookDao;

@Controller
public class BookController {

	@Autowired
	private BookDao bookDao;
	
	@RequestMapping("/list")
	@ResponseBody
	public String getAllBook(){
		return bookDao.getList().toString();
	}
	
}
