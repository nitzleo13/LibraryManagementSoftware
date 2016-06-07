package com.bodhi.dao;

import java.util.List;

import com.bodhi.model.Fines;
import com.bodhi.model.Loan;

public interface FinesDao {

	List<Fines> compute();

	List<Fines> lookupFines(Fines fines);

	int payInFull(Fines fines);
}
