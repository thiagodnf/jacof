import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.factory.ProblemFactory;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.Parameters;

public class GenericRunner {

	protected static Options options;
	
	public static void main(String[] args) throws ParseException, IOException {

		// Get the parameters
		options = constructTheOptions();

		Parameters parameters = parse(args);

		// Initialize the problem and the ACO
		Problem problem = ProblemFactory.getProblem(parameters.getString("p"), parameters.getString("in"));

//		ACO aco = ACOFactory.getACO(parameters.getString("aco"), problem, parameters);
		
		// Define the default parameters used in all aco's
//		aco.setNumberOfAnts(parameters.getInt("n", 10));
//		aco.setNumberOfIterations(parameters.getInt("it", 50));
//		aco.setAlpha(parameters.getDouble("a", 1.0));
//		aco.setBeta(parameters.getDouble("b", 2.0));
//		aco.setRho(parameters.getDouble("rho", 0.1));	
		
//		// Execute the algorithm
//		int[] bestSolution = aco.solve();
//
//		// Evaluate the solution
//		double value = problem.evaluate(bestSolution);
//
//		// Show the best solution
//		System.out.println(Arrays.toString(bestSolution) + " : " + value);
	}

	public static Parameters parse(String[] args) throws ParseException {

		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption("h")) {
			printHelp();
		}

		Parameters parameters = new Parameters();

		for (Option option : options.getOptions()) {
			parameters.put(option.getOpt(), cmd.getOptionValue(option.getOpt()));
		}

		return parameters;
	}
	
	protected static Options constructTheOptions() {
		final Options options = new Options();
		
		options.addOption("h", "help", false, "Show the help page");
		options.addOption("p", "problem", true, "The problem addressed");
		options.addOption("aco", "aco", true, "The aco's variation");
		options.addOption("in", "instance", true, "The problem instance");
		options.addOption("n", "ants", true, "The number of ants");
		options.addOption("it", "iterations", true, "The number of iterations");
		options.addOption("a", "alpha", true, "The alpha value");
		options.addOption("b", "beta", true, "The beta value");
		options.addOption("rho", "rho", true, "The evaporation rate");
		options.addOption("w", "weight", true, "The weight for ASRank and EAS algorithms");
		options.addOption("o", "omega", true, "The evaporation rate for the local update rule");
		options.addOption("q0", "q0", true, "A random number uniformly distributed in [0:1]");
		options.addOption("tMin", "tMin", true, "The minimum value of pheronome");
		options.addOption("tMax", "tMax", true, "The maximum value of pheronome");
		
		return options;
	}

	protected static void printHelp() {
		
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("jacof", options);

		System.exit(0);
	}
}
