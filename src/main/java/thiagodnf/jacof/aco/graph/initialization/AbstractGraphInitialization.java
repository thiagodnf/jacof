package thiagodnf.jacof.aco.graph.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

/**
 * This class represents the trail initialization. All kind
 * of trail initialization should implement this class.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public abstract class AbstractGraphInitialization{

	/** The Ant Colony Optimization used */
	protected ACO aco;
	
	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public AbstractGraphInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}
		
	/**
	 * Get the initial pheromone value. This value depends
	 * on the trail initialization chosen
	 * 
	 * @return the initial pheromone value
	 */
	public abstract double getT0();
	
	/**
	 * Returns a string representation of the object. 
	 */
	@Override
	public abstract String toString();
}
