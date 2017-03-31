package thiagodnf.jacof.aco.daemonactions;

import static com.google.common.base.Preconditions.checkArgument;

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

	protected int stagnation;
	
	protected int stagnationCounter = 0;
	
	protected Ant bestAnt;
	
	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(RestartCheck.class);
	
	public RestartCheck(ACO aco, int stagnation) {
		super(aco);

		checkArgument(stagnation >= 1, "The stagnation should be greater or equal than 0");
		
		this.stagnation = stagnation;		
	}

	public RestartCheck(ACO aco) {
		this(aco, 20);
	}

	@Override
	public void doAction() {
		
		LOGGER.debug("Verifing if the pheromone matrix should be restarted");

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
			
			aco.getGraph().initialize(aco.getGraph().getTMax());
			stagnationCounter = 0;
		}
	}

	@Override
	public String toString() {
		return RestartCheck.class.getSimpleName() + " after " + stagnation + " iterations";
	}
}
