package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Max-Min Ant System (MMAS) algorithm. This value
 * is based on Travelling Salesman Problem and was extracted from:
 * <br><br>
 * T. Stützle and H. H. Hoos, "MAX-MIN ant system," Future Generation 
 * Computer Systems, 2000, vol. 16, pp. 889-914.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class MMASInitialization extends AbstractGraphInitialization {

	/** The evaporation rate */
	protected double rate;

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param rate The evaporation rate
	 */
	public MMASInitialization(ACO aco, double rate) {
		super(aco);
		
		checkArgument(rate >= 0.0, "The rate should be greater or equal than 0.0");
		checkArgument(rate <= 1.0, "The rate should be less or equal than 1.0");

		this.rate = rate;
	}
	
	/**
	 * Constructor for the best value for TSP. rate = 0.02, 
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public MMASInitialization(ACO aco) {
		this(aco, 0.02);
	}

	@Override
	public double getT0() {
		double cnn = aco.getProblem().getCnn();

		return 1.0 / (rate * cnn);
	}

	@Override
	public String toString() {
		return MMASInitialization.class.getSimpleName() + " with rate=" + this.rate;
	}
}
