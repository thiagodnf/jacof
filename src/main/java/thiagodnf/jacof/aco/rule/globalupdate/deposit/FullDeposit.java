package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.subset.SubSet;

public class FullDeposit extends AbstractDeposit {

	protected SubSet subSet;

	public FullDeposit(ACO aco, double rate, SubSet subSet) {
		super(aco, rate);

		this.subSet = subSet;
	}

	@Override
	protected double getTheNewValue(int i, int j) {
		return aco.getGraph().getTau(i, j) + rate * getDeltaTau(i, j);
	}

	public double getDeltaTau(int i, int j) {

		double deltaTau = 0.0;

		for (Ant ant : subSet.getSubSet()) {
			if (ant.path[i][j] == 1) {
				deltaTau += aco.getProblem().getDeltaTau(ant.tourLength, i, j);
			}
		}

		return deltaTau;
	}
}
