package thiagodnf.jacof.aco.ant;

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
public class AntTest {{

	ACO aco = Mockito.mock(ACO.class);
	Problem p = Mockito.mock(Problem.class);
	
	when(aco.getProblem()).thenReturn(p);
	when(aco.getProblem().getNumberOfNodes()).thenReturn(3);
	
	describe("When create an instance of this class", () -> {
		
		it("should throw an exception when the aco is null", () -> {
			expect(() -> {
				new Ant(null, 1);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when the id is invalid", () -> {
			expect(() -> {
				new Ant(aco, -1);
			}).toThrow(IllegalArgumentException.class);
		});
	});	
	
	describe("When clone an ant", () -> {
		
		it("should return a new ant cloned", () -> {
			
			Ant source = new Ant(aco, 0);
			Ant clone = source.clone();
			
			expect(source != clone).toBeTrue();
			expect(source.getAntInitialization()).toEqual(clone.getAntInitialization());
			expect(source.getId()).toEqual(clone.getId());
			expect(source.getNodesToVisit()).toEqual(clone.getNodesToVisit());
			expect(source.getTourLength()).toEqual(clone.getTourLength());
			expect(source.currentNode).toEqual(clone.currentNode);
		});
	});

	
	describe("When call the toString method", () -> {

		it("should return a valid string", () -> {
			expect(new Ant(aco, 0).toString()).toBeNotNull();
		});
	});
}}
