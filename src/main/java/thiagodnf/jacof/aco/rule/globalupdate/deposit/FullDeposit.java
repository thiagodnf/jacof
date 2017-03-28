package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.subset.many.AllAnts;

public class FullDeposit extends PartialDeposit {

	public FullDeposit(ACO aco) {
		super(aco, 1.0, new AllAnts(aco));
	}
	
	@Override
	public String toString(){
		return FullDeposit.class.getSimpleName();
	}
}
