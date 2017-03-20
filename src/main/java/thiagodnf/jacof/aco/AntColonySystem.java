package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.TACSExploration;
import thiagodnf.jacof.aco.ant.globalupdate.TACSGlobalUpdatingRule;
import thiagodnf.jacof.aco.ant.localupdate.TACSLocalUpdatingRule;
import thiagodnf.jacof.aco.graph.initialization.TACSInitialization;
import thiagodnf.jacof.problem.Problem;

public class AntColonySystem extends ACO {

	protected double q0;

	public AntColonySystem(Problem problem) {
		super(problem);
	}

	@Override
	public void build() {
		setTrailInitialization(new TACSInitialization(this));
		setAntExploration(new TACSExploration(this, q0));
		setAntGlobalUpdate(new TACSGlobalUpdatingRule(this));
		setAntLocalUpdate(new TACSLocalUpdatingRule(this, 0.1));
	}

	public double getQ0() {
		return q0;
	}

	public void setQ0(double q0) {
		this.q0 = q0;
	}
}
