package thiagodnf.jacof.aco.ant.localupdate;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AntLocalUpdate {
	
	protected ACO aco;
	
	public AntLocalUpdate(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract void update(int currentNode, int nextNode);
}
