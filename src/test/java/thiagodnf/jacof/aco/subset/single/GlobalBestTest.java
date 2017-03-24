package thiagodnf.jacof.aco.subset.single;

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
public class GlobalBestTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);			
	
	when(aco.getProblem()).thenReturn(p);
	when(aco.getProblem().getNumberOfNodes()).thenReturn(5);

	describe("When initialize an GlobalBest", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new GlobalBest(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When get the list of ants", () -> {
				
		Ant a1 = new Ant(aco);
		a1.tourLength = 10;
		
		when(aco.getGlobalBest()).thenReturn(a1);
		
		it("should return the global best", () -> {
			
			AbstractSingleAnt singleAnts = new GlobalBest(aco);
			
			expect(singleAnts.getSubSet().size()).toEqual(1);
			expect(singleAnts.getSubSet().get(0).tourLength).toEqual(10);
			
			// Verify if the returned ants are a clone
			expect(a1 != singleAnts.getSubSet().get(0)).toBeTrue();			
		});
	});
}}
