package com.bodhi.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;


public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String book_id;
	public String title;
	public String author_name;
	public int no_of_copies;  
	public String copies_avl;
	public String branch_id;
	
	/**
	 * @return the book_id
	 */
	public String getBook_id() {
		return book_id;
	}
	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author_name
	 */
	public String getAuthor_name() {
		return author_name;
	}
	/**
	 * @param author_name the author_name to set
	 */
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	/**
	 * @return the no_of_copies
	 */
	public int getNo_of_copies() {
		return no_of_copies;
	}
	/**
	 * @param no_of_copies the no_of_copies to set
	 */
	public void setNo_of_copies(int no_of_copies) {
		this.no_of_copies = no_of_copies;
	}
	/**
	 * @return the copies_avl
	 */
	public String getCopies_avl() {
		return copies_avl;
	}
	/**
	 * @param copies_avl the copies_avl to set
	 */
	public void setCopies_avl(String copies_avl) {
		this.copies_avl = copies_avl;
	}
	/**
	 * @return the branch_id
	 */
	public String getBranch_id() {
		return branch_id;
	}
	/**
	 * @param branch_id the branch_id to set
	 */
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	


}
