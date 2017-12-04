package benchmark.runners;

import benchmark.output.CSV;
import benchmark.output.Output;
import benchmark.visualization.Visualization;
import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.AntColonySystem;
import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import benchmark.problem.AcoTSP;
import thiagodnf.jacof.util.ExecutionStats;
import tsplib.DistanceFunction;
import tsplib.MulticriteriaDistanceFunction;

import java.io.IOException;


public class ACORunner {

    private ACO aco;
    private String instance;
    private int iterationNumber;
    private Visualization visualization;
    private Output output;
    private DistanceFunction distanceFunction;

    public ACORunner withACO(ACO aco) {
        this.aco = aco;
        return this;
    }

    public ACORunner withVisualization(Visualization visualization) {
        this.visualization = visualization;
        return this;
    }

    public ACORunner withOutput(Output output) {
        this.output = output;
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

    public ACORunner withDistanceFunction(DistanceFunction distanceFunction){
        this.distanceFunction = distanceFunction;
        return this;
    }

    public void start() throws IOException {
        Problem problem = new AcoTSP(instance).withDistanceFunction(distanceFunction)
                                              .withVisualization(this.visualization)
                                              .build();

        aco.setProblem(problem);
        aco.setNumberOfIterations(this.iterationNumber);

        ExecutionStats es = ExecutionStats.execute(aco, problem);
        output.use(es);
    }

    public static void main(String[] args) throws IOException {

        String instance = "src/main/resources/problems/tsp/bays29.tsp";
//        String instance = "src/main/resources/problems/tsp/oliver30.tsp";

//        String instance = "src/main/resources/problems/tsp/a280.tsp";
//        String instance = "src/main/resources/problems/tsp/berlin52.tsp";
//        String instance = "src/main/resources/problems/tsp/rat195.tsp";

        AntSystem aco = new AntSystem();
        aco.setNumberOfAnts(100);
        aco.setNumberOfIterations(1000);
        aco.setAlpha(2.0);
        aco.setBeta(3.0);
        aco.setRho(0.01);

        new ACORunner()
                .withACO(aco)
                .withInstance(instance)
                .withDistanceFunction(new MulticriteriaDistanceFunction())
                .withIteration(100)
                .withVisualization(new Visualization(false))
                .withOutput(new CSV("test.csv"))
                .start();

    }

}
