package thiagodnf.jacof.aco.rule;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractRule {
	
	protected ACO aco;
	
	public AbstractRule(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}
}
