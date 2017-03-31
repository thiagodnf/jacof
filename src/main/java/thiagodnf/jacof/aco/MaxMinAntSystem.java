package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.PseudoRandomProportionalRule;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.daemonactions.RestartCheck;
import thiagodnf.jacof.aco.daemonactions.UpdateTMinAndTMaxValues;
import thiagodnf.jacof.aco.daemonactions.UpdatePheromoneMatrix;
import thiagodnf.jacof.aco.graph.initialization.MMASInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.PartialDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.subset.single.GlobalBest;
import thiagodnf.jacof.problem.Problem;

public class MaxMinAntSystem extends AntSystem {

	protected int stagnation;
	
	public MaxMinAntSystem(Problem problem) {
		super(problem);
	}
	
	public int getStagnation() {
		return stagnation;
	}

	public void setStagnation(int stagnation) {
		this.stagnation = stagnation;
	}

	@Override
	public void build() {
		// Initialization
		setGraphInitialization(new MMASInitialization(this, rho));
		setAntInitialization(new AnAntAtEachVertex(this));

		// Exploration
		setAntExploration(new PseudoRandomProportionalRule(this, new RouletteWheel()));

		// Global Update Pheromone Rule
		getEvaporations().add(new FullEvaporation(this, rho));
		getDeposits().add(new PartialDeposit(this, 1.0, new GlobalBest(this)));

		// Daemon Actions
		getDaemonActions().add(new UpdateTMinAndTMaxValues(this, rho));
		getDaemonActions().add(new UpdatePheromoneMatrix(this));
		getDaemonActions().add(new RestartCheck(this, stagnation));
	}

	@Override
	public String toString() {
		return MaxMinAntSystem.class.getSimpleName();
	}
}
