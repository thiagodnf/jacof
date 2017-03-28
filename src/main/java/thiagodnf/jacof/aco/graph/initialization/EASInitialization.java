package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Elitist Ant System (EAS) algorithm. This value
 * is based on Travelling Salesman Problem and was extracted from:
 * <br><br>
 * M. Dorigo, V. Maniezzo, and A. Colorni. Ant system: Optimization by a colony of
 * cooperating agents. IEEE Transactions on Systems, Man, and Cybernetics, Part B:
 * Cybernetics, 26(1):29–41, February 1996.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class EASInitialization extends AbstractGraphInitialization {

	/** The evaporation rate */
	protected double rate;

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param rate The evaporation rate
	 */
	public EASInitialization(ACO aco, double rate) {
		super(aco);
		
		checkArgument(rate >= 0.0, "The rate should be greater or equal than 0.0");
		checkArgument(rate <= 1.0, "The rate should be less or equal than 1.0");

		this.rate = rate;
	}
	
	/**
	 * Constructor for the best value for TSP. rate = 0.5, 
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public EASInitialization(ACO aco) {
		this(aco, 0.5);
	}

	@Override
	public double getT0() {
		double k = aco.getNumberOfAnts();
		double n = aco.getProblem().getNumberOfNodes();
		double cnn = aco.getProblem().getCnn();
		
		return (n + k) / (rate * cnn);
	}
	
	@Override
	public String toString() {
		return EASInitialization.class.getSimpleName() + " with rate=" + this.rate;
	}
}
