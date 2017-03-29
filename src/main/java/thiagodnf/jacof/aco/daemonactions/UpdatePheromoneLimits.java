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

	protected double rate;
	
	protected Ant bestSoFar;
	
	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(UpdatePheromoneLimits.class);

	public UpdatePheromoneLimits(ACO aco, double rate, double tMin, double tMax) {
		super(aco);

		this.rate = rate;

		aco.setTMin(tMin);
		aco.setTMax(tMax);
	}	

	@Override
	public void doAction() {
		
		LOGGER.debug("Updating the pheromone limits");
		
		if (bestSoFar == null) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();
		}
		
		if (bestSoFar.getTourLength() != aco.getGlobalBest().getTourLength()) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();
		}		
	}
	
	protected void updateMinAndMaxValues() {

		LOGGER.info("Updating tMin and tMax values");

		aco.setTMax(1.0 / (rate * bestSoFar.getTourLength()));
		aco.setTMin(aco.getTMax() / 4.0);

		LOGGER.info("Now tMin=" + aco.getTMin() + " and tMax=" + aco.getTMax());
	}
	
	@Override
	public String toString() {
		return UpdatePheromoneLimits.class.getSimpleName();
	}
}
