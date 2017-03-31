package thiagodnf.jacof.aco.daemonactions;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

/**
 * Each time a new best-so-far tour is found, the value of tmax is updated.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class UpdateTMinAndTMaxValues extends AbstractDaemonActions {

	protected double rate;
	
	protected Ant bestSoFar;
	
	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(UpdateTMinAndTMaxValues.class);

	/**
	 * Constructor 
	 * 
	 * @param aco The ant colony optimization
	 * @param rate The evaporation rate
	 */
	public UpdateTMinAndTMaxValues(ACO aco, double rate) {
		super(aco);

		checkArgument(rate > 0.0 && rate <= 1.0, "The rate should be between (0,1]");
		
		this.rate = rate;
	}	

	@Override
	public void doAction() {
		
		LOGGER.debug("Verifing if the pheromone limits should be updated");
		
		if (bestSoFar == null) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();			
		}else if (bestSoFar.getTourLength() != aco.getGlobalBest().getTourLength()) {
			bestSoFar = aco.getGlobalBest().clone();
			updateMinAndMaxValues();
		} else {
			LOGGER.info("No. The tMin and tMax should be the same");
		}
	}
	
	protected void updateMinAndMaxValues() {

		LOGGER.info("Yes. The tMin and tMax should be updated");

		aco.getGraph().setTMax(1.0 / (rate * bestSoFar.getTourLength()));
		aco.getGraph().setTMin(aco.getGraph().getTMax() / 10.0);

		LOGGER.info("Now tMin=" + aco.getGraph().getTMin() + " and tMax=" + aco.getGraph().getTMax());
	}
	
	@Override
	public String toString() {
		return UpdateTMinAndTMaxValues.class.getSimpleName();
	}
}
