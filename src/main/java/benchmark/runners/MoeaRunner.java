/* Copyright 2012 David Hadka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package benchmark.runners;

import benchmark.problem.MoeaTSP;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.spi.AlgorithmFactory;
import benchmark.visualization.Visualization;
import tsplib.AlgorithmName;
import tsplib.DistanceFunction;
import tsplib.MulticriteriaDistanceFunction;
import tsplib.TSPInstance;
import benchmark.output.CSV;
import benchmark.output.Output;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class MoeaRunner {

    private Visualization visualization;

    private Problem problem;

    private String algorithmName;

    private Algorithm algorithm;

    private Properties properties;

    private Output output;

    private int iterationNumber;

    private DistanceFunction distanceFunction;

    private String filename;

    private TSPInstance instance;

    public void start() throws IOException {

        this.instance = new TSPInstance(new File(filename), distanceFunction);
        this.visualization.prepareVisualization(instance);
        this.problem = MoeaTSP.getTspProblem(instance);

        this.algorithm = AlgorithmFactory.getInstance().getAlgorithm(
                algorithmName, properties, problem);

        int iteration = 0;

        while (iteration < iterationNumber) {
            algorithm.step();

            visualization.updateVisualization(iteration, algorithm);
            iteration++;
        }

        output.use(algorithm, instance);

    }

    public MoeaRunner withVisualization(boolean enabled) {
        this.visualization = new Visualization(enabled);
        return this;
    }

    public MoeaRunner withAlgorithmName(AlgorithmName algorithmName) {
        this.algorithmName = algorithmName.name();
        return this;
    }

    public MoeaRunner withTSPInstance(String filename) throws IOException {
        this.filename = filename;
        return this;
    }


    public MoeaRunner withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    public MoeaRunner withOutput(Output output) {
        this.output = output;
        return this;
    }

    private MoeaRunner withIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
        return this;
    }

    public MoeaRunner withDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
        return this;
    }

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("swap.rate", "0.7");
        properties.setProperty("insertion.rate", "0.3");
        properties.setProperty("pmx.rate", "0.2");
        properties.setProperty("populationSize", "25");
//        properties.setProperty("withReplacement", "false");
//        properties.setProperty("divisionsOuter", "false");
//        properties.setProperty("divisionsInner", "false");
//        properties.setProperty("divisions", "false");
//        properties.setProperty("neighborhoodSize", "false");
//        properties.setProperty("eta", "false");
//        properties.setProperty("delta", "false");
//        properties.setProperty("updateUtility", "false");
//        properties.setProperty("epsilon", "false");


        new MoeaRunner()
//                .withTSPInstance("src/main/resources/problems/tsp/bays29.tsp")
//                .withTSPInstance("src/main/resources/problems/tsp/oliver30.tsp")
                .withTSPInstance("src/main/resources/problems/tsp/a280.tsp")
                .withAlgorithmName(AlgorithmName.NSGAII)
                .withDistanceFunction(new MulticriteriaDistanceFunction())
                .withProperties(properties)
                .withIterationNumber(100)
                .withVisualization(true)
                .withOutput(new CSV("test.csv"))
                .start();


    }


}
