package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

public class FixedValueInitialization extends AbstractTrailInitialization{
	
	protected double value;
	
	public FixedValueInitialization(ACO aco, double value) {
		super(aco);
		
		this.value = value;
	}
	
	@Override
	public double getT0() {
		return value;
	}

}
