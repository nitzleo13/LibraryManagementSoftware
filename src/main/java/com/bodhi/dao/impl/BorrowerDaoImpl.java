package com.bodhi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;

import com.bodhi.dao.BorrowerDao;
import com.bodhi.model.Borrower;
import com.bodhi.util.SelectBuilderUtil;

public class BorrowerDaoImpl implements BorrowerDao {

	@Autowired
	DataSource datasource;
	
	@Autowired
	SqlServerMaxValueIncrementer incrementer;
	
	private static final Logger logger = Logger.getLogger(BorrowerDaoImpl.class);

	@Override
	public List<Borrower> getBorrowerSearchResults(Borrower borrower) {
	
		int card_id = 0;
		String fname = "";
		String lname = "";
		if(null!=borrower){
			card_id = borrower.getCard_no();
			fname = borrower.getFname();
			lname = borrower.getLname();
		}
		String sql = null;
	    List<String> params = new ArrayList<String>();
	    List<Borrower> borrowers = new ArrayList<Borrower>();
	    
	    SelectBuilderUtil selectUtil = new SelectBuilderUtil();
		
		sql = "select * from dbo.borrower where ";
		
		logger.debug(fname);
		logger.debug(fname.isEmpty());
		logger.debug(fname.trim().length());
		
		
		if(card_id > 0){
			sql = sql + "card_no = " + card_id;
		} else if((!fname.isEmpty()) || fname.trim().length()>0) {
			sql = sql + "fname like'%"+fname+"%' OR lname like '%"+lname+"%'";
		} else {
			sql = "select * from dbo.borrower";
		}
//		sql = "select * from dbo.borrowers";
		
		logger.debug("***************************");
		logger.debug(sql);
		logger.debug("***************************");
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		borrowers = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Borrower.class));
//		if(params.size()>0){
//			String[] input = new String[params.size()];
//			for (int i = 0; i < params.size(); i++) {
//				input[i]=params.get(i);
//			}
//			borrowers = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(Borrower.class));	
//		} else {
//			borrowers = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Borrower.class));
//		}
		return borrowers;
	}

	@Override
	public List<Borrower> addBorrower(Borrower borrower) {
		
		SimpleJdbcInsert insertCommand = new SimpleJdbcInsert(datasource); 
		insertCommand.withTableName("dbo.borrower").usingColumns("card_no","fname","lname","address","city","state","phone");
	    List<Borrower> borrowers = new ArrayList<Borrower>();
//	    incrementer.setColumnName("card_no");
	    int card_no = incrementer.nextIntValue();
	    logger.debug("card no from incrementer is : " + card_no);
		Map<String, Object> params = new HashMap<String, Object>(2); 
		params.put("card_no", card_no);
		params.put("fname",borrower.getFname());
		params.put("lname", borrower.getLname());
		params.put("address", borrower.getAddress());
		params.put("city", borrower.getCity());
		params.put("state", borrower.getState());
		params.put("phone", borrower.getPhone());
		
		int count = insertCommand.execute(params);
		logger.debug("insert complete : " + count);
		
		if(count > 0){
			borrower.setCard_no(card_no);
		}
		
		borrowers.add(borrower);
		
		return borrowers;
		
	}

	@Override
	public List<Borrower> updateBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		
		String sql = "UPDATE dbo.borrower SET fname=?,lname=?,address=?,city=?,state=?,phone=? where card_no="+borrower.card_no;
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		int status = jdbcTemplate.update(sql, new Object[] {borrower.getFname(),borrower.getLname(),borrower.getAddress(),borrower.getCity(),borrower.getState(),borrower.getPhone()});
		 List<Borrower> borrowers = new ArrayList<Borrower>();
		logger.debug("update status is : " +status);
		
		if(status >0 ){
			 borrowers.add(borrower);
		}
		
		return borrowers;
	}

	@Override
	public int deleteBorrower(Borrower borrower) {
		
		List<Borrower> borrowerList = getBorrowerSearchResults(borrower);
		if(null != borrowerList) {
			for (int i = 0; i < borrowerList.size(); i++) {
				Borrower b = borrowerList.get(i);
				if(b.getCard_no() == borrower.card_no){
					return -1001;
				}
			}
		} else {
			return -1;
		}
		
		String sql = "DELETE FROM dbo.borrower where card_no="+borrower.card_no;
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		int status = jdbcTemplate.update(sql);
		logger.debug("delete status is : " +status);
		
		return status;
	}
	
	@Override
	public List<Borrower> isExistingBorrower(Borrower borrower) {
		
		String address = "";
		String fname = "";
		String lname = "";
		String city="";
		String state="";
		if(null!=borrower){
			fname = borrower.getFname();
			lname = borrower.getLname();
			address = borrower.getAddress();
			city = borrower.getCity();
			state = borrower.getState();
		}
		String sql = null;
	    List<String> params = new ArrayList<String>();
	    List<Borrower> borrowers = new ArrayList<Borrower>();
	    
	    SelectBuilderUtil selectUtil = new SelectBuilderUtil();
		
		sql = "select * from dbo.borrower where fname='"+fname+"' and lname='"+lname+"' and address='"+address+"' and city='"+city+"' and state='"+state+"'";
		
		logger.debug(fname);
		logger.debug(fname.isEmpty());
		logger.debug(fname.trim().length());
		logger.debug("***************************");
		logger.debug(sql);
		logger.debug("***************************");
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		borrowers = jdbcTemplate.query(sql,new BeanPropertyRowMapper(Borrower.class));
		return borrowers;
		
	}
	
}
