package thiagodnf.jacof.util.random;

public interface PseudoRandomGenerator {

	public int nextInt(int lowerBound, int upperBound);

	public double nextDouble(double lowerBound, double upperBound);

	public double nextDouble();

	public void setSeed(long seed);

	public long getSeed();

	public String getName();
}
