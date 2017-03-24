package thiagodnf.jacof.problem;

import java.util.List;

public abstract class Problem {

	public boolean better(int[] s1, int[] s2) {
		return better(evaluate(s1), evaluate(s2));
	}

	public boolean better(double s1, double s2) {
		return s1 < s2;
	}

	public abstract double evaluate(int[] solution);

	public abstract int getNumberOfNodes();

	public abstract double getCnn();

	public abstract double getDeltaTau(double tourLength, int i, int j);

	public abstract double getNij(int i, int j);

	public abstract List<Integer> initNodesToVisit(int startingNode);

	public abstract List<Integer> updateNodesToVisit(List<Integer> tour, List<Integer> nodesToVisit);
}
