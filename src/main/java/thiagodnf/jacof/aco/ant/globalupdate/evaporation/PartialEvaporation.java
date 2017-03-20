package thiagodnf.jacof.aco.ant.globalupdate.evaporation;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.ant.globalupdate.subset.SubSet;

public class PartialEvaporation extends AbstractEvaporation {

	protected SubSet subSet;

	public PartialEvaporation(ACO aco, double rate, SubSet subSet) {
		super(aco, rate);

		this.subSet = subSet;
	}

	@Override
	protected double getEvaporation(int i, int j) {
		for (Ant ant : subSet.getSubSet()) {
			if (ant.path[i][j] == 1) {
				return (1.0 - rate) * aco.getGraph().getTau(i, j);
			}
		}

		return aco.getGraph().getTau(i, j);
	}
}
