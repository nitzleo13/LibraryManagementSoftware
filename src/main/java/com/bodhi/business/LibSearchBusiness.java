package com.bodhi.business;

import java.util.List;

import com.bodhi.model.Book;

public interface LibSearchBusiness {
	
	public List<Book> getSearchResults(Book book);

}
