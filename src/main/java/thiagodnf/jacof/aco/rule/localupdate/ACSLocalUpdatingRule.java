package thiagodnf.jacof.aco.rule.localupdate;

import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.ACO;

public class ACSLocalUpdatingRule extends AbstractLocalUpdateRule {

	protected double rate;

	public ACSLocalUpdatingRule(ACO aco, double rate) {
		super(aco);
		
		checkArgument(rate >= 0.0, "The rate should be greater or equal than 0.0");
		checkArgument(rate <= 1.0, "The rate should be less or equal than 1.0");

		this.rate = rate;
	}

	@Override
	public void update(int i, int j) {
		
		double evaporation = (1.0 - rate) * aco.getGraph().getTau(i, j);
		double deposition = rate * aco.getGraphInitialization().getT0();
		
		aco.getGraph().setTau(i, j, evaporation + deposition);
		aco.getGraph().setTau(j, i, evaporation + deposition);
	}
	
	@Override
	public String toString() {
		return ACSLocalUpdatingRule.class.getSimpleName() + " with rate=" + rate;
	}
}
