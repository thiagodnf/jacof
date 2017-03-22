package thiagodnf.jacof.aco.ant;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.initialization.AntInitialization;

public class Ant extends Observable implements Runnable{

	public static int ANT_ID = 0;
	
	/** Identifier */
	protected int id;
	
	public ACO aco;
		
	/** The Current Node */
	public int currentNode;
	
	public int[][] path;
	
	public List<Integer> tour;
	
	public List<Integer> nodesToVisit;

	public double tourLength;
	
	protected AntInitialization antInitialization;
	
	public Ant(ACO aco) {
		this.aco = aco;
		this.id = ANT_ID++;		
		this.nodesToVisit = new ArrayList<>();
		this.tour = new ArrayList<>();
		this.path = new int[aco.getProblem().getNumberOfNodes()][aco.getProblem().getNumberOfNodes()];
	}
	
	public void reset(){
		this.currentNode = antInitialization.getPosition(id);
		this.tourLength = 0.0;
		this.tour.clear();
		this.tour.add(new Integer(currentNode));
		this.path = new int[aco.getProblem().getNumberOfNodes()][aco.getProblem().getNumberOfNodes()];
		this.nodesToVisit = aco.getProblem().initNodesToVisit(this.currentNode);
	}
	
	@Override
	public void run() {
		reset();
		explore();		
		setChanged();
		notifyObservers(this);
	}
	
	public void explore() {

		while (!nodesToVisit.isEmpty()) {

			int nextNode = aco.getAntExploration().getNextNode(this, currentNode);

			nodesToVisit.remove(new Integer(nextNode));

			if (aco.getAntLocalUpdate() != null) {
				aco.getAntLocalUpdate().update(currentNode, nextNode);
			}
			
			// Save next node
			tour.add(new Integer(nextNode));

			path[currentNode][nextNode] = 1;
			path[nextNode][currentNode] = 1;

			nodesToVisit = aco.getProblem().updateNodesToVisit(tour, nodesToVisit);

			currentNode = nextNode;
		}
	}
			
	/**
	 * Convert from an ant solution to integer array
	 * 
	 * @return the solution formatted in integer array
	 */
	public int[] getSolution() {
		return tour.stream().mapToInt(i -> i).toArray();
	}
	
	public Ant clone() {
		Ant ant = new Ant(aco);
		
		ant.id = id;
		ant.currentNode = currentNode;
		ant.tourLength = tourLength;
		ant.tour = new ArrayList<>(tour);
		ant.nodesToVisit = new ArrayList<>(nodesToVisit);
		ant.antInitialization = antInitialization;
		ant.path = path.clone();
		
		return ant;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Integer> getNodesToVisit() {
		return nodesToVisit;
	}

	public void setNodesToVisit(List<Integer> nodesToVisit) {
		this.nodesToVisit = nodesToVisit;
	}

	@Override
	public String toString() {
		return "Ant " + id + " " + tour+" "+tourLength;
	}

	public AntInitialization getAntInitialization() {
		return antInitialization;
	}

	public void setAntInitialization(AntInitialization antInitialization) {
		this.antInitialization = antInitialization;
	}
}
