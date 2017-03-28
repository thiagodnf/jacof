package thiagodnf.jacof.aco.subset.single;

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
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class RandomAntTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);
		
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new RandomAnt(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the list of ants", () -> {
		
		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(p);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
		
		it("should return an array with size = 1", () -> {	
			
			Ant a1 = new Ant(aco, 1);
			Ant a2 = new Ant(aco, 2);
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1, a2});
			
			AbstractSingleAnt singleAnts = new RandomAnt(aco);
			expect(singleAnts.getSubSet().size()).toEqual(1);
		});
		
		it("should return a cloned ant", () -> {	
			
			Ant a1 = new Ant(aco, 1);
			Ant a2 = new Ant(aco, 2);
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1, a2});
			
			AbstractSingleAnt singleAnts = new RandomAnt(aco);
												
			expect(singleAnts.getSubSet().get(0) != a1).toBeTrue();
			expect(singleAnts.getSubSet().get(0) != a2).toBeTrue();			
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new RandomAnt(aco).toString()).toBeNotNull();
		});
	});
}}
