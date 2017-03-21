package thiagodnf.jacof.aco.graph;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.graph.initialization.AbstractTrailInitialization;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class AntGraphTest {{
	
	Problem problem = Mockito.mock(Problem.class);
	AbstractTrailInitialization trailInitialization = Mockito.mock(AbstractTrailInitialization.class);
	
	when(problem.getNumberOfNodes()).thenReturn(5);
	when(trailInitialization.getT0()).thenReturn(1.0);	
	
	describe("When initialize an AntGraph", () -> {

		it("should throw an exception when null problem is passed", () -> {
			expect(() -> {
				new AntGraph(null);
			}).toThrow(NullPointerException.class);
		});
	});
	
	describe("When initialize the pheronome matrix", () -> {
				
		AntGraph graph = new AntGraph(problem);
		graph.initialize(trailInitialization);
		
		it("should throw an exception when null is passed", () -> {
			expect(() -> {
				new AntGraph(problem).initialize(null);;
			}).toThrow(NullPointerException.class);
		});
		
		describe("and a valid trail initialization is passed", () -> {			
		
			it("should return a 5x5 pheromone matrix", () -> {
				double[][] matrix = graph.getTau();
				
				expect(matrix.length).toEqual(5);			
			});
			
			it("should return the correct T0 values", () -> {
				double[][] matrix = graph.getTau();
				
				expect(Arrays.equals(new double[]{1.0, 1.0, 1.0, 1.0, 1.0}, matrix[0])).toBeTrue();
				expect(Arrays.equals(new double[]{1.0, 1.0, 1.0, 1.0, 1.0}, matrix[1])).toBeTrue();
				expect(Arrays.equals(new double[]{1.0, 1.0, 1.0, 1.0, 1.0}, matrix[2])).toBeTrue();
				expect(Arrays.equals(new double[]{1.0, 1.0, 1.0, 1.0, 1.0}, matrix[3])).toBeTrue();
				expect(Arrays.equals(new double[]{1.0, 1.0, 1.0, 1.0, 1.0}, matrix[4])).toBeTrue();
			});
			
			it("should return 3.5 for arch(1,2) when set a pheronome value 3.5 for this one", () -> {
				
				graph.setTau(1, 2, 3.5);
				
				expect(graph.getTau(1, 2)).toEqual(3.5);
			});		
		});
	});	
	
	describe("When return toString method", () -> {
		
		when(problem.getNumberOfNodes()).thenReturn(3);
		
		AntGraph graph = new AntGraph(problem);
		graph.initialize(trailInitialization);
		
		it("should return the correct string", () -> {
			String expected = "[1.0, 1.0, 1.0][1.0, 1.0, 1.0][1.0, 1.0, 1.0]";
			expect(graph.toString().replaceAll("\n","")).toEqual(expected);			
		});		
	});
	
}}
