package com.example.demo.bean;

import java.util.Date;

public class Book {
	 @Override  
	    public String toString() {  
	        return "Book [id=" + id + ", title=" + title + ", price=" + price + "]";  
	    }  
	  
	    /** 
	     * 编号 
	     */  
	    private int id;  
	    /** 
	     * 书名 
	     */  
	    private String title;  
	    /** 
	     * 价格 
	     */  
	    private double price;  
	   
	    public Book(int id, String title, double price) {  
	        this.id = id;  
	        this.title = title;  
	        this.price = price;  
	    }  
	       
	    public Book() {  
	    }  
	   
	    public int getId() {  
	        return id;  
	    }  
	   
	    public void setId(int id) {  
	        this.id = id;  
	    }  
	   
	    public String getTitle() {  
	        return title;  
	    }  
	   
	    public void setTitle(String title) {  
	        this.title = title;  
	    }  
	   
	    public double getPrice() {  
	        return price;  
	    }  
	   
	    public void setPrice(double price) {  
	        this.price = price;  
	    }  
}
