package thiagodnf.jacof.aco.ant.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;

public class FullEvaporation extends AbstractEvaporation {

	public FullEvaporation(ACO aco, double rate) {
		super(aco, rate);
	}

	@Override
	protected double getEvaporation(int i, int j) {
		return (1.0 - rate) * aco.getGraph().getTau(i, j);
	}
}
