package com.ssm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ssm.entity.Book;
import com.ssm.service.BookService;

@Controller
@RequestMapping("/test")
public class GetBookController extends VelocityViewServlet{

	
	@Autowired
	BookService bookService;

	@RequestMapping("/t1")
	@ResponseBody
	public String getAllBook(){
		List<Book> list = bookService.getAllBooks();
		return list.get(0).toString();
	}

	@RequestMapping("/t2")
	public String view(HttpServletRequest request) throws IOException{
		request.setAttribute("name", "honglian");
		return "welcome";
	}
	
}
