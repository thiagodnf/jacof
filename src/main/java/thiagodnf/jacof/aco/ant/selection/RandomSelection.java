package thiagodnf.jacof.aco.ant.selection;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection extends AbstractAntSelection {

	public int select(double[] probability, double sumProbability) {
		
		checkNotNull(probability, "The probability should not be null");

		List<Integer> possibleValues = new ArrayList<>();

		for (int i = 0; i < probability.length; i++) {
			if (probability[i] != 0.0) {
				possibleValues.add(i);
			}
		}

		int index = rand.nextInt(0, possibleValues.size() - 1);

		return possibleValues.get(index);
	}
	
	@Override
	public String toString() {
		return RandomSelection.class.getSimpleName();
	}
}
