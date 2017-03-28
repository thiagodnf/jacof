package thiagodnf.jacof.problem.nrp;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;

import com.mscharhag.oleaster.runner.OleasterRunner;

@RunWith(OleasterRunner.class)
public class NextReleaseProblemTest {{
	
	String in = "src/main/resources/problems/nrp/delsagrado20.nrp";

	describe("When read an instance", () -> {

		it("should return the correct information", () -> {
			
			NextReleaseProblem problem = new NextReleaseProblem(in);
			
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
			expect(problem.getBudget()).toEqual(25);
			
			expect(problem.getCnn()).toEqual(1339);
			expect(problem.getDeltaTau(0, 0, 1)).toEqual(0);
			expect(problem.getNij(1, 0)).toEqual(92);
			
			expect(problem.getNumberOfNodes()).toEqual(problem.getNumberOfRequirements());
			expect(Arrays.equals(new double[]{92,78,41,60,88,95,38,84,79,73,67,71,57,69,84,47,64,51,68,33}, problem.getScore())).toBeTrue();
		});			
	});
	
	describe("When read an instance with no budget but with description", () -> {
		
		String instance = "src/main/resources/problems/nrp/dataset-2.nrp";
		
		NextReleaseProblem problem = new NextReleaseProblem(instance);
		
		it("should return 0 for budget", () -> {
			expect(problem.getBudget()).toEqual(0);
		});
		
		it("should return the correct description", () -> {
			expect(problem.getDescriptions()[0]).toEqual("Hierarchical dependencies");
			expect(problem.getDescriptions()[1]).toEqual("Grouping of features");
			expect(problem.getDescriptions()[11]).toEqual("Similarity analysis");
			expect(problem.getDescriptions()[24]).toEqual("Resource evaluation of alternative plans");
		});
	});
	
	describe("When request a fitness function evaluation", () -> {

		NextReleaseProblem problem = new NextReleaseProblem(in);
		
		it("should throw an exception when the solution is null", () -> {			
			expect(() -> {
				problem.evaluate(null);
			}).toThrow(NullPointerException.class);			
		});
		
		it("should return 211 as fitness function for [0,1,2]", () -> {			
			expect(problem.evaluate(new int[] { 0, 1, 2 })).toEqual(211);
		});
		
		it("should return 0 as fitness function for []", () -> {
			expect(problem.evaluate(new int[] {})).toEqual(0);
		});
	});
	
	describe("When request an effort evaluation", () -> {

		NextReleaseProblem problem = new NextReleaseProblem(in);
		
		it("should throw an exception when the solution is null", () -> {			
			expect(() -> {
				problem.effort(null);
			}).toThrow(NullPointerException.class);			
		});
		
		it("should return 211 as effort for [0,1,2]", () -> {			
			expect(problem.effort(new int[] { 0, 1, 2 })).toEqual(7);
		});
		
		it("should return 0 as effort for []", () -> {
			expect(problem.effort(new int[] {})).toEqual(0);
		});
	});
	
	describe("When compare two solutions", () -> {
		
		NextReleaseProblem problem = new NextReleaseProblem(in);
		
		it("should return that the first solution is better than the second one", () -> {			
			expect(problem.better(10, 5)).toBeTrue();
			expect(problem.better(5, 10)).toBeFalse();
		});
		
		it("should return that the first solution is better than the second one 2", () -> {			
			expect(problem.better(new int[] { 0, 1, 2 }, new int[] {})).toBeTrue();
		});
	});
	
	describe("When starting or updating the list of nodes to visit", () -> {

		NextReleaseProblem problem = new NextReleaseProblem(in);
		
		problem.setBudget(4);
		
		it("should return the correct starting list", () -> {
			List<Integer> result = problem.initNodesToVisit(0);
			List<Integer> expected = Arrays.asList(new Integer[]{1,2,3,4,7,8,9,10,13,14,15,17,19});
			expect(result).toEqual(expected);
		});
		
		it("should return the correct updated list", () -> {
			List<Integer> tour = new ArrayList<>(Arrays.asList(new Integer[]{0}))	;
			List<Integer> nodesToVisit = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,7,8,9,10,13,14,15,17,19}));
			
			List<Integer> result = problem.updateNodesToVisit(tour, nodesToVisit);
			List<Integer> expected = Arrays.asList(new Integer[]{2,3,7,8,9,10,13,14});
			expect(result).toEqual(expected);
		});		
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new NextReleaseProblem(in).toString()).toBeNotNull();
		});
	});
}}

