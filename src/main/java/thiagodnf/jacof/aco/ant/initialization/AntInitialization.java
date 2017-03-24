package thiagodnf.jacof.aco.ant.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.random.JMetalRandom;

public abstract class AntInitialization{
	
	protected ACO aco;
	
	protected JMetalRandom rand = JMetalRandom.getInstance();

	public AntInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract int getPosition(int antId);
}
