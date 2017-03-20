package thiagodnf.jacof.aco.ant.globalupdate;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class TACSGlobalUpdatingRule extends AntGlobalUpdate {

	public TACSGlobalUpdatingRule(ACO aco) {
		super(aco);
	}

	@Override
	public double getValue(Ant[] ant, Ant globalBest, int i, int j) {

		if (globalBest.path[i][j] == 1) {
			return aco.getRho() * aco.getProblem().getDeltaTau(globalBest.tourLength, i, j);
		}

		return 0.0;
	}
}
