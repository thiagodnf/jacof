package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.QSelection;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.ACSInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.PartialDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.rule.localupdate.ACSLocalUpdatingRule;
import thiagodnf.jacof.aco.subset.single.GlobalBest;
import thiagodnf.jacof.problem.Problem;

public class AntColonySystem extends AntSystem {
	
	protected double q0;
	
	protected double omega;

	public AntColonySystem(Problem problem) {
		super(problem);
	}
	
	public double getQ0() {
		return q0;
	}

	public void setQ0(double q0) {
		this.q0 = q0;
	}

	public double getOmega() {
		return omega;
	}

	public void setOmega(double omega) {
		this.omega = omega;
	}

	@Override
	public void build() {
		// Initialization
		setGraphInitialization(new ACSInitialization(this));
		setAntInitialization(new AnAntAtEachVertex(this));

		// Exploration
		setAntExploration(new QSelection(this, new RouletteWheel(), q0));

		// Local Update Pheromone Rule
		setAntLocalUpdate(new ACSLocalUpdatingRule(this, omega));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, getRho()));
		getDeposits().add(new PartialDeposit(this, getRho(), new GlobalBest(this)));
	}
	
	@Override
	public String toString() {
		return AntColonySystem.class.getSimpleName();
	}
}
