package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.subset.AbstractSubSet;

public class PartialDeposit extends AbstractDeposit {

	protected AbstractSubSet subSet;

	public PartialDeposit(ACO aco, double rate, AbstractSubSet subSet) {
		super(aco, rate);
		
		checkNotNull(subSet, "The sub set cannot be null");

		this.subSet = subSet;
	}

	@Override
	public double getTheNewValue(int i, int j) {
		return aco.getGraph().getTau(i, j) + rate * getDeltaTau(i, j);
	}

	public double getDeltaTau(int i, int j) {

		double deltaTau = 0.0;

		for (Ant ant : subSet.getSubSet()) {
			if (ant.path[i][j] == 1) {
				deltaTau += aco.getProblem().getDeltaTau(ant.getTourLength(), i, j);
			}
		}

		return deltaTau;
	}
	
	@Override
	public String toString() {
		return PartialDeposit.class.getSimpleName() + " with " + subSet + " and rate=" + rate;
	}
}
