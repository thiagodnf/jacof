package thiagodnf.jacof.aco.subset.many;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.subset.AbstractSubSet;

/**
 * This class represents the subset with a set of ants
 * All kind of many subset should implement this class.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public abstract class AbstractManyAnts extends AbstractSubSet{

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AbstractManyAnts(ACO aco) {
		super(aco);
	}
}
