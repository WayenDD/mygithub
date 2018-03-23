package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.entity.Book;
import com.ssm.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" }) 
public class test {
	
	@Autowired
	 private BookService bookService;  
	  
	      
	    @Test  
	    public void teatGetBook(){  
	        List<Book>  list = bookService.getAllBooks();  
	        for (Book book : list) {  
	            System.out.println(book.toString());  
	        }  
	    }  
}
