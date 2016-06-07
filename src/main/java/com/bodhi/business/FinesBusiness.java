package com.bodhi.business;

import java.util.List;

import com.bodhi.model.Fines;
import com.bodhi.model.Loan;

public interface FinesBusiness {

	List<Fines> compute();

	List<Fines> lookupFines(Fines fines);

	int payInFull(Fines fines);

}
