package thiagodnf.jacof.aco.subset.many;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * This class represents the subset with all ants used 
 * in the algorithm. If the rank value used in this class is
 * exactly the number of ants so this class has the same
 * behauvior of {@link AllAnts} 
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class RankAnt extends AbstractManyAnts {

	/** The number of selected ants*/
	protected int rank;

	/**
	 * Constructor
	 * 
	 * @param aco The ant colony optimization used
	 * @param rank The number of selected ants
	 */
	public RankAnt(ACO aco, int rank) {
		super(aco);
		
		checkArgument(rank >= 0, "The rank should be greater or equal than 0.0");
		checkArgument(rank <= aco.getNumberOfAnts(), "The rank should be greater or equal than the number of ants");
		
		this.rank = rank;
	}
	
	/**
	 * Constructor. The default value for rank is the numberOfAnts/2
	 * 
	 * @param aco The ant colony optimization used
	 */
	public RankAnt(ACO aco) {
		this(aco, aco.getNumberOfAnts()/2);
	}

	@Override
	public List<Ant> getSubSet() {

		Ant[] ants = aco.getAnts();
		
		Ant[] copy = Arrays.copyOf(ants, ants.length);
		
		Arrays.sort(copy, (Ant ant1, Ant ant2) -> {
			if (aco.getProblem().better(ant1.getTourLength(), ant2.getTourLength())) {
				return -1;
			} else if (aco.getProblem().better(ant2.getTourLength(), ant1.getTourLength())) {
				return 1;
			} else {
				return 0;
			}
		});
		
		List<Ant> list = new ArrayList<>();
		
		for (int i = 0; i < rank; i++) {
			list.add(copy[i].clone());
		}
		
		return list;
	}

	@Override
	public String toString(){
		return RankAnt.class.getSimpleName();
	}
}
