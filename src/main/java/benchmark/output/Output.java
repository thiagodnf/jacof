package benchmark.output;

import org.moeaframework.core.Algorithm;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.ExecutionStats;
import tsplib.TSPInstance;

/**
 * Created by wojci on 30.10.2017.
 */
public interface Output {

    void use(Algorithm algorithm, TSPInstance instance);
    void use(ExecutionStats executionStats);

}
