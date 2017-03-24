package thiagodnf.jacof.aco.ant.selection;

import thiagodnf.jacof.random.JMetalRandom;

public abstract class AntSelection {
	
	protected JMetalRandom rand = JMetalRandom.getInstance();

	public abstract int select(double[] probability, double sumProbability);
}
