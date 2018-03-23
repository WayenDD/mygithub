package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping(value="index1")
	@ResponseBody
	public String msg(){
		return "index";
	}
	
	@RequestMapping(value="index2")
	public String msg1(){
		return "index";
	}
	
}
