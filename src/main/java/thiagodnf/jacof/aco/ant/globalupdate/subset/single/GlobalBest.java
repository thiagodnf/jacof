package thiagodnf.jacof.aco.ant.globalupdate.subset.single;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class GlobalBest extends SingleAnt {

	public GlobalBest(ACO aco) {
		super(aco);
	}

	@Override
	public List<Ant> getSubSet() {
		List<Ant> list = new ArrayList<Ant>();

		list.add(aco.getGlobalBest().clone());

		return list;
	}
}
