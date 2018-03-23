package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.demo.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class BookDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	public List<Book> getList(){
		String sql="select * from books";
		
		List<Book> list = new ArrayList<>();
		
		List<Map<String, Object>> maps = jdbctemplate.queryForList(sql);
		
		for(Map<String, Object> map : maps){
			Set<Entry<String, Object>> entries = map.entrySet();
			if(entries != null){
				Iterator<Entry<String, Object>> its = entries.iterator();
				Book book = new Book();
				while(its.hasNext()){
					Entry<String, Object> it = its.next();
					
					Object key = it.getKey();
					Object value = it.getValue();
					if(key.toString().equals("id")){
						book.setId(Integer.parseInt(value.toString()));
					}else if(key.toString().equals("price")){
						book.setPrice(Double.parseDouble(value.toString()));
					}else if(key.toString().equals("title")){
						book.setTitle(value.toString());
					}
					
				}
				list.add(book);
			}
		}
		
		return list;
		
		/*return (List<Book>)jdbctemplate.query(sql, new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setPrice(rs.getInt("price"));
				book.setPublishDate(rs.getDate("publishdate"));
				book.setTitle(rs.getString("title"));
				return book;
			}
		});*/
	}

}
