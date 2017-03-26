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
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class ACSInitializationTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	ACO aco = Mockito.mock(ACO.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new ACSInitialization(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When n=5 and Cnn=10", () -> {
		
		before(() -> {
			when(aco.getNumberOfAnts()).thenReturn(2);
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getProblem().getCnn()).thenReturn(10.0);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});

		it("should return 0.02", () -> {
			expect(new ACSInitialization(aco).getT0()).toEqual(0.02);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return the correct string", () -> {
			expect(new ACSInitialization(aco).toString()).toEqual(ACSInitialization.class.getSimpleName());
		});
	});
}}
