package com.bodhi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bodhi.dao.BranchDao;
import com.bodhi.model.Branch;

public class BranchDaoImpl implements BranchDao {

	@Autowired
	DataSource datasource;
	
	
	private static final Logger logger = Logger.getLogger(BranchDaoImpl.class);

	@Override
	public List<Branch> getBranches() {
		
		String sql = "select * from [Bodhi_lbms].[dbo].[library_branch]";
		JdbcTemplate  jdbcTemplate = new JdbcTemplate (datasource);
		List<Branch> branch = new ArrayList<Branch>();
		branch = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Branch>(Branch.class));	
		return branch;
	}

	
	
}
