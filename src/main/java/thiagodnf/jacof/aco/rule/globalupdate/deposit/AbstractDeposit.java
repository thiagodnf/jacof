package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.globalupdate.AbstractGlobalRule;

public abstract class AbstractDeposit extends AbstractGlobalRule{

	public AbstractDeposit(ACO aco, double rate) {
		super(aco, rate);		
	}

	public abstract double getTheNewValue(int i, int j);
}
