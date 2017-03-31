package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Max-Min Ant System (MMAS) algorithm. This value
 * is based on Travelling Salesman Problem and was extracted from:
 * <p>
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
		
		checkArgument(rate > 0.0 && rate <= 1.0, "The rate should be between (0,1]");
		
		this.rate = rate;
		
		// Define tMin and tMax regarding
		aco.getGraph().setTMax(getT0());
		aco.getGraph().setTMin(aco.getGraph().getTMax() / 10.0);
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
