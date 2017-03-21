package thiagodnf.jacof.aco.graph.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class FixedValueInitializationTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	ACO aco = Mockito.mock(ACO.class);
	
	when(aco.getNumberOfAnts()).thenReturn(2);
	when(aco.getProblem()).thenReturn(problem);
	when(aco.getProblem().getCnn()).thenReturn(10.0);
	
	describe("When initialize an FixedValueInitialization", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new FixedValueInitialization(null, 2);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When value is 2.0", () -> {
		
		it("should return 2.0", () -> {
			expect(new FixedValueInitialization(aco, 2.0).getT0()).toEqual(2.0);
		});
	});
	
	describe("When call toString method", () -> {

		it("should return the correct name", () -> {
			expect(new FixedValueInitialization(aco, 2.0).toString()).toEqual("FixedValueInitialization 2.0");
		});
	});
}}
