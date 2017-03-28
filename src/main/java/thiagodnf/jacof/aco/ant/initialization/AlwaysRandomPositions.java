package thiagodnf.jacof.aco.ant.initialization;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.util.random.JMetalRandom;

/**
 * This class represents that all ants will be always positioned in random positions
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class AlwaysRandomPositions extends AbstractAntInitialization {

	/** The rand class*/
	protected JMetalRandom rand = JMetalRandom.getInstance();
	
	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AlwaysRandomPositions(ACO aco) {
		super(aco);
	}

	@Override
	public int getPosition(int antId) {
		return rand.nextInt(0, aco.getProblem().getNumberOfNodes() - 1);
	}
	
	@Override
	public String toString() {
		return AlwaysRandomPositions.class.getSimpleName();
	}
}
