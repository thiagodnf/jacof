package thiagodnf.jacof.aco.subset.many;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class RankAnt extends ManyAnts {

	protected int rank;

	public RankAnt(ACO aco, int rank) {
		super(aco);
		this.rank = rank;
	}

	@Override
	public List<Ant> getSubSet() {

		Ant[] ants = aco.getAnts();

		Arrays.sort(ants, new Comparator<Ant>() {

			@Override
			public int compare(Ant ant1, Ant ant2) {
				if (ant1.tourLength > ant2.tourLength) {
					return 1;
				} else if (ant1.tourLength < ant2.tourLength) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		List<Ant> list = new ArrayList<Ant>();

		for (int i = 0; i < rank; i++) {
			list.add(ants[i].clone());
		}

		return null;
	}

}
