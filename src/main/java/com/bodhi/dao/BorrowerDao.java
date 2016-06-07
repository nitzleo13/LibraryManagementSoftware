package com.bodhi.dao;

import java.util.List;

import com.bodhi.model.Borrower;

public interface BorrowerDao {

	public List<Borrower> getBorrowerSearchResults(Borrower borrower);

	public List<Borrower> addBorrower(Borrower borrower);

	public List<Borrower> updateBorrower(Borrower borrower);

	public int deleteBorrower(Borrower borrower);

	List<Borrower> isExistingBorrower(Borrower borrower);
	
}
