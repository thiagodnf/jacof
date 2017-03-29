package thiagodnf.jacof.aco.subset;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.util.random.JMetalRandom;

/**
 * This class represents the subset of ants used to evaporate or deposit
 * values in the pheromone matrix. All kind of subset should implement this class.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public abstract class AbstractSubSet {

	/** The ant colony optimization used */
	protected ACO aco;
	
	/** The rand class */
	protected JMetalRandom rand = JMetalRandom.getInstance();
	
	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(AbstractSubSet.class);
	
	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AbstractSubSet(ACO aco){
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}
	
	/**
	 * Get the list of ants that will be used to evaporate or
	 * deposit the pheromone in the pheromone graph
	 * 
	 * @return a list of ants
	 */
	public abstract List<Ant> getSubSet();
	
	@Override
	public abstract String toString();
}
