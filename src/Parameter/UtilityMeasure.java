package Parameter;

public abstract class UtilityMeasure {
	
	private double mainMetric;
	public UtilityMeasure(double avg) {
		this.mainMetric = avg;
	}

	public double getMainMetric(){
		return this.mainMetric;
	}
	
}
