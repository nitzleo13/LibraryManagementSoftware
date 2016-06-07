package com.bodhi.model;

import java.io.Serializable;

public class Branch implements Serializable {
	
	public String address;
	public String branch_id;
	public String branch_name;
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the branch_name
	 */
	public String getBranch_name() {
		return branch_name;
	}
	/**
	 * @param branch_name the branch_name to set
	 */
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	
}
