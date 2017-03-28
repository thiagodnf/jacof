package thiagodnf.jacof.aco.ant.initialization;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.util.random.JMetalRandom;

/**
 * This class represents that all ants will be positioned in random vertex
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class AnAntAtARandomVertex extends AbstractAntInitialization {

	/** The ant's positions */
	protected int[] positions;
	
	/** The rand class*/
	protected JMetalRandom rand = JMetalRandom.getInstance();

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public AnAntAtARandomVertex(ACO aco) {
		super(aco);

		checkArgument(aco.getNumberOfAnts() >= 1, "The number of ants should be greater or equal than 1");
		
		this.positions = new int[aco.getNumberOfAnts()];

		for (int i = 0; i < positions.length; i++) {
			positions[i] = rand.nextInt(0, aco.getProblem().getNumberOfNodes() - 1);
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
		return AnAntAtARandomVertex.class.getSimpleName();
	}
}
