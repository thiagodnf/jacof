package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractTrailInitialization{

	protected ACO aco;
	
	public AbstractTrailInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract double getT0();
}
