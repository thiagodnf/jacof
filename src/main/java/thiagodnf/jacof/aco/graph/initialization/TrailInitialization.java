package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class TrailInitialization{

	protected ACO aco;
	
	public TrailInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	public abstract double getT0();
}
