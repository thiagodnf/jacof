package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.subset.many.AbstractManyAnts;
import thiagodnf.jacof.aco.subset.many.RankAnt;

public class RankDeposit extends ElitistDeposit {

	protected AbstractManyAnts manyAnts;

	public RankDeposit(ACO aco, int weight, AbstractManyAnts manyAnts) {
		super(aco, weight);
		
		checkNotNull(manyAnts, "The manyAnts cannot be null");

		this.manyAnts = manyAnts;
	}
	
	public RankDeposit(ACO aco) {
		this(aco, 6, new RankAnt(aco));
	}

	@Override
	public double getTheNewValue(int i, int j) {
		return aco.getGraph().getTau(i, j) + getDeltaTau(i, j) + weight * super.getDeltaTauGlobalBest(i, j);
	}

	@Override
	public double getDeltaTau(int i, int j) {

		double deltaTau = 0.0;
		
		for (int r = 1; r <= (weight - 1); r++) {

			Ant ant = manyAnts.getSubSet().get(r - 1);

			if (ant.path[i][j] == 1) {
				deltaTau += (weight - r) * aco.getProblem().getDeltaTau(ant.getTourLength(), i, j);
			}
		}

		return deltaTau;
	}
	
	@Override
	public String toString() {
		return RankDeposit.class.getSimpleName() + " with weight=" + weight;
	}
}
