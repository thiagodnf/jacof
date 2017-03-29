package thiagodnf.jacof.problem.nrp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Preconditions;

import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.io.InstanceReader;

/**
 * Class to create an instance of the Next Release Problem with no dependencies
 *
 * @author Thiago N. Ferreira
 */
public class NextReleaseProblem extends Problem {

	protected int numberOfRequirements;
	
	protected int numberOfCustomers;
	
	protected double[] customersWeights;
	
	protected double[] requirementsCosts;
	
	protected double[][] importance;
	
	protected double budget;
	
	protected String[] descriptions;
	
	protected double satR;
	
	protected double costR;
	
	protected double[] score;
	
	protected double MI = 1.0;
	
	public NextReleaseProblem(String filename) throws IOException{
		
		InstanceReader reader = new InstanceReader(new File(filename));
		
		this.numberOfRequirements = (int) reader.readDoubleValue();
		this.numberOfCustomers = (int) reader.readDoubleValue();
		this.customersWeights = reader.readDoubleArray();
		this.requirementsCosts = reader.readDoubleArray();
		this.importance = reader.readDoubleMatrix(numberOfCustomers);
		this.budget = (int) reader.readDoubleValue();
		
		this.descriptions = new String[numberOfRequirements];

		for (int i = 0; i < numberOfRequirements; i++) {
			this.descriptions[i] = reader.readLine();
		}
		
		this.score = new double[numberOfRequirements];

		for (int i = 0; i < numberOfRequirements; i++) {
			for (int j = 0; j < numberOfCustomers; j++) {
				score[i] += customersWeights[j] * importance[j][i];
			}
		}
		
		this.satR = Arrays.stream(score).reduce(Double::sum).getAsDouble();
		this.costR = Arrays.stream(requirementsCosts).reduce(Double::sum).getAsDouble();
	}

	@Override
	public double evaluate(int[] solution) {

		Preconditions.checkNotNull(solution, "The solution cannot be null");

		double sum = 0.0;

		for (int i = 0; i < solution.length; i++) {
			sum += this.score[solution[i]];
		}

		return sum;
	}
	
	@Override
	public boolean better(double s1, double best) {
		return s1 > best;
	}
	
	public double effort(int[] solution) {
	
		Preconditions.checkNotNull(solution, "The solution cannot be null");
		
		double sum = 0.0;

		for (int i = 0; i < solution.length; i++) {
			sum += this.requirementsCosts[solution[i]];
		}

		return sum;
	}
	
	public int getNumberOfRequirements() {
		return this.numberOfRequirements;
	}

	public int getNumberOfCustomers() {
		return this.numberOfCustomers;
	}

	public double[] getCustomersWeights() {
		return customersWeights;
	}

	public double[] getRequirementsCosts() {
		return requirementsCosts;
	}

	public double[][] getImportance() {
		return importance;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public String[] getDescriptions() {
		return descriptions;
	}	
	
	public double getSatR(){
		return this.satR;
	}
	
	public double getCostR(){
		return this.costR;
	}
	
	public double[] getScore(){
		return this.score;
	}

	@Override
	public int getNumberOfNodes() {
		return this.getNumberOfRequirements();
	}

	@Override
	public double getCnn() {
		return this.satR;
	}

	@Override
	public double getDeltaTau(double tourLength, int i, int j) {
		return tourLength / satR;
	}

	@Override
	public double getNij(int i, int j) {
		return MI * (score[j] / requirementsCosts[j]);
	}

	@Override
	public List<Integer> initNodesToVisit(int startingNode) {

		List<Integer> nodesToVisit = new ArrayList<>();

		for (int i = 0; i < getNumberOfNodes(); i++) {
			if (i != startingNode && requirementsCosts[i] <= budget) {
				nodesToVisit.add(new Integer(i));
			}
		}

		return nodesToVisit;
	}
	
	@Override
	public List<Integer> updateNodesToVisit(List<Integer> tour, List<Integer> nodesToVisit) {

		List<Integer> nodesToRemove = new ArrayList<Integer>();

		double totalCost = 0.0;

		for (Integer i : tour) {
			totalCost += requirementsCosts[i];
		}

		for (Integer i : nodesToVisit) {
			if (totalCost + requirementsCosts[i] > budget) {
				nodesToRemove.add(i);
			}
		}
		
		for (Integer i : nodesToRemove) {
			nodesToVisit.remove(i);
		}

		return nodesToVisit;
	}

	@Override
	public String toString() {
		return NextReleaseProblem.class.getSimpleName();
	}
}
