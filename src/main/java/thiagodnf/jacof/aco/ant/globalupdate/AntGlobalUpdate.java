package thiagodnf.jacof.aco.ant.globalupdate;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public abstract class AntGlobalUpdate {
	
	protected ACO aco;
	
	public AntGlobalUpdate(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract double getValue(Ant[] ant, Ant globalBest, int currentNode, int nextNode);
}
