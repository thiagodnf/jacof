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
package tsplib.moeatsp;

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.Problem;
import org.moeaframework.core.spi.AlgorithmFactory;
import runner.Visualization;
import tsplib.AlgorithmName;
import tsplib.TSPInstance;
import tsplib.moeatsp.output.CSV;
import tsplib.moeatsp.output.Output;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class TSPWrapper {

    private Visualization visualization;

    private TSPInstance instance;

    private Problem problem;

    private String algorithmName;

    private Algorithm algorithm;

    private Properties properties;

    private Output output;

    private int iterationNumber;

    public void start() {
        this.visualization.prepareVisualization(instance);
        this.problem = TSPProblem.getTspProblem(instance);

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

    public TSPWrapper withVisualization(boolean enabled) {
        this.visualization = new Visualization(enabled);
        return this;
    }

    public TSPWrapper withAlgorithmName(AlgorithmName algorithmName) {
        this.algorithmName = algorithmName.name();
        return this;
    }

    public TSPWrapper withTSPInstance(String file) throws IOException {
        this.instance = new TSPInstance(new File(file));
        return this;
    }


    public TSPWrapper withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    public TSPWrapper withOutput(Output output) {
        this.output = output;
        return this;
    }

    private TSPWrapper withIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
        return this;
    }

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("swap.rate", "0.7");
        properties.setProperty("insertion.rate", "0.9");
        properties.setProperty("pmx.rate", "0.4");
        properties.setProperty("populationSize", "1000");
//        properties.setProperty("withReplacement", "false");
//        properties.setProperty("divisionsOuter", "false");
//        properties.setProperty("divisionsInner", "false");
//        properties.setProperty("divisions", "false");
//        properties.setProperty("neighborhoodSize", "false");
//        properties.setProperty("eta", "false");
//        properties.setProperty("delta", "false");
//        properties.setProperty("updateUtility", "false");
//        properties.setProperty("updateUtility", "false");


        new TSPWrapper()
                .withTSPInstance("src/main/resources/problems/tsp/bays29.tsp")
                .withAlgorithmName(AlgorithmName.NSGAII)
                .withProperties(properties)
                .withIterationNumber(1000)
                .withVisualization(false)
                .withOutput(new CSV())
                .start();


    }


}
