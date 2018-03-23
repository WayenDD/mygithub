package com.ssm.springmvcpro;

import java.io.File;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceView;

@Component
public class VMProperties extends InternalResourceView{

	public boolean checkResource(Locale locale){
		File file = new File(this.getServletContext().getRealPath("/") + getUrl());
		return file.exists();
	}
	
	/**
	 * 报java.lang.IllegalArgumentException: Property 'url' is required错误的时候重写该方法，原类返回true，重写后返回false
	 */
	public boolean isUrlRequired(){
		return false;
	}
}
