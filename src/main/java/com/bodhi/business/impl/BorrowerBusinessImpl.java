package com.bodhi.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bodhi.business.BorrowerBusiness;
import com.bodhi.dao.BorrowerDao;
import com.bodhi.model.Borrower;

public class BorrowerBusinessImpl implements BorrowerBusiness {

	@Autowired
	BorrowerDao borrowerDao;
	

	@Override
	public List<Borrower> getBorrowerSearchResults(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerDao.getBorrowerSearchResults(borrower);
	}


	@Override
	public List<Borrower> addBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerDao.addBorrower(borrower);
	}


	@Override
	public List<Borrower> updateBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerDao.updateBorrower(borrower);
	}


	@Override
	public int deleteBorrower(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerDao.deleteBorrower(borrower);
	}


	@Override
	public List<Borrower> isBorrowerExisting(Borrower borrower) {
		// TODO Auto-generated method stub
		return borrowerDao.isExistingBorrower(borrower);
	}


}
