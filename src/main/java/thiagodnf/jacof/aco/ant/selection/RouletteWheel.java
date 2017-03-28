package thiagodnf.jacof.aco.ant.selection;

import static com.google.common.base.Preconditions.checkNotNull;

public class RouletteWheel extends AbstractAntSelection {

	public int select(double[] probability, double sumProbability) {

		checkNotNull(probability, "The probability should not be null");
		
		int j = 0;

		double p = probability[j];

		double r = rand.nextDouble(0.0, sumProbability);

		while (p < r) {
			j = j + 1;
			p = p + probability[j];
		}

		return j;
	}
	
	@Override
	public String toString() {
		return RouletteWheel.class.getSimpleName();
	}
}
