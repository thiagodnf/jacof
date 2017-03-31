package thiagodnf.jacof.problem.qap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.io.InstanceReader;

/**
 * Working in progress
 * 
 * @author Thiago N. Ferreira
 * @version 1.0.0
 */
public class QuadraticAssignmentProblem extends Problem{

	public static final double Q = 100.0;
	
	protected int size;
	
	protected int[][] F;
	
	protected int[][] D;
	
	//Defined by Dorigo
	protected int[] potencialD;
	
	//Defined by Dorigo	
	protected int[] potencialF;
	
	public QuadraticAssignmentProblem(String filename) throws IOException{
		
		InstanceReader reader = new InstanceReader(new File(filename));

		this.size = reader.readIntegerValue();
		this.F = reader.readIntegerMatrix(size);
		this.D = reader.readIntegerMatrix(size);

		// Calculate the potencial D e potencial F
		this.potencialD = new int[size];
		this.potencialF = new int[size];

		for (int i = 0; i < size; i++) {
			int pD = 0;
			int pF = 0;
			for (int j = 0; j < size; j++) {
				pD += D[i][j];
				pF += F[i][j];
			}
			potencialD[i] = pD;
			potencialF[i] = pF;
		}
		
		int[] bestSolution = new int[] { 6, 4, 11, 1, 0, 2, 8, 10, 9, 5, 7, 3 };
		System.out.println(evaluate(bestSolution));
	}
	
	@Override
	public boolean better(double s1, double best) {
		return s1 < best;
	}

	@Override
	public double evaluate(int[] solution) {
		
		double value = 0.0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int piI = solution[i];
				int piJ = solution[j];
				value += F[i][j] * D[piI][piJ];
			}
		}

		return value;
	}

	@Override
	public int getNumberOfNodes() {
		return size;
	}

	@Override
	public double getCnn() {
		return 1.0;
	}

	@Override
	public double getDeltaTau(double tourLength, int i, int j) {
		return Q / tourLength;
	}

	@Override
	public double getNij(int i, int j) {
		return 1.0 / (potencialF[i]*potencialD[j]);
	}

	@Override
	public String toString() {
		return QuadraticAssignmentProblem.class.getSimpleName();
	}

	@Override
	public List<Integer> initNodesToVisit(int startingNode) {
		
		List<Integer> nodesToVisit = new ArrayList<>();

		// Add all nodes less the start node
		for (int i = 0; i < getNumberOfNodes(); i++) {
			if (i != startingNode) {
				nodesToVisit.add(new Integer(i));
			}
		}

		return nodesToVisit;
	}

	@Override
	public List<Integer> updateNodesToVisit(List<Integer> tour, List<Integer> nodesToVisit) {
		return nodesToVisit;
	}

}
