package thiagodnf.jacof.aco.ant.selection;

public class RouletteWheel extends AntSelection {

	public int select(double[] probability, double sumProbability) {

		int j = 0;

		double p = probability[j];

		double r = rand.nextDouble(0.0, sumProbability);

		while (p < r) {
			j = j + 1;
			p = p + probability[j];
		}

		return j;
	}
}
