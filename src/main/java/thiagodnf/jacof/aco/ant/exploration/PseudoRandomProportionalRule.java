package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkState;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.ant.selection.AbstractAntSelection;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;

/**
 * This class represents how an ant in AS algorithm chooses the next node
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class PseudoRandomProportionalRule extends AbstractAntExploration {

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 * @param antSelection The ant selection used
	 */
	public PseudoRandomProportionalRule(ACO aco, AbstractAntSelection antSelection) {
		super(aco, antSelection);
	}
	
	/**
	 * Constructor by using RouletteWheel as default ant selection
	 * 
	 * @param aco The ant colony optimization used
	 */
	public PseudoRandomProportionalRule(ACO aco) {
		this(aco, new RouletteWheel());
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

			checkState(aco.getGraph().getTau(i, j) != 0.0, "The tau(i,j) should not be 0.0");

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
		nextNode = antSelection.select(probability, sumProbability);

		checkState(nextNode != -1, "The next node should not be -1");

		return nextNode;
	}
	
	@Override
	public String toString() {
		return PseudoRandomProportionalRule.class.getSimpleName() + " with " + antSelection;
	}
}
