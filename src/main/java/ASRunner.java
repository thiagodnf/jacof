import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.cli.ParseException;

import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.problem.tsp.TravellingSalesmanProblem;

public class ASRunner {

	public static void main(String[] args) throws ParseException, IOException {

		String instance = "src/main/resources/problems/tsp/oliver30.tsp";

		Problem problem = new TravellingSalesmanProblem(instance);

		AntSystem aco = new AntSystem(problem);

		aco.setNumberOfAnts(1);
		aco.setNumberOfIterations(10000);
		aco.setAlpha(1.0);
		aco.setBeta(5.0);
		aco.setRho(0.01);
		
		int[] bestSolution = aco.solve();

		double value = problem.evaluate(bestSolution);

		System.out.println(Arrays.toString(bestSolution) + " : " + value);

	}

}
