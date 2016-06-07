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

import com.bodhi.business.LibSearchBusiness;
import com.bodhi.model.Book;
 
@Controller
public class SearchController {
	
	private static final Logger logger = Logger.getLogger(SearchController.class);

	
	@Autowired
	LibSearchBusiness searchLib;
	
	@Autowired
	Book book;
		
	@RequestMapping(value = { "/admin/search" },method=RequestMethod.GET)
	public ModelAndView search() {
		
		System.out.println("in search controller"); 
		ModelAndView mv = new ModelAndView("bodhi_search");
		
		return mv;
	}
	
	@RequestMapping(value = { "/admin/searchResults" },method=RequestMethod.GET)
	public ModelAndView searchResults(
			HttpServletRequest request,
			HttpServletResponse response) {
		
		System.out.println("in searchResults controller"); 
		
		book.setAuthor_name(request.getParameter("searchAuthorName"));
		book.setBook_id(request.getParameter("searchBookId"));
		book.setTitle(request.getParameter("searchBookName"));
		List<Book> searchResultList = searchLib.getSearchResults(book);
				
		ModelAndView mv = new ModelAndView("bodhi_search");
		if(null != searchResultList && searchResultList.size() > 0 ){
			
			mv.addObject("bookresults",searchResultList);
			
		} else {
			mv.addObject("bookresults", "nomatchingrecords");
		}
		
		return mv;
	}
	
	
}