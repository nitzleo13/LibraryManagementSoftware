package com.bodhi.business;

import java.util.List;

import com.bodhi.model.Book;
import com.bodhi.model.Borrower;
import com.bodhi.model.Branch;

public interface BorrowerBusiness {
	
	public List<Borrower> getBorrowerSearchResults(Borrower borrower);

	public List<Borrower> addBorrower(Borrower borrower);

	public List<Borrower> updateBorrower(Borrower borrower);

	public int deleteBorrower(Borrower borrower);

	public List<Borrower> isBorrowerExisting(Borrower borrower);


}
