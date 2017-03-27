package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for Rank-based Ant System (ASRank) algorithm. This value
 * is based on Travelling Salesman Problem and was extracted from:
 * <br><br>
 * Bullnheimer, Bernd, Richard F. Hartl, and Christine Strauss. 
 * "A new rank based version of the Ant System. A computational study." (1997).
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class ASRankInitialization extends AbstractGraphInitialization {

	/** The evaporation rate */
	protected double rate;
	
	/** The weight*/
	protected double weight;

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param rate The evaporation rate
	 * @param weight The weight rate
	 */
	public ASRankInitialization(ACO aco, double rate, double weight) {
		super(aco);
		
		checkArgument(rate >= 0.0, "The rate should be greater or equal than 0.0");
		checkArgument(rate <= 1.0, "The rate should be less or equal than 1.0");
		checkArgument(weight >= 0, "The weight should be greater or equal than 0.0");

		this.rate = rate;
		this.weight = weight;
	}
	
	/**
	 * Constructor for the best value for TSP. rate=0.1 and weight=6, 
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public ASRankInitialization(ACO aco) {
		this(aco, 0.1, 6);
	}

	@Override
	public double getT0() {
		double cnn = aco.getProblem().getCnn();

		return (0.5 * weight * (weight - 1.0)) / (rate * cnn);
	}
	
	@Override
	public String toString() {
		return ASRankInitialization.class.getSimpleName() + " " + this.rate + " " + this.weight;
	}
}
