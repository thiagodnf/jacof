package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.util.random.JMetalRandom;

/**
 * Pheromone initialization for a range [minValue:maxValue]. 
 * A random number will be chosen taking into consideration this range
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class UniformInitialization extends AbstractGraphInitialization {

	/** The minimum value */
	protected double minValue;

	/** The maximum value */
	protected double maxValue;
	
	protected JMetalRandom rand = JMetalRandom.getInstance();

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param minValue The minimum value
	 * @param maxValue The maximum value
	 */
	public UniformInitialization(ACO aco, double minValue, double maxValue) {
		super(aco);

		checkArgument(minValue <= maxValue, "The minValue should be less or equal than maxValue");
		
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	/**
	 * Constructor for minValue=0.0 and maxValue=1.0
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public UniformInitialization(ACO aco) {
		this(aco, 0.0, 1.0);
	}

	@Override
	public double getT0() {
		return rand.nextDouble(minValue, maxValue);
	}

	@Override
	public String toString() {
		return String.format("%s [%s:%s]", UniformInitialization.class.getSimpleName(), this.minValue, this.maxValue);
	}
}
