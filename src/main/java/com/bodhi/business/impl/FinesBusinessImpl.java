package com.bodhi.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bodhi.business.FinesBusiness;
import com.bodhi.business.LoanBusiness;
import com.bodhi.dao.BorrowerDao;
import com.bodhi.dao.FinesDao;
import com.bodhi.dao.LoanDao;
import com.bodhi.model.Book;
import com.bodhi.model.Borrower;
import com.bodhi.model.Fines;
import com.bodhi.model.Loan;

public class FinesBusinessImpl implements FinesBusiness {

	private static final Logger logger = Logger
			.getLogger(FinesBusinessImpl.class);

	
	@Autowired
	FinesDao finesDao;
	
	@Autowired
	BorrowerDao borrowerDao;
	
	@Autowired
	Borrower borrower;

	@Override
	public List<Fines> compute() {
		List<Fines> fines = new ArrayList<Fines>();

		try{
			fines = finesDao.compute();
		} catch (Exception e) {
			logger.debug("exception occurred : " + e.getMessage());
			return null;
		}
		
		return fines;

	}

	@Override
	public List<Fines> lookupFines(Fines fines) {
		
		return finesDao.lookupFines(fines);
	}

	@Override
	public int payInFull(Fines fines) {
		
		return finesDao.payInFull(fines);
	}

	

	

}
