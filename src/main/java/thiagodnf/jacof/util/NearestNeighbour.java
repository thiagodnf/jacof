package thiagodnf.jacof.util;

import java.util.ArrayList;
import java.util.List;

import thiagodnf.jacof.problem.tsp.TravellingSalesmanProblem;
import thiagodnf.jacof.util.random.JMetalRandom;

/**
 * Nearest Neighbour Algorithm
 * 
 * @author Thiago Nascimento
 * @version 1.0
 * @since 2014-07-24
 */
public class NearestNeighbour {
	
	protected JMetalRandom rand = JMetalRandom.getInstance();
	
	public int[] solve(TravellingSalesmanProblem p) {
		
		List<Integer> citiesToVisit = new ArrayList<Integer>();
		List<Integer> solution = new ArrayList<Integer>();

		int currentCity = rand.nextInt(0, p.getNumberOfNodes() - 1);

		for (int i = 0; i < p.getNumberOfNodes(); i++) {
			if (i != currentCity) {
				citiesToVisit.add(new Integer(i));
			}
		}
		
		solution.add(new Integer(currentCity));

		while (!citiesToVisit.isEmpty()) {

			int nextCity = -1;

			double minDistance = Double.MAX_VALUE;

			for (Integer j : citiesToVisit) {
				
				double distance = p.getDistance(currentCity, j);
				
				if (distance < minDistance) {
					minDistance = distance;
					nextCity = j;
				}
			}

			solution.add(new Integer(nextCity));
			citiesToVisit.remove(new Integer(nextCity));
			currentCity = nextCity;
		}
		
		//Add the start city in the solution
		solution.add(solution.get(0));

		return solution.stream().mapToInt(x -> x).toArray();
	}
}