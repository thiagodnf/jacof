package thiagodnf.jacof.aco.daemonactions;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;

/**
 * Each time a new best-so-far tour is found, the value of tmax is updated.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class UpdatePheromoneLimits extends AbstractDaemonActions {

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(UpdatePheromoneLimits.class);

	public UpdatePheromoneLimits(ACO aco, double tMin, double tMax) {
		super(aco);

		aco.setTMin(tMin);
		aco.setTMax(tMax);
	}	

	@Override
	public void doAction() {
		
		LOGGER.debug("Verifing if the pheromone limits should be updated");			
	}

	@Override
	public String toString() {
		return UpdatePheromoneLimits.class.getSimpleName();
	}
}
