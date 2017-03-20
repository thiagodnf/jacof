package thiagodnf.jacof.aco.rule.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.rule.globalupdate.AbstractGlobalRule;

public abstract class AbstractEvaporation extends AbstractGlobalRule {

	public AbstractEvaporation(ACO aco, double rate) {
		super(aco, rate);
	}

	public void doEvaporation() {
		for (int i = 0; i < aco.getProblem().getNumberOfNodes(); i++) {

			for (int j = i; j < aco.getProblem().getNumberOfNodes(); j++) {

				if (i != j) {

					aco.getGraph().setTau(i, j, getTheNewValue(i, j));
					aco.getGraph().setTau(j, i, aco.getGraph().getTau(i, j));
				}
			}
		}
	}

	protected abstract double getTheNewValue(int i, int j);
}
