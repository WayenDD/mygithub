package myspring;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.bean.Book;
import com.ssm.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:spring-mybatis.xml"})

public class PersonTest {

	private static Logger logger = Logger.getLogger(PersonTest.class);
	private ApplicationContext ac = null;

	@Resource
	private BookService bookService = null;
	
	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookService = (BookService) ac.getBean("userService");
	}

	@Test
	public void test1() {
		List<Book> list = bookService.getAllBooks();
		 System.out.println(list.size());
	}
}

