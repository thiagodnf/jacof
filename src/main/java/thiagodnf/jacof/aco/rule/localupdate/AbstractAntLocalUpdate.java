package thiagodnf.jacof.aco.rule.localupdate;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractAntLocalUpdate {
	
	protected ACO aco;
	
	public AbstractAntLocalUpdate(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract void update(int currentNode, int nextNode);
}
