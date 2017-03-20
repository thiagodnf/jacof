package thiagodnf.jacof.aco.graph;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

import thiagodnf.jacof.aco.graph.initialization.TrailInitialization;
import thiagodnf.jacof.problem.Problem;

public class AntGraph {

	/** The pheromone matrix */
	protected double[][] tau;

	protected Problem problem;

	final static Logger LOGGER = Logger.getLogger(AntGraph.class);
	
	/**
	 * Constructor
	 * 
	 * @param problem
	 *            The addressed problem
	 */
	public AntGraph(Problem problem) {

		Preconditions.checkNotNull(problem, "The problem cannot be null");

		LOGGER.info("Creating a AntGraph instance by using a matrix");

		this.problem = problem;
	}

	public void initialize(TrailInitialization trailInitialization) {

		int numberOfNodes = problem.getNumberOfNodes();

		LOGGER.info("Initializing the graph with " + numberOfNodes + " nodes");

		this.tau = new double[numberOfNodes][numberOfNodes];

		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = 0; j < numberOfNodes; j++) {
				this.tau[i][j] = trailInitialization.getT0();
			}
		}
	}

	public void setTau(int i, int j, double value) {
		this.tau[i][j] = value;
	}

	public double getTau(int i, int j) {
		return this.tau[i][j];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < tau.length; i++) {
			builder.append(Arrays.toString(tau[i]));
			builder.append("\n");
		}

		return builder.toString();
	}
}
