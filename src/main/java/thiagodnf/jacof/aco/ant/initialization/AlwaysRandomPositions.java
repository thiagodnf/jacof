package thiagodnf.jacof.aco.ant.initialization;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.random.PseudoRandom;

public class AlwaysRandomPositions extends AntInitialization {

	public AlwaysRandomPositions(ACO aco) {
		super(aco);
	}

	@Override
	public int getPosition(int antId) {
		return PseudoRandom.randInt(0, aco.getProblem().getNumberOfNodes() - 1);
	}
}
