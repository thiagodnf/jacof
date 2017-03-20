package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkState;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class TASExploration extends AntExploration {

	public TASExploration(ACO aco) {
		super(aco);
	}
	
	@Override
	public int getNextNode(Ant ant, int i) {
		return doExploration(ant, i);	
	}

	public int doExploration(Ant ant, int i) {
		
		int nextNode = -1;

		double sum = 0.0;
		
		double[] tij = new double[aco.getProblem().getNumberOfNodes()];
		double[] nij = new double[aco.getProblem().getNumberOfNodes()];

		// Update the sum
		for (Integer j : ant.getNodesToVisit()) {

			checkState(aco.getGraph().getTau(i, j) != 0.0, "The tau node should not be 0.0");

			tij[j] = Math.pow(aco.getGraph().getTau(i, j), aco.getAlpha());
			nij[j] = Math.pow(aco.getProblem().getNij(i, j), aco.getBeta());

			sum += tij[j] * nij[j];
		}
		
		checkState(sum != 0.0, "The sum cannot be 0.0");

		double[] probability = new double[aco.getProblem().getNumberOfNodes()];

		double sumProbability = 0.0;

		for (Integer j : ant.getNodesToVisit()) {

			probability[j] = (tij[j] * nij[j]) / sum;

			sumProbability += probability[j];
		}

		// Select the next node by probability
		nextNode = aco.getAntSelection().select(probability, sumProbability);

		checkState(nextNode != -1, "The next node should not be -1");

		return nextNode;
	}
}
