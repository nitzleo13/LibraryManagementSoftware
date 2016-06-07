package com.bodhi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bodhi.business.FinesBusiness;
import com.bodhi.dao.BorrowerDao;
import com.bodhi.model.Borrower;
import com.bodhi.model.Fines;
import com.bodhi.model.Loan;





@Controller
public class FinesController {

	private static final Logger logger = Logger
			.getLogger(FinesController.class);


	@Autowired
	FinesBusiness finesBusiness;
	
	@Autowired
	Fines fines;

	@RequestMapping(value = { "/admin/fines/compute" }, method = RequestMethod.GET)
	public ModelAndView compute() {

		logger.debug("in fines controller : compute");
		ModelAndView mv = new ModelAndView("compute_fines");

		return mv;
	}
	

	@RequestMapping(value = { "/admin/fines/collect" }, method = RequestMethod.GET)
	public ModelAndView collect() {

		logger.debug("in fines controller : compute");
		ModelAndView mv = new ModelAndView("collect_fines");

		return mv;
	}
	
	@RequestMapping(value = { "/admin/fines/computefines" }, method = RequestMethod.GET)
	public ModelAndView computefines() {

		logger.debug("in fines controller : computefines");
		ModelAndView mv = new ModelAndView("compute_fines");

		
		
		List<Fines> computeFine = new ArrayList<Fines>();
		computeFine = finesBusiness.compute();
		if(null != computeFine && computeFine.size() >= 0) {
			mv.addObject("computeFineResults", "computed");
		} else {
			mv.addObject("computeFineResults", "error");
		}
		return mv;
	}

	@RequestMapping(value = { "/admin/fines/collectfines" }, method = RequestMethod.GET)
	public ModelAndView collectfines(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in fines controller : computefines");
		String card_no = request.getParameter("borrowerId");
		logger.debug("card number is : " + card_no);

		fines.setFname(request.getParameter("borrowerName"));
		fines.setLname(request.getParameter("borrowerName"));
		card_no = card_no.trim();

		logger.debug(card_no.isEmpty());
		logger.debug("".equals(card_no));
		logger.debug(card_no.trim().length());

		if ((!(card_no.isEmpty()) || (!("".equals(card_no))) || card_no.trim()
				.length() > 0)) {

			fines.setCard_no(Integer.parseInt(card_no));
		} else {
			fines.setCard_no(0);
		}
		ModelAndView mv = new ModelAndView("collect_fines");
		
		List<Fines> lookupFinesResults = new ArrayList<Fines>();
		lookupFinesResults = finesBusiness.lookupFines(fines);
		if(null != lookupFinesResults && lookupFinesResults.size() > 0) {
			mv.addObject("lookUpFineResults", lookupFinesResults);
		} else if (lookupFinesResults.size() == 0) {
			mv.addObject("lookUpFineResults", "nomatchingrecords");
		} else {
			mv.addObject("lookUpFineResults", "error");
		}
		return mv;
	}
	
	@RequestMapping(value = { "/admin/fines/payfull" }, method = RequestMethod.GET)
	public ModelAndView payfull(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in fines controller : payfull");
		String card_no = request.getParameter("card_no");
		logger.debug("card number is : " + card_no);

		card_no = card_no.trim();

		logger.debug(card_no.isEmpty());
		logger.debug("".equals(card_no));
		logger.debug(card_no.trim().length());
		ModelAndView mv = new ModelAndView("collect_fines");
		if ((!(card_no.isEmpty()) || (!("".equals(card_no))) || card_no.trim()
				.length() > 0)) {

			fines.setCard_no(Integer.parseInt(card_no));
		} else {
			mv.addObject("payinfullResults", "error");
		}
		
		 
		int payinFull = finesBusiness.payInFull(fines);
		if(payinFull > 0) {
			mv.addObject("payinfullResults", "paidInFull");
		} else {
			mv.addObject("payinfullResults", "error");
		}
		return mv;
	}

}