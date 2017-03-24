package thiagodnf.jacof.aco.ant.initialization;

import thiagodnf.jacof.aco.ACO;

public class AlwaysRandomPositions extends AntInitialization {

	public AlwaysRandomPositions(ACO aco) {
		super(aco);
	}

	@Override
	public int getPosition(int antId) {
		return rand.nextInt(0, aco.getProblem().getNumberOfNodes() - 1);
	}
}
