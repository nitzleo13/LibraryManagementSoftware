package com.bodhi.dao.impl;

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
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;

import com.bodhi.dao.BranchDao;
import com.bodhi.dao.LoanDao;
import com.bodhi.model.Book;
import com.bodhi.model.Borrower;
import com.bodhi.model.Branch;
import com.bodhi.model.Loan;
import com.bodhi.util.DateUtil;

public class LoanDaoImpl implements LoanDao {

	@Autowired
	DataSource datasource;
	
	@Autowired
	SqlServerMaxValueIncrementer loanIdIncrementer;
	
	private static final Logger logger = Logger.getLogger(LoanDaoImpl.class);

	@Override
	public List<Loan> getLoans(Loan loan) {
		
		String sql = "select * from [Bodhi_lbms].[dbo].[book_loans] where card_no=? and date_in is NULL";
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		List<Loan> loans = new ArrayList<Loan>();
		loans = jdbcTemplate.query(sql,new Object[]{loan.getCard_no()},new BeanPropertyRowMapper<Loan>(Loan.class));	
		return loans;
	}

	@Override
	public List<Loan> checkOut(Loan loan) {
		
		SimpleJdbcInsert insertCommand = new SimpleJdbcInsert(datasource); 
		insertCommand.withTableName("[Bodhi_lbms].[dbo].[book_loans]").usingColumns("loan_id","book_id","branch_id","card_no","date_out","due_date");
	    List<Loan> loanList = new ArrayList<Loan>();
	    int loan_no = loanIdIncrementer.nextIntValue();
	    logger.debug("loan_no from incrementer is : " + loan_no);
		Map<String, Object> params = new HashMap<String, Object>(2); 
		params.put("loan_id", loan_no);
		params.put("book_id",loan.getBook_id());
		params.put("branch_id", loan.getBranch_id());
		params.put("card_no", loan.getCard_no());
		params.put("date_out", DateUtil.getCurrentDate());
		params.put("due_date", DateUtil.getNextDueDate());
		int count = insertCommand.execute(params);
		logger.debug("insert complete : " + count);
		
		if(count > 0){
			loan.setLoan_id(""  + loan_no);
			loan.setDue_date(DateUtil.getNextDueDate());
		}
		
		loanList.add(loan);
		
		return loanList;
	}

	@Override
	public List<Book> getAvlCopies(Loan loan) {
		
		String book_id = "";
		String branch_id = "";
		if(null!=loan){
			book_id = loan.getBook_id();
			branch_id = loan.getBranch_id();
		}
		String sql = null;
	    List<String> params = new ArrayList<String>();
	    List<Book> books = new ArrayList<Book>();
		  params.add(book_id);
		  params.add(branch_id);
		  
			
		sql = "select " +
				"Bodhi_lbms.dbo.Books.book_id," +
				"Bodhi_lbms.dbo.Books.title," +
				"Bodhi_lbms.dbo.book_authors.author_name," +
				"Bodhi_lbms.dbo.book_copies.branch_id," +
				"SUM(Bodhi_lbms.dbo.book_copies.no_of_copies) as no_of_copies, " +
				"(ISNULL((SUM([Bodhi_lbms].[dbo].[book_copies].no_of_copies) - active_count),no_of_copies)) as copies_avl " +
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
				"AND Bodhi_lbms.dbo.Books.book_id = ? " +
				"AND Bodhi_lbms.dbo.book_copies.branch_id = ? " +
				"GROUP BY " +
				"Bodhi_lbms.dbo.Books.book_id," +
				"Bodhi_lbms.dbo.Books.title," +
				"Bodhi_lbms.dbo.book_authors.author_name," +
				"Bodhi_lbms.dbo.book_copies.branch_id," +
				"no_of_copies," +
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

	@Override
	public List<Loan> findLoans(Loan loan) {
		
		int card_no = loan.getCard_no();
		String book_id = "%"+loan.getBook_id()+"%";
		String fname = "%"+loan.getFname()+"%";
		String lname = "%"+loan.getLname()+"%";
		
		   List<String> params = new ArrayList<String>();
		    List<Loan> loanList = new ArrayList<Loan>();
			
		
		String sql = "SELECT " +
				"a.loan_id," +
				"a.book_id," +
				"a.branch_id," +
				"a.card_no," +
				"a.due_date," +
				"a.date_out," +
				"b.fname," +
				"b.lname  " +
				"FROM [Bodhi_lbms].[dbo].[book_loans] a," +
				"[Bodhi_lbms].[dbo].[borrower] b " +
				"WHERE " +
				"a.card_no = b.card_no " +
				"AND a.date_in IS NULL " +
				"AND a.book_id LIKE ? " +
				"AND a.card_no IN (SELECT " +
				"card_no " +
				"FROM " +
				"[Bodhi_lbms].[dbo].[borrower] " +
				"WHERE fname like ? " +
				"OR " +
				"lname like ?)";
		
		String sql1 = "SELECT " +
				"a.loan_id," +
				"a.book_id," +
				"a.branch_id," +
				"a.card_no," +
				"a.due_date," +
				"a.date_out," +
				"b.fname," +
				"b.lname  " +
				"FROM [Bodhi_lbms].[dbo].[book_loans] a," +
				"[Bodhi_lbms].[dbo].[borrower] b " +
				"WHERE " +
				"a.card_no = b.card_no " +
				"AND a.date_in IS NULL " +
				"AND a.book_id LIKE ? " +
				"AND a.card_no IN (SELECT " +
				"card_no " +
				"FROM " +
				"[Bodhi_lbms].[dbo].[borrower] " +
				"WHERE fname like ? " +
				"OR " +
				"lname like ?) " +
				"AND a.card_no = ?";
		
		if(card_no == 0){
			logger.debug("***************************");
			logger.debug(sql);
			params.add(book_id);
			params.add(fname);
			params.add(lname);
			logger.debug("***************************");
		} else {
			logger.debug("***************************");
			logger.debug(sql);
			sql = sql1;
			params.add(book_id);
			params.add(fname);
			params.add(lname);
			params.add(""+card_no);
			logger.debug("***************************");
		}
		
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		if(params.size()>0){
			String[] input = new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				input[i]=params.get(i);
			}
			loanList = jdbcTemplate.query(sql,input,new BeanPropertyRowMapper<Loan>(Loan.class));	
		} else {
			loanList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Loan>(Loan.class));
		}
		
		
		
		return loanList;
	}

	@Override
	public int checkIn(Loan loan) {
		// TODO Auto-generated method stub
		
				String loan_id = loan.getLoan_id();
				String sql = "UPDATE [Bodhi_lbms].[dbo].[book_loans] SET date_in=? where loan_id="+loan_id;
				JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
				int status = jdbcTemplate.update(sql, new Object[] {DateUtil.getCurrentDate()});
				logger.debug("update status is : " +status);
		return status;
	}
	
	@Override
	public int renew(Loan loan) {
		// TODO Auto-generated method stub
		
				String loan_id = loan.getLoan_id();
				String sql = "UPDATE [Bodhi_lbms].[dbo].[book_loans] SET due_date=? where loan_id="+loan_id;
				JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
				int status = jdbcTemplate.update(sql, new Object[] {DateUtil.getNextDueDate()});
				logger.debug("update status is : " +status);
		return status;
	}
	
}
