package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Ant Colony System (ACS) algorithm. This value
 * is based on Travelling Salesman Problem and it was extracted from:
 * <br><br>
 * Dorigo, M., and Gambardella, L. M. (1997b). Ant Colony System: A 
 * cooperative learning approach to the traveling salesman problem. 
 * IEEE Transactions on Evolutionary Computation, 1(1), 53–66.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class ACSInitialization extends AbstractGraphInitialization {

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public ACSInitialization(ACO aco) {
		super(aco);
	}

	@Override
	public double getT0() {
		double n = aco.getProblem().getNumberOfNodes();
		double cnn = aco.getProblem().getCnn();

		return 1.0 / (n * cnn);
	}

	@Override
	public String toString() {
		return ACSInitialization.class.getSimpleName();
	}
}
