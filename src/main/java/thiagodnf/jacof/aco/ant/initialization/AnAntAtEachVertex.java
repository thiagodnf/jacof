package thiagodnf.jacof.aco.ant.initialization;

import thiagodnf.jacof.aco.ACO;

public class AnAntAtEachVertex extends AntInitialization {

	protected int[] positions;

	public AnAntAtEachVertex(ACO aco) {
		super(aco);

		this.positions = new int[aco.getNumberOfAnts()];

		for (int i = 0; i < positions.length; i++) {
			positions[i] = i;
		}
	}

	@Override
	public int getPosition(int antId) {
		return this.positions[antId];
	}
}
