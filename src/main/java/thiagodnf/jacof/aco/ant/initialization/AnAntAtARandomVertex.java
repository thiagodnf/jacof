package thiagodnf.jacof.aco.ant.initialization;

import thiagodnf.jacof.aco.ACO;

public class AnAntAtARandomVertex extends AntInitialization {

	protected int[] positions;

	public AnAntAtARandomVertex(ACO aco) {
		super(aco);

		this.positions = new int[aco.getNumberOfAnts()];

		for (int i = 0; i < positions.length; i++) {
			positions[i] = rand.nextInt(0, aco.getProblem().getNumberOfNodes() - 1);
		}
	}

	@Override
	public int getPosition(int antId) {
		return this.positions[antId];
	}
}
