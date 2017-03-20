package thiagodnf.jacof.aco;

import thiagodnf.jacof.aco.ant.exploration.TASExploration;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.initialization.TASInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.FullDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.subset.many.AllAnts;
import thiagodnf.jacof.problem.Problem;

public class AntSystem extends ACO {

	protected double rho;

	public AntSystem(Problem problem) {
		super(problem);
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	@Override
	public void build() {
		setTrailInitialization(new TASInitialization(this));
		setAntInitialization(new AnAntAtEachVertex(this));
		
		setAntSelection(new RouletteWheel());
		setAntExploration(new TASExploration(this));

		// Global Update Pheronome
		setEvaporation(new FullEvaporation(this, rho));
		setDeposit(new FullDeposit(this, 1.0, new AllAnts(this)));
	}
}
