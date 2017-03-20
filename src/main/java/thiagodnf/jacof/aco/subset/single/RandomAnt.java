package thiagodnf.jacof.aco.subset.single;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.random.PseudoRandom;

public class RandomAnt extends SingleAnt{

	public RandomAnt(ACO aco) {
		super(aco);
	}

	@Override
	public List<Ant> getSubSet() {

		Ant[] ants = aco.getAnts();

		int index = PseudoRandom.randInt(0, ants.length - 1);

		List<Ant> list = new ArrayList<Ant>();

		list.add(ants[index].clone());

		return list;
	}
}
