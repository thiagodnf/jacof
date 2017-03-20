package thiagodnf.jacof.aco.rule.globalupdate;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.AbstractRule;

public abstract class AbstractGlobalRule extends AbstractRule{
	
	protected double rate;
	
	public AbstractGlobalRule(ACO aco, double rate) {
		super(aco);
		this.rate = rate;
	}
}
