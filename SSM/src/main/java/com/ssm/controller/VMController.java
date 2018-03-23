package com.ssm.controller;

import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/vm")
public class VMController {

	@RequestMapping("/vm1")
//	@ResponseBody
	public String MyVM(HttpServletRequest request, HttpServletResponse response){

		//初始化参数
		Properties properties=new Properties();
		//设置velocity资源加载方式为class
		properties.setProperty("resource.loader", "file");
		//设置velocity资源加载方式为file时的处理类
		properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, 
				this.getClass().getResource("/").toString().replaceAll("^file:/", "").replaceAll("classes","vm"));

		properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
		properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		//实例化一个VelocityEngine对象
		VelocityEngine velocityEngine=new VelocityEngine(properties);

		//实例化一个VelocityContext
		VelocityContext context=new VelocityContext();
		//向VelocityContext中放入键值
		context.put("name", "黄伟");
		context.put("date", new Date().toString());
		//实例化一个StringWriter
		StringWriter writer=new StringWriter();

		Template template =velocityEngine.getTemplate("vm1.vm"); 
		template.merge(context, writer);  
		System.out.println(writer);

		return "vm1";

	}

}
