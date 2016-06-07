package com.bodhi.model;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
	
	public String loan_id;
	public String book_id;
	public String branch_id;
	public int card_no;
	public Date date_out;
	public Date due_date;
	public Date date_in;
	public String fname;
	public String lname;
	/**
	 * @return the loan_id
	 */
	public String getLoan_id() {
		return loan_id;
	}
	/**
	 * @param loan_id the loan_id to set
	 */
	public void setLoan_id(String loan_id) {
		this.loan_id = loan_id;
	}
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
	/**
	 * @return the card_no
	 */
	public int getCard_no() {
		return card_no;
	}
	/**
	 * @param card_no the card_no to set
	 */
	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}
	/**
	 * @return the date_out
	 */
	public Date getDate_out() {
		return date_out;
	}
	/**
	 * @param date_out the date_out to set
	 */
	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}
	/**
	 * @return the due_date
	 */
	public Date getDue_date() {
		return due_date;
	}
	/**
	 * @param due_date the due_date to set
	 */
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	/**
	 * @return the date_in
	 */
	public Date getDate_in() {
		return date_in;
	}
	/**
	 * @param date_in the date_in to set
	 */
	public void setDate_in(Date date_in) {
		this.date_in = date_in;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
}
