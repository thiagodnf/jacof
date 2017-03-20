package thiagodnf.jacof.aco.ant.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AntInitialization{
	
	protected ACO aco;

	public AntInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract int getPosition(int antId);
}
