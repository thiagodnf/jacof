package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for a predefined value. 
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class FixedValueInitialization extends AbstractGraphInitialization{
	
	protected double value;
	
	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param value The predefined value
	 */
	public FixedValueInitialization(ACO aco, double value) {
		super(aco);
		
		checkArgument(value >= 0.0, "The value should be greater or equal than 0.0");
		
		this.value = value;
	}
	
	/**
	 * Default Constructor for value=0.5, 
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public FixedValueInitialization(ACO aco) {
		this(aco, 0.5);
	}
	
	@Override
	public double getT0() {
		return value;
	}
	
	@Override
	public String toString() {
		return FixedValueInitialization.class.getSimpleName() + " " + this.value;
	}
}
