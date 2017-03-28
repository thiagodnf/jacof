package thiagodnf.jacof.aco.rule.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.globalupdate.AbstractGlobalRule;

public abstract class AbstractEvaporation extends AbstractGlobalRule {

	public AbstractEvaporation(ACO aco, double rate) {
		super(aco, rate);
	}

	public abstract double getTheNewValue(int i, int j);
	
	public abstract String toString();
}
