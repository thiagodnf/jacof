package thiagodnf.jacof.aco.rule.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.globalupdate.AbstractGlobalUpdateRule;

public abstract class AbstractEvaporation extends AbstractGlobalUpdateRule {

	public AbstractEvaporation(ACO aco, double rate) {
		super(aco, rate);
	}

	public abstract double getTheNewValue(int i, int j);
	
	@Override
	public abstract String toString();
}
