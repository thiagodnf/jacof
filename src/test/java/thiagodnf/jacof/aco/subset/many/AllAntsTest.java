package thiagodnf.jacof.aco.subset.many;

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
public class AllAntsTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new AllAnts(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the list of ants", () -> {	
		
		beforeEach(() -> {			
			when(aco.getProblem()).thenReturn(p);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
		
		it("should return a empty list with no ants", () -> {
			when(aco.getAnts()).thenReturn(new Ant[]{});
			AbstractManyAnts manyAnts = new AllAnts(aco);			
			expect(manyAnts.getSubSet().size()).toEqual(0);
		});
		
		it("should return the correct ants", () -> {
			
			Ant a1 = new Ant(aco, 1);
			Ant a2 = new Ant(aco, 2);
			
			a1.setTourLength(20.0);
			a2.setTourLength(10.0);
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1,a2});
			
			AbstractManyAnts manyAnts = new AllAnts(aco);
			
			expect(manyAnts.getSubSet().size()).toEqual(2);
			expect(manyAnts.getSubSet().get(0).getTourLength()).toEqual(a1.getTourLength());
			expect(manyAnts.getSubSet().get(1).getTourLength()).toEqual(a2.getTourLength());
		});
		
		it("should return cloned ants", () -> {
			
			Ant a1 = new Ant(aco, 1);
			Ant a2 = new Ant(aco, 2);
			
			a1.setTourLength(20.0);
			a2.setTourLength(10.0);
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1,a2});
			
			AbstractManyAnts manyAnts = new AllAnts(aco);
			
			expect(a1 != manyAnts.getSubSet().get(0)).toBeTrue();
			expect(a2 != manyAnts.getSubSet().get(1)).toBeTrue();
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new AllAnts(aco).toString()).toBeNotNull();
		});
	});
}}
