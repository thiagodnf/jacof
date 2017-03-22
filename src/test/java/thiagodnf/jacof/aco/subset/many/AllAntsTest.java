package thiagodnf.jacof.aco.subset.many;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
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


	describe("When initialize an AllAnts", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new AllAnts(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the list of ants", () -> {

		ACO aco = Mockito.mock(ACO.class);
		
		it("should return a empty list with no ants", () -> {
			when(aco.getAnts()).thenReturn(new Ant[0]);
			AbstractManyAnts manyAnts = new AllAnts(aco);			
			expect(manyAnts.getSubSet().size()).toEqual(0);
		});
		
		it("should return a clone of all ants", () -> {
			
			Problem p = Mockito.mock(Problem.class);
			
			
			when(aco.getProblem()).thenReturn(p);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
			
			Ant a1 = new Ant(aco);
			Ant a2 = new Ant(aco);
			
			a1.tourLength = 10;
			a2.tourLength = 20;
			
			when(aco.getAnts()).thenReturn(new Ant[]{a1,a2});
			
			AbstractManyAnts manyAnts = new AllAnts(aco);
			
			expect(manyAnts.getSubSet().size()).toEqual(2);
			expect(manyAnts.getSubSet().get(0).getId()).toEqual(0);
			expect(manyAnts.getSubSet().get(1).getId()).toEqual(1);
			
			// Test the clones
			
			Ant c1 = manyAnts.getSubSet().get(0);
			Ant c2 = manyAnts.getSubSet().get(1);
			
			c1.tourLength = 30;
			c2.tourLength = 50;
			
			expect(a1.tourLength != c1.tourLength).toBeTrue();
			expect(a2.tourLength != c2.tourLength).toBeTrue();
		});
	});
}}
