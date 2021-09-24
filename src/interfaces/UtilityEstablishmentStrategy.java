package interfaces;

import optimizationComponents.ParameterVector;
import optimizationComponents.UtilityMeasure;

public interface UtilityEstablishmentStrategy {
	
	public abstract UtilityMeasure calculateUtility(ParameterVector vec);
}
