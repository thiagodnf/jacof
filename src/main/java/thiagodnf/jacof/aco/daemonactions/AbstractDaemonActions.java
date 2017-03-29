package thiagodnf.jacof.aco.daemonactions;

import static com.google.common.base.Preconditions.checkNotNull;

import thiagodnf.jacof.aco.ACO;

public abstract class AbstractDaemonActions {

	protected ACO aco;
	
	public AbstractDaemonActions(ACO aco){
		
		checkNotNull(aco, "The aco cannot be null");
		
		this.aco = aco;
	}
	
	public abstract void doAction();
	
	@Override
	public abstract String toString();
}
