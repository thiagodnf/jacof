package thiagodnf.jacof.aco.ant.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class AnAntAtARandomVertexTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem problem = Mockito.mock(Problem.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new AnAntAtARandomVertex(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when the number of ants < 1", () -> {
			
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(0);
			
			expect(() -> {
				new AnAntAtARandomVertex(aco);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When get the ant's position", () -> {

		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(5);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
		
		it("should return a position between zero and the number of nodes", () -> {
			expect(new AnAntAtARandomVertex(aco).getPosition(0)).toBeBetween(0, aco.getProblem().getNumberOfNodes());
		});
		
		it("should return the same position", () -> {
			AbstractAntInitialization initialization = new AnAntAtARandomVertex(aco);
			
			int posA = initialization.getPosition(0);
			int posB = initialization.getPosition(0);
			
			expect(posA).toEqual(posB);
		});
		
		it("should throw an exception when the ant's id is negative", () -> {
			expect(() -> {
				new AnAntAtARandomVertex(aco).getPosition(-1);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when the ant's id is greater than the number of ants", () -> {
			
			when(aco.getNumberOfAnts()).thenReturn(5);
			
			expect(() -> {
				new AnAntAtARandomVertex(aco).getPosition(5);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new AnAntAtARandomVertex(aco).toString()).toBeNotNull();
		});
	});
}}
