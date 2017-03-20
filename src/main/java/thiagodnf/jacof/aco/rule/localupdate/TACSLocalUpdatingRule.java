package thiagodnf.jacof.aco.rule.localupdate;

import thiagodnf.jacof.aco.ACO;

public class TACSLocalUpdatingRule extends AntLocalUpdate {

	protected double p;

	public TACSLocalUpdatingRule(ACO aco, double p) {
		super(aco);

		this.p = p;
	}

	@Override
	public void update(int i, int j) {
		
		double evaporation = (1.0 - p) * aco.getGraph().getTau(i, j);
		double deposition = p * aco.getTrailInitialization().getT0();
		
		aco.getGraph().setTau(i, j, evaporation + deposition);
		aco.getGraph().setTau(j, i, evaporation + deposition);
	}
}
