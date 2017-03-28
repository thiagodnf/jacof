package thiagodnf.jacof.aco.ant.exploration;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.beforeEach;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.mscharhag.oleaster.runner.OleasterRunner;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;
import thiagodnf.jacof.aco.ant.selection.AbstractAntSelection;
import thiagodnf.jacof.aco.graph.AntGraph;
import thiagodnf.jacof.problem.Problem;

@RunWith(OleasterRunner.class)
public class QSelectionTest {{
	
		Problem p = Mockito.mock(Problem.class);
		ACO aco = Mockito.mock(ACO.class);
		Ant ant = Mockito.mock(Ant.class);
		AntGraph graph = Mockito.mock(AntGraph.class);
		
		describe("When create an instance of this class", () -> {
			
			it("should throw an exception when a null aco is passed", () -> {
				expect(() -> {
					new QSelection(null, 0.9);
				}).toThrow(NullPointerException.class);
			});
			
			it("should throw an exception when a null ant selection is passed", () -> {
				expect(() -> {
					new QSelection(aco, null, 0.9);
				}).toThrow(NullPointerException.class);
			});
			
			it("should throw an exception when a null q0 >= 1 is passed", () -> {
				expect(() -> {
					new QSelection(aco, 1.1);
				}).toThrow(IllegalArgumentException.class);
			});
			
			it("should throw an exception when a null q0 <= 0 is passed", () -> {
				expect(() -> {
					new QSelection(aco, -0.3);
				}).toThrow(IllegalArgumentException.class);
			});
		});		

		describe("When get the next node by using exploitation", () -> {
			
			beforeEach(() -> {			
				when(aco.getProblem()).thenReturn(p);
				when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
				when(aco.getGraph()).thenReturn(graph);
				when(aco.getAlpha()).thenReturn(1.0);
				when(aco.getBeta()).thenReturn(2.0);	
				when(ant.getNodesToVisit()).thenReturn(Arrays.asList(new Integer[] { 2 }));				
			});	
						
			it("should throw an exception when a tau(i,j) is 0.0", () -> {
				
				when(graph.getTau(1, 2)).thenReturn(0.0);	
				when(p.getNij(1, 2)).thenReturn(2.0);
				
				expect(() -> {
					new QSelection(aco, 1.0).getNextNode(ant, 1);
				}).toThrow(IllegalStateException.class);
			});
			
			it("should throw an exception when the sum is 0.0", () -> {
				
				when(graph.getTau(1, 2)).thenReturn(1.0);	
				when(p.getNij(1, 2)).thenReturn(0.0);
				
				expect(() -> {
					new QSelection(aco, 1.0).getNextNode(ant, 1);
				}).toThrow(IllegalStateException.class);
			});
			
			it("should throw an exception when the next node is -1", () -> {
				
				when(ant.getNodesToVisit()).thenReturn(Arrays.asList(new Integer[] { 2, 3}));
				when(graph.getTau(1, 2)).thenReturn(1.0);
				when(graph.getTau(1, 3)).thenReturn(1.0);
				when(p.getNij(1, 2)).thenReturn(Double.MIN_VALUE);
				when(p.getNij(1, 3)).thenReturn(Double.MIN_VALUE);
				
				expect(() -> {
					new QSelection(aco, 1.0).getNextNode(ant, 1);
				}).toThrow(IllegalStateException.class);
			});
			
			it("should return the correct next node", () -> {
				
				when(ant.getNodesToVisit()).thenReturn(Arrays.asList(new Integer[] { 2, 3}));
				when(graph.getTau(1, 2)).thenReturn(1.0);
				when(graph.getTau(1, 3)).thenReturn(1.0);
				when(p.getNij(1, 2)).thenReturn(1.0);
				when(p.getNij(1, 3)).thenReturn(5.0);
				
				expect(new QSelection(aco, 1.0).getNextNode(ant, 1)).toEqual(3);
			});
		});
		
		describe("When get the next node by using exploration", () -> {
			
			beforeEach(() -> {			
				when(aco.getProblem()).thenReturn(p);
				when(aco.getProblem().getNumberOfNodes()).thenReturn(5);
				when(aco.getGraph()).thenReturn(graph);
				when(aco.getAlpha()).thenReturn(1.0);
				when(aco.getBeta()).thenReturn(2.0);	
				when(ant.getNodesToVisit()).thenReturn(Arrays.asList(new Integer[] { 2 }));				
			});	
						
			it("should return the correct next node", () -> {
				
				AbstractAntSelection antSelection = Mockito.mock(AbstractAntSelection.class);
				
				when(antSelection.select(new double[]{0.0, 0.0, 0.2, 0.8, 0.0}, 1.0)).thenReturn(2);				
				when(ant.getNodesToVisit()).thenReturn(Arrays.asList(new Integer[] { 2, 3}));
				when(graph.getTau(1, 2)).thenReturn(1.0);
				when(graph.getTau(1, 3)).thenReturn(1.0);
				when(p.getNij(1, 2)).thenReturn(1.0);
				when(p.getNij(1, 3)).thenReturn(2.0);
				
				expect(new QSelection(aco, antSelection, 0.0).getNextNode(ant, 1)).toEqual(2);
			});
		});
		
		describe("When call the toString method", () -> {

			AbstractAntSelection antSelection = Mockito.mock(AbstractAntSelection.class);
			
			it("should return a valid string", () -> {
				expect(new QSelection(aco, antSelection, 0.0).toString()).toBeNotNull();
			});
		});
	}
}
