package com.bodhi.model;

public class Fines {
	
	public int card_no;
	public String fname;
	public String lname;
	public String loan_id;
	public double fine_amt;
	public int paid;
	public int no_of_days;
	
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
	 * @return the fine_amt
	 */
	public double getFine_amt() {
		return fine_amt;
	}
	/**
	 * @param fine_amt the fine_amt to set
	 */
	public void setFine_amt(double fine_amt) {
		this.fine_amt = fine_amt;
	}
	/**
	 * @return the paid
	 */
	public int getPaid() {
		return paid;
	}
	/**
	 * @param paid the paid to set
	 */
	public void setPaid(int paid) {
		this.paid = paid;
	}
	/**
	 * @return the no_of_days
	 */
	public int getNo_of_days() {
		return no_of_days;
	}
	/**
	 * @param no_of_days the no_of_days to set
	 */
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
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
