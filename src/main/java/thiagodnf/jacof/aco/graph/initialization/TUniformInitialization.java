package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.random.PseudoRandom;

public class TUniformInitialization extends TrailInitialization {

	protected double minValue;

	protected double maxValue;

	public TUniformInitialization(ACO aco, double minValue, double maxValue) {
		super(aco);

		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public double getT0() {
		return PseudoRandom.randDouble(minValue, maxValue);
	}

	@Override
	public String toString() {
		return String.format("%s [%s:%s]", TUniformInitialization.class.getSimpleName(), this.minValue, this.maxValue);
	}
}
