package thiagodnf.jacof.aco.subset.single;

import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.random.PseudoRandom;

public class GlobalOrCurrentBest extends SingleAnt {

	protected double probability;

	public GlobalOrCurrentBest(ACO aco, double probability) {
		super(aco);
		this.probability = probability;		
	}

	@Override
	public List<Ant> getSubSet() {
		if (PseudoRandom.randDouble() <= probability) {
			return new GlobalBest(aco).getSubSet();
		} else {
			return new CurrentBest(aco).getSubSet();
		}
	}

}
