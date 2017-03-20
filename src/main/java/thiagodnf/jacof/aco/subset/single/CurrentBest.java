package thiagodnf.jacof.aco.subset.single;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class CurrentBest extends SingleAnt {

	public CurrentBest(ACO aco) {
		super(aco);
	}

	@Override
	public List<Ant> getSubSet() {
		List<Ant> list = new ArrayList<Ant>();

		list.add(aco.getCurrentBest().clone());

		return list;
	}
}
