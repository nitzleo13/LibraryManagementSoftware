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
import com.bodhi.model.Borrower;

@Controller
public class BorrowerController {

	private static final Logger logger = Logger
			.getLogger(BorrowerController.class);

	@Autowired
	BorrowerBusiness borrowerBusiness;

	@Autowired
	Borrower borrower;

	@RequestMapping(value = { "/admin/borrowers/search" }, method = RequestMethod.GET)
	public ModelAndView search() {

		logger.debug("in borrower search controller");
		ModelAndView mv = new ModelAndView("searchborrower");

		return mv;
	}

	@RequestMapping(value = { "/admin/borrowers/searchResults" }, method = RequestMethod.GET)
	public ModelAndView searchResults(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Borrower searchResults controller");
		String card_no = request.getParameter("borrowerId");
		logger.debug("card number is : " + card_no);

		borrower.setFname(request.getParameter("borrowerName"));
		borrower.setLname(request.getParameter("borrowerName"));
		card_no = card_no.trim();

		logger.debug(card_no.isEmpty());
		logger.debug("".equals(card_no));
		logger.debug(card_no.trim().length());

		if ((!(card_no.isEmpty()) || (!("".equals(card_no))) || card_no.trim()
				.length() > 0)) {

			borrower.setCard_no(Integer.parseInt(card_no));
		} else {
			borrower.setCard_no(0);
		}

		List<Borrower> searchBorrowerResultList = borrowerBusiness
				.getBorrowerSearchResults(borrower);

		ModelAndView mv = new ModelAndView("searchborrower");
		if (null != searchBorrowerResultList
				&& searchBorrowerResultList.size() > 0) {

			mv.addObject("borrowerresults", searchBorrowerResultList);

		} else {
			mv.addObject("borrowerresults", "nomatchingrecords");
		}

		return mv;
	}

	@RequestMapping(value = { "/admin/borrowers/delete" }, method = RequestMethod.GET)
	public ModelAndView deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		logger.debug("in borrower delete controller");
		ModelAndView mv = new ModelAndView("searchborrower");

		int card_no = Integer.parseInt(request.getParameter("card_no"));
		borrower.setCard_no(card_no);
		int deleteStatus = borrowerBusiness
				.deleteBorrower(borrower);
		if (deleteStatus > 0) {

			mv.addObject("deleteStatus", "deleted");

		} else if(deleteStatus == -1001){
			mv.addObject("deleteStatus", "checkedOutBooks");
		} else {
			mv.addObject("deleteStatus", "error");
		}
		return mv;

	}
	
	@RequestMapping(value = { "/admin/borrowers/add" }, method = RequestMethod.GET)
	public ModelAndView addBorrower(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in borrower add controller");
		ModelAndView mv = new ModelAndView("addborrower");

		return mv;

	}

	@RequestMapping(value = { "/admin/borrowers/update" }, method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in borrower add controller");
		ModelAndView mv = new ModelAndView("updateborrower");

		int card_no = Integer.parseInt(request.getParameter("card_no"));
		borrower.setCard_no(card_no);
		List<Borrower> searchBorrowerResultList = borrowerBusiness
				.getBorrowerSearchResults(borrower);
		if (null != searchBorrowerResultList
				&& searchBorrowerResultList.size() > 0) {

			mv.addObject("update", searchBorrowerResultList);

		} else {
			mv.addObject("update", "error");
		}

		return mv;

	}

	@RequestMapping(value = { "/admin/borrowers/updateBorrower" }, method = RequestMethod.GET)
	public ModelAndView updateBorrower(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Borrower update results controller");
		borrower.setFname(request.getParameter("fname"));
		borrower.setLname(request.getParameter("lname"));
		borrower.setAddress(request.getParameter("address"));
		borrower.setCity(request.getParameter("city"));
		borrower.setState(request.getParameter("state"));
		borrower.setPhone(request.getParameter("phone"));
		borrower.setCard_no(Integer.parseInt(request.getParameter("card_no")));

		List<Borrower> updateBorrowerResult = borrowerBusiness
				.updateBorrower(borrower);

		ModelAndView mv = new ModelAndView("updateborrower");
		if (null != updateBorrowerResult && updateBorrowerResult.size() > 0) {

			if (updateBorrowerResult.get(0).getCard_no() > 0) {
				mv.addObject("updateresults", updateBorrowerResult);
			} else {
				mv.addObject("updateresults", "error");
			}

		} else {
			mv.addObject("updateresults", "error");
		}

		return mv;

	}

	@RequestMapping(value = { "/admin/borrowers/addresults" }, method = RequestMethod.GET)
	public ModelAndView addBorrowerResults(HttpServletRequest request,
			HttpServletResponse response) {

		logger.debug("in Borrower add results controller");
		borrower.setFname(request.getParameter("fname"));
		borrower.setLname(request.getParameter("lname"));
		borrower.setAddress(request.getParameter("address"));
		borrower.setCity(request.getParameter("city"));
		borrower.setState(request.getParameter("state"));
		borrower.setPhone(request.getParameter("phone"));
		
		List<Borrower> isBorrowerExisting = borrowerBusiness
				.isBorrowerExisting(borrower);
		ModelAndView mv = new ModelAndView("addborrower");

		if (null != isBorrowerExisting && isBorrowerExisting.size() > 0) {
			
			mv.addObject("borrowerExists", isBorrowerExisting);
			
		} else {
			List<Borrower> addBorrowerResult = borrowerBusiness
					.addBorrower(borrower);

			if (null != addBorrowerResult && addBorrowerResult.size() > 0) {

				if (addBorrowerResult.get(0).getCard_no() > 0) {
					mv.addObject("addresults", addBorrowerResult);
				} else {
					mv.addObject("addresults", "error");
				}

			} else {
				mv.addObject("addresults", "error");
			}

		}

		return mv;

	}

}