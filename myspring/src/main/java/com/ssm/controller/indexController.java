package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class indexController{
	@RequestMapping("t1")  
    public String getString() {  
        return "hello";  
    }  
    /** 
     * 方法描述: 返回map对象 
     */  
    @RequestMapping("/t2")  
    @ResponseBody  
    public Map<String, String> getJSON() {  
  
        Map<String, String> data = new HashMap<String, String>();  
        data.put("id", "001");  
        data.put("name", "张三");  
        return data;  
    }  

    /** 
     * 方法描述: 访问restful接口并将参数作为结果返回 
     */  
    @RequestMapping("/{clientType}/{loginId}/{userType}/t3")  
    @ResponseBody  
    public Map<String, String> testRestful(@PathVariable String clientType, @PathVariable String loginId,  
            @PathVariable String userType) {  
  
        Map<String, String> data = new HashMap<String, String>();  
        data.put("clientType", clientType);  
        data.put("loginid", loginId);  
        data.put("userType", userType);  
        return data;  
    }  
}
