package thiagodnf.jacof.aco.graph.initialization;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class ASRankInitializationTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	ACO aco = Mockito.mock(ACO.class);
	
	describe("When create an instance of this class", () -> {

		it("should throw an exception when null aco is passed", () -> {
			expect(() -> {
				new ASRankInitialization(null);
			}).toThrow(NullPointerException.class);
		});
		
		it("should throw an exception when rate > 1", () -> {
			expect(() -> {
				new ASRankInitialization(aco, 1.01, 2);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when rate < 0", () -> {
			expect(() -> {
				new ASRankInitialization(aco, -0.01, 2);
			}).toThrow(IllegalArgumentException.class);
		});
		
		it("should throw an exception when weight < 0", () -> {
			expect(() -> {
				new ASRankInitialization(aco, 0.5, -1);
			}).toThrow(IllegalArgumentException.class);
		});
	});
	
	describe("When w=6, rate=0.5 and Cnn=10", () -> {
		
		before(() -> {
			when(aco.getProblem()).thenReturn(problem);
			when(aco.getNumberOfAnts()).thenReturn(2);			
			when(aco.getProblem().getCnn()).thenReturn(10.0);
			when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
		});
		
		it("should return 3", () -> {
			expect(new ASRankInitialization(aco, 0.5, 6).getT0()).toEqual(3);
		});
	});
	
	describe("When call the toString method", () -> {

		it("should return the correct string", () -> {
			expect(new ASRankInitialization(aco).toString()).toEqual("ASRankInitialization 0.1 6.0");
		});
	});
}}
