package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheronome Initialization for Max-Min Ant System Algorithm
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class TMMASInitialization extends TrailInitialization {

	protected double rate;

	public TMMASInitialization(ACO aco, double rate) {
		super(aco);

		this.rate = rate;
	}

	@Override
	public double getT0() {
		double cnn = aco.getProblem().getCnn();

		return 1 / rate * cnn;
	}

	@Override
	public String toString() {
		return TMMASInitialization.class.getSimpleName() + ": " + this.rate;
	}
}
