package tsplib.moeatsp;


import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.problem.AbstractProblem;
import tsplib.TSP2OptHeuristic;
import tsplib.TSPInstance;
import tsplib.Tour;

import static tsplib.Tour.fromTour;
import static tsplib.Tour.toTour;

public class TSPProblem extends AbstractProblem {

    private final TSPInstance instance;
    private final TSP2OptHeuristic heuristic;

    public static TSPProblem getTspProblem(TSPInstance instance){
        return new TSPProblem(instance);
    }

    private TSPProblem(TSPInstance instance) {
        super(1, 1);
        this.instance = instance;

        heuristic = new TSP2OptHeuristic(instance);
    }

    @Override
    public void evaluate(Solution solution) {
        Tour tour = toTour(solution);

        // apply the heuristic and save the modified tour
        heuristic.apply(tour);
        fromTour(solution, tour);

        solution.setObjective(0, tour.distance(instance));
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(1, 1);

        solution.setVariable(0, EncodingUtils.newPermutation(
                instance.getDimension()));

        return solution;
    }

}
