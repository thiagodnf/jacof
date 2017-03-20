package thiagodnf.jacof.aco.ant.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractEvaporation {

	protected ACO aco;

	protected double rate;

	public AbstractEvaporation(ACO aco, double rate) {
		this.aco = aco;
		this.rate = rate;
	}

	public void doEvaporation() {
		for (int i = 0; i < aco.getProblem().getNumberOfNodes(); i++) {
			for (int j = i; j < aco.getProblem().getNumberOfNodes(); j++) {
				if (i != j) {

					double evaporation = getEvaporation(i, j);

					aco.getGraph().setTau(i, j, evaporation);
					aco.getGraph().setTau(j, i, aco.getGraph().getTau(i, j));
				}
			}
		}
	}

	protected abstract double getEvaporation(int i, int j);
}
