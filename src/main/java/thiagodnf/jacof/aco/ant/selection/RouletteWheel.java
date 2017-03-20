package thiagodnf.jacof.aco.ant.selection;

import thiagodnf.jacof.random.PseudoRandom;

public class RouletteWheel extends AntSelection {

	public int select(double[] probability, double sumProbability) {

		int j = 0;

		double p = probability[j];

		double r = PseudoRandom.randDouble(0.0, sumProbability);

		while (p < r) {
			j = j + 1;
			p = p + probability[j];
		}

		return j;
	}
}
