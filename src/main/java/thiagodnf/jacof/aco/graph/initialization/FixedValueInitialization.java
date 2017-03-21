package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheromone initialization for a predefined value. 
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class FixedValueInitialization extends AbstractTrailInitialization{
	
	protected double value;
	
	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 * @param value The predefined value
	 */
	public FixedValueInitialization(ACO aco, double value) {
		super(aco);
		
		this.value = value;
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
