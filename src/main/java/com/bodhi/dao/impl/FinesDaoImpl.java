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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;

import com.bodhi.dao.FinesDao;
import com.bodhi.model.Borrower;
import com.bodhi.model.Fines;
import com.bodhi.util.DateUtil;
import com.bodhi.util.SelectBuilderUtil;


public class FinesDaoImpl implements FinesDao {

	@Autowired
	DataSource datasource;
	
	@Autowired
	SqlServerMaxValueIncrementer loanIdIncrementer;
	
	private static final Logger logger = Logger.getLogger(FinesDaoImpl.class);

	@Override
	public List<Fines> compute() {
		String sql = "select " +
				"loan_id," +
				"datediff(day,due_date,ISNULL(date_in,GETDATE())) as no_of_days " +
				"from " +
				"Bodhi_lbms.dbo.book_loans " +
				"where " +
				"due_date < date_in or " +
				"(due_date <  CONVERT (date, SYSDATETIME()) and date_in is NULL) ";
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		List<Fines> fines = new ArrayList<Fines>();
		fines = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Fines>(Fines.class));
		SimpleJdbcInsert insertCommand = new SimpleJdbcInsert(datasource); 
		insertCommand.withTableName(" Bodhi_lbms.dbo.fines").usingColumns("loan_id","fine_amt","paid");
		String updatesql = "UPDATE Bodhi_lbms.dbo.fines SET fine_amt=? where loan_id=";
		for (int i = 0; i < fines.size(); i++) {
			Fines fine = fines.get(i);
			fine.setFine_amt(fine.getNo_of_days()*0.25);
			fine.setPaid(1);
			Map<String, Object> params = new HashMap<String, Object>(); 
			params.put("loan_id", fine.getLoan_id());
			params.put("fine_amt",fine.getFine_amt());
			params.put("paid", fine.getPaid());
			int status = jdbcTemplate.update(updatesql+fine.getLoan_id(), new Object[] {fine.getFine_amt()});
			if(status > 0 ){
				continue;
			} else {
				int count = insertCommand.execute(params);
			}
		}
		return fines;
}

	@Override
	public List<Fines> lookupFines(Fines fines) {
		
		int card_id = 0;
		String fname = "";
		String lname = "";
		if(null!=fines){
			card_id = fines.getCard_no();
			fname = fines.getFname();
			lname = fines.getLname();
		}
		String sql = null;
	    List<Fines> finesList = new ArrayList<Fines>();
	    
	
		String sqlcard = "";
		String sqlfname = "";
		sqlfname = "";
		if(card_id > 0){
			sqlcard = "and b.card_no = " + card_id + " ";
		} else if((!fname.isEmpty()) || fname.trim().length()>0) {
			sqlfname = "and (fname like'%"+fname+"%' OR lname like '%"+lname+"%') ";
		} else {
			sqlfname = "";
			sqlcard = "";
		}
		
		sql = "select " +
				"b.card_no," +
				"c.fname," +
				"a.paid," +
				"c.lname, " +
				"sum(a.fine_amt) as fine_amt " +
				"from " +
				"Bodhi_lbms.dbo.fines a, " +
				"Bodhi_lbms.dbo.borrower c," +
				"Bodhi_lbms.dbo.book_loans b " +
				"where a.loan_id = b.loan_id " +
				"and b.card_no = c.card_no " +
				"" + sqlcard + "" +
				"" + sqlfname + "" +
				"group by " +
				"b.card_no," +
				"a.paid," +
				"c.fname," +
				"c.lname;";
		
		logger.debug("***************************");
		logger.debug(sql);
		logger.debug("***************************");
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		finesList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Fines>(Fines.class));

		return finesList;
	}

	@Override
	public int payInFull(Fines fines) {
		
		// TODO Auto-generated method stub
		
		String card_no = "" + fines.getCard_no();
		String sql = "UPDATE [Bodhi_lbms].[dbo].[fines] SET paid=0 where loan_id IN (select loan_id from Bodhi_lbms.dbo.book_loans where card_no = "+card_no+")";
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		int status = jdbcTemplate.update(sql);
		logger.debug("update status is : " +status);
		return status;
	}
}
