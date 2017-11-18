package tsplib;

/**
 * Created by wojci on 18.11.2017.
 */
public class MulticriteriaDistanceFunction extends DistanceFunction {
    @Override
    public double distance(int length, double[] position1, double[] position2) {
        double a = 0.4;
        double b = 0.6;

        GeographicalDistance geographicalDistance = new GeographicalDistance();
        ManhattanDistance manhattanDistance = new ManhattanDistance();

        return a * geographicalDistance.distance(length, position1, position2)
                + b * manhattanDistance.distance(length, position1, position2);
    }
}
