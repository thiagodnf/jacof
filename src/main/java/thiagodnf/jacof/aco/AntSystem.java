package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.TASExploration;
import thiagodnf.jacof.aco.ant.globalupdate.TASGlobalUpdatingRule;
import thiagodnf.jacof.aco.graph.initialization.TASInitialization;
import thiagodnf.jacof.problem.Problem;

public class AntSystem extends ACO {

	public AntSystem(Problem problem) {
		super(problem);
	}

	@Override
	public void build() {
		setTrailInitialization(new TASInitialization(this));
		setAntExploration(new TASExploration(this));
		setAntGlobalUpdate(new TASGlobalUpdatingRule(this));
	}
}
