package thiagodnf.jacof.aco.subset.single;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * This class represents the subset with a randomly-selected solution.
 * The solution is selected from the set of all ants
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class RandomAnt extends AbstractSingleAnt {

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 */
	public RandomAnt(ACO aco) {
		super(aco);
	}

	@Override
	public List<Ant> getSubSet() {

		Ant[] ants = aco.getAnts();

		int index = rand.nextInt(0, ants.length - 1);

		List<Ant> list = new ArrayList<>();

		list.add(ants[index].clone());

		return list;
	}
	
	@Override
	public String toString(){
		return RandomAnt.class.getSimpleName();
	}
}
