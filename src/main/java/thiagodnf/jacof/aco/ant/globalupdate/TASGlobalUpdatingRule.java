package thiagodnf.jacof.aco.ant.globalupdate;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public class TASGlobalUpdatingRule extends AntGlobalUpdate {

	public TASGlobalUpdatingRule(ACO aco) {
		super(aco);
	}

	@Override
	public double getValue(Ant[] ant, Ant globalBest, int i, int j) {

		double deltaTau = 0.0;

		for (int k = 0; k < ant.length; k++) {
			if (ant[k].path[i][j] == 1) {
				deltaTau += aco.getProblem().getDeltaTau(ant[k].tourLength, i, j);
			}
		}

		return deltaTau;
	}
}
