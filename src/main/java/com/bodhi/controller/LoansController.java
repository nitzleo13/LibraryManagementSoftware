package com.bodhi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bodhi.business.BorrowerBusiness;
import com.bodhi.business.BranchBusiness;
import com.bodhi.business.LoanBusiness;
import com.bodhi.dao.BorrowerDao;
import com.bodhi.model.Borrower;
import com.bodhi.model.Branch;
import com.bodhi.model.Loan;



@Controller
public class LoansController {

	private static final Logger logger = Logger
			.getLogger(LoansController.class);

	@Autowired
	BranchBusiness branchBusiness;
	
	@Autowired
	LoanBusiness loanBusiness;
	
	@Autowired
	BorrowerBusiness borrowerBusiness;
	
	@Autowired
	Loan loan;
	
	
	@Autowired
	BorrowerDao borrowerDao;
	
	@Autowired
	Borrower borrower;

	@RequestMapping(value = { "/admin/loan/checkout" }, method = RequestMethod.GET)
	public ModelAndView checkout() {

		logger.debug("in check out controller");
		List<Branch> branchList = branchBusiness.getBranchList();
		ModelAndView mv = new ModelAndView("checkout");
		if (null != branchList && branchList.size() > 0) {

			mv.addObject("branchList", branchList);

		} else {
			mv.addObject("branchList", "noresults");
		}

		return mv;
	}

	@RequestMapping(value = { "/admin/loan/checkOutResults" }, method = RequestMethod.GET)
	public ModelAndView checkOutResults(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Loan  searchResults controller");
		String card_no = request.getParameter("borrowerId");
		logger.debug("card number is : " + card_no);
		String book_id = request.getParameter("bookId");
		logger.debug("book_id number is : " + book_id);
		card_no = card_no.trim();
		logger.debug(card_no.isEmpty());
		logger.debug("".equals(card_no));
		logger.debug(card_no.trim().length());
		ModelAndView mv = new ModelAndView("checkout");
		List<Branch> branchList = branchBusiness.getBranchList();
		if (null != branchList && branchList.size() > 0) {

			mv.addObject("branchList", branchList);

		} else {
			mv.addObject("branchList", "noresults");
		}

		loan.setBranch_id(request.getParameter("branchId"));
		if ((!(card_no.isEmpty()) || (!("".equals(card_no))) || card_no.trim()
				.length() > 0)) {
			
			loan.setCard_no(Integer.parseInt(card_no));
		} else {
			mv.addObject("checkOutResults", "error");
			return mv;
		}

		if ((!(book_id.isEmpty()) || (!("".equals(book_id))) || book_id.trim()
				.length() > 0)) {

			loan.setBook_id(book_id);
		} else {
			mv.addObject("checkOutResults", "error");
			return mv;
		}
		List<Loan> checkOut = null;
		//step 1 : check if borrower has reached his limit of check outs.
			boolean isCheckOutLimit = loanBusiness.isCheckOutLimitReached(loan);
			if(isCheckOutLimit){
				mv.addObject("checkOutResults", "checkOutLimit");
				return mv;
			} else {
				boolean isCopyAvl = loanBusiness.isCopyAvl(loan);
				if(isCopyAvl){
					checkOut = loanBusiness	
						.checkOut(loan);
				} else {
					mv.addObject("checkOutResults", "noCopyAvl");
					return mv;
				}
			}
			
			if (null != checkOut) {
				if (checkOut.size() == 1 && checkOut.get(0).getCard_no() == -1001 ) {
					mv.addObject("checkOutResults", "fines");
				} else if (checkOut.size() > 0) {
					
					mv.addObject("checkOutResultsNoErr", checkOut);
				} else {
					mv.addObject("checkOutResults", "error");
				}
			} else {
				mv.addObject("checkOutResults", "error");
			}
			
		return mv;
	}
	
	@RequestMapping(value = { "/admin/loan/checkin" }, method = RequestMethod.GET)
	public ModelAndView checkin() {

		logger.debug("in check in controller");
		ModelAndView mv = new ModelAndView("checkin");
		return mv;
	}
	
	@RequestMapping(value = { "/admin/loan/findLoans" }, method = RequestMethod.GET)
	public ModelAndView lookupRecordsForCheckIn(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Loan  findLoans controller");
		String card_no = request.getParameter("borrowerId");
		logger.debug("card number is : " + card_no);
		String book_id = request.getParameter("bookId");
		logger.debug("book_id number is : " + book_id);
		card_no = card_no.trim();
		String borrowerName = request.getParameter("borrowerName");
		logger.debug(card_no.isEmpty());
		logger.debug("".equals(card_no));
		logger.debug(card_no.trim().length());
		ModelAndView mv = new ModelAndView("checkin");
		
		if ((!(card_no.isEmpty()) || (!("".equals(card_no))) || card_no.trim()
				.length() > 0)) {
			
			loan.setCard_no(Integer.parseInt(card_no));
		} else {
			loan.setCard_no(0);
		}
		loan.setBook_id(book_id);
		loan.setFname(borrowerName);
		loan.setLname(borrowerName);
		
		List<Loan> findLoans = null;
		
		
		
		findLoans =  loanBusiness.findLoans(loan);
		if (null != findLoans
				&& findLoans.size() > 0) {

			mv.addObject("findLoansResults", findLoans);

		} else if (findLoans.size() == 0){
			mv.addObject("findLoansResults", "nomatchingrecords");
		} else {
			mv.addObject("findLoansResults", "error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = { "/admin/loan/loancheckin" }, method = RequestMethod.GET)
	public ModelAndView loanCheckIn(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Loan  loanCheckIn controller");
		String loan_id = request.getParameter("loan_id");
		logger.debug("loan number is : " + loan_id);
		loan_id = loan_id.trim();
		logger.debug(loan_id.isEmpty());
		logger.debug("".equals(loan_id));
		logger.debug(loan_id.trim().length());
		ModelAndView mv = new ModelAndView("checkin");
		
		if ((!(loan_id.isEmpty()) || (!("".equals(loan_id))) || loan_id.trim()
				.length() > 0)) {
			
			loan.setLoan_id(loan_id);
		} else {
			mv.addObject("checkInResults", "error");
		}
		int findLoans =  loanBusiness.checkin(loan);
		if ( findLoans > 0) {

			mv.addObject("checkInResults", "checkedin");

		} else {
			mv.addObject("checkInResults", "error");
		}
		
		return mv;
	}
	
	@RequestMapping(value = { "/admin/loan/renew" }, method = RequestMethod.GET)
	public ModelAndView renew(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Loan  loanCheckIn controller");
		String loan_id = request.getParameter("loan_id");
		logger.debug("loan number is : " + loan_id);
		loan_id = loan_id.trim();
		logger.debug(loan_id.isEmpty());
		logger.debug("".equals(loan_id));
		logger.debug(loan_id.trim().length());
		ModelAndView mv = new ModelAndView("checkin");
		
		if ((!(loan_id.isEmpty()) || (!("".equals(loan_id))) || loan_id.trim()
				.length() > 0)) {
			
			loan.setLoan_id(loan_id);
		} else {
			mv.addObject("renewResults", "error");
		}
		int findLoans =  loanBusiness.renew(loan);
		if ( findLoans > 0) {

			mv.addObject("renewResults", "renewed");

		} else {
			mv.addObject("renewResults", "error");
		}
		
		return mv;
	}
	

}