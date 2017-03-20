package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

public class TFixedValueInitialization extends TrailInitialization{
	
	protected double value;
	
	public TFixedValueInitialization(ACO aco, double value) {
		super(aco);
		
		this.value = value;
	}
	
	@Override
	public double getT0() {
		return value;
	}

}
