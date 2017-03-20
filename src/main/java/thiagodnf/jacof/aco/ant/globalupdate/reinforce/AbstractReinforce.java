package thiagodnf.jacof.aco.ant.globalupdate.reinforce;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractReinforce {

	protected ACO aco;
	
	public AbstractReinforce(ACO aco) {
		this.aco = aco;
	}
	
	public void doReinforce(){
		for (int i = 0; i < aco.getProblem().getNumberOfNodes(); i++) {
			for (int j = i; j < aco.getProblem().getNumberOfNodes(); j++) {
				if (i != j) {

					double reinforce = getReinforce(i, j);

					aco.getGraph().setTau(i, j, reinforce);
					aco.getGraph().setTau(j, i, aco.getGraph().getTau(i, j));
				}
			}
		}
	}
	
	protected abstract double getReinforce(int i, int j);
}
