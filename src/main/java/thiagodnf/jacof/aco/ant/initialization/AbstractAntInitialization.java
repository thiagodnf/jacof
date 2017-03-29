package thiagodnf.jacof.aco.ant.initialization;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

/**
 * This class represents how the ants will be positioned in the graph nodes
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public abstract class AbstractAntInitialization{
	
	/** The ant colony optimization used*/
	protected ACO aco;
	
	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AbstractAntInitialization(ACO aco) {
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}

	/** 
	 * Return the ant position given a antId
	 * 
	 * @param antId The ant's id
	 * @return the ant position
	 */
	public abstract int getPosition(int antId);
	
	@Override
	public abstract String toString();
}
