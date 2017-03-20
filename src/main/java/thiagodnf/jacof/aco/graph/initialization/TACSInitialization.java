package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheronome Initialization for Ant Colony System Algorithm
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class TACSInitialization extends TrailInitialization {

	public TACSInitialization(ACO aco) {
		super(aco);
	}

	@Override
	public double getT0() {
		double n = aco.getProblem().getNumberOfNodes();
		double cnn = aco.getProblem().getCnn();

		return 1.0 / n * cnn;
	}

	@Override
	public String toString() {
		return TACSInitialization.class.getSimpleName();
	}
}
