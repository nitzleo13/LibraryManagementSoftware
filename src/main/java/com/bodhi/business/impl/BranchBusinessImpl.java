package com.bodhi.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bodhi.business.BranchBusiness;
import com.bodhi.dao.BorrowerDao;
import com.bodhi.dao.BranchDao;
import com.bodhi.model.Branch;

public class BranchBusinessImpl implements BranchBusiness {

	@Autowired
	BranchDao branchDao;
	
	@Override
	public List<Branch> getBranchList() {
		// TODO Auto-generated method stub
		return branchDao.getBranches();
	}

	

}
