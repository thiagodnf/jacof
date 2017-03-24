package thiagodnf.jacof.aco.ant.selection;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection extends AntSelection {

	public int select(double[] probability, double sumProbability) {

		List<Integer> possibleValues = new ArrayList<>();

		for (int i = 0; i < probability.length; i++) {
			if (probability[i] != 0.0) {
				possibleValues.add(i);
			}
		}

		int index = rand.nextInt(0, possibleValues.size() - 1);

		return possibleValues.get(index);
	}
}
