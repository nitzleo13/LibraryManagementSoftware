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

public class LoanBusinessImpl implements LoanBusiness {

	private static final Logger logger = Logger
			.getLogger(LoanBusinessImpl.class);

	
	@Autowired
	LoanDao loanDao;
	
	@Autowired
	BorrowerDao borrowerDao;
	
	@Autowired
	Borrower borrower;
	
	@Autowired
	FinesDao finesDao;
	
	@Autowired
	Fines fine;

	@Override
	public List<Loan> checkOut(Loan loan) {
		
		fine.setCard_no(loan.getCard_no());
		List<Fines> fineList = finesDao.lookupFines(fine);
		if(null != fineList) {
			for (int i = 0; i < fineList.size(); i++) {
				Fines fineObj = fineList.get(i); 
				if(loan.getCard_no() == fineObj.getCard_no()){
					List<Loan> a = new ArrayList<Loan>();
					Loan x = new Loan();
					x.setCard_no(-1001);
					a.add(x);
					return a;
				}
			}
			
			
		}
		
		List<Loan> loanList = new ArrayList<Loan>();
		List<Loan> newloanList = new ArrayList<Loan>();
		try{
			loanList = loanDao.checkOut(loan);
		} catch (Exception e) {
			logger.debug("exception occurred : " + e.getMessage());
			return null;
		}
		
		if(null != loanList){
			for (int i = 0; i < loanList.size(); i++) {
				Loan formatLoan = loanList.get(i);
				String book_id = formatLoan.getBook_id();
				formatLoan.setBook_id(StringUtils.leftPad(book_id,10,"0"));
				newloanList.add(formatLoan);
			}
		}
		return newloanList;

	}

	@Override
	public boolean isCheckOutLimitReached(Loan loan) {
		
		
		List<Loan> loanList = loanDao.getLoans(loan);
		if(null == loanList){
			return true;
		} else if (loanList.size()>=3){
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean isCopyAvl(Loan loan) {
		List<Book> books = loanDao.getAvlCopies(loan);
		logger.debug("in isCopyAvl : " +books.size() );
		int copyAvl = 0;
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			logger.debug("Copies Avl is : " + book.getCopies_avl());
			copyAvl = Integer.parseInt(book.getCopies_avl());
		}
		
		if(copyAvl > 0){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Loan> findLoans(Loan loan) {
		List<Loan> loanList = new ArrayList<Loan>();
		List<Loan> newloanList = new ArrayList<Loan>();
		loanList = loanDao.findLoans(loan);
		
		if(null != loanList){
			for (int i = 0; i < loanList.size(); i++) {
				Loan formatLoan = loanList.get(i);
				String book_id = formatLoan.getBook_id();
				formatLoan.setBook_id(StringUtils.leftPad(book_id,10,"0"));
				newloanList.add(formatLoan);
			}
		}
		return newloanList;
	}

	@Override
	public int checkin(Loan loan) {
		return loanDao.checkIn(loan);
	}

	@Override
	public int renew(Loan loan) {
		// TODO Auto-generated method stub
		return loanDao.renew(loan);
	}


	

}
