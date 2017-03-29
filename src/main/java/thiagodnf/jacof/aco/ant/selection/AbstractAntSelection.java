package thiagodnf.jacof.aco.ant.selection;

import thiagodnf.jacof.util.random.JMetalRandom;

public abstract class AbstractAntSelection {
	
	protected JMetalRandom rand = JMetalRandom.getInstance();

	public abstract int select(double[] probability, double sumProbability);
	
	@Override
	public abstract String toString();
}
