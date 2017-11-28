package tsplib;

import org.apache.log4j.Logger;
import thiagodnf.jacof.aco.ACO;

/**
 * Created by wojci on 18.11.2017.
 */
public class MulticriteriaDistanceFunction extends DistanceFunction {

    static final Logger LOGGER = Logger.getLogger(ACO.class);

    @Override
    public double distance(int length, double[] position1, double[] position2) {
        double a = 0.4;
        double b = 0.6;

        GeographicalDistance geographicalDistance = new GeographicalDistance();
        ManhattanDistance manhattanDistance = new ManhattanDistance();

//        LOGGER.debug(MulticriteriaDistanceFunction.class.getName() + " "
//                + geographicalDistance.distance(length, position1, position2) + " " + manhattanDistance.distance(length, position1, position2));

        return a * geographicalDistance.distance(length, position1, position2)
                + b * manhattanDistance.distance(length, position1, position2);
    }
}
