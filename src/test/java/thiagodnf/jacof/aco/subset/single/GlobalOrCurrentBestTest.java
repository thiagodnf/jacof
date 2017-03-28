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
import thiagodnf.jacof.util.random.JMetalRandom;
import thiagodnf.jacof.util.random.JavaRandomGenerator;

@RunWith(OleasterRunner.class)
public class GlobalOrCurrentBestTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);
		
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new GlobalOrCurrentBest(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when the probability < 0", () -> {
			expect(() -> {
				new GlobalOrCurrentBest(aco, -0.1);
			}).toThrow(IllegalArgumentException.class);
		});

		it("should throw an exception when the probability > 1", () -> {
			expect(() -> {
				new GlobalOrCurrentBest(aco, 1.1);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When get the list of ants", () -> {
		
		beforeEach(() -> {
			when(aco.getProblem()).thenReturn(p);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
		
		it("should return an array with size = 1", () -> {
			
			Ant gAnt = new Ant(aco, 1);
			Ant cAnt = new Ant(aco, 2);
			
			when(aco.getCurrentBest()).thenReturn(gAnt);
			when(aco.getGlobalBest()).thenReturn(cAnt);
			
			AbstractSingleAnt singleAnts = new GlobalOrCurrentBest(aco);
			expect(singleAnts.getSubSet().size()).toEqual(1);
		});
		
		it("should return the the global best ant", () -> {
			JMetalRandom.getInstance().setRandomGenerator(new JavaRandomGenerator(112323));
			
			Ant gAnt = new Ant(aco, 1);
			Ant cAnt = new Ant(aco, 2);
			
			gAnt.setTourLength(10);
			
			when(aco.getCurrentBest()).thenReturn(cAnt);
			when(aco.getGlobalBest()).thenReturn(gAnt);
			
			AbstractSingleAnt singleAnts = new GlobalOrCurrentBest(aco);
			expect(singleAnts.getSubSet().get(0).getTourLength()).toEqual(10);
		});
		
		it("should return the the current best ant", () -> {
			JMetalRandom.getInstance().setRandomGenerator(new JavaRandomGenerator(1));
			
			Ant gAnt = new Ant(aco, 1);
			Ant cAnt = new Ant(aco, 2);
			
			cAnt.setTourLength(20);
			
			when(aco.getCurrentBest()).thenReturn(cAnt);
			when(aco.getGlobalBest()).thenReturn(gAnt);
			
			AbstractSingleAnt singleAnts = new GlobalOrCurrentBest(aco);
			expect(singleAnts.getSubSet().get(0).getTourLength()).toEqual(20);
		});
		
		it("should return a cloned ant", () -> {
			
			Ant gAnt = new Ant(aco, 1);
			Ant cAnt = new Ant(aco, 2);
			
			when(aco.getCurrentBest()).thenReturn(gAnt);
			when(aco.getGlobalBest()).thenReturn(cAnt);
			
			AbstractSingleAnt singleAnts = new GlobalOrCurrentBest(aco);
			
			expect(singleAnts.getSubSet().get(0) != gAnt).toBeTrue();
			expect(singleAnts.getSubSet().get(0) != cAnt).toBeTrue();			
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new GlobalOrCurrentBest(aco).toString()).toBeNotNull();
		});
	});
}}
