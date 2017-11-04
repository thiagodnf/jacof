package benchmark.runners;

import benchmark.output.CSV;
import benchmark.output.Output;
import benchmark.visualization.Visualization;
import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import benchmark.problem.AcoTSP;
import thiagodnf.jacof.util.ExecutionStats;

import java.io.IOException;


public class ACORunner {

    private ACO aco;
    private String instance;
    private int iterationNumber;
    private Visualization visualization;
    private Output output;

    public ACORunner withACO(ACO aco) {
        this.aco = aco;
        return this;
    }

    public ACORunner withVisualization(Visualization visualization) {
        this.visualization = visualization;
        return this;
    }

    public ACORunner withCSVOutput(boolean csvOutputEnable) {
        if (csvOutputEnable) {
            this.output = new CSV();
        }
        return this;
    }

    public ACORunner withIteration(int iterationNumber) {
        this.iterationNumber = iterationNumber;
        return this;
    }

    public ACORunner withInstance(String instance) {
        this.instance = instance;
        return this;
    }

    public void start() throws IOException {
        Problem problem = new AcoTSP(instance).withVisualization(this.visualization);

        aco.setProblem(problem);
        aco.setNumberOfIterations(this.iterationNumber);

        ExecutionStats es = ExecutionStats.execute(aco, problem);
        output.use(es);
    }

    public static void main(String[] args) throws IOException {

        String instance = "src/main/resources/problems/tsp/bays29.tsp";

        AntSystem aco = new AntSystem();
        aco.setNumberOfAnts(30);
        aco.setNumberOfIterations(1000);
        aco.setAlpha(1.0);
        aco.setBeta(5.0);
        aco.setRho(0.01);

        new ACORunner()
                .withACO(aco)
                .withInstance(instance)
                .withIteration(1000)
                .withVisualization(new Visualization(true))
                .withCSVOutput(true)
                .start();


    }

}
