package thiagodnf.jacof.aco.graph.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.graph.initialization.FixedValueInitialization;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class FixedValueInitializationTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	ACO aco = Mockito.mock(ACO.class);
		
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new FixedValueInitialization(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when value < 0", () -> {
			expect(() -> {
				new FixedValueInitialization(aco, -0.22);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When the value parameter is 2.0", () -> {
		
		before(() -> {
			when(aco.getProblem()).thenReturn(problem);
		});
		
		it("should return 2.0", () -> {
			expect(new FixedValueInitialization(aco, 2.0).getT0()).toEqual(2.0);			
		});
	});
	
	describe("When the value parameter has the default value", () -> {
		
		before(() -> {
			when(aco.getProblem()).thenReturn(problem);
		});
		
		it("should return 0.5", () -> {
			expect(new FixedValueInitialization(aco).getT0()).toEqual(0.5);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return the correct string", () -> {
			expect(new FixedValueInitialization(aco, 2.0).toString()).toEqual("FixedValueInitialization 2.0");
		});
	});
}}
