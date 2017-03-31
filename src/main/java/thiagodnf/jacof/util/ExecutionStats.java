package thiagodnf.jacof.util;

import java.util.Arrays;

import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.problem.Problem;

/**
 *  The algorithm's executor class
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class ExecutionStats {

	public double executionTime;

	public ACO aco;
	
	public Problem problem;

	public int[] bestSolution;

	/** The class logger */
	static final Logger LOGGER = Logger.getLogger(ExecutionStats.class);

	public static ExecutionStats execute(ACO aco, Problem problem) {
		ExecutionStats ets = new ExecutionStats();
		
		ets.aco = aco;
		ets.problem = problem;
		
		long initTime = System.currentTimeMillis();
		ets.bestSolution = aco.solve();
		ets.executionTime = System.currentTimeMillis() - initTime ;
		
		return ets;
	}

	public void printStats() {
		LOGGER.info("==================================================");
		LOGGER.info("Execution Time: " + executionTime);
		LOGGER.info("Best Value: " + problem.evaluate(bestSolution));
		LOGGER.info("Best Solution: " + Arrays.toString(bestSolution));
		LOGGER.info("==================================================");
	}

	public void printDotFormat() {
		System.out.println(Convert.toDot(aco.getGraph().getTau()));
	}
}
