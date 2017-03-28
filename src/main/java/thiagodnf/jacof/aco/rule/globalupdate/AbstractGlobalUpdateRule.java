package thiagodnf.jacof.aco.rule.globalupdate;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.AbstractRule;

public abstract class AbstractGlobalUpdateRule extends AbstractRule{
	
	protected double rate;
	
	public AbstractGlobalUpdateRule(ACO aco, double rate) {
		super(aco);
		
		checkNotNull(aco, "The aco cannot be null");		
		checkArgument(rate >= 0.0, "The rate should be greater or equal than 0");
		checkArgument(rate <= 1.0, "The rate should be less or equal than 1");
		
		this.rate = rate;
	}
}
