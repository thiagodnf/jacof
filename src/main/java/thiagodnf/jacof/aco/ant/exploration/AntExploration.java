package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public abstract class AntExploration {

	protected ACO aco;
	
	public AntExploration(ACO aco){
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract int getNextNode(Ant ant, int currentNode);
}
