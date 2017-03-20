import java.io.IOException;
import java.util.Arrays;

import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.problem.nrp.NextReleaseProblem;

public class ASRunner {

	public static void main(String[] args) throws IOException {

		String instance = "src/main/resources/problems/nrp/delsagrado20.nrp";

		Problem problem = new NextReleaseProblem(instance);

		AntSystem aco = new AntSystem(problem);

		aco.setNumberOfAnts(10);
		aco.setNumberOfIterations(10);
		aco.setAlpha(1.0);
		aco.setBeta(2.0);
		aco.setRho(0.1);
		
		int[] bestSolution = aco.solve();

		double value = problem.evaluate(bestSolution);

		System.out.println(Arrays.toString(bestSolution) + " : " + value);
	}

}
