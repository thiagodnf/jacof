package thiagodnf.jacof.aco.rule.localupdate;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.graph.AntGraph;
import thiagodnf.jacof.aco.graph.initialization.AbstractGraphInitialization;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class ACSLocalUpdatingRuleTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem problem = Mockito.mock(Problem.class);
	AbstractGraphInitialization initialization = Mockito.mock(AbstractGraphInitialization.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new ACSLocalUpdatingRule(null, 1.0);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when rate < 0", () -> {
			expect(() -> {
				new ACSLocalUpdatingRule(aco, -1);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when rate > 1", () -> {
			expect(() -> {
				new ACSLocalUpdatingRule(aco, 1.1);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When get the new value", () -> {

		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(2);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
			
			when(aco.getGraphInitialization()).thenReturn(initialization);
			when(initialization.getT0()).thenReturn(2.0);
			
			AntGraph graph = new AntGraph(problem);
			graph.initialize(initialization);
			
			when(aco.getGraph()).thenReturn(graph);
		});
	
		it("should return the correct value", () -> {
			new ACSLocalUpdatingRule(aco, 0.1).update(0, 1);
			expect(aco.getGraph().getTau(0, 1)).toEqual(2.0);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new ACSLocalUpdatingRule(aco, 0.1).toString()).toBeNotNull();
		});
	});
}}
