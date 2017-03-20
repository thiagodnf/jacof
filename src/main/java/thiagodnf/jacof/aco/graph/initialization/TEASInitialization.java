package thiagodnf.jacof.aco.graph.initialization;

import thiagodnf.jacof.aco.ACO;

/**
 * Pheronome Initialization for Elitist Ant System Algorithm
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class TEASInitialization extends TrailInitialization {

	protected double rate;

	public TEASInitialization(ACO aco, double rate) {
		super(aco);

		this.rate = rate;
	}

	@Override
	public double getT0() {
		double k = aco.getNumberOfAnts();
		double n = aco.getProblem().getNumberOfNodes();
		double cnn = aco.getProblem().getCnn();
		
		return (n + k) / rate * cnn;
	}
	
	@Override
	public String toString() {
		return TEASInitialization.class.getSimpleName() + ": " + this.rate;
	}
}
