package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkState;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class TACSExploration extends TASExploration {

	protected double q0;

	public TACSExploration(ACO aco, double q0) {
		super(aco);

		this.q0 = q0;
	}

	@Override
	public int getNextNode(Ant ant, int i) {
		if (rand.nextDouble(0.0, 1.0) <= q0) {
			return doExploitation(ant, i);
		} else {
			return doExploration(ant, i);
		}
	}

	protected int doExploitation(Ant ant, int i) {
		int nextNode = -1;
		
		double maxValue = Double.MIN_VALUE;

		// Update the sum
		for (Integer j : ant.getNodesToVisit()) {

			checkState(aco.getGraph().getTau(i, j) != 0.0, "The tau node should not br 0.0");

			double tij = aco.getGraph().getTau(i, j);
			double nij = Math.pow(aco.getProblem().getNij(i, j), aco.getBeta());
			double value = tij * nij;

			if (value > maxValue) {
				maxValue = value;
				nextNode = j;
			}
		}

		checkState(nextNode != -1, "The next node should not be -1");

		return nextNode;
	}
}
