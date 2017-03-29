package thiagodnf.jacof.aco.ant.exploration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.ant.selection.AbstractAntSelection;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;

/**
 * This class represents how an ant in ACS algorithm chooses the next node
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class QSelection extends PseudoRandomProportionalRule {

	/** The probability of exploration or exploitation*/
	protected double q0;

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 * @param antSelection The ant selection used
	 * @param q0 the probability of exploration or exploitation
	 */
	public QSelection(ACO aco, AbstractAntSelection antSelection, double q0) {
		super(aco, antSelection);

		checkArgument(q0 >= 0.0, "The q0 should be greater or equal than 0.0");
		checkArgument(q0 <= 1.0, "The q0 should be less or equal than 1.0");
		
		this.q0 = q0;
	}
	
	/**
	 * Constructor by using RouletteWheel as default ant selection
	 * 
	 * @param aco The ant colony optimization used
	 */
	public QSelection(ACO aco, double q0) {
		this(aco, new RouletteWheel(), q0);
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

			checkState(aco.getGraph().getTau(i, j) != 0.0, "The tau(i,j) should not be 0.0");

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
	
	@Override
	public String toString() {
		return QSelection.class.getSimpleName() + " with " + antSelection + " and q0=" + q0;
	}
}
