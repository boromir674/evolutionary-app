package tools;

import optimizationComponents.ParameterVector;
import optimizationComponents.SPO;
import optimizationComponents.UtilityMeasure;

public class AvgMaxFitnessUtilityCalculator extends UtilityCalculator {

	public AvgMaxFitnessUtilityCalculator(SPO spo) {
		super(spo);
	}

	@Override
	public UtilityMeasure calculateUtility(ParameterVector vec) {
		
		return null;
	}

}
