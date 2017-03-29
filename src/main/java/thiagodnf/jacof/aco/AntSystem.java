package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.PseudoRandomProportionalRule;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.ASInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.FullDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.Parameters;

public class AntSystem extends ACO {

	protected Parameters parameters;

	public AntSystem(Problem problem, Parameters parameters) {
		super(problem);
		
		this.parameters = parameters;
	}

	@Override
	public void build() {
		setGraphInitialization(new ASInitialization(this));
		setAntInitialization(new AnAntAtEachVertex(this));
		
		setAntExploration(new PseudoRandomProportionalRule(this, new RouletteWheel()));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new FullDeposit(this));
	}
	
	@Override
	public String toString() {
		return AntSystem.class.getSimpleName();
	}
}
