package thiagodnf.jacof.aco.ant.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

/**
 * This class positions each ant in a vertex sequentially. If the number of ants 
 * is greater than the number of nodes, a circular position is used.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class AnAntAtEachVertex extends AbstractAntInitialization {

	/** The ant's positions */
	protected int[] positions;

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AnAntAtEachVertex(ACO aco) {
		super(aco);

		checkArgument(aco.getNumberOfAnts() >= 1, "The number of ants should be greater or equal than 1");
		
		this.positions = new int[aco.getNumberOfAnts()];

		for (int i = 0; i < positions.length; i++) {
			positions[i] = i % aco.getProblem().getNumberOfNodes();
		}		
	}

	@Override
	public int getPosition(int antId) {
		
		checkArgument(antId >= 0, "The ant's id should be greater or equal than 0");
		checkArgument(antId < aco.getNumberOfAnts(), "The ant's id should be less than the number of ants");
		
		return this.positions[antId];
	}
	
	@Override
	public String toString() {
		return AnAntAtEachVertex.class.getSimpleName();
	}
}
