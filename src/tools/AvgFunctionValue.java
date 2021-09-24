package tools;

import Parameter.UtilityMeasure;

public class AvgFunctionValue extends UtilityMeasure {

	private double std;

	public AvgFunctionValue(double avg, double std) {
		super(avg);
		this.std = std;
	}
	
	public double getStandarDeviation(){
		return std;
	}

}
