package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.TACSExploration;
import thiagodnf.jacof.aco.graph.initialization.TACSInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.FullDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.rule.localupdate.TACSLocalUpdatingRule;
import thiagodnf.jacof.aco.subset.single.GlobalBest;
import thiagodnf.jacof.problem.Problem;

public class AntColonySystem extends AntSystem {

	protected double q0;

	public double getQ0() {
		return q0;
	}

	public void setQ0(double q0) {
		this.q0 = q0;
	}
	
	public AntColonySystem(Problem problem) {
		super(problem);
	}

	@Override
	public void build() {
		setTrailInitialization(new TACSInitialization(this));
		setAntExploration(new TACSExploration(this, q0));
		
		setAntLocalUpdate(new TACSLocalUpdatingRule(this, 0.1));
				
		// Global Update Pheronome
		setEvaporation(new FullEvaporation(this, rho));
		setDeposit(new FullDeposit(this, rho, new GlobalBest(this)));
	}	
}
