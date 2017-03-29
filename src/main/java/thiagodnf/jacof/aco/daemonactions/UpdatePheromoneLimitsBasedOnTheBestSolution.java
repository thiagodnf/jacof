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
public class UpdatePheromoneLimitsBasedOnTheBestSolution extends AbstractDaemonActions {

	protected double rate;
	
	protected Ant bestSoFar;
	
	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(UpdatePheromoneLimitsBasedOnTheBestSolution.class);

	public UpdatePheromoneLimitsBasedOnTheBestSolution(ACO aco, double rate) {
		super(aco);

		this.rate = rate;
	}	

	@Override
	public void doAction() {
		
		LOGGER.debug("Verifing if the pheromone limits should be updated");
		
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

		LOGGER.info("Yes. The tMin and tMax should be updated");

		aco.setTMax(1.0 / (rate * bestSoFar.getTourLength()));
		aco.setTMin(aco.getTMax() / 5.0);

		LOGGER.info("Now tMin=" + aco.getTMin() + " and tMax=" + aco.getTMax());
	}
	
	@Override
	public String toString() {
		return UpdatePheromoneLimitsBasedOnTheBestSolution.class.getSimpleName();
	}
}
