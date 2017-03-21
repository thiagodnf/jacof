package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.TACSExploration;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.ACSInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.FullDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.rule.localupdate.TACSLocalUpdatingRule;
import thiagodnf.jacof.aco.subset.single.GlobalBest;
import thiagodnf.jacof.problem.Problem;

public class AntColonySystem extends AntSystem {

	protected double q0;

	public AntColonySystem(Problem problem) {
		super(problem);
	}

	public double getQ0() {
		return q0;
	}

	public void setQ0(double q0) {
		this.q0 = q0;
	}

	@Override
	public void build() {
		setAntInitialization(new AnAntAtEachVertex(this));
		setTrailInitialization(new ACSInitialization(this));

		setAntExploration(new TACSExploration(this, q0));
		setAntSelection(new RouletteWheel());

		setAntLocalUpdate(new TACSLocalUpdatingRule(this, 0.1));

		// Global Update Pheronome
		setEvaporation(new FullEvaporation(this, rho));
		setDeposit(new FullDeposit(this, rho, new GlobalBest(this)));
	}
}
