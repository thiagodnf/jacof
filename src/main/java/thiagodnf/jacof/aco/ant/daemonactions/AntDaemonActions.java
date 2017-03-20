package thiagodnf.jacof.aco.ant.daemonactions;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AntDaemonActions {

	protected ACO aco;
	
	public AntDaemonActions(ACO aco){
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}
	
	public abstract void doAction();
}
