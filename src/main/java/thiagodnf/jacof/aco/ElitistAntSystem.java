package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.PseudoRandomProportionalRule;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.EASInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.ElitistDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.Parameters;

public class ElitistAntSystem extends AntSystem {

	public ElitistAntSystem(Problem problem, Parameters parameters) {
		super(problem, parameters);
	}

	@Override
	public void build() {
		setGraphInitialization(new EASInitialization(this, rho));
		setAntInitialization(new AnAntAtEachVertex(this));

		setAntExploration(new PseudoRandomProportionalRule(this, new RouletteWheel()));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new ElitistDeposit(this, parameters.getInt("w", 5)));
	}

	@Override
	public String toString() {
		return ElitistAntSystem.class.getSimpleName();
	}
}
