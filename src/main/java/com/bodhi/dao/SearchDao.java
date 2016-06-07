package com.bodhi.dao;

import java.util.List;

import com.bodhi.model.Book;

public interface SearchDao {

	public List<Book> getSearchResults(Book book);
	
	
}
