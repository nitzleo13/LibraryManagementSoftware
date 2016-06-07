package com.bodhi.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.bodhi.business.LibSearchBusiness;
import com.bodhi.dao.SearchDao;
import com.bodhi.model.Book;

public class LibSearchBusinessImpl implements LibSearchBusiness {

	@Autowired
	SearchDao srchDao;
	
	@Override
	public List<Book> getSearchResults(Book book) {
		// TODO Auto-generated method stub
		
		List<Book> bookList= new ArrayList<Book>();
		List<Book> newbookList= new ArrayList<Book>();
		bookList = srchDao.getSearchResults(book);
		
		if(null != bookList){
			for (int i = 0; i < bookList.size(); i++) {
				Book formatBook = bookList.get(i);
				String book_id = formatBook.getBook_id();
				formatBook.setBook_id(StringUtils.leftPad(book_id,10,"0"));
				newbookList.add(formatBook);
			}
		}
		return newbookList;
	}

}
