package thiagodnf.jacof.aco.rule.localupdate;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractLocalUpdateRule {
	
	protected ACO aco;
	
	public AbstractLocalUpdateRule(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract void update(int currentNode, int nextNode);
}
