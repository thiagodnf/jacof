package thiagodnf.jacof.aco.subset.many;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class AllAnts extends ManyAnts {

	public AllAnts(ACO aco) {
		super(aco);
	}

	@Override
	public List<Ant> getSubSet() {

		Ant[] ants = aco.getAnts();

		List<Ant> list = new ArrayList<Ant>();

		for (int i = 0; i < ants.length; i++) {
			list.add(ants[i].clone());
		}

		return list;
	}

}
