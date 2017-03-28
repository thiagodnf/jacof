package thiagodnf.jacof.aco.rule.globalupdate.deposit;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.graph.AntGraph;
import thiagodnf.jacof.aco.subset.many.AllAnts;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class PartialDepositTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem problem = Mockito.mock(Problem.class);
	AntGraph graph = Mockito.mock(AntGraph.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new PartialDeposit(null, 0.5, null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when rate < 0", () -> {
			expect(() -> {
				new PartialDeposit(aco, -0.1, null);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when rate > 1", () -> {
			expect(() -> {
				new PartialDeposit(aco, 1.1, null);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when subSet is null", () -> {
			expect(() -> {
				new PartialDeposit(aco, 1.0, null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the new value", () -> {

		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(50);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
			when(aco.getGraph()).thenReturn(graph);
			when(aco.getGraph().getTau(0, 1)).thenReturn(1.0);
			
			Ant a1 = new Ant(aco, 1);
			Ant a2 = new Ant(aco, 2);
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1, a2});
		});
	
		it("should return the same value when rate is 0.0", () -> {
			expect(new PartialDeposit(aco, 0.0, new AllAnts(aco)).getTheNewValue(0, 1)).toEqual(1.0);
		});
		
		it("should return the correct value when only one ant has the path", () -> {
			aco.getAnts()[0].path[0][1] = 1;
			aco.getAnts()[1].path[0][2] = 1;
			
			aco.getAnts()[0].setTourLength(10);
			
			when(problem.getDeltaTau(10, 0, 1)).thenReturn(2.0);
			
			expect(new PartialDeposit(aco, 1.0, new AllAnts(aco)).getTheNewValue(0, 1)).toEqual(3.0);
		});
		
		it("should return the correct value when two ants have the path", () -> {
			aco.getAnts()[0].path[0][1] = 1;
			aco.getAnts()[1].path[0][1] = 1;
			
			aco.getAnts()[0].setTourLength(10);
			aco.getAnts()[1].setTourLength(20);
			
			when(problem.getDeltaTau(10, 0, 1)).thenReturn(2.0);
			when(problem.getDeltaTau(20, 0, 1)).thenReturn(2.0);
			
			expect(new PartialDeposit(aco, 1.0, new AllAnts(aco)).getTheNewValue(0, 1)).toEqual(5.0);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new PartialDeposit(aco, 1.0, new AllAnts(aco)).toString()).toBeNotNull();
		});
	});
}}
