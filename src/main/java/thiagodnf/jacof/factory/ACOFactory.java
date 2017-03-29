package thiagodnf.jacof.factory;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.AntColonySystem;
import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.aco.ElitistAntSystem;
import thiagodnf.jacof.aco.MaxMinAntSystem;
import thiagodnf.jacof.aco.RankBasedAntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.Parameters;

public class ACOFactory {

	public static ACO getACO(String acoName, Problem p, Parameters parameters) throws IOException {

		checkNotNull(acoName, "The acoName parameter should not be null");
		checkNotNull(parameters, "The parameters parameter should not be null");
		checkArgument(!acoName.isEmpty(), "The acoName parameter cannot be empty");

		if (acoName.equalsIgnoreCase("AS")) {
			return new AntSystem(p, parameters);
		} else if (acoName.equalsIgnoreCase("ACS")) {
			return new AntColonySystem(p, parameters);
		} else if (acoName.equalsIgnoreCase("EAS")) {
			return new ElitistAntSystem(p, parameters);
		} else if (acoName.equalsIgnoreCase("ASRANK")) {
			return new RankBasedAntSystem(p, parameters);
		} else if (acoName.equalsIgnoreCase("MMAS")) {
			return new MaxMinAntSystem(p, parameters);
		} else {
			throw new IllegalArgumentException("The specified algorithm was not found");
		}
	}
}
