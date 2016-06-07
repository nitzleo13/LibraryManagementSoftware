package com.bodhi.dao;

import java.util.List;

import com.bodhi.model.Book;
import com.bodhi.model.Branch;
import com.bodhi.model.Loan;


public interface LoanDao {


	List<Loan> getLoans(Loan loan);

	List<Loan> checkOut(Loan loan);

	List<Book> getAvlCopies(Loan loan);

	List<Loan> findLoans(Loan loan);

	int checkIn(Loan loan);

	int renew(Loan loan);
	
}
