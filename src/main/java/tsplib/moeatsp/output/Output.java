package tsplib.moeatsp.output;

import org.moeaframework.core.Algorithm;
import tsplib.TSPInstance;

/**
 * Created by wojci on 30.10.2017.
 */
public interface Output {

    void use(Algorithm algorithm, TSPInstance instance);

}
