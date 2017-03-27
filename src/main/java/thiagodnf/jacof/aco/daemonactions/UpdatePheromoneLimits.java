package thiagodnf.jacof.aco.daemonactions;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * Each time a new best-so-far tour is found, the value of tmax is updated.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class UpdatePheromoneLimits extends AbstractDaemonActions {

	protected double tMin;

	protected double tMax;
	
	protected double rate;
	
	protected Ant bestSoFar;
	
	/** The class logger*/
	final static Logger LOGGER = Logger.getLogger(UpdatePheromoneLimits.class);

	public UpdatePheromoneLimits(ACO aco, double rate, double tMin, double tMax) {
		super(aco);

		this.rate = rate;
		this.tMin = tMin;
		this.tMax = tMax;
	}	

	@Override
	public void doAction() {
		
		if (bestSoFar == null) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();
		}
		
		if (bestSoFar.getTourLength() != aco.getGlobalBest().getTourLength()) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();			
		}
		
		for (int i = 0; i < aco.getProblem().getNumberOfNodes(); i++) {

			for (int j = i; j < aco.getProblem().getNumberOfNodes(); j++) {

				if (i != j) {
					aco.getGraph().setTau(i, j, Math.min(aco.getGraph().getTau(i, j), tMax));
					aco.getGraph().setTau(i, j, Math.max(aco.getGraph().getTau(i, j), tMin));
					aco.getGraph().setTau(j, i, aco.getGraph().getTau(i, j));
				}
			}
		}
	}
	
	protected void updateMinAndMaxValues() {

		tMax = 1.0 / (rate * bestSoFar.getTourLength());
		tMin = tMax / 2.0;

		LOGGER.info("The bound was updated for tMin="+tMin+" and tMax=" + tMax);
	}
}
