package thiagodnf.jacof.aco.graph.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.graph.initialization.ASInitialization;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class ASInitializationTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	ACO aco = Mockito.mock(ACO.class);
	
	when(aco.getNumberOfAnts()).thenReturn(2);
	when(aco.getProblem()).thenReturn(problem);
	when(aco.getProblem().getCnn()).thenReturn(10.0);
	
	describe("When initialize an ASInitialization", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new ASInitialization(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When k=2 and Cnn=10", () -> {
		
		it("should return 0.25", () -> {
			expect(new ASInitialization(aco).getT0()).toEqual(0.2);
		});
	});
	
	describe("When call toString method", () -> {

		it("should return the correct name", () -> {
			expect(new ASInitialization(aco).toString()).toEqual(ASInitialization.class.getSimpleName());
		});
	});
}}
