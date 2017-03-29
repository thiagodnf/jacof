package thiagodnf.jacof.aco.subset.single;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * This class represents the subset with only one ant selected randomly 
 * from set of global or current-best solutions.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class GlobalOrCurrentBest extends AbstractSingleAnt {

	/** Probability used to select the global or current-best solution */
	protected double probability;

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 * @param probability the probability used to select the global or current best solution
	 */
	public GlobalOrCurrentBest(ACO aco, double probability) {
		super(aco);
		
		checkArgument(probability >= 0.0, "The probability should be greater or equal than 0.0");
		checkArgument(probability <= 1.0, "The probability should be less or equal than 1.0");		
		
		this.probability = probability;
	}
	
	/**
	 * Constructor. Default probability is 0.5
	 * 
	 * @param aco The ant colony optimization used
	 */
	public GlobalOrCurrentBest(ACO aco) {
		this(aco, 0.5);
	}

	@Override
	public List<Ant> getSubSet() {
		if (rand.nextDouble() <= probability) {
			return new GlobalBest(aco).getSubSet();
		} else {
			return new CurrentBest(aco).getSubSet();
		}
	}
	
	@Override
	public String toString(){
		return GlobalOrCurrentBest.class.getSimpleName();
	}

}
