package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheronome Initialization for Ant System Algorithm
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class TASInitialization extends TrailInitialization {

	public TASInitialization(ACO aco) {
		super(aco);
	}

	@Override
	public double getT0() {
		double k = aco.getNumberOfAnts();
		double cnn = aco.getProblem().getCnn();

		return k / cnn;
	}

	@Override
	public String toString() {
		return TASInitialization.class.getSimpleName();
	}
}
