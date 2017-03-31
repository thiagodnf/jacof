package thiagodnf.jacof.aco.graph;

import java.util.Arrays;

import org.apache.log4j.Logger;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import thiagodnf.jacof.aco.graph.initialization.AbstractGraphInitialization;
import thiagodnf.jacof.problem.Problem;

/**
 * This class represents the graph where the ants 
 * will traveled. The default implementation uses a double matrix
 * to represent the pheronome's values.
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class AntGraph {

	/** The pheromone matrix */
	protected double[][] tau;

	/** The addressed problem*/
	protected Problem problem;

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(AntGraph.class);

	protected double tMin;
	
	protected double tMax;
	
	/**
	 * Constructor
	 * 
	 * @param problem The addressed problem
	 * @param tMin the minimum pheromone value
	 * @param tMax the maximum pheromone value
	 */
	public AntGraph(Problem problem, double tMin, double tMax) {

		checkNotNull(problem, "The problem cannot be null");
		checkArgument(problem.getNumberOfNodes() > 0, "The number of nodes should be > 0. Passed: %s", problem.getNumberOfNodes());
		checkArgument(tMin <= tMax, "The tMin value should be less or equal than tMax one");
				
		this.tMin = tMin;
		this.tMax = tMax;
		this.problem = problem;		
	}
	
	/**
	 * Constructor
	 * 
	 * @param problem The addressed problem
	 */
	public AntGraph(Problem problem) {
		this(problem, 0.0001, 1.0);
	}

	/**
	 * Initialize all edges with the T0 values. 
	 * 
	 * @param t0 The initial pheromone value
	 */
	public void initialize(double t0) {

		int numberOfNodes = problem.getNumberOfNodes();

		this.tau = new double[numberOfNodes][numberOfNodes];

		for (int i = 0; i < numberOfNodes; i++) {
			for (int j = i; j < numberOfNodes; j++) {
				if (i != j) {
					this.tau[i][j] = this.tau[j][i] = t0;
				}
			}
		}

		LOGGER.debug("Creating a graph with " + numberOfNodes + " nodes and T0=" + t0);
		LOGGER.debug("tMin=" + tMin + " and tMax" + tMax);
	}
	
	/**
	 * Initialize all arc by using a specific graph initialization 
	 * 
	 * @param trailInitialization The method used to initialize the arc(i,j)
	 */
	public void initialize(AbstractGraphInitialization graphInitialization) {

		checkNotNull(graphInitialization, "The graph initialization should not be null");

		this.initialize(graphInitialization.getT0());
	}

	/**
	 * Set the pheromone value for an arc(i,j)
	 * 
	 * @param i Starting vertex
	 * @param j Final vertex
	 * @param value The pheromone value
	 */
	public synchronized void setTau(int i, int j, double value) {
		this.tau[i][j] = value;
	}

	/**
	 * Get the pheromone value for an arc(i,j)
	 * 
	 * @param i Starting vertex
	 * @param j Final vertex
	 * @return The pheromone value
	 */
	public synchronized double getTau(int i, int j) {
		return this.tau[i][j];
	}
	
	/**
	 * Get the the pheromone matrix
	 * 
	 * @return the matrix
	 */
	public double[][] getTau(){
		return this.tau;
	}
	
	public double getTMin() {
		return tMin;
	}

	public void setTMin(double tMin) {
		this.tMin = tMin;
	}

	public double getTMax() {
		return tMax;
	}

	public void setTMax(double tMax) {
		this.tMax = tMax;
	}
	
	/**
	 * Returns a string representation of the object. 
	 */	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < tau.length; i++) {
			builder.append(Arrays.toString(tau[i])).append("\n");
		}

		return builder.toString();
	}
}
