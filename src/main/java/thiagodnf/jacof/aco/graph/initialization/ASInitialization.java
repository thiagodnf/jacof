package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Ant System (AS) algorithm. This value
 * is based on Travelling Salesman Problem and was extracted from:
 * <br><br>
 * M. Dorigo, V. Maniezzo, and A. Colorni, "Ant system: Optimization by a colony 
 * of cooperating agents," IEEE Transaction on Systems, Man, and Cybernetics, 
 * vol. 26, pp. 29-41, 1996.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class ASInitialization extends AbstractGraphInitialization {

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public ASInitialization(ACO aco) {
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
		return ASInitialization.class.getSimpleName();
	}
}
