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
public class GlobalBestTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);			
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new GlobalBest(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the list of ants", () -> {
		
		beforeEach(() -> {			
			when(aco.getProblem()).thenReturn(p);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
				
		it("should return the correct ant", () -> {
			
			Ant ant = new Ant(aco, 1);
			ant.setTourLength(10.0);
			
			when(aco.getGlobalBest()).thenReturn(ant);
			
			AbstractSingleAnt singleAnts = new GlobalBest(aco);
			
			expect(singleAnts.getSubSet().size()).toEqual(1);
			expect(singleAnts.getSubSet().get(0).getTourLength()).toEqual(10);	
		});
		
		it("should return a cloned ant", () -> {
			
			Ant ant = new Ant(aco, 1);
			ant.setTourLength(10.0);
			
			when(aco.getGlobalBest()).thenReturn(ant);
			
			AbstractSingleAnt singleAnts = new GlobalBest(aco);
			
			expect(ant != singleAnts.getSubSet().get(0)).toBeTrue();	
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new GlobalBest(aco).toString()).toBeNotNull();
		});
	});
}}
