package com.bodhi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bodhi.dao.SearchDao;
import com.bodhi.model.Book;

public class SearchDaoImpl implements SearchDao {

	@Autowired
	DataSource datasource;
	
	private static final Logger logger = Logger.getLogger(SearchDaoImpl.class);
	
	private static final String SPACE = " ";

	@Override
	public List<Book> getSearchResults(Book book) {
	
		String book_id = "";
		String author_name = "";
		String title = "";
		if(null!=book){
			book_id = book.getBook_id();
			author_name = book.getAuthor_name();
			title = book.getTitle();
		}
		String sql = null;
	    List<String> params = new ArrayList<String>();
	    List<Book> books = new ArrayList<Book>();
		   if(null!=book_id){
		    	params.add("%"+book_id+"%");
			} else {
				params.add("%"+""+"%");
			}
			if(null!=title){
		    	params.add("%" + title + "%");
			} else {
				params.add("%" + "" + "%");
			}
			if(null!=author_name){
		    	params.add("%" + author_name + "%");
			} else {
				params.add("%" + "" + "%");
			}
			
		sql = "select " +
				"Bodhi_lbms.dbo.Books.book_id," +
				"Bodhi_lbms.dbo.Books.title," +
				"Bodhi_lbms.dbo.book_authors.author_name," +
				"Bodhi_lbms.dbo.book_copies.branch_id," +
				"SUM(Bodhi_lbms.dbo.book_copies.no_of_copies) as no_of_copies, " +
				"(SUM([Bodhi_lbms].[dbo].[book_copies].no_of_copies) - active_count) as copies_avl " +
				"FROM " +
				"Bodhi_lbms.dbo.Books," +
				"[Bodhi_lbms].[dbo].[book_authors], " +
				"[Bodhi_lbms].[dbo].[book_copies] " +
				"LEFT JOIN " +
				"(SELECT " +
				"[Bodhi_lbms].[dbo].[book_loans].[book_id], " +
				"[Bodhi_lbms].[dbo].[book_loans].[branch_id], " +
				"COUNT([Bodhi_lbms].[dbo].[book_loans].[book_id]) as active_count " +
				"FROM " +
				"[Bodhi_lbms].[dbo].[book_loans] " +
				"WHERE " +
				"date_in IS NULL " +
				"GROUP BY " +
				"Bodhi_lbms.dbo.book_loans.book_id," +
				"[Bodhi_lbms].[dbo].[book_loans].[branch_id]) " +
				"as " +
				"b " +
				"ON " +
				"[Bodhi_lbms].[dbo].[book_copies].book_id = b.book_id " +
				"AND [Bodhi_lbms].[dbo].[book_copies].branch_id = b.branch_id " +
				"WHERE " +
				"" +
				"Bodhi_lbms.dbo.Books.book_id = Bodhi_lbms.dbo.book_authors.book_id " +
				"AND Bodhi_lbms.dbo.Books.book_id = Bodhi_lbms.dbo.book_copies.book_id " +
				"AND Bodhi_lbms.dbo.Books.book_id like ? " +
				"AND Bodhi_lbms.dbo.Books.title like ? " +
				"AND Bodhi_lbms.dbo.book_authors.author_name like ? " +
				"GROUP BY " +
				"Bodhi_lbms.dbo.Books.book_id," +
				"Bodhi_lbms.dbo.Books.title," +
				"Bodhi_lbms.dbo.book_authors.author_name," +
				"Bodhi_lbms.dbo.book_copies.branch_id," +
				"active_count";
		
		logger.debug("***************************");
		logger.debug(sql);
		logger.debug("***************************");
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		if(params.size()>0){
			String[] input = new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				input[i]=params.get(i);
			}
			books = jdbcTemplate.query(sql,input,new BeanPropertyRowMapper<Book>(Book.class));	
		} else {
			books = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Book>(Book.class));
		}
		return books;
	}

	
	
}
