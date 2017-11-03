package runner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.problem.Problem;

/**
 * Created by wojci on 28.10.2017.
 */
public class Runner {

    private ACO aco;
    private Problem problem;

    public Runner withACO(ACO aco) {
        this.aco = aco;
        return this;
    }

    public Runner withProblem(Problem problem) {
        aco.setProblem(problem);
        return this;

    }

    public Runner withVisualization(Visualization visualization) {
//        aco.s
        return this;
    }

    public Runner withCSVOutput(boolean b) {
        return this;
    }

    public Runner withIteration(int i) {
        return this;
    }
}
