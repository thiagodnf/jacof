import java.io.IOException;
import java.util.Arrays;

import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.aco.ant.globalupdate.evaporation.FullEvaporation;
import thiagodnf.jacof.aco.ant.globalupdate.reinforce.FullReinforce;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.problem.nrp.NextReleaseProblem;

public class ASRunner {
	
	public static void main(String[] args) throws IOException{
		
		String instance = "src/main/resources/problems/nrp/delsagrado20.nrp";

		Problem problem = new NextReleaseProblem(instance);

		AntSystem aco = new AntSystem(problem);

		aco.setNumberOfAnts(10);
		aco.setNumberOfIterations(10);
		aco.setAlpha(1.0);
		aco.setBeta(2.0);
		aco.setRho(0.1);
		aco.setAntInitialization(new AnAntAtEachVertex(aco));
		aco.setAntSelection(new RouletteWheel());
		
		aco.setEvaporation(new FullEvaporation(aco, 0.5));
		aco.setReinforce(new FullReinforce(aco));

		int[] bestSolution = aco.solve();

		double value = problem.evaluate(bestSolution);

		System.out.println(Arrays.toString(bestSolution) + " : " + value);
	}

}
