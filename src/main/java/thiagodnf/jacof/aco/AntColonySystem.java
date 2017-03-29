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
import thiagodnf.jacof.util.Parameters;

public class AntColonySystem extends AntSystem {

	public AntColonySystem(Problem problem, Parameters parameters) {
		super(problem, parameters);
	}

	@Override
	public void build() {
		setGraphInitialization(new ACSInitialization(this));
		setAntInitialization(new AnAntAtEachVertex(this));

		setAntExploration(new QSelection(this, new RouletteWheel(), parameters.getDouble("q0", 0.95)));

		setAntLocalUpdate(new ACSLocalUpdatingRule(this, parameters.getDouble("o", 0.1)));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new PartialDeposit(this, rho, new GlobalBest(this)));
	}
	
	@Override
	public String toString() {
		return AntColonySystem.class.getSimpleName();
	}
}
