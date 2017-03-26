package thiagodnf.jacof.aco.subset.single;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.subset.AbstractSubSet;

/**
 * This class represents the subset with only one ant.
 * All kind of single subset should implement this class.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public abstract class AbstractSingleAnt extends AbstractSubSet{

	/**
	 * Constructor
	 * 
	 * @param aco The Ant Colony Optimization used
	 */
	public AbstractSingleAnt(ACO aco) {
		super(aco);
	}
}
