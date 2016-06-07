package com.bodhi.business;

import java.util.List;

import com.bodhi.model.Loan;


public interface LoanBusiness {


	List<Loan> checkOut(Loan loan);

	boolean isCheckOutLimitReached(Loan loan);

	boolean isCopyAvl(Loan loan);

	List<Loan> findLoans(Loan loan);

	int checkin(Loan loan);

	int renew(Loan loan);



}
