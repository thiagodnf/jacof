package thiagodnf.jacof.aco;

import java.util.Observable;
import java.util.Observer;

import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.ant.daemonactions.AntDaemonActions;
import thiagodnf.jacof.aco.ant.exploration.AbstractAntExploration;
import thiagodnf.jacof.aco.ant.initialization.AbstractAntInitialization;
import thiagodnf.jacof.aco.graph.AntGraph;
import thiagodnf.jacof.aco.graph.initialization.AbstractTrailInitialization;
import thiagodnf.jacof.aco.rule.globalupdate.deposit.AbstractDeposit;
import thiagodnf.jacof.aco.rule.globalupdate.evaporation.AbstractEvaporation;
import thiagodnf.jacof.aco.rule.localupdate.AntLocalUpdate;
import thiagodnf.jacof.problem.Problem;

public abstract class ACO implements Observer{
	
	/** Importance of pheromone*/
	protected double alpha;
	
	/** Importance of heuristic information*/
	protected double beta;
	
	/** The number of ants */
	protected int numberOfAnts;
	
	/** The number of iterations */
	protected int numberOfIterations;
	
	/** Ants **/
	protected Ant[] ants;
	
	/** The pheronome matrix */
	protected AntGraph graph;

	/** The current iteration */
	protected int it = 0;
	
	/** Total of ants that finished your tour */
	protected int finishedAnts = 0;
	
	/** Best Ant in tour */
	protected Ant globalBest;
	
	/** Best Current Ant in tour */
	protected Ant currentBest;
	
	/** The addressed problem */
	protected Problem problem;
	
	protected AbstractTrailInitialization trailInitialization;
	
	protected AbstractAntInitialization antInitialization;
	
	protected AbstractAntExploration antExploration;
	
	protected AntLocalUpdate antLocalUpdate;
	
	protected AntDaemonActions antDaemonActions;
	
	protected AbstractEvaporation evaporation;
	
	protected AbstractDeposit deposit;
	
	public ACO(Problem problem) {
		this.problem = problem;
	}
	
	public int[] solve() {
		
		build();

		initializePheromones();
		initializeAnts();

		while (!terminationCondition()) {
			constructAntsSolutions();
			updatePheromones();
			daemonActions(); // optional
		}

		return globalBest.getSolution();
	}
	
	protected void initializePheromones() {
		this.graph = new AntGraph(problem);
		this.graph.initialize(trailInitialization);
	}
	
	protected void initializeAnts() {
		
		this.ants = new Ant[numberOfAnts];

		for (int k = 0; k < numberOfAnts; k++) {
			ants[k] = new Ant(this);
			ants[k].setAntInitialization(getAntInitialization());
			ants[k].addObserver(this);
		}
	}
	
	protected boolean terminationCondition() {
		return ++it > numberOfIterations;
	}
	
	private void updatePheromones() {
		evaporation.doEvaporation();
		deposit.doDeposit();		
	}
	
	private synchronized void constructAntsSolutions() {
		
		currentBest = null;

		//Construct the ant solutions by using threads
		for (int k = 0; k < numberOfAnts; k++) {
			Thread t = new Thread(ants[k], "Ant " + ants[k].getId());
			t.start();
		}
		
		//Wait all ants finish your tour
		try{
			wait();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}	
	}
	
	public void daemonActions() {
		if (antDaemonActions != null) {
			antDaemonActions.doAction();
		}
	}

	public synchronized void update(Observable obj, Object arg) {
		
		Ant ant = (Ant) obj;

		ant.setTourLength(problem.evaluate(ant.getSolution()));

		if (currentBest == null || problem.better(ant.getSolution(), currentBest.getSolution())) {
			currentBest = ant.clone();
		}
		
		if (globalBest == null || problem.better(ant.getSolution(), globalBest.getSolution())) {
			globalBest = ant.clone();
		}

		if (++finishedAnts == numberOfAnts) {
			// Restart the counter to build the solutions again
			finishedAnts = 0;
			// Continue all execution
			notify();
		}
	}	

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}
	
	public AntGraph getGraph() {
		return graph;
	}

	public void setGraph(AntGraph graph) {
		this.graph = graph;
	}
	
	public int getNumberOfAnts() {
		return numberOfAnts;
	}

	public void setNumberOfAnts(int numberOfAnts) {
		this.numberOfAnts = numberOfAnts;
	}

	public int getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(int numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}	
	
	public Ant[] getAnts() {
		return ants;
	}

	public void setAnts(Ant[] ants) {
		this.ants = ants;
	}	

	public Ant getGlobalBest() {
		return globalBest;
	}

	public void setGlobalBest(Ant globalBest) {
		this.globalBest = globalBest;
	}

	public Ant getCurrentBest() {
		return currentBest;
	}

	public void setCurrentBest(Ant currentBest) {
		this.currentBest = currentBest;
	}

	public AbstractAntInitialization getAntInitialization() {
		return antInitialization;
	}

	public void setAntInitialization(AbstractAntInitialization antInitialization) {
		this.antInitialization = antInitialization;
	}
	
	public void setTrailInitialization(AbstractTrailInitialization trailInitialization) {
		this.trailInitialization = trailInitialization;
	}
	
	public AbstractTrailInitialization getTrailInitialization() {
		return trailInitialization;
	}	
	
	public AbstractAntExploration getAntExploration() {
		return antExploration;
	}

	public void setAntExploration(AbstractAntExploration antExploration) {
		this.antExploration = antExploration;
	}	

	public AntLocalUpdate getAntLocalUpdate() {
		return antLocalUpdate;
	}

	public void setAntLocalUpdate(AntLocalUpdate antLocalUpdate) {
		this.antLocalUpdate = antLocalUpdate;
	}
	
	public AntDaemonActions getAntDaemonActions() {
		return antDaemonActions;
	}

	public void setAntDaemonActions(AntDaemonActions antDaemonActions) {
		this.antDaemonActions = antDaemonActions;
	}
		
	public AbstractEvaporation getEvaporation() {
		return evaporation;
	}

	public void setEvaporation(AbstractEvaporation evaporation) {
		this.evaporation = evaporation;
	}
	
	public AbstractDeposit getDeposit() {
		return deposit;
	}

	public void setDeposit(AbstractDeposit deposit) {
		this.deposit = deposit;
	}

	public abstract void build();
	
}
