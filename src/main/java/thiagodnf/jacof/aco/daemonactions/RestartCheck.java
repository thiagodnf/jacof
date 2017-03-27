package thiagodnf.jacof.aco.daemonactions;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * Pheromone trail reinitialization is typically triggered when the algorithm
 * approaches the stagnation behavior (as measured by some statistics on the
 * pheromone trails) or if for a given number of algorithm iterations no
 * improved tour is found. This class uses the number of iterations
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class RestartCheck extends AbstractDaemonActions {

	protected double stagnation;
	
	protected double stagnationCounter = 0;
	
	protected Ant bestAnt;
	
	/** The class logger*/
	final static Logger LOGGER = Logger.getLogger(RestartCheck.class);
	
	public RestartCheck(ACO aco, double stagnation) {
		super(aco);

		this.stagnation = stagnation;		
	}

	public RestartCheck(ACO aco) {
		this(aco, 20);
	}

	@Override
	public void doAction() {

		if (bestAnt == null) {
			bestAnt = aco.getGlobalBest().clone();
		}

		if (bestAnt.getTourLength() == aco.getGlobalBest().getTourLength()) {
			stagnationCounter++;
		}else{
			bestAnt = aco.getGlobalBest().clone();
			stagnationCounter = 0;
		}

		if (stagnationCounter == stagnation) {
			
			LOGGER.debug("The stagnation was reached. The pheromone matrix will be restarted");
			
			aco.getGraph().initialize(aco.getTrailInitialization());
			stagnationCounter = 0;
		}
	}	

}
