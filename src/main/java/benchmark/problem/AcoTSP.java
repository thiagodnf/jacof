package benchmark.problem;

import benchmark.visualization.Visualization;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.NearestNeighbour;
import tsplib.DistanceFunction;
import tsplib.DistanceTable;
import tsplib.TSPInstance;
import tsplib.Tour;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Travelling Salesman Problem Class
 *
 * @author Thiago Nascimento
 * @version 1.0
 * @since 2014-07-27
 */
public class AcoTSP extends Problem {

    private final String filename;

    public double Q = 1.0;

    /**
     * Distance Matrix
     */
    protected double[][] distance;

    /**
     * Number of Cities
     */
    protected int numberOfCities;

    /**
     * Nearest Neighbour heuristic
     */
    protected double cnn;

    private Visualization visualization;
    private DistanceFunction distanceFunction;
    private TSPInstance tspInstance;

    public AcoTSP(String filename) throws IOException {
        this.filename = filename;
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

    public int[] getTheBestSolution() {
        return new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 23, 25, 26, 27, 28, 29, 1, 0};
    }

    private double[][] calculateDistanceMatrix(DistanceTable distanceTable) {
        double distances[][] = new double[numberOfCities][numberOfCities];

        for (int i = 0; i < numberOfCities; i++) {
            distances[i][i] = 0;
            for (int j = i; j < numberOfCities; j++) {
                if (i != j) {
                    distances[i][j] = distanceTable.getDistanceBetween(i + 1, j + 1);
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

    public Visualization getVisualization() {
        return visualization;
    }

    public void setVisualization(Visualization visualization) {
        this.visualization = visualization;
    }

    @Override
    public String toString() {
        return AcoTSP.class.getSimpleName();
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

    public AcoTSP withVisualization(Visualization visualization) {
        this.visualization = visualization;
        return this;
    }

    public AcoTSP withDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
        return this;
    }

    public AcoTSP build() throws IOException {
        this.tspInstance = new TSPInstance(new File(filename), distanceFunction);
        numberOfCities = tspInstance.getDimension();
        distance = calculateDistanceMatrix(tspInstance.getDistanceTable());

        visualization.prepareVisualization(tspInstance);

        NearestNeighbour nn = new NearestNeighbour();

        this.cnn = evaluate(nn.solve(this));

        return this;
    }

    public TSPInstance getTspInstance() {
        return tspInstance;
    }
}