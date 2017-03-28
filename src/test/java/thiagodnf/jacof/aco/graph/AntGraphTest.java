package thiagodnf.jacof.aco.graph;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.before;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.graph.initialization.AbstractGraphInitialization;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class AntGraphTest {{
	
		Problem problem = Mockito.mock(Problem.class);
		AbstractGraphInitialization trailInitialization = Mockito.mock(AbstractGraphInitialization.class);
		
		describe("When create an instance of this class", () -> {
			
			it("should throw an exception when a null problem is passed", () -> {
				expect(() -> {
					new AntGraph(null);
				}).toThrow(NullPointerException.class);
			});

			it("should throw an exception when the number of nodes <= 0", () -> {

				when(problem.getNumberOfNodes()).thenReturn(0);

				expect(() -> {
					new AntGraph(problem);
				}).toThrow(IllegalArgumentException.class);
			});
		});		

		describe("When initialize the pheronome matrix", () -> {
			
			describe("and null trail initialization is passed", () -> {
				
				before(() -> {
					when(problem.getNumberOfNodes()).thenReturn(5);
				});
				
				it("should throw an exception", () -> {
					expect(() -> {
						new AntGraph(problem).initialize(null);						
					}).toThrow(NullPointerException.class);
				});
			});
			
			describe("and a valid trail initialization is passed", () -> {

				beforeEach(() -> {
					when(trailInitialization.getT0()).thenReturn(1.0);
					when(problem.getNumberOfNodes()).thenReturn(5);
				});
				
				it("should return a matrix with the same size of the number of nodes", () -> {
					AntGraph graph = new AntGraph(problem);
					graph.initialize(trailInitialization);
					expect(graph.getTau().length).toEqual(5);
				});
				
				it("should return the correct T0 values", () -> {
					
					AntGraph graph = new AntGraph(problem);
					graph.initialize(trailInitialization);
					
					double[][] matrix = graph.getTau();
					
					expect(Arrays.equals(new double[] { 0.0, 1.0, 1.0, 1.0, 1.0 }, matrix[0])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 0.0, 1.0, 1.0, 1.0 }, matrix[1])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 0.0, 1.0, 1.0 }, matrix[2])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 0.0, 1.0 }, matrix[3])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 0.0 }, matrix[4])).toBeTrue();
				});
			});
		});

		describe("When set a pheromone value", () -> {

			before(() -> {
				when(problem.getNumberOfNodes()).thenReturn(5);
			});			

			it("should return the correct value", () -> {
				
				AntGraph graph = new AntGraph(problem);

				graph.initialize(trailInitialization);

				graph.setTau(1, 2, 3.5);

				expect(graph.getTau(1, 2)).toEqual(3.5);
			});
		});

		describe("When call the toString method", () -> {

			before(() -> {
				when(problem.getNumberOfNodes()).thenReturn(3);
			});
						
			it("should return the correct string", () -> {
				
				AntGraph graph = new AntGraph(problem);
				graph.initialize(trailInitialization);
				
				String expected = "[0.0, 1.0, 1.0][1.0, 0.0, 1.0][1.0, 1.0, 0.0]";
				expect(graph.toString().replaceAll("\n", "")).toEqual(expected);
			});
		});
	}
}
