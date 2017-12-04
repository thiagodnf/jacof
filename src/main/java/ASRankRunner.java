import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import thiagodnf.jacof.aco.RankBasedAntSystem;
import thiagodnf.jacof.problem.Problem;
import benchmark.problem.AcoTSP;
import thiagodnf.jacof.util.ExecutionStats;

import java.io.IOException;

public class ASRankRunner {

	/** The class logger*/
	static final Logger LOGGER = Logger.getLogger(ASRankRunner.class);
	
	public static void main(String[] args) throws ParseException, IOException {

		String instance = "src/main/resources/problems/tsp/bays29.tsp";



		Problem problem = new AcoTSP(instance);

		RankBasedAntSystem aco = new RankBasedAntSystem(problem);

		aco.setNumberOfAnts(30);
		aco.setNumberOfIterations(5000);
		aco.setAlpha(1.0);
		aco.setBeta(2.0);
		aco.setRho(0.1);
		aco.setWeight(30);
		
		ExecutionStats es = ExecutionStats.execute(aco, problem);
		es.printStats();
	}

}
