package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.PseudoRandomProportionalRule;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.ASRankInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.RankDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.subset.many.RankAnt;
import thiagodnf.jacof.problem.Problem;

public class RankBasedAntSystem extends ElitistAntSystem {

	public RankBasedAntSystem(Problem problem) {
		super(problem);
	}

	@Override
	public void build() {
		setGraphInitialization(new ASRankInitialization(this, rho, weight));
		setAntInitialization(new AnAntAtEachVertex(this));

		setAntExploration(new PseudoRandomProportionalRule(this, new RouletteWheel()));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new RankDeposit(this, weight, new RankAnt(this, getNumberOfAnts())));
	}
	
	@Override
	public String toString() {
		return RankBasedAntSystem.class.getSimpleName();
	}
}
