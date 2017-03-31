import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.problem.tsp.TravellingSalesmanProblem;
import thiagodnf.jacof.util.ExecutionStats;

public class ASRunner {

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(ASRunner.class);
	
	public static void main(String[] args) throws ParseException, IOException {

		String instance = "src/main/resources/problems/tsp/oliver30.tsp";

		Problem problem = new TravellingSalesmanProblem(instance);

		AntSystem aco = new AntSystem(problem);

		aco.setNumberOfAnts(30);
		aco.setNumberOfIterations(1000);
		aco.setAlpha(1.0);
		aco.setBeta(5.0);
		aco.setRho(0.01);
		
		ExecutionStats es = ExecutionStats.execute(aco, problem);
		es.printStats();
	}

}
