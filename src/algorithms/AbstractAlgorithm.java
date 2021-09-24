package algorithms;

import interfaces.Algorithm;
import interfaces.Constraints;

public abstract class AbstractAlgorithm implements Algorithm {
	
	protected Constraints c;
	
	public void setConstraints(Constraints con) {
		this.c = con;
	}
	@Override
	public void randomInitialization() {
		// TODO Auto-generated method stub

	}
	
	public abstract void setNumericalParameters(); 

}
