package thiagodnf.jacof.problem.nrp;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.util.Arrays;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class NextReleaseProblemTest {
	{
		
		describe("When read an instance with budget", () -> {

			it("should return the correct information", () -> {
				
				String instance = "src/main/resources/problems/nrp/delsagrado20.nrp";

				NextReleaseProblem problem = new NextReleaseProblem(instance);
				
				expect(problem.getNumberOfRequirements()).toEqual(20);
				expect(problem.getNumberOfCustomers()).toEqual(5);
				expect(Arrays.equals(new double[]{4,4,3,5,5}, problem.getCustomersWeights())).toBeTrue();
				expect(Arrays.equals(new double[]{1,4,2,3,4,7,10,2,1,3,2,5,8,2,1,4,10,4,8,4}, problem.getRequirementsCosts())).toBeTrue();
				
				expect(Arrays.equals(new double[]{4,2,1,2,5,5,2,4,4,4,2,3,4,2,4,4,4,1,3,2}, problem.getImportance()[0])).toBeTrue();
				expect(Arrays.equals(new double[]{4,4,2,2,4,5,1,4,4,5,2,3,2,4,4,2,3,2,3,1}, problem.getImportance()[1])).toBeTrue();
				expect(Arrays.equals(new double[]{5,3,3,3,4,5,2,4,4,4,2,4,1,5,4,1,2,3,3,2}, problem.getImportance()[2])).toBeTrue();
				expect(Arrays.equals(new double[]{4,5,2,3,3,4,2,4,2,3,5,2,3,2,4,3,5,4,3,2}, problem.getImportance()[3])).toBeTrue();
				expect(Arrays.equals(new double[]{5,4,2,4,5,4,2,4,5,2,4,5,3,4,4,1,1,2,4,1}, problem.getImportance()[4])).toBeTrue();
				expect(problem.getBudget()).toEqual(25);
				
				expect(problem.getCostR()).toEqual(85);
				expect(problem.getSatR()).toEqual(1339);
				expect(Arrays.equals(new double[]{92,78,41,60,88,95,38,84,79,73,67,71,57,69,84,47,64,51,68,33}, problem.getScore())).toBeTrue();
			});			
		});
		
		describe("When define the budget", () -> {
			
			it("should return the correct budget", () -> {
				
				String instance = "src/main/resources/problems/nrp/dataset-2.nrp";
				
				NextReleaseProblem problem = new NextReleaseProblem(instance);
				
				problem.setBudget(20);
				
				expect(problem.getBudget()).toEqual(20);
			});
		});

		describe("When read an instance with no budget", () -> {
			
			it("should return 0 for budget", () -> {
				
				String instance = "src/main/resources/problems/nrp/dataset-2.nrp";
				
				NextReleaseProblem problem = new NextReleaseProblem(instance);
				
				expect(problem.getBudget()).toEqual(0);
			});
		});
		
		describe("When read an instance with description", () -> {
			
			it("should return the correct description", () -> {
				
				String instance = "src/main/resources/problems/nrp/dataset-2.nrp";
				
				NextReleaseProblem problem = new NextReleaseProblem(instance);
				
				expect(problem.getDescriptions()[0]).toEqual("Hierarchical dependencies");
				expect(problem.getDescriptions()[1]).toEqual("Grouping of features");
				expect(problem.getDescriptions()[11]).toEqual("Similarity analysis");
				expect(problem.getDescriptions()[24]).toEqual("Resource evaluation of alternative plans");
			});
		});
		
		describe("When request a solution evaluation for [0,1,2]", () -> {

			it("should return 211 as fitness function", () -> {

				String instance = "src/main/resources/problems/nrp/delsagrado20.nrp";

				NextReleaseProblem problem = new NextReleaseProblem(instance);

				expect(problem.evaluate(new int[] { 0, 1, 2 })).toEqual(211);
			});
		});
		
		describe("When request a solution evaluation for []", () -> {

			it("should return 0 as fitness function", () -> {

				String instance = "src/main/resources/problems/nrp/delsagrado20.nrp";

				NextReleaseProblem problem = new NextReleaseProblem(instance);

				expect(problem.evaluate(new int[] {})).toEqual(0);
			});
		});	
	}
}

