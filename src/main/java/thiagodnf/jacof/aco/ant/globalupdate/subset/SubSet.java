package thiagodnf.jacof.aco.ant.globalupdate.subset;

import java.util.List;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.Ant;

public abstract class SubSet {

protected ACO aco;
	
	public SubSet(ACO aco){
		this.aco = aco;
	}
	
	public abstract List<Ant> getSubSet();
}
