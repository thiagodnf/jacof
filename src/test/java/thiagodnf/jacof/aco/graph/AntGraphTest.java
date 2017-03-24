package thiagodnf.jacof.aco.graph;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
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
		
		describe("When create an AntGraph", () -> {
			
			it("should throw an exception when a null problem is passed", () -> {
				expect(() -> {
					new AntGraph(null);
				}).toThrow(NullPointerException.class);
			});

			it("should throw an exception when the number of nodes < 2", () -> {

				when(problem.getNumberOfNodes()).thenReturn(1);

				expect(() -> {
					new AntGraph(problem);
				}).toThrow(IllegalArgumentException.class);
			});
		});		

		describe("When initialize the pheronome matrix", () -> {
			
			describe("and null trail initialization is passed", () -> {
				
				beforeEach(() -> {
					when(problem.getNumberOfNodes()).thenReturn(5);
				});

				it("should throw an exception", () -> {
					
					expect(() -> {
						new AntGraph(problem).initialize(null);						
					}).toThrow(NullPointerException.class);
				});
			});
			
			describe("and a valid trail initialization is passed", () -> {

				AntGraph graph = new AntGraph(problem);

				beforeEach(() -> {
					when(problem.getNumberOfNodes()).thenReturn(5);
					graph.initialize(trailInitialization);
				});
				
				it("should return a 5x5 pheromone matrix", () -> {
					expect(graph.getTau().length).toEqual(5);
				});

				it("should return the correct T0 values", () -> {
					double[][] matrix = graph.getTau();

					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 }, matrix[0])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 }, matrix[1])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 }, matrix[2])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 }, matrix[3])).toBeTrue();
					expect(Arrays.equals(new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 }, matrix[4])).toBeTrue();
				});
			});
		});

		describe("When set the pheromone value to 3.5 in arch(1,2)", () -> {

			when(problem.getNumberOfNodes()).thenReturn(5);
			
			AntGraph graph = new AntGraph(problem);

			it("should return 3.5 for arch(1,2)", () -> {

				graph.initialize(trailInitialization);

				graph.setTau(1, 2, 3.5);

				expect(graph.getTau(1, 2)).toEqual(3.5);
			});
		});

		describe("When return toString method", () -> {

			when(problem.getNumberOfNodes()).thenReturn(3);

			AntGraph graph = new AntGraph(problem);
			graph.initialize(trailInitialization);

			it("should return the correct string", () -> {
				String expected = "[1.0, 1.0, 1.0][1.0, 1.0, 1.0][1.0, 1.0, 1.0]";
				expect(graph.toString().replaceAll("\n", "")).toEqual(expected);
			});
		});
	}
}
