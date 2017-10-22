package thiagodnf.jacof.problem.tsp;

import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.NearestNeighbour;
import tsplib.DistanceTable;
import tsplib.TSPInstance;
import tsplib.TSPPanel;
import tsplib.Tour;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.lightGray;

/**
 * The Travelling Salesman Problem Class
 * 
 * @author Thiago Nascimento
 * @since 2014-07-27
 * @version 1.0
 */
public class TravellingSalesmanProblem extends Problem {

	public double Q = 1.0;

	/** Distance Matrix */
	protected double[][] distance;

	/** Number of Cities */
	protected int numberOfCities;
	
	/** Nearest Neighbour heuristic */
	protected double cnn;

	private JFrame frame;

	private TSPPanel panel;

	private JTextArea progressText;

	private StringBuilder progress;

	private TSPInstance tspInstance;

	public TravellingSalesmanProblem(String filename) throws IOException {
		this(filename, false);
	}

	public TravellingSalesmanProblem(String filename, boolean isTspLibFormmat) throws IOException {
		
//		TSPLIBReader r = new TSPLIBReader(new InstanceReader(new File(filename)));
//		numberOfCities = r.getDimension();
//		distance = r.getDistance();

		tspInstance = new TSPInstance(new File(filename));
		numberOfCities = tspInstance.getDimension();
		distance = calculateDistanceMatrix(tspInstance.getDistanceTable());

		prepareVisualization();

		NearestNeighbour nn = new NearestNeighbour();
		
		this.cnn = evaluate(nn.solve(this));

		//TODO: below lines
//		System.out.println("Best Solution: " + Arrays.toString(getTheBestSolution()));
//		System.out.println("Best Value: " + evaluate(getTheBestSolution()));
	}

	private void prepareVisualization() {
		panel = new TSPPanel(tspInstance);
		panel.setAutoRepaint(false);

		// create other components on the display
		progress = new StringBuilder();
		progressText = new JTextArea();

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(panel);
		splitPane.setBottomComponent(new JScrollPane(progressText));
		splitPane.setDividerLocation(300);
		splitPane.setResizeWeight(1.0);

		// display the panel on a window
		frame = new JFrame(tspInstance.getName());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// create the optimization problem and evolutionary algorithm
//		org.moeaframework.core.Problem problem = new TSPExample.TSPProblem(tspInstance);
//
//		Properties properties = new Properties();
//		properties.setProperty("swap.rate", "0.7");
//		properties.setProperty("insertion.rate", "0.9");
//		properties.setProperty("pmx.rate", "0.4");
//
//		Algorithm algorithm = AlgorithmFactory.getInstance().getAlgorithm(
//				"NSGAII", properties, problem);
//
//		int iteration = 0;


	}

	public void updateVisualization(int iteration, Ant globalBest, Ant[] ants) {

		if(frame.isVisible()) {

			// clear existing tours in display
			panel.clearTours();

			// display population with light gray lines

			for (Ant ant : ants) {
				panel.displayTour(toTour(ant), lightGray);
			}


			// display current optimal solutions with red line
			Tour best = toTour(globalBest);
			panel.displayTour(best, Color.RED, new BasicStroke(2.0f));
			progress.insert(0, "Iteration " + iteration + ": " +
					best.distance(tspInstance) + "\n");
			progressText.setText(progress.toString());

			// repaint the TSP display
			panel.repaint();
		}
	}

	public static Tour toTour(Ant ant) {
		int[] permutation = ant.getSolution();

		// increment values since TSP nodes start at 1
		for (int i = 0; i < permutation.length; i++) {
			permutation[i]++;
		}

		return Tour.createTour(permutation);
	}
	
	@Override
	public double getNij(int i, int j) {
		return 1.0 / distance[i][j];
	}

	@Override
	public boolean better(double s1, double best) {
		return s1 < best;
	}
	
	public double getDistance(int i, int j) {
		return this.distance[i][j];
	}
	
	public int[] getTheBestSolution(){
		return new int[]{0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,24,23,25,26,27,28,29,1, 0};
	}

	private double[][] calculateDistanceMatrix(DistanceTable distanceTable) {
		double distances[][] = new double[numberOfCities][numberOfCities];

		for (int i = 0; i < numberOfCities; i++) {
			distances[i][i] = 0;
			for (int j = i; j < numberOfCities; j++) {
				if (i != j) {
					distances[i][j] = distanceTable.getDistanceBetween(i+1, j+1);
					distances[j][i] = distances[i][j];
				}
			}
		}

		return distances;
	}
	
	@Override
	public double evaluate(int[] solution) {
		
		double totalDistance = 0;

		for (int h = 1; h < solution.length; h++) {
			
			int i = solution[h - 1];
			int j = solution[h];
			
			totalDistance += distance[i][j];
		}
		
		return totalDistance;
	}

	@Override
	public int getNumberOfNodes() {
		return numberOfCities;
	}

	@Override
	public double getCnn() {
		return cnn;
	}

	@Override
	public double getDeltaTau(double tourLength, int i, int j) {
		return Q / tourLength;
	}

	@Override
	public String toString() {
		return TravellingSalesmanProblem.class.getSimpleName();
	}

	@Override
	public List<Integer> initNodesToVisit(int startingNode) {
		
		List<Integer> nodesToVisit = new ArrayList<>();

		// Add all nodes (or cities) less the start node
		for (int i = 0; i < getNumberOfNodes(); i++) {
			if (i != startingNode) {
				nodesToVisit.add(new Integer(i));
			}
		}

		return nodesToVisit;
	}

	@Override
	public List<Integer> updateNodesToVisit(List<Integer> tour, List<Integer> nodesToVisit) {
		
		if (nodesToVisit.isEmpty()) {
			if (!tour.get(0).equals(tour.get(tour.size() - 1))) {
				nodesToVisit.add(tour.get(0));
			}
		}
		
		return nodesToVisit;
	}	
}