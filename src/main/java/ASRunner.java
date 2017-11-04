import benchmark.output.CSV;
import benchmark.runners.ACORunner;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import benchmark.visualization.Visualization;
import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import benchmark.problem.AcoTSP;
import thiagodnf.jacof.util.ExecutionStats;

import java.io.IOException;

public class ASRunner {

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(ASRunner.class);
	
	public static void main(String[] args) throws ParseException, IOException {

		String instance = "src/main/resources/problems/tsp/bays29.tsp";

		Problem problem = new AcoTSP(instance).withVisualization(new Visualization(true));

		AntSystem aco = new AntSystem(problem);

		new ACORunner().withACO(aco).withInstance(instance).withIteration(1000).withVisualization(new Visualization(true)).withOutput(new CSV("testASRunner.csv"));

		aco.setNumberOfAnts(30);
		aco.setNumberOfIterations(1000);
		aco.setAlpha(1.0);
		aco.setBeta(5.0);
		aco.setRho(0.01);

		ExecutionStats es = ExecutionStats.execute(aco, problem);
		es.printStats();
	}

}
