package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.PseudoRandomProportionalRule;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.EASInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.ElitistDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.problem.Problem;

public class ElitistAntSystem extends AntSystem {
	
	protected int weight;

	public ElitistAntSystem(Problem problem) {
		super(problem);
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public void build() {
		setGraphInitialization(new EASInitialization(this, rho));
		setAntInitialization(new AnAntAtEachVertex(this));

		setAntExploration(new PseudoRandomProportionalRule(this, new RouletteWheel()));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new ElitistDeposit(this, weight));
	}

	@Override
	public String toString() {
		return ElitistAntSystem.class.getSimpleName();
	}
}
