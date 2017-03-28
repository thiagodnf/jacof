package thiagodnf.jacof.aco.rule.globalupdate.evaporation;

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
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class FullEvaporationTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem problem = Mockito.mock(Problem.class);
	AntGraph graph = Mockito.mock(AntGraph.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new FullEvaporation(null, 0.5);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when rate < 0", () -> {
			expect(() -> {
				new FullEvaporation(aco, -0.1);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when rate > 1", () -> {
			expect(() -> {
				new FullEvaporation(aco, 1.1);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When get the new value", () -> {

		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(50);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
			when(aco.getGraph()).thenReturn(graph);
			when(aco.getGraph().getTau(0, 1)).thenReturn(1.0);			
		});
	
		it("should return a position between zero and the number of nodes", () -> {
			expect(new FullEvaporation(aco, 0.1).getTheNewValue(0, 1)).toEqual(0.9);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new FullEvaporation(aco, 0.1).toString()).toBeNotNull();
		});
	});
}}
