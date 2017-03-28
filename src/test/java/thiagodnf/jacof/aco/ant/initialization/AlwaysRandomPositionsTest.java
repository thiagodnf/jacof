package thiagodnf.jacof.aco.ant.initialization;

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
public class AlwaysRandomPositionsTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem problem = Mockito.mock(Problem.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new AlwaysRandomPositions(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the ant's position", () -> {

		it("should return a position between zero and the number of nodes", () -> {
			
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
			
			expect(new AlwaysRandomPositions(aco).getPosition(0)).toBeBetween(0, aco.getProblem().getNumberOfNodes());
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new AlwaysRandomPositions(aco).toString()).toBeNotNull();
		});
	});
}}
