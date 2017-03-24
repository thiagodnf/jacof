package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.random.JMetalRandom;

public abstract class AntExploration {

	protected ACO aco;
	
	protected JMetalRandom rand = JMetalRandom.getInstance();
	
	public AntExploration(ACO aco){
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract int getNextNode(Ant ant, int currentNode);
}
